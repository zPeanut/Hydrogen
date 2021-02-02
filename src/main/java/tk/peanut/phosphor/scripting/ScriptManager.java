package tk.peanut.phosphor.scripting;

import com.google.common.io.ByteStreams;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import tk.peanut.phosphor.modules.ModuleCategory;
import tk.peanut.phosphor.scripting.runtime.ScriptRuntime;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class ScriptManager {
    private static final String moduleScriptHeader = "var rt = Java.type('" + ScriptRuntime.class.getCanonicalName() + "');\n";
    private ScriptEngine engine;

    public ScriptManager() {
        newScript();
    }

    public void newScript() {
        engine = new ScriptEngineManager().getEngineByName("nashorn");

        if (engine == null) return;

        try {
            engine.eval(moduleScriptHeader);
        } catch (ScriptException e) {
            e.printStackTrace();
        }
    }

    public Object eval(String script) throws ScriptException {
        if (engine == null) return "Failed to initialize engine";

        return engine.eval(script);
    }

    public Script load(File scriptFile) {
        try {
            ZipFile zipFile = new ZipFile(scriptFile);

            ZipEntry manifestEntry = zipFile.getEntry("manifest.json");

            if (manifestEntry == null) throw new RuntimeException("There's no manifest file ('manifest.json')");

            JsonElement manifestElement = new JsonParser().parse(new InputStreamReader(zipFile.getInputStream(manifestEntry)));

            if (!manifestElement.isJsonObject()) throw new RuntimeException("Manifest is not valid");

            JsonObject manifest = manifestElement.getAsJsonObject();

            String scriptName;
            String scriptVersion;

            {
                if (!manifest.has("name")) throw new RuntimeException("There's no 'name' in the manifest");
                JsonElement element = manifest.get("name");

                if (element.isJsonPrimitive()) scriptName = element.getAsString();
                else throw new RuntimeException("'name' is invalid");

                if (!manifest.has("version")) throw new RuntimeException("There's no 'version' in the manifest");
                element = manifest.get("version");

                if (element.isJsonPrimitive()) scriptVersion = element.getAsString();
                else throw new RuntimeException("'version' is invalid");
            }

            Script script = new Script(scriptName, scriptVersion);

            if (manifest.has("modules")) {
                JsonElement element = manifest.get("modules");

                if (!element.isJsonArray()) throw new RuntimeException("'modules' have to be an array");

                for (JsonElement jsonElement : element.getAsJsonArray()) {
                    ScriptModule scriptModule = loadModule(jsonElement, zipFile);
                    script.getModules().add(scriptModule);
                }
            }

            script.register();

            System.out.println("Successfully loaded " + script.getName() + " " + script.getVersion());

            return script;
        } catch (IOException ex) {
            throw new RuntimeException("Failed to open Zip file", ex);
        }
    }

    private ScriptModule loadModule(JsonElement jsonElement, ZipFile file) {
        if (!jsonElement.isJsonObject()) throw new RuntimeException("A module have to be a json object");

        JsonObject obj = jsonElement.getAsJsonObject();

        String name;
        String desc;
        String cat;
        String scriptFile;

        //<editor-fold desc="Metadata">
        {
            if (!obj.has("name")) throw new RuntimeException("There's no 'name' in the module");
            JsonElement element = obj.get("name");

            if (element.isJsonPrimitive()) name = element.getAsString();
            else throw new RuntimeException("'name' is invalid");
        }
        {
            if (!obj.has("description"))
                throw new RuntimeException("There's no 'description' specified in '" + name + "'");
            JsonElement element = obj.get("description");

            if (element.isJsonPrimitive()) desc = element.getAsString();
            else throw new RuntimeException("'description' is invalid");
        }
        {
            if (!obj.has("category")) throw new RuntimeException("There's no 'category' specified in '" + name + "'");
            JsonElement element = obj.get("category");

            if (element.isJsonPrimitive()) cat = element.getAsString();
            else throw new RuntimeException("'category' is invalid");
        }
        {
            if (!obj.has("script")) throw new RuntimeException("There's no 'script' specified in '" + name + "'");
            JsonElement element = obj.get("script");

            if (element.isJsonPrimitive()) scriptFile = element.getAsString();
            else throw new RuntimeException("'script' is invalid");
        }
        //</editor-fold>

        ModuleCategory category;

        try {
            category = ModuleCategory.valueOf(cat.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("There's no category called '" + cat + "'. Allowed Categories: " + Arrays.toString(ModuleCategory.values()));
        }

        ScriptModule module = new ScriptModule(name, desc, category);

        ZipEntry entry = file.getEntry(scriptFile);

        if (entry == null) {
            throw new RuntimeException("There's no '" + scriptFile + "' in the script file");
        }
        String scriptContent;

        try {
            scriptContent = new String(ByteStreams.toByteArray(file.getInputStream(entry)), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        scriptContent = moduleScriptHeader + scriptContent;

        ScriptEngine scriptEngine = new ScriptEngineManager().getEngineByName("nashorn");

        try {
            scriptEngine.eval(scriptContent);
        } catch (ScriptException e) {
            throw new RuntimeException("Failed to compile script", e);
        }

        module.setScriptEngine(scriptEngine);

        return module;
    }
}
