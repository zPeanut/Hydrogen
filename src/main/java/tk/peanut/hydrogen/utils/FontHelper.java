package tk.peanut.hydrogen.utils;

import tk.peanut.hydrogen.font.H2FontRenderer;

import java.awt.*;
import java.io.File;
import java.io.InputStream;

/**
 * Created by peanut on 15/02/2021
 */
public class FontHelper {

    public static H2FontRenderer fontnormal;
    public static H2FontRenderer fontlarge;

    public static H2FontRenderer cfontnormal;


    public static void loadFonts() {
        try {

            fontlarge = new H2FontRenderer(new Font("Verdana", Font.PLAIN, 40), true, 8);
            fontnormal = new H2FontRenderer(new Font("Verdana", Font.PLAIN, 32), true, 8);

            InputStream stream = FontHelper.class.getClass().getResourceAsStream("/assets/hydrogen/SF-UI-Display-Thin.otf");
            Font font = Font.createFont(Font.TRUETYPE_FONT, stream).deriveFont(36f);
            cfontnormal = new H2FontRenderer(font, true, 8);

        } catch (Exception exception) {}
    }
}
