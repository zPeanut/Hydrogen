package tk.peanut.hydrogen.scripting.runtime.minecraft.world;

import net.minecraft.world.WorldSettings;

public class WrapperWorldSettings {
    private WorldSettings real;

    public WrapperWorldSettings(WorldSettings var1) {
        this.real = var1;
    }

    public WorldSettings unwrap() {
        return this.real;
    }

    public WrapperWorldSettings enableBonusChest() {
        return new WrapperWorldSettings(this.real.enableBonusChest());
    }

    public WrapperWorldSettings enableCommands() {
        return new WrapperWorldSettings(this.real.enableCommands());
    }

    public boolean isBonusChestEnabled() {
        return this.real.isBonusChestEnabled();
    }

    public long getSeed() {
        return this.real.getSeed();
    }

    public boolean getHardcoreEnabled() {
        return this.real.getHardcoreEnabled();
    }

    public boolean isMapFeaturesEnabled() {
        return this.real.isMapFeaturesEnabled();
    }

    public WrapperWorldType getTerrainType() {
        return new WrapperWorldType(this.real.getTerrainType());
    }

    public boolean areCommandsAllowed() {
        return this.real.areCommandsAllowed();
    }

    public String getWorldName() {
        return this.real.getWorldName();
    }

    public WrapperWorldSettings setWorldName(String var1) {
        return new WrapperWorldSettings(this.real.setWorldName(var1));
    }
}
