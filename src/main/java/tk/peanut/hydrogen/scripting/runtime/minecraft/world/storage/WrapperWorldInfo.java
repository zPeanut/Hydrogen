package tk.peanut.hydrogen.scripting.runtime.minecraft.world.storage;

import net.minecraft.world.storage.WorldInfo;
import tk.peanut.hydrogen.scripting.runtime.minecraft.util.WrapperBlockPos;
import tk.peanut.hydrogen.scripting.runtime.minecraft.world.WrapperWorldSettings;
import tk.peanut.hydrogen.scripting.runtime.minecraft.world.WrapperWorldType;

public class WrapperWorldInfo {
    private WorldInfo real;

    public WrapperWorldInfo(WorldInfo var1) {
        this.real = var1;
    }

    public WorldInfo unwrap() {
        return this.real;
    }

    public void populateFromWorldSettings(WrapperWorldSettings var1) {
        this.real.populateFromWorldSettings(var1.unwrap());
    }

    public long getSeed() {
        return this.real.getSeed();
    }

    public int getSpawnX() {
        return this.real.getSpawnX();
    }

    public void setSpawnX(int var1) {
        this.real.setSpawnX(var1);
    }

    public int getSpawnY() {
        return this.real.getSpawnY();
    }

    public void setSpawnY(int var1) {
        this.real.setSpawnY(var1);
    }

    public int getSpawnZ() {
        return this.real.getSpawnZ();
    }

    public void setSpawnZ(int var1) {
        this.real.setSpawnZ(var1);
    }

    public long getWorldTotalTime() {
        return this.real.getWorldTotalTime();
    }

    public void setWorldTotalTime(long var1) {
        this.real.setWorldTotalTime(var1);
    }

    public long getWorldTime() {
        return this.real.getWorldTime();
    }

    public void setWorldTime(long var1) {
        this.real.setWorldTime(var1);
    }

    public long getSizeOnDisk() {
        return this.real.getSizeOnDisk();
    }

    public void setSpawn(WrapperBlockPos var1) {
        this.real.setSpawn(var1.unwrap());
    }

    public String getWorldName() {
        return this.real.getWorldName();
    }

    public void setWorldName(String var1) {
        this.real.setWorldName(var1);
    }

    public int getSaveVersion() {
        return this.real.getSaveVersion();
    }

    public void setSaveVersion(int var1) {
        this.real.setSaveVersion(var1);
    }

    public long getLastTimePlayed() {
        return this.real.getLastTimePlayed();
    }

    public int getCleanWeatherTime() {
        return this.real.getCleanWeatherTime();
    }

    public void setCleanWeatherTime(int var1) {
        this.real.setCleanWeatherTime(var1);
    }

    public boolean isThundering() {
        return this.real.isThundering();
    }

    public void setThundering(boolean var1) {
        this.real.setThundering(var1);
    }

    public int getThunderTime() {
        return this.real.getThunderTime();
    }

    public void setThunderTime(int var1) {
        this.real.setThunderTime(var1);
    }

    public boolean isRaining() {
        return this.real.isRaining();
    }

    public void setRaining(boolean var1) {
        this.real.setRaining(var1);
    }

    public int getRainTime() {
        return this.real.getRainTime();
    }

    public void setRainTime(int var1) {
        this.real.setRainTime(var1);
    }

    public boolean isMapFeaturesEnabled() {
        return this.real.isMapFeaturesEnabled();
    }

    public void setMapFeaturesEnabled(boolean var1) {
        this.real.setMapFeaturesEnabled(var1);
    }

    public boolean isHardcoreModeEnabled() {
        return this.real.isHardcoreModeEnabled();
    }

    public void setHardcore(boolean var1) {
        this.real.setHardcore(var1);
    }

    public WrapperWorldType getTerrainType() {
        return new WrapperWorldType(this.real.getTerrainType());
    }

    public void setTerrainType(WrapperWorldType var1) {
        this.real.setTerrainType(var1.unwrap());
    }

    public String getGeneratorOptions() {
        return this.real.getGeneratorOptions();
    }

    public boolean areCommandsAllowed() {
        return this.real.areCommandsAllowed();
    }

    public void setAllowCommands(boolean var1) {
        this.real.setAllowCommands(var1);
    }

    public boolean isInitialized() {
        return this.real.isInitialized();
    }

    public void setServerInitialized(boolean var1) {
        this.real.setServerInitialized(var1);
    }

    public double getBorderCenterX() {
        return this.real.getBorderCenterX();
    }

    public double getBorderCenterZ() {
        return this.real.getBorderCenterZ();
    }

    public double getBorderSize() {
        return this.real.getBorderSize();
    }

    public void setBorderSize(double var1) {
        this.real.setBorderSize(var1);
    }

    public long getBorderLerpTime() {
        return this.real.getBorderLerpTime();
    }

    public void setBorderLerpTime(long var1) {
        this.real.setBorderLerpTime(var1);
    }

    public double getBorderLerpTarget() {
        return this.real.getBorderLerpTarget();
    }

    public void setBorderLerpTarget(double var1) {
        this.real.setBorderLerpTarget(var1);
    }

    public void getBorderCenterZ(double var1) {
        this.real.getBorderCenterZ(var1);
    }

    public void getBorderCenterX(double var1) {
        this.real.getBorderCenterX(var1);
    }

    public double getBorderSafeZone() {
        return this.real.getBorderSafeZone();
    }

    public void setBorderSafeZone(double var1) {
        this.real.setBorderSafeZone(var1);
    }

    public double getBorderDamagePerBlock() {
        return this.real.getBorderDamagePerBlock();
    }

    public void setBorderDamagePerBlock(double var1) {
        this.real.setBorderDamagePerBlock(var1);
    }

    public int getBorderWarningDistance() {
        return this.real.getBorderWarningDistance();
    }

    public void setBorderWarningDistance(int var1) {
        this.real.setBorderWarningDistance(var1);
    }

    public int getBorderWarningTime() {
        return this.real.getBorderWarningTime();
    }

    public void setBorderWarningTime(int var1) {
        this.real.setBorderWarningTime(var1);
    }

    public boolean isDifficultyLocked() {
        return this.real.isDifficultyLocked();
    }

    public void setDifficultyLocked(boolean var1) {
        this.real.setDifficultyLocked(var1);
    }
}
