package tk.peanut.phosphor.ui.clickgui.util;

import java.awt.Color;

import tk.peanut.phosphor.Phosphor;

public class ColorUtil {
	
	public static Color getClickGUIColor(){
		return new Color((int) Phosphor.getInstance().settingsManager.getSettingByName("Red").getValDouble(), (int)Phosphor.getInstance().settingsManager.getSettingByName("Green").getValDouble(), (int) Phosphor.getInstance().settingsManager.getSettingByName("Blue").getValDouble());
	}
}
