package tk.peanut.hydrogen.utils;

import tk.peanut.hydrogen.font.H2FontRenderer;

import java.awt.*;

/**
 * Created by peanut on 15/02/2021
 */
public class FontHelper {
    public static H2FontRenderer hfont;

    public static void loadFonts() {
        try {
            hfont = new H2FontRenderer(new Font("Century Gothic", 1, 60), true, 8);
        } catch (Exception exception) {}
    }
}
