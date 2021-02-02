package tk.peanut.phosphor.scripting.runtime.minecraft.client.renderer;

import net.minecraft.client.renderer.ViewFrustum;
import net.minecraft.client.renderer.chunk.RenderChunk;

public class WrapperViewFrustum {
    private ViewFrustum real;

    public WrapperViewFrustum(ViewFrustum var1) {
        this.real = var1;
    }

    public ViewFrustum unwrap() {
        return this.real;
    }

    public void deleteGlResources() {
        this.real.deleteGlResources();
    }

    public void markBlocksForUpdate(int var1, int var2, int var3, int var4, int var5, int var6) {
        this.real.markBlocksForUpdate(var1, var2, var3, var4, var5, var6);
    }

    public RenderChunk[] getRenderChunks() {
        return this.real.renderChunks;
    }

    public void setRenderChunks(RenderChunk[] var1) {
        this.real.renderChunks = var1;
    }
}
