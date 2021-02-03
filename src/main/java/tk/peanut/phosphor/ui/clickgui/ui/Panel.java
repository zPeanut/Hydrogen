package tk.peanut.phosphor.ui.clickgui.ui;

import java.awt.Color;
import java.util.ArrayList;

import net.minecraft.client.gui.Gui;
import tk.peanut.phosphor.Phosphor;
import tk.peanut.phosphor.ui.clickgui.ui.elements.ModuleButton;
import tk.peanut.phosphor.ui.clickgui.ui.util.ColorUtil;
import tk.peanut.phosphor.ui.clickgui.ui.util.FontUtil;
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
	 * Wird in ClickGUI �berschrieben, sodass auch ModuleButtons hinzugef�gt werden k�nnen :3
	 */
	public void setup() {}

	/*
	 * Rendern des Elements.
	 */
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		if (!this.visible)
			return;

		if (this.dragging) {
			x = x2 + mouseX;
			y = y2 + mouseY;
		}
		
		Color temp = ColorUtil.getClickGUIColor().darker();
		int outlineColor = new Color(temp.getRed(), temp.getGreen(), temp.getBlue(), 170).getRGB();

		Utils.drawRect(x, y, x + width, y + height, 0xff121212);
		if(Phosphor.getInstance().settingsManager.getSettingByName("Design").getValString().equalsIgnoreCase("New")){
			Utils.drawRect(x - 2, y, x, y + height, outlineColor);
			FontUtil.drawStringWithShadow(title, x + 2, y + height / 2 - FontUtil.getFontHeight()/2, 0xffefefef);
		}else if(Phosphor.getInstance().settingsManager.getSettingByName("Design").getValString().equalsIgnoreCase("JellyLike")){
			Utils.drawRect(x + 4,			y + 2, x + 4.3, 		y + height - 2, 0xffaaaaaa);
			Utils.drawRect(x - 4 + width, y + 2, x - 4.3 + width, y + height - 2, 0xffaaaaaa);
			FontUtil.drawTotalCenteredStringWithShadow(title, x + width / 2, y + height / 2, 0xffefefef);
		}
		
		if (this.extended && !Elements.isEmpty()) {
			double startY = y + height;
			int epanelcolor = Phosphor.getInstance().settingsManager.getSettingByName("Design").getValString().equalsIgnoreCase("New") ? 0xff232323 : Phosphor.getInstance().settingsManager.getSettingByName("Design").getValString().equalsIgnoreCase("JellyLike") ? 0xbb151515 : 0;;
			for (ModuleButton et : Elements) {
				if(Phosphor.getInstance().settingsManager.getSettingByName("Design").getValString().equalsIgnoreCase("New")){
					Utils.drawRect(x - 2, startY, x + width, startY + et.height + 1, outlineColor);
				}
				Utils.drawRect(x, startY, x + width, startY + et.height + 1, epanelcolor);
				et.x = x + 2;
				et.y = startY;
				et.width = width - 4;
				et.drawScreen(mouseX, mouseY, partialTicks);
				startY += et.height + 1;
			}
			Utils.drawRect(x, startY + 1, x + width, startY + 1, epanelcolor);
		
		}
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
