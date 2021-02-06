package tk.peanut.hydrogen.scripting.runtime.minecraft.world;

import net.minecraft.world.MinecraftException;
import net.minecraft.world.World;
import tk.peanut.hydrogen.scripting.runtime.minecraft.block.WrapperBlock;
import tk.peanut.hydrogen.scripting.runtime.minecraft.block.material.WrapperMaterial;
import tk.peanut.hydrogen.scripting.runtime.minecraft.block.state.WrapperIBlockState;
import tk.peanut.hydrogen.scripting.runtime.minecraft.entity.WrapperEntity;
import tk.peanut.hydrogen.scripting.runtime.minecraft.entity.player.WrapperEntityPlayer;
import tk.peanut.hydrogen.scripting.runtime.minecraft.profiler.WrapperProfiler;
import tk.peanut.hydrogen.scripting.runtime.minecraft.util.WrapperAxisAlignedBB;
import tk.peanut.hydrogen.scripting.runtime.minecraft.util.WrapperBlockPos;
import tk.peanut.hydrogen.scripting.runtime.minecraft.util.WrapperEnumFacing;
import tk.peanut.hydrogen.scripting.runtime.minecraft.util.WrapperVec3;
import tk.peanut.hydrogen.scripting.runtime.minecraft.world.storage.WrapperWorldInfo;

import java.util.*;

public class WrapperWorld {
    private World real;

    public WrapperWorld(World var1) {
        this.real = var1;
    }

    public World unwrap() {
        return this.real;
    }

    public WrapperWorld init() {
        return new WrapperWorld(this.real.init());
    }

    public void initialize(WrapperWorldSettings var1) {
        this.real.initialize(var1.unwrap());
    }

    public void setInitialSpawnLocation() {
        this.real.setInitialSpawnLocation();
    }

    public WrapperBlock getGroundAboveSeaLevel(WrapperBlockPos var1) {
        return new WrapperBlock(this.real.getGroundAboveSeaLevel(var1.unwrap()));
    }

    public boolean isAirBlock(WrapperBlockPos var1) {
        return this.real.isAirBlock(var1.unwrap());
    }

    public boolean isBlockLoaded(WrapperBlockPos var1) {
        return this.real.isBlockLoaded(var1.unwrap());
    }

    public boolean isBlockLoaded(WrapperBlockPos var1, boolean var2) {
        return this.real.isBlockLoaded(var1.unwrap(), var2);
    }

    public boolean isAreaLoaded(WrapperBlockPos var1, int var2) {
        return this.real.isAreaLoaded(var1.unwrap(), var2);
    }

    public boolean isAreaLoaded(WrapperBlockPos var1, int var2, boolean var3) {
        return this.real.isAreaLoaded(var1.unwrap(), var2, var3);
    }

    public boolean isAreaLoaded(WrapperBlockPos var1, WrapperBlockPos var2) {
        return this.real.isAreaLoaded(var1.unwrap(), var2.unwrap());
    }

    public boolean isAreaLoaded(WrapperBlockPos var1, WrapperBlockPos var2, boolean var3) {
        return this.real.isAreaLoaded(var1.unwrap(), var2.unwrap(), var3);
    }

    public boolean setBlockState(WrapperBlockPos var1, WrapperIBlockState var2, int var3) {
        return this.real.setBlockState(var1.unwrap(), var2.unwrap(), var3);
    }

    public boolean setBlockToAir(WrapperBlockPos var1) {
        return this.real.setBlockToAir(var1.unwrap());
    }

    public boolean destroyBlock(WrapperBlockPos var1, boolean var2) {
        return this.real.destroyBlock(var1.unwrap(), var2);
    }

    public boolean setBlockState(WrapperBlockPos var1, WrapperIBlockState var2) {
        return this.real.setBlockState(var1.unwrap(), var2.unwrap());
    }

    public void markBlockForUpdate(WrapperBlockPos var1) {
        this.real.markBlockForUpdate(var1.unwrap());
    }

    public void notifyNeighborsRespectDebug(WrapperBlockPos var1, WrapperBlock var2) {
        this.real.notifyNeighborsRespectDebug(var1.unwrap(), var2.unwrap());
    }

    public void markBlocksDirtyVertical(int var1, int var2, int var3, int var4) {
        this.real.markBlocksDirtyVertical(var1, var2, var3, var4);
    }

    public void markBlockRangeForRenderUpdate(WrapperBlockPos var1, WrapperBlockPos var2) {
        this.real.markBlockRangeForRenderUpdate(var1.unwrap(), var2.unwrap());
    }

    public void markBlockRangeForRenderUpdate(int var1, int var2, int var3, int var4, int var5, int var6) {
        this.real.markBlockRangeForRenderUpdate(var1, var2, var3, var4, var5, var6);
    }

    public void notifyNeighborsOfStateChange(WrapperBlockPos var1, WrapperBlock var2) {
        this.real.notifyNeighborsOfStateChange(var1.unwrap(), var2.unwrap());
    }

    public void notifyNeighborsOfStateExcept(WrapperBlockPos var1, WrapperBlock var2, WrapperEnumFacing var3) {
        this.real.notifyNeighborsOfStateExcept(var1.unwrap(), var2.unwrap(), var3.unwrap());
    }

    public void notifyBlockOfStateChange(WrapperBlockPos var1, WrapperBlock var2) {
        this.real.notifyBlockOfStateChange(var1.unwrap(), var2.unwrap());
    }

    public boolean isBlockTickPending(WrapperBlockPos var1, WrapperBlock var2) {
        return this.real.isBlockTickPending(var1.unwrap(), var2.unwrap());
    }

    public boolean canSeeSky(WrapperBlockPos var1) {
        return this.real.canSeeSky(var1.unwrap());
    }

    public boolean canBlockSeeSky(WrapperBlockPos var1) {
        return this.real.canBlockSeeSky(var1.unwrap());
    }

    public int getLight(WrapperBlockPos var1) {
        return this.real.getLight(var1.unwrap());
    }

    public int getLightFromNeighbors(WrapperBlockPos var1) {
        return this.real.getLightFromNeighbors(var1.unwrap());
    }

    public int getLight(WrapperBlockPos var1, boolean var2) {
        return this.real.getLight(var1.unwrap(), var2);
    }

    public WrapperBlockPos getHeight(WrapperBlockPos var1) {
        return new WrapperBlockPos(this.real.getHeight(var1.unwrap()));
    }

    public int getChunksLowestHorizon(int var1, int var2) {
        return this.real.getChunksLowestHorizon(var1, var2);
    }

    public void notifyLightSet(WrapperBlockPos var1) {
        this.real.notifyLightSet(var1.unwrap());
    }

    public int getCombinedLight(WrapperBlockPos var1, int var2) {
        return this.real.getCombinedLight(var1.unwrap(), var2);
    }

    public float getLightBrightness(WrapperBlockPos var1) {
        return this.real.getLightBrightness(var1.unwrap());
    }

    public WrapperIBlockState getBlockState(WrapperBlockPos var1) {
        return new WrapperIBlockState(this.real.getBlockState(var1.unwrap()));
    }

    public boolean isDaytime() {
        return this.real.isDaytime();
    }

    public void playSoundAtEntity(WrapperEntity var1, String var2, float var3, float var4) {
        this.real.playSoundAtEntity(var1.unwrap(), var2, var3, var4);
    }

    public void playSoundToNearExcept(WrapperEntityPlayer var1, String var2, float var3, float var4) {
        this.real.playSoundToNearExcept(var1.unwrap(), var2, var3, var4);
    }


    public void playRecord(WrapperBlockPos var1, String var2) {
        this.real.playRecord(var1.unwrap(), var2);
    }

    public boolean addWeatherEffect(WrapperEntity var1) {
        return this.real.addWeatherEffect(var1.unwrap());
    }

    public boolean spawnEntityInWorld(WrapperEntity var1) {
        return this.real.spawnEntityInWorld(var1.unwrap());
    }

    public void removeEntity(WrapperEntity var1) {
        this.real.removeEntity(var1.unwrap());
    }

    public void removePlayerEntityDangerously(WrapperEntity var1) {
        this.real.removePlayerEntityDangerously(var1.unwrap());
    }

    public List getCollidingBoundingBoxes(WrapperEntity var1, WrapperAxisAlignedBB var2) {
        return this.real.getCollidingBoundingBoxes(var1.unwrap(), var2.unwrap());
    }

    public int calculateSkylightSubtracted(float var1) {
        return this.real.calculateSkylightSubtracted(var1);
    }

    public float getSunBrightness(float var1) {
        return this.real.getSunBrightness(var1);
    }

    public WrapperVec3 getSkyColor(WrapperEntity var1, float var2) {
        return new WrapperVec3(this.real.getSkyColor(var1.unwrap(), var2));
    }

    public float getCelestialAngle(float var1) {
        return this.real.getCelestialAngle(var1);
    }

    public int getMoonPhase() {
        return this.real.getMoonPhase();
    }

    public float getCurrentMoonPhaseFactor() {
        return this.real.getCurrentMoonPhaseFactor();
    }

    public float getCelestialAngleRadians(float var1) {
        return this.real.getCelestialAngleRadians(var1);
    }

    public WrapperVec3 getCloudColour(float var1) {
        return new WrapperVec3(this.real.getCloudColour(var1));
    }

    public WrapperVec3 getFogColor(float var1) {
        return new WrapperVec3(this.real.getFogColor(var1));
    }

    public WrapperBlockPos getPrecipitationHeight(WrapperBlockPos var1) {
        return new WrapperBlockPos(this.real.getPrecipitationHeight(var1.unwrap()));
    }

    public WrapperBlockPos getTopSolidOrLiquidBlock(WrapperBlockPos var1) {
        return new WrapperBlockPos(this.real.getTopSolidOrLiquidBlock(var1.unwrap()));
    }

    public float getStarBrightness(float var1) {
        return this.real.getStarBrightness(var1);
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

    public void addTileEntities(Collection var1) {
        this.real.addTileEntities(var1);
    }

    public void updateEntity(WrapperEntity var1) {
        this.real.updateEntity(var1.unwrap());
    }

    public void updateEntityWithOptionalForce(WrapperEntity var1, boolean var2) {
        this.real.updateEntityWithOptionalForce(var1.unwrap(), var2);
    }

    public boolean checkNoEntityCollision(WrapperAxisAlignedBB var1) {
        return this.real.checkNoEntityCollision(var1.unwrap());
    }

    public boolean checkNoEntityCollision(WrapperAxisAlignedBB var1, WrapperEntity var2) {
        return this.real.checkNoEntityCollision(var1.unwrap(), var2.unwrap());
    }

    public boolean checkBlockCollision(WrapperAxisAlignedBB var1) {
        return this.real.checkBlockCollision(var1.unwrap());
    }

    public boolean isAnyLiquid(WrapperAxisAlignedBB var1) {
        return this.real.isAnyLiquid(var1.unwrap());
    }

    public boolean isFlammableWithin(WrapperAxisAlignedBB var1) {
        return this.real.isFlammableWithin(var1.unwrap());
    }

    public boolean handleMaterialAcceleration(WrapperAxisAlignedBB var1, WrapperMaterial var2, WrapperEntity var3) {
        return this.real.handleMaterialAcceleration(var1.unwrap(), var2.unwrap(), var3.unwrap());
    }

    public boolean isMaterialInBB(WrapperAxisAlignedBB var1, WrapperMaterial var2) {
        return this.real.isMaterialInBB(var1.unwrap(), var2.unwrap());
    }

    public boolean isAABBInMaterial(WrapperAxisAlignedBB var1, WrapperMaterial var2) {
        return this.real.isAABBInMaterial(var1.unwrap(), var2.unwrap());
    }

    public float getBlockDensity(WrapperVec3 var1, WrapperAxisAlignedBB var2) {
        return this.real.getBlockDensity(var1.unwrap(), var2.unwrap());
    }

    public boolean extinguishFire(WrapperEntityPlayer var1, WrapperBlockPos var2, WrapperEnumFacing var3) {
        return this.real.extinguishFire(var1.unwrap(), var2.unwrap(), var3.unwrap());
    }

    public String getDebugLoadedEntities() {
        return this.real.getDebugLoadedEntities();
    }

    public String getProviderName() {
        return this.real.getProviderName();
    }

    public void removeTileEntity(WrapperBlockPos var1) {
        this.real.removeTileEntity(var1.unwrap());
    }

    public boolean isBlockFullCube(WrapperBlockPos var1) {
        return this.real.isBlockFullCube(var1.unwrap());
    }

    public boolean isBlockNormalCube(WrapperBlockPos var1, boolean var2) {
        return this.real.isBlockNormalCube(var1.unwrap(), var2);
    }

    public void calculateInitialSkylight() {
        this.real.calculateInitialSkylight();
    }

    public void setAllowedSpawnTypes(boolean var1, boolean var2) {
        this.real.setAllowedSpawnTypes(var1, var2);
    }

    public void tick() {
        this.real.tick();
    }

    public void forceBlockUpdateTick(WrapperBlock var1, WrapperBlockPos var2, Random var3) {
        this.real.forceBlockUpdateTick(var1.unwrap(), var2.unwrap(), var3);
    }

    public boolean canBlockFreezeWater(WrapperBlockPos var1) {
        return this.real.canBlockFreezeWater(var1.unwrap());
    }

    public boolean canBlockFreezeNoWater(WrapperBlockPos var1) {
        return this.real.canBlockFreezeNoWater(var1.unwrap());
    }

    public boolean canBlockFreeze(WrapperBlockPos var1, boolean var2) {
        return this.real.canBlockFreeze(var1.unwrap(), var2);
    }

    public boolean canSnowAt(WrapperBlockPos var1, boolean var2) {
        return this.real.canSnowAt(var1.unwrap(), var2);
    }

    public boolean checkLight(WrapperBlockPos var1) {
        return this.real.checkLight(var1.unwrap());
    }

    public boolean tickUpdates(boolean var1) {
        return this.real.tickUpdates(var1);
    }

    public List getEntitiesWithinAABBExcludingEntity(WrapperEntity var1, WrapperAxisAlignedBB var2) {
        return this.real.getEntitiesWithinAABBExcludingEntity(var1.unwrap(), var2.unwrap());
    }

    public List getEntitiesWithinAABB(Class var1, WrapperAxisAlignedBB var2) {
        return this.real.getEntitiesWithinAABB(var1, var2.unwrap());
    }

    public WrapperEntity findNearestEntityWithinAABB(Class var1, WrapperAxisAlignedBB var2, WrapperEntity var3) {
        return new WrapperEntity(this.real.findNearestEntityWithinAABB(var1, var2.unwrap(), var3.unwrap()));
    }

    public WrapperEntity getEntityByID(int var1) {
        return new WrapperEntity(this.real.getEntityByID(var1));
    }

    public List getLoadedEntityList() {
        return this.real.getLoadedEntityList();
    }

    public int countEntities(Class var1) {
        return this.real.countEntities(var1);
    }

    public void loadEntities(Collection var1) {
        this.real.loadEntities(var1);
    }

    public void unloadEntities(Collection var1) {
        this.real.unloadEntities(var1);
    }


    public int getStrongPower(WrapperBlockPos var1, WrapperEnumFacing var2) {
        return this.real.getStrongPower(var1.unwrap(), var2.unwrap());
    }

    public WrapperWorldType getWorldType() {
        return new WrapperWorldType(this.real.getWorldType());
    }

    public int getStrongPower(WrapperBlockPos var1) {
        return this.real.getStrongPower(var1.unwrap());
    }

    public boolean isSidePowered(WrapperBlockPos var1, WrapperEnumFacing var2) {
        return this.real.isSidePowered(var1.unwrap(), var2.unwrap());
    }

    public int getRedstonePower(WrapperBlockPos var1, WrapperEnumFacing var2) {
        return this.real.getRedstonePower(var1.unwrap(), var2.unwrap());
    }

    public boolean isBlockPowered(WrapperBlockPos var1) {
        return this.real.isBlockPowered(var1.unwrap());
    }

    public int isBlockIndirectlyGettingPowered(WrapperBlockPos var1) {
        return this.real.isBlockIndirectlyGettingPowered(var1.unwrap());
    }

    public WrapperEntityPlayer getClosestPlayerToEntity(WrapperEntity var1, double var2) {
        return new WrapperEntityPlayer(this.real.getClosestPlayerToEntity(var1.unwrap(), var2));
    }

    public WrapperEntityPlayer getPlayerEntityByName(String var1) {
        return new WrapperEntityPlayer(this.real.getPlayerEntityByName(var1));
    }

    public WrapperEntityPlayer getPlayerEntityByUUID(UUID var1) {
        return new WrapperEntityPlayer(this.real.getPlayerEntityByUUID(var1));
    }

    public void sendQuittingDisconnectingPacket() {
        this.real.sendQuittingDisconnectingPacket();
    }

    public void checkSessionLock() throws MinecraftException {
        this.real.checkSessionLock();
    }

    public long getSeed() {
        return this.real.getSeed();
    }

    public long getTotalWorldTime() {
        return this.real.getTotalWorldTime();
    }

    public void setTotalWorldTime(long var1) {
        this.real.setTotalWorldTime(var1);
    }

    public long getWorldTime() {
        return this.real.getWorldTime();
    }

    public void setWorldTime(long var1) {
        this.real.setWorldTime(var1);
    }

    public WrapperBlockPos getSpawnPoint() {
        return new WrapperBlockPos(this.real.getSpawnPoint());
    }

    public void setSpawnPoint(WrapperBlockPos var1) {
        this.real.setSpawnPoint(var1.unwrap());
    }

    public void joinEntityInSurroundings(WrapperEntity var1) {
        this.real.joinEntityInSurroundings(var1.unwrap());
    }

    public boolean isBlockModifiable(WrapperEntityPlayer var1, WrapperBlockPos var2) {
        return this.real.isBlockModifiable(var1.unwrap(), var2.unwrap());
    }

    public void setEntityState(WrapperEntity var1, byte var2) {
        this.real.setEntityState(var1.unwrap(), var2);
    }

    public void addBlockEvent(WrapperBlockPos var1, WrapperBlock var2, int var3, int var4) {
        this.real.addBlockEvent(var1.unwrap(), var2.unwrap(), var3, var4);
    }

    public WrapperWorldInfo getWorldInfo() {
        return new WrapperWorldInfo(this.real.getWorldInfo());
    }

    public void updateAllPlayersSleepingFlag() {
        this.real.updateAllPlayersSleepingFlag();
    }

    public float getThunderStrength(float var1) {
        return this.real.getThunderStrength(var1);
    }

    public void setThunderStrength(float var1) {
        this.real.setThunderStrength(var1);
    }

    public float getRainStrength(float var1) {
        return this.real.getRainStrength(var1);
    }

    public void setRainStrength(float var1) {
        this.real.setRainStrength(var1);
    }

    public boolean isThundering() {
        return this.real.isThundering();
    }

    public boolean isRaining() {
        return this.real.isRaining();
    }


    public boolean isBlockinHighHumidity(WrapperBlockPos var1) {
        return this.real.isBlockinHighHumidity(var1.unwrap());
    }

    public int getUniqueDataId(String var1) {
        return this.real.getUniqueDataId(var1);
    }

    public void playBroadcastSound(int var1, WrapperBlockPos var2, int var3) {
        this.real.playBroadcastSound(var1, var2.unwrap(), var3);
    }

    public void playAuxSFX(int var1, WrapperBlockPos var2, int var3) {
        this.real.playAuxSFX(var1, var2.unwrap(), var3);
    }

    public void playAuxSFXAtEntity(WrapperEntityPlayer var1, int var2, WrapperBlockPos var3, int var4) {
        this.real.playAuxSFXAtEntity(var1.unwrap(), var2, var3.unwrap(), var4);
    }

    public int getHeight() {
        return this.real.getHeight();
    }

    public int getActualHeight() {
        return this.real.getActualHeight();
    }

    public Random setRandomSeed(int var1, int var2, int var3) {
        return this.real.setRandomSeed(var1, var2, var3);
    }

    public WrapperBlockPos getStrongholdPos(String var1, WrapperBlockPos var2) {
        return new WrapperBlockPos(this.real.getStrongholdPos(var1, var2.unwrap()));
    }

    public boolean extendedLevelsInChunkCache() {
        return this.real.extendedLevelsInChunkCache();
    }

    public double getHorizon() {
        return this.real.getHorizon();
    }

    public void sendBlockBreakProgress(int var1, WrapperBlockPos var2, int var3) {
        this.real.sendBlockBreakProgress(var1, var2.unwrap(), var3);
    }

    public Calendar getCurrentDate() {
        return this.real.getCurrentDate();
    }

    public void updateComparatorOutputLevel(WrapperBlockPos var1, WrapperBlock var2) {
        this.real.updateComparatorOutputLevel(var1.unwrap(), var2.unwrap());
    }

    public int getSkylightSubtracted() {
        return this.real.getSkylightSubtracted();
    }

    public void setSkylightSubtracted(int var1) {
        this.real.setSkylightSubtracted(var1);
    }

    public int getLastLightningBolt() {
        return this.real.getLastLightningBolt();
    }

    public void setLastLightningBolt(int var1) {
        this.real.setLastLightningBolt(var1);
    }

    public boolean isFindingSpawnPoint() {
        return this.real.isFindingSpawnPoint();
    }

    public boolean isSpawnChunk(int var1, int var2) {
        return this.real.isSpawnChunk(var1, var2);
    }

    public List getLoadedTileEntityList() {
        return this.real.loadedTileEntityList;
    }

    public List getTickableTileEntities() {
        return this.real.tickableTileEntities;
    }

    public List getPlayerEntities() {
        return this.real.playerEntities;
    }

    public List getWeatherEffects() {
        return this.real.weatherEffects;
    }

    public Random getRand() {
        return this.real.rand;
    }

    public WrapperProfiler getTheProfiler() {
        return new WrapperProfiler(this.real.theProfiler);
    }

    public boolean IsRemote() {
        return this.real.isRemote;
    }
}
