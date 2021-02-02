package tk.peanut.phosphor.scripting.runtime.minecraft.client.entity;

import net.minecraft.client.entity.AbstractClientPlayer;
import tk.peanut.phosphor.scripting.runtime.minecraft.entity.player.WrapperEntityPlayer;

public class WrapperAbstractClientPlayer extends WrapperEntityPlayer {
    private AbstractClientPlayer real;

    public WrapperAbstractClientPlayer(AbstractClientPlayer var1) {
        super(var1);
        this.real = var1;
    }

    public AbstractClientPlayer unwrap() {
        return this.real;
    }

    public boolean isSpectator() {
        return this.real.isSpectator();
    }

    public boolean hasPlayerInfo() {
        return this.real.hasPlayerInfo();
    }

    public boolean hasSkin() {
        return this.real.hasSkin();
    }

    public String getSkinType() {
        return this.real.getSkinType();
    }

    public float getFovModifier() {
        return this.real.getFovModifier();
    }
}
