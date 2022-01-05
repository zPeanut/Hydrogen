package me.peanut.hydrogen.ui.clickgui.component;

import java.awt.*;
import java.util.ArrayList;
import java.util.Objects;

import me.peanut.hydrogen.module.Category;
import me.peanut.hydrogen.module.Module;
import me.peanut.hydrogen.ui.clickgui.component.components.Button;
import me.peanut.hydrogen.utils.FontUtil;
import me.peanut.hydrogen.utils.Utils;
import net.minecraft.client.gui.FontRenderer;
import me.peanut.hydrogen.Hydrogen;
import me.peanut.hydrogen.module.modules.ui.ClickGUI;

public class Frame {

	public final ArrayList<me.peanut.hydrogen.ui.clickgui.component.Component> components;
	public final Category category;
	public boolean open;
	public final int width;
	public int y;
	public int x;
	public final int barHeight;
	private boolean isDragging;
	public int dragX;
	public int dragY;
	private int dragged;

	private int scroll;
	private boolean scrollbar = true;

	public Frame(Category cat) {
		this.components = new ArrayList<>();
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
		Module cgui = Hydrogen.getClient().moduleManager.getModule(ClickGUI.class);
		int maxShownModules = 8;
		boolean scrollbar = this.components.size() >= maxShownModules;
		if (this.scrollbar != scrollbar) {
			this.scrollbar = scrollbar;
		}
		int count = 0;
		for (Component component : components) {
			if (++count > this.scroll && count < this.scroll + (maxShownModules + 1) && this.scroll < this.components.size()) {
				if (this.open) {
					if (!this.components.isEmpty()) {
						component.renderComponent();
					}
				}
			}
		}
		int color = new Color((int)Hydrogen.getClient().settingsManager.getSettingByName(cgui, "Red").getValue(), (int)Hydrogen.getClient().settingsManager.getSettingByName(cgui, "Green").getValue(), (int)Hydrogen.getClient().settingsManager.getSettingByName(cgui, "Blue").getValue(), (int)Hydrogen.getClient().settingsManager.getSettingByName(cgui, "Alpha").getValue()).getRGB();
		Utils.rect(this.x - 2, this.y - 2, this.x + this.width + 2, this.y + this.barHeight, color);

		Utils.rect((float)(this.x - 2), (float)(this.y + 21), (float) this.x, (float)(this.y + 16), Integer.MAX_VALUE);
		Utils.rect((this.x - 2), this.y + 30 / (this.components.size() - 12) * dragged - 10.0f, this.x, (float) (this.y + 40 / this.components.size()) - 12 * dragged, Integer.MIN_VALUE);
		if(Hydrogen.getClient().settingsManager.getSettingByName("Font Type").getMode().equalsIgnoreCase("TTF")) {
			FontUtil.drawTotalCenteredStringWithShadow4(this.category.name(), (this.x + this.width / 2), (this.y + 7) - 3, Color.white);
		} else {
			FontUtil.drawTotalCenteredStringWithShadow(this.category.name(), (this.x + this.width / 2), (this.y + 7) - 1, -1);
		}

	}

	public void refresh() {
		int off = this.barHeight;
		for (Component comp : components) {
			comp.setOff(off);
			off += comp.getHeight();
		}
	}

	public boolean handleScroll(final int mouseX, final int mouseY, final int wheel) {
		final int maxElements = 8;
		if (mouseX >= this.getX() && mouseX <= this.getX() + 100 && mouseY >= this.getY() && mouseY <= this.getY() + 19 + this.barHeight) {
			if (wheel < 0 && this.scroll < this.components.size() - maxElements) {
				++this.scroll;
				if (this.scroll < 0) {
					this.scroll = 0;
				}
			}
			else if (wheel > 0) {
				--this.scroll;
				if (this.scroll < 0) {
					this.scroll = 0;
				}
			}
			if (wheel < 0) {
				if (this.dragged < this.components.size() - maxElements) {
					++this.dragged;
				}
			}
			else if (wheel > 0 && this.dragged >= 1) {
				--this.dragged;
			}
			return true;
		}
		return false;
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
