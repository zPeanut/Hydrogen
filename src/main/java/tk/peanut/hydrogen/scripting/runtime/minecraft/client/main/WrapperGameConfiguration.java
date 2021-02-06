package tk.peanut.hydrogen.scripting.runtime.minecraft.client.main;

import net.minecraft.client.main.GameConfiguration;

public class WrapperGameConfiguration {
    private GameConfiguration real;

    public WrapperGameConfiguration(GameConfiguration var1) {
        this.real = var1;
    }

    public GameConfiguration unwrap() {
        return this.real;
    }
}
