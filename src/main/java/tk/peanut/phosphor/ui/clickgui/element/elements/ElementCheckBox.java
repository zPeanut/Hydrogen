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

public class ElementCheckBox extends Element {
	/*
	 * Konstrukor
	 */
	public ElementCheckBox(ModuleButton iparent, Setting iset) {
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
		Utils.drawRect(x, y , x + width, y + height, 0x50000000);

		
		
		
		/*
		 * Titel und Checkbox rendern.
		 */
		Minecraft.getMinecraft().fontRendererObj.drawStringWithShadow(set.getValBoolean() ? "§b" + setstrg :  setstrg, (float)(x + 17), (float)(y + FontUtil.getFontHeight() / 2 - 0.5), 0xffffffff);
		Utils.drawRect(x + 3, y + 2, x + 14, y + 13, set.getValBoolean() ? 0x99000000 : 0x44111111);
		if (isCheckHovered(mouseX, mouseY))
			Utils.drawRect(x + 3, y + 2, x + 14, y + 13, 0x55111111);
	}

	/*
	 * 'true' oder 'false' bedeutet hat der Nutzer damit interagiert und
	 * sollen alle anderen Versuche der Interaktion abgebrochen werden?
	 */
	public boolean mouseClicked(int mouseX, int mouseY, int mouseButton) {
		if (mouseButton == 0 && isCheckHovered(mouseX, mouseY)) {
			set.setValBoolean(!set.getValBoolean());
			//TODO: SettingsButtonFile.saveState();
			return true;
		}
		
		return super.mouseClicked(mouseX, mouseY, mouseButton);
	}

	/*
	 * Einfacher HoverCheck, ben§tigt damit die Value ge§ndert werden kann
	 */
	public boolean isCheckHovered(int mouseX, int mouseY) {
		return mouseX >= x + 1 && mouseX <= x + 12 && mouseY >= y + 2 && mouseY <= y + 13;
	}
}
