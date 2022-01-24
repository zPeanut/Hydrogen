package me.peanut.hydrogen.utils;

import me.peanut.hydrogen.Hydrogen;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.util.ChatComponentText;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.util.Random;

public class Utils {

    private static final Random RANDOM = new Random();

    private static final Minecraft mc = Minecraft.getMinecraft();

    public static int deltaTime;

    public static int random(int min, int max) {
        if (max <= min) return min;

        return RANDOM.nextInt(max - min) + min;
    }

    public static void sendChatMessage(final String message) {
        ChatComponentText chatcomponenttext = new ChatComponentText(String.format("%s %s", Hydrogen.prefix, message));
        Minecraft.getMinecraft().ingameGUI.getChatGUI().printChatMessage(chatcomponenttext);
    }

    public static void sendChatMessage(final ChatComponentText chatComponentText) {
        Minecraft.getMinecraft().ingameGUI.getChatGUI().printChatMessage(chatComponentText);
    }

    public static String abbreviateString(String input, int maxLength) {
        if (input.length() <= maxLength) {
            return input;
        } else {
            return input.substring(0, maxLength - 2) + "...";
        }
    }

    public static synchronized void playSound(final String url) {
        new Thread(() -> {
            try {
                Clip clip = AudioSystem.getClip();
                AudioInputStream inputStream = AudioSystem.getAudioInputStream(Utils.class.getResourceAsStream("/assets/hydrogen/" + url));
                clip.open(inputStream);
                clip.start();
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }, "sound play").start();
    }
    public static float slide = 1f;
    public static void addSlide(float needX, float steps) {
        if (slide != needX) {
            if (slide < needX) {
                if (slide <= needX - steps) {
                    slide += steps;
                } else if (slide > needX - steps) {
                    slide = needX;
                }
            }
            if (slide > needX) {
                if (slide >= needX + steps) {
                    slide -= steps;
                } else if (slide < needX + steps) {
                    slide = needX;
                }
            }
        }
    }

    public static ScaledResolution getScaledRes() {
        final ScaledResolution scaledRes = new ScaledResolution(Minecraft.getMinecraft());
        return scaledRes;
    }

    public static double distance(float x, float y, float x1, float y1) {
        return Math.sqrt((x - x1) * (x - x1) + (y - y1) * (y - y1));
    }

    public static void errorLog(String message) {
        System.err.println("[Hydrogen] " + message);
    }

    public static void log(String message) {
        System.out.println("[Hydrogen] " + message);
    }
}
