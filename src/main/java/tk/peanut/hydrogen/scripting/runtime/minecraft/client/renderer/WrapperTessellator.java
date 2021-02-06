package tk.peanut.hydrogen.scripting.runtime.minecraft.client.renderer;

import net.minecraft.client.renderer.Tessellator;

public class WrapperTessellator {
    private Tessellator real;

    public WrapperTessellator(Tessellator var1) {
        this.real = var1;
    }

    public static WrapperTessellator getInstance() {
        return new WrapperTessellator(Tessellator.getInstance());
    }

    public Tessellator unwrap() {
        return this.real;
    }

    public void draw() {
        this.real.draw();
    }

    public WrapperWorldRenderer getWorldRenderer() {
        return new WrapperWorldRenderer(this.real.getWorldRenderer());
    }
}
