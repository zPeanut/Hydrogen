package tk.peanut.hydrogen.utils;

import tk.peanut.hydrogen.font.H2FontRenderer;

import java.awt.*;

/**
 * Created by peanut on 15/02/2021
 */
public class FontHelper {

    public static H2FontRenderer fontnormal;
    public static H2FontRenderer fontlarge;

    public static H2FontRenderer cfontnormal;
    public static H2FontRenderer cfontlarge;



    public static void loadFonts() {
        try {

            fontlarge = new H2FontRenderer(new Font("Verdana", Font.PLAIN, 40), true, 8);
            fontnormal = new H2FontRenderer(new Font("Verdana", Font.PLAIN, 32), true, 8);

            cfontlarge = new H2FontRenderer(new Font("Comfortaa", Font.PLAIN, 40), true, 8);
            cfontnormal = new H2FontRenderer(new Font("Comfortaa", Font.PLAIN, 32), true, 8);

        } catch (Exception exception) {}
    }
}
