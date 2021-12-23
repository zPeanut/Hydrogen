package me.peanut.hydrogen.ui.clickgui.component;

import java.awt.*;
import java.util.ArrayList;

import me.peanut.hydrogen.module.Category;
import me.peanut.hydrogen.module.Module;
import me.peanut.hydrogen.ui.clickgui.component.components.Button;
import me.peanut.hydrogen.utils.FontUtil;
import me.peanut.hydrogen.utils.Utils;
import net.minecraft.client.gui.FontRenderer;
import me.peanut.hydrogen.Hydrogen;
import me.peanut.hydrogen.ui.clickgui.ClickGuiModule;

public class Frame {

	public ArrayList<me.peanut.hydrogen.ui.clickgui.component.Component> components;
	public Category category;
	public boolean open;
	public int width;
	public int y;

	public int x;
	public int barHeight;
	private boolean isDragging;
	public int dragX;
	public int dragY;

	private int padding;

	public Frame(Category cat) {
		this.components = new ArrayList<me.peanut.hydrogen.ui.clickgui.component.Component>();
		this.category = cat;
		this.width = 88;
		this.x = 5;
		this.y = 5;
		this.barHeight = 13;
		this.dragX = 0;
		this.open = false;
		this.isDragging = false;
		int tY = this.barHeight;


		for (Module mod : Hydrogen.getClient().moduleManager.getModulesInCategory(category)) {
			me.peanut.hydrogen.ui.clickgui.component.components.Button modButton = new Button(mod, this, tY);
			this.components.add(modButton);
			tY += 12;
		}
	}

	public ArrayList<me.peanut.hydrogen.ui.clickgui.component.Component> getComponents() {
		return components;
	}

	public void setX(int newX) {
		this.x = newX;
	}

	public void setY(int newY) {
		this.y = newY;
	}

	public void setDrag(boolean drag) {
		this.isDragging = drag;
	}

	public boolean isOpen() {
		return open;
	}

	public void setOpen(boolean open) {
		this.open = open;
	}

	public void renderFrame(FontRenderer fontRenderer) {
		Module cgui = Hydrogen.getClient().moduleManager.getModule(ClickGuiModule.class);
		int color = new Color((int)Hydrogen.getClient().settingsManager.getSettingByName(cgui, "Red").getValDouble(), (int)Hydrogen.getClient().settingsManager.getSettingByName(cgui, "Green").getValDouble(), (int)Hydrogen.getClient().settingsManager.getSettingByName(cgui, "Blue").getValDouble(), (int)Hydrogen.getClient().settingsManager.getSettingByName(cgui, "Alpha").getValDouble()).getRGB();
		Utils.rect(this.x - 2, this.y - 2, this.x + this.width + 2, this.y + this.barHeight, color);

		if(Hydrogen.getClient().settingsManager.getSettingByName("Font Type").getValString().equalsIgnoreCase("TTF")) {
			FontUtil.drawTotalCenteredStringWithShadow4(this.category.name(), (this.x + this.width / 2), (this.y + 7) - 3, Color.white);
		} else {
			FontUtil.drawTotalCenteredStringWithShadow(this.category.name(), (this.x + this.width / 2), (this.y + 7) - 1, -1);
		}
		if (this.open) {
			if (!this.components.isEmpty()) {
				for (me.peanut.hydrogen.ui.clickgui.component.Component component : components) {
					component.renderComponent();
				}
			}
		}
	}

	public void refresh() {
		int off = this.barHeight;
		for (Component comp : components) {
			comp.setOff(off);
			off += comp.getHeight();
		}
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getWidth() {
		return width;
	}

	public void updatePosition(int mouseX, int mouseY) {
		if (this.isDragging) {
			this.setX(mouseX - dragX);
			this.setY(mouseY - dragY);
		}
	}

	public boolean isWithinHeader(int x, int y) {
		return x >= this.x && x <= this.x + this.width && y >= this.y && y <= this.y + this.barHeight;
	}
}
