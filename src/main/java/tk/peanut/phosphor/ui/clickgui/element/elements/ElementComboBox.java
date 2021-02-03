package tk.peanut.phosphor.ui.clickgui.element.elements;

import java.awt.Color;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import tk.peanut.phosphor.Phosphor;
import tk.peanut.phosphor.ui.clickgui.element.Element;
import tk.peanut.phosphor.ui.clickgui.element.ModuleButton;
import tk.peanut.phosphor.ui.clickgui.settings.Setting;
import tk.peanut.phosphor.ui.clickgui.util.ColorUtil;
import tk.peanut.phosphor.ui.clickgui.util.FontUtil;
import tk.peanut.phosphor.utils.Utils;

public class ElementComboBox extends Element {
	/*
	 * Konstrukor
	 */
	public ElementComboBox(ModuleButton iparent, Setting iset) {
		parent = iparent;
		set = iset;
		super.setup();
	}

	/*
	 * Rendern des Elements
	 */
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		Color temp = ColorUtil.getClickGUIColor();
		int color = new Color(temp.getRed(), temp.getGreen(), temp.getBlue(), (int) Phosphor.getInstance().settingsManager.getSettingByName("Alpha").getValDouble()).getRGB();
		
		/*
		 * Die Box und Umrandung rendern
		 */
		Utils.drawRect(x, y, x + width, y + height, 0x80000000);

		FontUtil.drawTotalCenteredString(setstrg, x + width / 2, y + 15/2, 0xffffffff);
		int clr1 = color;
		int clr2 = temp.getRGB();

		Utils.drawRect(x, y + 14, x + width, y + 15, 0x30000000);
		if (comboextended) {
			Utils.drawRect(x, y + 15, x + width, y + height, 0x30000000);
			double ay = y + 15;
			for (String sld : set.getOptions()) {
				String elementtitle = sld.substring(0, 1).toUpperCase() + sld.substring(1, sld.length());
				FontUtil.drawCenteredStringWithShadow("§7" + elementtitle, x + width / 2, ay + 2, 0xffffffff);
				
				/*
				 * Ist das Element ausgew§hlt, wenn ja dann markiere
				 * das Element in der ComboBox
				 */
				if (sld.equalsIgnoreCase(set.getValString())) {
					FontUtil.drawCenteredStringWithShadow("§b" + elementtitle, x + width / 2, ay + 2, 0xffffffff);
				}
				/*
				 * Wie bei mouseClicked 'is hovered', wenn ja dann markiere
				 * das Element in der ComboBox
				 */
				if (mouseX >= x && mouseX <= x + width && mouseY >= ay && mouseY < ay + FontUtil.getFontHeight() + 2) {
					FontUtil.drawCenteredStringWithShadow(sld.equalsIgnoreCase(set.getValString()) ? "§b" + elementtitle : "§8" + elementtitle, x + width / 2, ay + 2, 0xffffffff);
				}
				ay += FontUtil.getFontHeight() + 2;
			}
		}
	}

	/*
	 * 'true' oder 'false' bedeutet hat der Nutzer damit interagiert und
	 * sollen alle anderen Versuche der Interaktion abgebrochen werden?
	 */
	public boolean mouseClicked(int mouseX, int mouseY, int mouseButton) {
		if (mouseButton == 0) {
			if (isButtonHovered(mouseX, mouseY)) {
				comboextended = !comboextended;
				return true;
			}
			
			/*
			 * Also wenn die Box ausgefahren ist, dann wird f§r jede m§gliche Options
			 * §berpr§ft, ob die Maus auf diese zeigt, wenn ja dann global jeder weitere 
			 * call an mouseClicked gestoppt und die Values werden aktualisiert
			 */
			if (!comboextended)return false;
			double ay = y + 15;
			for (String slcd : set.getOptions()) {
				if (mouseX >= x && mouseX <= x + width && mouseY >= ay && mouseY <= ay + FontUtil.getFontHeight() + 2) {
					if(Phosphor.getInstance().settingsManager.getSettingByName("Sound").getValBoolean())
					Minecraft.getMinecraft().thePlayer.playSound("random.wood_click", 1, 20);
					
					if(clickgui != null && clickgui.setmgr != null)
					clickgui.setmgr.getSettingByName(set.getName()).setValString(slcd.toLowerCase());
					return true;
				}
				ay += FontUtil.getFontHeight() + 2;
			}
		}

		return super.mouseClicked(mouseX, mouseY, mouseButton);
	}

	/*
	 * Einfacher HoverCheck, ben§tigt damit die Combobox ge§ffnet und geschlossen werden kann
	 */
	public boolean isButtonHovered(int mouseX, int mouseY) {
		return mouseX >= x && mouseX <= x + width && mouseY >= y && mouseY <= y + 15;
	}
}
