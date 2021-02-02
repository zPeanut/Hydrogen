package tk.peanut.phosphor.scripting.runtime.minecraft.util;

import net.minecraft.util.Timer;

public class WrapperTimer {
    private Timer real;

    public WrapperTimer(Timer var1) {
        this.real = var1;
    }

    public Timer unwrap() {
        return this.real;
    }

    public void updateTimer() {
        this.real.updateTimer();
    }

    public int getElapsedTicks() {
        return this.real.elapsedTicks;
    }

    public void setElapsedTicks(int var1) {
        this.real.elapsedTicks = var1;
    }

    public float getRenderPartialTicks() {
        return this.real.renderPartialTicks;
    }

    public void setRenderPartialTicks(float var1) {
        this.real.renderPartialTicks = var1;
    }

    public float getTimerSpeed() {
        return this.real.timerSpeed;
    }

    public void setTimerSpeed(float var1) {
        this.real.timerSpeed = var1;
    }

    public float getElapsedPartialTicks() {
        return this.real.elapsedPartialTicks;
    }

    public void setElapsedPartialTicks(float var1) {
        this.real.elapsedPartialTicks = var1;
    }
}
