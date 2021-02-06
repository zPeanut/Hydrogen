package tk.peanut.hydrogen.scripting.runtime.minecraft.world;

import net.minecraft.world.WorldType;

public class WrapperWorldType {
    private WorldType real;

    public WrapperWorldType(WorldType var1) {
        this.real = var1;
    }

    public static WrapperWorldType parseWorldType(String var0) {
        return new WrapperWorldType(WorldType.parseWorldType(var0));
    }

    public WorldType unwrap() {
        return this.real;
    }

    public String getWorldTypeName() {
        return this.real.getWorldTypeName();
    }

    public String getTranslateName() {
        return this.real.getTranslateName();
    }

    public int getGeneratorVersion() {
        return this.real.getGeneratorVersion();
    }

    public WrapperWorldType getWorldTypeForGeneratorVersion(int var1) {
        return new WrapperWorldType(this.real.getWorldTypeForGeneratorVersion(var1));
    }

    public boolean getCanBeCreated() {
        return this.real.getCanBeCreated();
    }

    public boolean isVersioned() {
        return this.real.isVersioned();
    }

    public int getWorldTypeID() {
        return this.real.getWorldTypeID();
    }

    public boolean showWorldInfoNotice() {
        return this.real.showWorldInfoNotice();
    }

    public WorldType[] getWorldTypes() {
        return WorldType.worldTypes;
    }

    public WrapperWorldType getDEFAULT() {
        return new WrapperWorldType(WorldType.DEFAULT);
    }

    public WrapperWorldType getFLAT() {
        return new WrapperWorldType(WorldType.FLAT);
    }

    public WrapperWorldType getLARGE_BIOMES() {
        return new WrapperWorldType(WorldType.LARGE_BIOMES);
    }

    public WrapperWorldType getAMPLIFIED() {
        return new WrapperWorldType(WorldType.AMPLIFIED);
    }

    public WrapperWorldType getCUSTOMIZED() {
        return new WrapperWorldType(WorldType.CUSTOMIZED);
    }

    public WrapperWorldType getDEBUG_WORLD() {
        return new WrapperWorldType(WorldType.DEBUG_WORLD);
    }

    public WrapperWorldType getDEFAULT_1_1() {
        return new WrapperWorldType(WorldType.DEFAULT_1_1);
    }
}
