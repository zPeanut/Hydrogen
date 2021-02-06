package tk.peanut.hydrogen.scripting.runtime.minecraft.world;

import net.minecraft.world.WorldServer;
import tk.peanut.hydrogen.scripting.runtime.minecraft.block.WrapperBlock;
import tk.peanut.hydrogen.scripting.runtime.minecraft.entity.WrapperEntity;
import tk.peanut.hydrogen.scripting.runtime.minecraft.entity.player.WrapperEntityPlayer;
import tk.peanut.hydrogen.scripting.runtime.minecraft.util.WrapperBlockPos;

import java.util.List;
import java.util.UUID;

public class WrapperWorldServer extends WrapperWorld {
    private WorldServer real;

    public WrapperWorldServer(WorldServer var1) {
        super(var1);
        this.real = var1;
    }

    public WorldServer unwrap() {
        return this.real;
    }

    public WrapperWorld init() {
        return new WrapperWorld(this.real.init());
    }

    public void tick() {
        this.real.tick();
    }

    public void updateAllPlayersSleepingFlag() {
        this.real.updateAllPlayersSleepingFlag();
    }

    public boolean areAllPlayersAsleep() {
        return this.real.areAllPlayersAsleep();
    }

    public void setInitialSpawnLocation() {
        this.real.setInitialSpawnLocation();
    }

    public boolean isBlockTickPending(WrapperBlockPos var1, WrapperBlock var2) {
        return this.real.isBlockTickPending(var1.unwrap(), var2.unwrap());
    }

    public void scheduleUpdate(WrapperBlockPos var1, WrapperBlock var2, int var3) {
        this.real.scheduleUpdate(var1.unwrap(), var2.unwrap(), var3);
    }

    public void updateBlockTick(WrapperBlockPos var1, WrapperBlock var2, int var3, int var4) {
        this.real.updateBlockTick(var1.unwrap(), var2.unwrap(), var3, var4);
    }

    public void scheduleBlockUpdate(WrapperBlockPos var1, WrapperBlock var2, int var3, int var4) {
        this.real.scheduleBlockUpdate(var1.unwrap(), var2.unwrap(), var3, var4);
    }

    public void updateEntities() {
        this.real.updateEntities();
    }

    public void resetUpdateEntityTick() {
        this.real.resetUpdateEntityTick();
    }

    public boolean tickUpdates(boolean var1) {
        return this.real.tickUpdates(var1);
    }

    public void updateEntityWithOptionalForce(WrapperEntity var1, boolean var2) {
        this.real.updateEntityWithOptionalForce(var1.unwrap(), var2);
    }

    public List getTileEntitiesIn(int var1, int var2, int var3, int var4, int var5, int var6) {
        return this.real.getTileEntitiesIn(var1, var2, var3, var4, var5, var6);
    }

    public boolean isBlockModifiable(WrapperEntityPlayer var1, WrapperBlockPos var2) {
        return this.real.isBlockModifiable(var1.unwrap(), var2.unwrap());
    }

    public void initialize(WrapperWorldSettings var1) {
        this.real.initialize(var1.unwrap());
    }

    public WrapperBlockPos getSpawnCoordinate() {
        return new WrapperBlockPos(this.real.getSpawnCoordinate());
    }

    public void saveChunkData() {
        this.real.saveChunkData();
    }

    public boolean addWeatherEffect(WrapperEntity var1) {
        return this.real.addWeatherEffect(var1.unwrap());
    }

    public void setEntityState(WrapperEntity var1, byte var2) {
        this.real.setEntityState(var1.unwrap(), var2);
    }

    public void addBlockEvent(WrapperBlockPos var1, WrapperBlock var2, int var3, int var4) {
        this.real.addBlockEvent(var1.unwrap(), var2.unwrap(), var3, var4);
    }

    public void flush() {
        this.real.flush();
    }

    public WrapperEntity getEntityFromUuid(UUID var1) {
        return new WrapperEntity(this.real.getEntityFromUuid(var1));
    }

    public boolean isCallingFromMinecraftThread() {
        return this.real.isCallingFromMinecraftThread();
    }

    public boolean isDisableLevelSaving() {
        return this.real.disableLevelSaving;
    }

    public void setDisableLevelSaving(boolean var1) {
        this.real.disableLevelSaving = var1;
    }
}
