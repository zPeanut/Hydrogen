package me.peanut.hydrogen.ui.clickgui;

import java.io.IOException;
import java.util.ArrayList;

import me.peanut.hydrogen.file.files.ClickGuiFile;
import me.peanut.hydrogen.module.Category;
import me.peanut.hydrogen.ui.clickgui.component.Component;
import me.peanut.hydrogen.utils.ParticleGenerator;
import me.peanut.hydrogen.utils.ReflectionUtil;
import me.peanut.hydrogen.utils.Utils;
import net.minecraft.client.Minecraft;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.shader.ShaderGroup;
import net.minecraft.util.ResourceLocation;
import me.peanut.hydrogen.Hydrogen;

public class ClickGui extends GuiScreen {

	public static ArrayList<me.peanut.hydrogen.ui.clickgui.component.Frame> frames;
	public static int color = 0x99cfdcff;
	private final ParticleGenerator particleGen;
	
	public ClickGui() {
		frames = new ArrayList<me.peanut.hydrogen.ui.clickgui.component.Frame>();
		this.particleGen = new ParticleGenerator(60, Utils.getScaledRes().getScaledWidth(), Utils.getScaledRes().getScaledHeight());
		int frameX = 5;
		for(Category category : Category.values()) {
			me.peanut.hydrogen.ui.clickgui.component.Frame frame = new me.peanut.hydrogen.ui.clickgui.component.Frame(category);
			frame.setX(frameX);
			frames.add(frame);
			frameX += frame.getWidth() + 1;
		}
		}

	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {

		drawRect(0, 0, this.width, this.height, 0x66101010);
		for (me.peanut.hydrogen.ui.clickgui.component.Frame frame : frames) {
			frame.renderFrame(this.fontRendererObj);
			frame.updatePosition(mouseX, mouseY);
			for (me.peanut.hydrogen.ui.clickgui.component.Component comp : frame.getComponents()) {
				comp.updateComponent(mouseX, mouseY);
			}
		}
		if (Hydrogen.getClient().settingsManager.getSettingByName("Blur").isEnabled()) {
			if (OpenGlHelper.shadersSupported) {
				if (mc.entityRenderer.getShaderGroup() != null) {
					mc.entityRenderer.getShaderGroup().deleteShaderGroup();
				}
				mc.entityRenderer.loadShader(new ResourceLocation("shaders/post/blur.json"));
			}
		} else {
			if (this.mc.entityRenderer.getShaderGroup() != null) {
				this.mc.entityRenderer.getShaderGroup().deleteShaderGroup();
				try {
					ReflectionUtil.theShaderGroup.set(Minecraft.getMinecraft().entityRenderer, null);
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}
	}

	@Override
    protected void mouseClicked(final int mouseX, final int mouseY, final int mouseButton) throws IOException {
		for(me.peanut.hydrogen.ui.clickgui.component.Frame frame : frames) {
			if(frame.isWithinHeader(mouseX, mouseY) && mouseButton == 0) {
				frame.setDrag(true);
				frame.dragX = mouseX - frame.getX();
				frame.dragY = mouseY - frame.getY();
			}
			if(frame.isWithinHeader(mouseX, mouseY) && mouseButton == 1) {
				frame.setOpen(!frame.isOpen());
			}
			if(frame.isOpen()) {
				if(!frame.getComponents().isEmpty()) {
					for(me.peanut.hydrogen.ui.clickgui.component.Component component : frame.getComponents()) {
						component.mouseClicked(mouseX, mouseY, mouseButton);
					}
				}
			}
		}
	}
	
	@Override
	protected void keyTyped(char typedChar, int keyCode) {
		for(me.peanut.hydrogen.ui.clickgui.component.Frame frame : frames) {
			if(frame.isOpen() && keyCode != 1) {
				if(!frame.getComponents().isEmpty()) {
					for(me.peanut.hydrogen.ui.clickgui.component.Component component : frame.getComponents()) {
						component.keyTyped(typedChar, keyCode);
					}
				}
			}
		}
		if (keyCode == 1) {
            this.mc.displayGuiScreen(null);
        }
	}

	@Override
	public void onGuiClosed() {
		if(this.mc.entityRenderer.getShaderGroup() != null) {
			this.mc.entityRenderer.getShaderGroup().deleteShaderGroup();
			try {
				ReflectionUtil.theShaderGroup.set(Minecraft.getMinecraft().entityRenderer, null);
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		ClickGuiFile.saveClickGui();

		super.onGuiClosed();
	}

	
	@Override
    protected void mouseReleased(int mouseX, int mouseY, int state) {
		for(me.peanut.hydrogen.ui.clickgui.component.Frame frame : frames) {
			frame.setDrag(false);
		}
		for(me.peanut.hydrogen.ui.clickgui.component.Frame frame : frames) {
			if(frame.isOpen()) {
				if(!frame.getComponents().isEmpty()) {
					for(Component component : frame.getComponents()) {
						component.mouseReleased(mouseX, mouseY, state);
					}
				}
			}
		}
	}

	
	@Override
	public boolean doesGuiPauseGame() {
		return false;
	}
}
