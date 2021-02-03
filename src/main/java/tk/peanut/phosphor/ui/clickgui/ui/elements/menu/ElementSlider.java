package tk.peanut.phosphor.ui.clickgui.ui.elements.menu;

import java.awt.Color;

import net.minecraft.client.gui.Gui;
import net.minecraft.util.MathHelper;
import tk.peanut.phosphor.ui.clickgui.settings.Setting;
import tk.peanut.phosphor.ui.clickgui.ui.elements.Element;
import tk.peanut.phosphor.ui.clickgui.ui.elements.ModuleButton;
import tk.peanut.phosphor.ui.clickgui.ui.util.ColorUtil;
import tk.peanut.phosphor.ui.clickgui.ui.util.FontUtil;
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
		int color = new Color(temp.getRed(), temp.getGreen(), temp.getBlue(), hoveredORdragged ? 250 : 200).getRGB();
		int color2 = new Color(temp.getRed(), temp.getGreen(), temp.getBlue(), hoveredORdragged ? 255 : 230).getRGB();
		
		//selected = iset.getValDouble() / iset.getMax();
		double percentBar = (set.getValDouble() - set.getMin())/(set.getMax() - set.getMin());
		
		/*
		 * Die Box und Umrandung rendern
		 */
		Utils.drawRect(x, y, x + width, y + height, 0xff1a1a1a);

		/*
		 * Den Text rendern
		 */
		FontUtil.drawString(setstrg, x + 1, y + 2, 0xffffffff);
		FontUtil.drawString(displayval, x + width - FontUtil.getStringWidth(displayval), y + 2, 0xffffffff);

		/*
		 * Den Slider rendern
		 */
		Utils.drawRect(x, y + 12, x + width, y + 13.5, 0xff101010);
		Utils.drawRect(x, y + 12, x + (percentBar * width), y + 13.5, color);
		
		if(percentBar > 0 && percentBar < 1)
			Utils.drawRect(x + (percentBar * width)-1, y + 12, x + Math.min((percentBar * width), width), y + 13.5, color2);
		

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
			return true;
		}
		
		return super.mouseClicked(mouseX, mouseY, mouseButton);
	}

	/*
	 * Wenn die Maus losgelassen wird soll aufgeh�rt werden die Slidervalue zu ver�ndern
	 */
	public void mouseReleased(int mouseX, int mouseY, int state) {
		this.dragging = false;
	}

	/*
	 * Einfacher HoverCheck, ben�tigt damit dragging auf true gesetzt werden kann
	 */
	public boolean isSliderHovered(int mouseX, int mouseY) {
		return mouseX >= x && mouseX <= x + width && mouseY >= y + 11 && mouseY <= y + 14;
	}
}