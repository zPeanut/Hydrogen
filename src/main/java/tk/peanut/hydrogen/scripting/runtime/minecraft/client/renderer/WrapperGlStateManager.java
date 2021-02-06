package tk.peanut.hydrogen.scripting.runtime.minecraft.client.renderer;

import net.minecraft.client.renderer.GlStateManager;

import java.nio.FloatBuffer;

public class WrapperGlStateManager {
    private GlStateManager real;

    public WrapperGlStateManager(GlStateManager var1) {
        this.real = var1;
    }

    public static void pushAttrib() {
        GlStateManager.pushAttrib();
    }

    public static void popAttrib() {
        GlStateManager.popAttrib();
    }

    public static void disableAlpha() {
        GlStateManager.disableAlpha();
    }

    public static void enableAlpha() {
        GlStateManager.enableAlpha();
    }

    public static void alphaFunc(int var0, float var1) {
        GlStateManager.alphaFunc(var0, var1);
    }

    public static void enableLighting() {
        GlStateManager.enableLighting();
    }

    public static void disableLighting() {
        GlStateManager.disableLighting();
    }

    public static void enableLight(int var0) {
        GlStateManager.enableLight(var0);
    }

    public static void disableLight(int var0) {
        GlStateManager.disableLight(var0);
    }

    public static void enableColorMaterial() {
        GlStateManager.enableColorMaterial();
    }

    public static void disableColorMaterial() {
        GlStateManager.disableColorMaterial();
    }

    public static void colorMaterial(int var0, int var1) {
        GlStateManager.colorMaterial(var0, var1);
    }

    public static void disableDepth() {
        GlStateManager.disableDepth();
    }

    public static void enableDepth() {
        GlStateManager.enableDepth();
    }

    public static void depthFunc(int var0) {
        GlStateManager.depthFunc(var0);
    }

    public static void depthMask(boolean var0) {
        GlStateManager.depthMask(var0);
    }

    public static void disableBlend() {
        GlStateManager.disableBlend();
    }

    public static void enableBlend() {
        GlStateManager.enableBlend();
    }

    public static void blendFunc(int var0, int var1) {
        GlStateManager.blendFunc(var0, var1);
    }

    public static void tryBlendFuncSeparate(int var0, int var1, int var2, int var3) {
        GlStateManager.tryBlendFuncSeparate(var0, var1, var2, var3);
    }

    public static void enableFog() {
        GlStateManager.enableFog();
    }

    public static void disableFog() {
        GlStateManager.disableFog();
    }

    public static void setFog(int var0) {
        GlStateManager.setFog(var0);
    }

    public static void setFogDensity(float var0) {
        GlStateManager.setFogDensity(var0);
    }

    public static void setFogStart(float var0) {
        GlStateManager.setFogStart(var0);
    }

    public static void setFogEnd(float var0) {
        GlStateManager.setFogEnd(var0);
    }

    public static void enableCull() {
        GlStateManager.enableCull();
    }

    public static void disableCull() {
        GlStateManager.disableCull();
    }

    public static void cullFace(int var0) {
        GlStateManager.cullFace(var0);
    }

    public static void enablePolygonOffset() {
        GlStateManager.enablePolygonOffset();
    }

    public static void disablePolygonOffset() {
        GlStateManager.disablePolygonOffset();
    }

    public static void doPolygonOffset(float var0, float var1) {
        GlStateManager.doPolygonOffset(var0, var1);
    }

    public static void enableColorLogic() {
        GlStateManager.enableColorLogic();
    }

    public static void disableColorLogic() {
        GlStateManager.disableColorLogic();
    }

    public static void colorLogicOp(int var0) {
        GlStateManager.colorLogicOp(var0);
    }

    public static void setActiveTexture(int var0) {
        GlStateManager.setActiveTexture(var0);
    }

    public static void enableTexture2D() {
        GlStateManager.enableTexture2D();
    }

    public static void disableTexture2D() {
        GlStateManager.disableTexture2D();
    }

    public static int generateTexture() {
        return GlStateManager.generateTexture();
    }

    public static void deleteTexture(int var0) {
        GlStateManager.deleteTexture(var0);
    }

    public static void bindTexture(int var0) {
        GlStateManager.bindTexture(var0);
    }

    public static void enableNormalize() {
        GlStateManager.enableNormalize();
    }

    public static void disableNormalize() {
        GlStateManager.disableNormalize();
    }

    public static void shadeModel(int var0) {
        GlStateManager.shadeModel(var0);
    }

    public static void enableRescaleNormal() {
        GlStateManager.enableRescaleNormal();
    }

    public static void disableRescaleNormal() {
        GlStateManager.disableRescaleNormal();
    }

    public static void viewport(int var0, int var1, int var2, int var3) {
        GlStateManager.viewport(var0, var1, var2, var3);
    }

    public static void colorMask(boolean var0, boolean var1, boolean var2, boolean var3) {
        GlStateManager.colorMask(var0, var1, var2, var3);
    }

    public static void clearDepth(double var0) {
        GlStateManager.clearDepth(var0);
    }

    public static void clearColor(float var0, float var1, float var2, float var3) {
        GlStateManager.clearColor(var0, var1, var2, var3);
    }

    public static void clear(int var0) {
        GlStateManager.clear(var0);
    }

    public static void matrixMode(int var0) {
        GlStateManager.matrixMode(var0);
    }

    public static void loadIdentity() {
        GlStateManager.loadIdentity();
    }

    public static void pushMatrix() {
        GlStateManager.pushMatrix();
    }

    public static void popMatrix() {
        GlStateManager.popMatrix();
    }

    public static void getFloat(int var0, FloatBuffer var1) {
        GlStateManager.getFloat(var0, var1);
    }

    public static void ortho(double param0, double param2, double param4, double param6, double param8, double param10) {
        GlStateManager.ortho(param0, param2, param4, param6, param8, param10);
    }

    public static void rotate(float var0, float var1, float var2, float var3) {
        GlStateManager.rotate(var0, var1, var2, var3);
    }

    public static void scale(float var0, float var1, float var2) {
        GlStateManager.scale(var0, var1, var2);
    }

    public static void scale(double param0, double param2, double param4) {
        GlStateManager.scale(param0, param2, param4);
    }

    public static void translate(float var0, float var1, float var2) {
        GlStateManager.translate(var0, var1, var2);
    }

    public static void translate(double param0, double param2, double param4) {
        GlStateManager.translate(param0, param2, param4);
    }

    public static void multMatrix(FloatBuffer var0) {
        GlStateManager.multMatrix(var0);
    }

    public static void color(float var0, float var1, float var2, float var3) {
        GlStateManager.color(var0, var1, var2, var3);
    }

    public static void color(float var0, float var1, float var2) {
        GlStateManager.color(var0, var1, var2);
    }

    public static void resetColor() {
        GlStateManager.resetColor();
    }

    public static void callList(int var0) {
        GlStateManager.callList(var0);
    }

    public GlStateManager unwrap() {
        return this.real;
    }
}
