package tk.peanut.phosphor.ui.clickgui;

import java.awt.Color;
import java.util.ArrayList;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import tk.peanut.phosphor.Phosphor;
import tk.peanut.phosphor.ui.clickgui.element.ModuleButton;
import tk.peanut.phosphor.ui.clickgui.util.FontUtil;
import tk.peanut.phosphor.utils.Utils;

public class Panel {
	public String title;
	public double x;
	public double y;
	private double x2;
	private double y2;
	public double width;
	public double height;
	public boolean dragging;
	public boolean extended;
	public boolean visible;
	public ArrayList<ModuleButton> Elements = new ArrayList<>();
	public ClickGUI clickgui;

	/*
	 * Konstrukor
	 */
	public Panel(String ititle, double ix, double iy, double iwidth, double iheight, boolean iextended, ClickGUI parent) {
		this.title = ititle;
		this.x = ix;
		this.y = iy;
		this.width = iwidth;
		this.height = iheight;
		this.extended = iextended;
		this.dragging = false;
		this.visible = true;
		this.clickgui = parent;
		setup();
	}

	/*
	 * Wird in ClickGUI §berschrieben, sodass auch ModuleButtons hinzugef§gt werden k§nnen :3
	 */
	public void setup() {}

	/*
	 * Rendern des Elements.
	 */
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {

		if (!this.visible)
			return;

		int outlineColor = -1;

		if (this.extended && !Elements.isEmpty()) {
			double startY = y + height;
			for (ModuleButton et : Elements) {

				// ebenfalls das main panel und nicht die sidebar

				Utils.drawRect(x - 2, startY, x + width + 2, startY + et.height + 1, outlineColor);

				if(et == Elements.get(Elements.size() - 1)) {

					Utils.drawRect(x - 2, 14 + startY, x + width + 2, startY + et.height + 1, outlineColor);
				}

				// main panel not the sidebar

				Utils.drawRect(x, startY, x + width, startY + et.height + 1, 0x50000000);

				et.x = x + 2;
				et.y = startY;
				et.width = width - 4;
				et.drawScreen(mouseX, mouseY, partialTicks);
				startY += et.height + 1;
			}
			Utils.drawRect(x, startY + 1, x + width, startY + 1, outlineColor);

		}

		if (this.dragging) {
			x = x2 + mouseX;
			y = y2 + mouseY;
		}



		if(!this.extended) {
			Utils.drawRect(x - 2, y + height, x + width + 2, y + height + 2, 0x77000000);
			Utils.drawRect(x - 2, y, x, y + height, 0x77000000);
			Utils.drawRect(x + width, y, x + width + 2, y + height, 0x77000000);
			Utils.drawRect(x - 2, y, x + width + 2, y - 2, 0x77000000);
		} else {
			Utils.drawRect(x - 2, y, x, y + height, outlineColor);
			Utils.drawRect(x + width, y, x + width + 2, y + height, outlineColor);
			Utils.drawRect(x - 2, y, x + width + 2, y - 2, outlineColor);
		}

		// top siderect
		Utils.drawRect(x, y, x + width , y + height, 0x99000000);
		Minecraft.getMinecraft().fontRendererObj.drawStringWithShadow(title, (float)x + 2, (float)y + (float)height / 2 - FontUtil.getFontHeight() / 2, -1);;


	}

	/*
	 * Zum Bewegen und Extenden des Panels
	 * usw.
	 */
	public boolean mouseClicked(int mouseX, int mouseY, int mouseButton) {
		if (!this.visible) {
			return false;
		}
		if (mouseButton == 0 && isHovered(mouseX, mouseY)) {
			x2 = this.x - mouseX;
			y2 = this.y - mouseY;
			dragging = true;
			return true;
		} else if (mouseButton == 1 && isHovered(mouseX, mouseY)) {
			extended = !extended;
			return true;
		} else if (extended) {
			for (ModuleButton et : Elements) {
				if (et.mouseClicked(mouseX, mouseY, mouseButton)) {
					return true;
				}
			}
		}
		return false;
	}

	/*
	 * Damit das Panel auch losgelassen werden kann 
	 */
	public void mouseReleased(int mouseX, int mouseY, int state) {
		if (!this.visible) {
			return;
		}
		if (state == 0) {
			this.dragging = false;
		}
	}

	/*
	 * HoverCheck
	 */
	public boolean isHovered(int mouseX, int mouseY) {
		return mouseX >= x && mouseX <= x + width && mouseY >= y && mouseY <= y + height;
	}
}
