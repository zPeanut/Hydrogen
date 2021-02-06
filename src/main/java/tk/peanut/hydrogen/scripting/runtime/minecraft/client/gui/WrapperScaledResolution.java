package tk.peanut.hydrogen.scripting.runtime.minecraft.client.gui;

import net.minecraft.client.gui.ScaledResolution;

public class WrapperScaledResolution {
    private ScaledResolution real;

    public WrapperScaledResolution(ScaledResolution var1) {
        this.real = var1;
    }

    public ScaledResolution unwrap() {
        return this.real;
    }

    public int getScaledWidth() {
        return this.real.getScaledWidth();
    }

    public int getScaledHeight() {
        return this.real.getScaledHeight();
    }

    public double getScaledWidth_double() {
        return this.real.getScaledWidth_double();
    }

    public double getScaledHeight_double() {
        return this.real.getScaledHeight_double();
    }

    public int getScaleFactor() {
        return this.real.getScaleFactor();
    }
}
