package tk.peanut.hydrogen.scripting.runtime.minecraft.client.entity;

import net.minecraft.client.entity.EntityOtherPlayerMP;
import tk.peanut.hydrogen.scripting.runtime.minecraft.util.WrapperBlockPos;
import tk.peanut.hydrogen.scripting.runtime.minecraft.util.WrapperIChatComponent;

public class WrapperEntityOtherPlayerMP extends WrapperAbstractClientPlayer {
    private EntityOtherPlayerMP real;

    public WrapperEntityOtherPlayerMP(EntityOtherPlayerMP var1) {
        super(var1);
        this.real = var1;
    }

    public EntityOtherPlayerMP unwrap() {
        return this.real;
    }

    public void setPositionAndRotation2(double param1, double param3, double param5, float param7, float param8, int param9, boolean param10) {
        real.setPositionAndRotation2(param1, param3, param5, param7, param8, param9, param10);
    }

    public void onUpdate() {
        this.real.onUpdate();
    }

    public void onLivingUpdate() {
        this.real.onLivingUpdate();
    }

    public void addChatMessage(WrapperIChatComponent var1) {
        this.real.addChatMessage(var1.unwrap());
    }

    public boolean canCommandSenderUseCommand(int var1, String var2) {
        return this.real.canCommandSenderUseCommand(var1, var2);
    }

    public WrapperBlockPos getPosition() {
        return new WrapperBlockPos(this.real.getPosition());
    }
}
