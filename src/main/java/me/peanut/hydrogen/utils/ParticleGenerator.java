package me.peanut.hydrogen.utils;

import net.minecraft.util.MathHelper;
import org.lwjgl.opengl.GL11;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by peanut on 09/02/2021
 */
public class ParticleGenerator {

    private final int count;
    private final int width;
    private final int height;
    private final ArrayList<Particle> particles = new ArrayList();
    private final Random random = new Random();
    public ParticleGenerator(int count, int width, int height) {
        this.count = count;
        this.width = width;
        this.height = height;
        for (int i = 0; i < count; i++) {
            this.particles.add(new Particle(this.random.nextInt(width), this.random.nextInt(height)));
        }
    }

    public void drawParticles(int mouseX, int mouseY, boolean drawLine) {
        for (Particle p : this.particles) {
            if (p.reset) {
                p.resetPosSize();
                p.reset = false;
            }
            p.draw(mouseX, mouseY, drawLine);
        }
    }

    public class Particle {
        private int x;
        private int y;
        private int k;
        private float size;
        private boolean reset;
        private final Random random = new Random();

        public Particle(int x, int y) {
            this.x = x;
            this.y = y;
            this.size = genRandom(1.0F, 3.0F);
        }

        public void draw(int mouseX, int mouseY, boolean drawLine) {
            if (this.size <= 0.0F) {
                this.reset = true;
            }

            this.size -= 0.05F;
            this.k += 1;
            int xx = (int)(MathHelper.cos(0.1F * (this.x + this.k)) * 10.0F);
            int yy = (int)(MathHelper.cos(0.1F * (this.y + this.k)) * 10.0F);
            RenderUtil.drawBorderedCircle(this.x + xx, this.y + yy, this.size, 0, 553648127);


            if(drawLine) {
                float distance = (float) Utils.distance(this.x + xx, this.y + yy, mouseX, mouseY);

                if (distance < 50) {
                    GL11.glEnable(GL11.GL_LINE_SMOOTH);
                    GL11.glDisable(GL11.GL_DEPTH_TEST);
                    GL11.glColor4f(255F, 255F, 255F, 255F);
                    GL11.glDisable(GL11.GL_TEXTURE_2D);
                    GL11.glDepthMask(false);
                    GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
                    GL11.glEnable(GL11.GL_BLEND);
                    GL11.glLineWidth(0.1F);
                    GL11.glBegin(GL11.GL_LINES);

                    GL11.glVertex2f(this.x + xx, this.y + yy);
                    GL11.glVertex2f(mouseX, mouseY);
                    GL11.glEnd();
                }
            }
        }

        public void resetPosSize() {
            this.x = this.random.nextInt(ParticleGenerator.this.width);
            this.y = this.random.nextInt(ParticleGenerator.this.height);
            this.size = genRandom(1.0F, 3.0F);
        }

        public float genRandom(float min, float max) {
            return (float)(min + Math.random() * (max - min + 1.0F));
        }
    }
}
