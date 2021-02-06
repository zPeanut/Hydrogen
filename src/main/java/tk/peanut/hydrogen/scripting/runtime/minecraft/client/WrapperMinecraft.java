package tk.peanut.hydrogen.scripting.runtime.minecraft.client;

import net.minecraft.client.Minecraft;
import tk.peanut.hydrogen.scripting.runtime.minecraft.client.entity.WrapperEntityPlayerSP;
import tk.peanut.hydrogen.scripting.runtime.minecraft.client.gui.WrapperFontRenderer;
import tk.peanut.hydrogen.scripting.runtime.minecraft.client.gui.WrapperGuiIngame;
import tk.peanut.hydrogen.scripting.runtime.minecraft.client.gui.WrapperGuiScreen;
import tk.peanut.hydrogen.scripting.runtime.minecraft.client.multiplayer.WrapperPlayerControllerMP;
import tk.peanut.hydrogen.scripting.runtime.minecraft.client.multiplayer.WrapperServerData;
import tk.peanut.hydrogen.scripting.runtime.minecraft.client.multiplayer.WrapperWorldClient;
import tk.peanut.hydrogen.scripting.runtime.minecraft.client.network.WrapperNetHandlerPlayClient;
import tk.peanut.hydrogen.scripting.runtime.minecraft.entity.WrapperEntity;
import tk.peanut.hydrogen.scripting.runtime.minecraft.profiler.WrapperProfiler;
import tk.peanut.hydrogen.scripting.runtime.minecraft.util.WrapperSession;
import tk.peanut.hydrogen.scripting.runtime.minecraft.world.WrapperWorldSettings;

import java.io.File;
import java.io.IOException;
import java.net.Proxy;
import java.util.Map;

public class WrapperMinecraft {
    private Minecraft real;

    public WrapperMinecraft(Minecraft var1) {
        this.real = var1;
    }

    public static boolean isGuiEnabled() {
        return Minecraft.isGuiEnabled();
    }

    public static boolean isFancyGraphicsEnabled() {
        return Minecraft.isFancyGraphicsEnabled();
    }

    public static boolean isAmbientOcclusionEnabled() {
        return Minecraft.isAmbientOcclusionEnabled();
    }

    public static WrapperMinecraft getMinecraft() {
        return new WrapperMinecraft(Minecraft.getMinecraft());
    }

    public static int getGLMaximumTextureSize() {
        return Minecraft.getGLMaximumTextureSize();
    }

    public static void stopIntegratedServer() {
        Minecraft.stopIntegratedServer();
    }

    public static long getSystemTime() {
        return Minecraft.getSystemTime();
    }

    public static int getDebugFPS() {
        return Minecraft.getDebugFPS();
    }

    public static Map getSessionInfo() {
        return Minecraft.getSessionInfo();
    }

    public Minecraft unwrap() {
        return this.real;
    }

    public void run() {
        this.real.run();
    }

    public String getVersion() {
        return this.real.getVersion();
    }

    public boolean isUnicode() {
        return this.real.isUnicode();
    }

    public void refreshResources() {
        this.real.refreshResources();
    }

    public void displayGuiScreen(WrapperGuiScreen var1) {
        this.real.displayGuiScreen(var1.unwrap());
    }

    public void shutdownMinecraftApplet() {
        this.real.shutdownMinecraftApplet();
    }

    public void updateDisplay() {
        this.real.updateDisplay();
    }

    public int getLimitFramerate() {
        return this.real.getLimitFramerate();
    }

    public boolean isFramerateLimitBelowMax() {
        return this.real.isFramerateLimitBelowMax();
    }

    public void freeMemory() {
        this.real.freeMemory();
    }

    public void shutdown() {
        this.real.shutdown();
    }

    public void setIngameFocus() {
        this.real.setIngameFocus();
    }

    public void setIngameNotInFocus() {
        this.real.setIngameNotInFocus();
    }

    public void displayInGameMenu() {
        this.real.displayInGameMenu();
    }

    public void toggleFullscreen() {
        this.real.toggleFullscreen();
    }

    public void runTick() throws IOException {
        this.real.runTick();
    }

    public void launchIntegratedServer(String var1, String var2, WrapperWorldSettings var3) {
        this.real.launchIntegratedServer(var1, var2, var3.unwrap());
    }

    public void loadWorld(WrapperWorldClient var1) {
        this.real.loadWorld(var1.unwrap());
    }

    public void loadWorld(WrapperWorldClient var1, String var2) {
        this.real.loadWorld(var1.unwrap(), var2);
    }

    public void setDimensionAndSpawnPlayer(int var1) {
        this.real.setDimensionAndSpawnPlayer(var1);
    }

    public final boolean isDemo() {
        return this.real.isDemo();
    }

    public WrapperNetHandlerPlayClient getNetHandler() {
        return new WrapperNetHandlerPlayClient(this.real.getNetHandler());
    }

    public boolean isSnooperEnabled() {
        return this.real.isSnooperEnabled();
    }

    public void setServerData(WrapperServerData var1) {
        this.real.setServerData(var1.unwrap());
    }

    public WrapperServerData getCurrentServerData() {
        return new WrapperServerData(this.real.getCurrentServerData());
    }

    public boolean isIntegratedServerRunning() {
        return this.real.isIntegratedServerRunning();
    }

    public boolean isSingleplayer() {
        return this.real.isSingleplayer();
    }

    public boolean isFullScreen() {
        return this.real.isFullScreen();
    }

    public WrapperSession getSession() {
        return new WrapperSession(this.real.getSession());
    }

    public Proxy getProxy() {
        return this.real.getProxy();
    }

    public boolean isJava64bit() {
        return this.real.isJava64bit();
    }

    public boolean isGamePaused() {
        return this.real.isGamePaused();
    }

    public void dispatchKeypresses() {
        this.real.dispatchKeypresses();
    }

    public WrapperEntity getRenderViewEntity() {
        return new WrapperEntity(this.real.getRenderViewEntity());
    }

    public void setRenderViewEntity(WrapperEntity var1) {
        this.real.setRenderViewEntity(var1.unwrap());
    }

    public boolean isCallingFromMinecraftThread() {
        return this.real.isCallingFromMinecraftThread();
    }

    public boolean IsRunningOnMac() {
        return Minecraft.isRunningOnMac;
    }

    public byte[] getMemoryReserve() {
        return Minecraft.memoryReserve;
    }

    public void setMemoryReserve(byte[] var1) {
        Minecraft.memoryReserve = var1;
    }

    public WrapperPlayerControllerMP getPlayerController() {
        return new WrapperPlayerControllerMP(this.real.playerController);
    }

    public void setPlayerController(WrapperPlayerControllerMP var1) {
        this.real.playerController = var1.unwrap();
    }

    public int getDisplayWidth() {
        return this.real.displayWidth;
    }

    public void setDisplayWidth(int var1) {
        this.real.displayWidth = var1;
    }

    public int getDisplayHeight() {
        return this.real.displayHeight;
    }

    public void setDisplayHeight(int var1) {
        this.real.displayHeight = var1;
    }

    public WrapperWorldClient getTheWorld() {
        return new WrapperWorldClient(this.real.theWorld);
    }

    public void setTheWorld(WrapperWorldClient var1) {
        this.real.theWorld = var1.unwrap();
    }

    public WrapperEntityPlayerSP getThePlayer() {
        return new WrapperEntityPlayerSP(this.real.thePlayer);
    }

    public void setThePlayer(WrapperEntityPlayerSP var1) {
        this.real.thePlayer = var1.unwrap();
    }

    public WrapperEntity getPointedEntity() {
        return new WrapperEntity(this.real.pointedEntity);
    }

    public void setPointedEntity(WrapperEntity var1) {
        this.real.pointedEntity = var1.unwrap();
    }

    public WrapperFontRenderer getFontRendererObj() {
        return new WrapperFontRenderer(this.real.fontRendererObj);
    }

    public void setFontRendererObj(WrapperFontRenderer var1) {
        this.real.fontRendererObj = var1.unwrap();
    }

    public WrapperFontRenderer getStandardGalacticFontRenderer() {
        return new WrapperFontRenderer(this.real.standardGalacticFontRenderer);
    }

    public void setStandardGalacticFontRenderer(WrapperFontRenderer var1) {
        this.real.standardGalacticFontRenderer = var1.unwrap();
    }

    public WrapperGuiScreen getCurrentScreen() {
        return new WrapperGuiScreen(this.real.currentScreen);
    }

    public void setCurrentScreen(WrapperGuiScreen var1) {
        this.real.currentScreen = var1.unwrap();
    }

    public WrapperGuiIngame getIngameGUI() {
        return new WrapperGuiIngame(this.real.ingameGUI);
    }

    public void setIngameGUI(WrapperGuiIngame var1) {
        this.real.ingameGUI = var1.unwrap();
    }

    public boolean isSkipRenderWorld() {
        return this.real.skipRenderWorld;
    }

    public void setSkipRenderWorld(boolean var1) {
        this.real.skipRenderWorld = var1;
    }

    public File getMcDataDir() {
        return this.real.mcDataDir;
    }

    public boolean isInGameHasFocus() {
        return this.real.inGameHasFocus;
    }

    public void setInGameHasFocus(boolean var1) {
        this.real.inGameHasFocus = var1;
    }

    public WrapperProfiler getMcProfiler() {
        return new WrapperProfiler(this.real.mcProfiler);
    }

    public String getDebug() {
        return this.real.debug;
    }

    public void setDebug(String var1) {
        this.real.debug = var1;
    }

    public boolean isField_175613_B() {
        return this.real.field_175613_B;
    }

    public void setField_175613_B(boolean var1) {
        this.real.field_175613_B = var1;
    }

    public boolean isField_175614_C() {
        return this.real.field_175614_C;
    }

    public void setField_175614_C(boolean var1) {
        this.real.field_175614_C = var1;
    }

    public boolean isField_175611_D() {
        return this.real.field_175611_D;
    }

    public void setField_175611_D(boolean var1) {
        this.real.field_175611_D = var1;
    }

    public boolean isRenderChunksMany() {
        return this.real.renderChunksMany;
    }

    public void setRenderChunksMany(boolean var1) {
        this.real.renderChunksMany = var1;
    }
}
