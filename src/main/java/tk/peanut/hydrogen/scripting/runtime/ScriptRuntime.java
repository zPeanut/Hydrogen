package tk.peanut.hydrogen.scripting.runtime;

import tk.peanut.hydrogen.scripting.runtime.minecraft.client.WrapperMinecraft;

public class ScriptRuntime {

    public static WrapperMinecraft getMinecraft() {
        return WrapperMinecraft.getMinecraft();
    }

}
