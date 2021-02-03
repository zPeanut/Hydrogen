package tk.peanut.phosphor.ui.clickgui.ui.util;

import java.awt.Color;

import tk.peanut.phosphor.Phosphor;

public class ColorUtil {
	
	public static Color getClickGUIColor(){
		return new Color((int) Phosphor.getInstance().settingsManager.getSettingByName("GuiRed").getValDouble(), (int)Phosphor.getInstance().settingsManager.getSettingByName("GuiGreen").getValDouble(), (int)Phosphor.getInstance().settingsManager.getSettingByName("GuiBlue").getValDouble());
	}
}
