package tk.peanut.phosphor.ui.clickgui.element.elements;

import java.awt.Color;

import net.minecraft.client.gui.Gui;
import net.minecraft.util.MathHelper;
import tk.peanut.phosphor.Phosphor;
import tk.peanut.phosphor.ui.clickgui.element.Element;
import tk.peanut.phosphor.ui.clickgui.element.ModuleButton;
import tk.peanut.phosphor.ui.clickgui.settings.Setting;
import tk.peanut.phosphor.ui.clickgui.util.ColorUtil;
import tk.peanut.phosphor.ui.clickgui.util.FontUtil;
import tk.peanut.phosphor.utils.Utils;

public class ElementSlider extends Element {
	public boolean dragging;

	/*
	 * Konstrukor
	 */
	public ElementSlider(ModuleButton iparent, Setting iset) {
		parent = iparent;
		set = iset;
		dragging = false;
		super.setup();
	}

	/*
	 * Rendern des Elements 
	 */
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		String displayval = "" + Math.round(set.getValDouble() * 100D)/ 100D;
		boolean hoveredORdragged = isSliderHovered(mouseX, mouseY) || dragging;
		
		Color temp = ColorUtil.getClickGUIColor();
		int color = new Color((int) Phosphor.getInstance().settingsManager.getSettingByName("Red").getValDouble(), (int) Phosphor.getInstance().settingsManager.getSettingByName("Green").getValDouble(), (int) Phosphor.getInstance().settingsManager.getSettingByName("Blue").getValDouble(), hoveredORdragged ? 250 : 200).getRGB();
		int color2 = new Color((int) Phosphor.getInstance().settingsManager.getSettingByName("Red").getValDouble(), (int) Phosphor.getInstance().settingsManager.getSettingByName("Green").getValDouble(), (int) Phosphor.getInstance().settingsManager.getSettingByName("Blue").getValDouble(), hoveredORdragged ? 255 : 230).getRGB();
		
		//selected = iset.getValDouble() / iset.getMax();
		double percentBar = (set.getValDouble() - set.getMin())/(set.getMax() - set.getMin());
		
		/*
		 * Die Box und Umrandung rendern
		 */
		Utils.drawRect(x, y, x + width, y + height, 0x50000000);

		/*
		 * Den Text rendern
		 */
		FontUtil.drawStringWithShadow(setstrg, x + 2, y + 2, 0xffffffff);




		if(setstrg.equalsIgnoreCase("speed")) {

			// AUTOCLICKER

			FontUtil.drawStringWithShadow("§7" + displayval + " CPS", x + width - FontUtil.getStringWidth(displayval  + " CPS"), y + 2, 0xffffffff);

		} else if(setstrg.equalsIgnoreCase("delay")) {

			// CHESTSTEALER


			FontUtil.drawStringWithShadow("§7" + displayval + "ms", x + width - FontUtil.getStringWidth(displayval + "ms"), y + 2, 0xffffffff);

		} else {

			FontUtil.drawStringWithShadow("§7" + displayval, x + width - FontUtil.getStringWidth(displayval), y + 2, 0xffffffff);
		}



		/*
		 * Den Slider rendern
		 */
		Utils.drawRect(x, y + 12, x + width, y + 13.5, 0x99111111);
		Utils.drawRect(x, y + 12, x + (percentBar * width), y + 13.5, color);
		
		if(percentBar > 0 && percentBar < 1)
		Utils.drawRect(x + (percentBar * width)-1, y + 11, x + Math.min((percentBar * width), width) + 2, y + 14.5, color2);
		

		/*
		 * Neue Value berechnen, wenn dragging
		 */
		if (this.dragging) {
			double diff = set.getMax() - set.getMin();
			double val = set.getMin() + (MathHelper.clamp_double((mouseX - x) / width, 0, 1)) * diff;
			set.setValDouble(val); //Die Value im Setting updaten
		}

	}

	/*
	 * 'true' oder 'false' bedeutet hat der Nutzer damit interagiert und
	 * sollen alle anderen Versuche der Interaktion abgebrochen werden?
	 */
	public boolean mouseClicked(int mouseX, int mouseY, int mouseButton) {
		if (mouseButton == 0 && isSliderHovered(mouseX, mouseY)) {
			this.dragging = true;
			//TODO: SettingsSliderFile.saveState();
			return true;
		}
		
		return super.mouseClicked(mouseX, mouseY, mouseButton);
	}

	/*
	 * Wenn die Maus losgelassen wird soll aufgeh§rt werden die Slidervalue zu ver§ndern
	 */
	public void mouseReleased(int mouseX, int mouseY, int state) {
		this.dragging = false;
	}

	/*
	 * Einfacher HoverCheck, ben§tigt damit dragging auf true gesetzt werden kann
	 */
	public boolean isSliderHovered(int mouseX, int mouseY) {
		return mouseX >= x && mouseX <= x + width && mouseY >= y + 11 && mouseY <= y + 14;
	}
}