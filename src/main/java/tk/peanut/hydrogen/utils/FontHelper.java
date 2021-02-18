package tk.peanut.hydrogen.utils;

import tk.peanut.hydrogen.font.H2FontRenderer;

import java.awt.*;

/**
 * Created by peanut on 15/02/2021
 */
public class FontHelper {

    public static H2FontRenderer hfontbold;
    public static H2FontRenderer hfontboldlarge;

    public static H2FontRenderer hfontnormal;
    public static H2FontRenderer hfontnormal2;
    public static H2FontRenderer hfontnormal3;
    public static H2FontRenderer hfontnormallarge;
    public static H2FontRenderer hfontnormalcurved;



    public static void loadFonts() {
        try {

            hfontnormal = new H2FontRenderer(new Font("Verdana", Font.PLAIN, 40), true, 8);
            hfontbold = new H2FontRenderer(new Font("Verdana", Font.PLAIN, 32), true, 8);
            hfontnormalcurved = new H2FontRenderer(new Font("Verdana", Font.ITALIC, 40), true, 8);

            hfontnormal2 = new H2FontRenderer(new Font("Verdana", Font.PLAIN, 36), true, 8);
            hfontnormal3 = new H2FontRenderer(new Font("Verdana", Font.PLAIN, 34), true, 8);

            hfontnormallarge = new H2FontRenderer(new Font("Verdana", Font.PLAIN, 60), true, 8);
            hfontboldlarge = new H2FontRenderer(new Font("Verdana", Font.BOLD, 60), true, 8);

        } catch (Exception exception) {}
    }
}
