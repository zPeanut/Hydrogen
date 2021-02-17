package tk.peanut.hydrogen.utils;

import tk.peanut.hydrogen.font.H2FontRenderer;

import java.awt.*;

/**
 * Created by peanut on 15/02/2021
 */
public class FontHelper {
    public static H2FontRenderer cfClickGui;

    public static H2FontRenderer cfClickGuiNew = new H2FontRenderer(new Font("Sans", 1, 40), true, 8);

    public static H2FontRenderer cfArrayList;

    public static H2FontRenderer cfSmall;

    public static H2FontRenderer cfButton;

    public static H2FontRenderer cfBig;

    public static void loadFonts() {
        try {
            cfClickGui = new H2FontRenderer(new Font("Sans", 1, 46), true, 8);
            cfArrayList = new H2FontRenderer(new Font("Sans", 1, 36), true, 8);
            cfSmall = new H2FontRenderer(new Font("Sans", 1, 30), true, 8);
            cfButton = new H2FontRenderer(new Font("Sans", 1, 40), true, 8);
            cfBig = new H2FontRenderer(new Font("Sans", 1, 60), true, 8);
        } catch (Exception exception) {}
    }
}
