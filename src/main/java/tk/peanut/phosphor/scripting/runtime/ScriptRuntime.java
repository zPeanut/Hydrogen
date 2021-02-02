package tk.peanut.phosphor.scripting.runtime;

import tk.peanut.phosphor.scripting.runtime.minecraft.client.WrapperMinecraft;

public class ScriptRuntime {

    public static WrapperMinecraft getMinecraft() {
        return WrapperMinecraft.getMinecraft();
    }

}
