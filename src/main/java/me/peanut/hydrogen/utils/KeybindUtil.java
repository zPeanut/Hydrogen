package me.peanut.hydrogen.utils;

/**
 * Created by peanut on 03/02/2021
 */
import me.peanut.hydrogen.file.files.ModuleConfig;
import me.peanut.hydrogen.module.Module;

public class KeybindUtil
{
    public void bind(Module mod, int bind) {
        mod.setKeyBind(bind);
    }

    public void unbind(Module mod) {
        mod.setKeyBind(0);
    }

    public int toInt(String keyCode) {
        if (keyCode.equalsIgnoreCase("a")) {
            return 30;
        }
        if (keyCode.equalsIgnoreCase("b")) {
            return 48;
        }
        if (keyCode.equalsIgnoreCase("c")) {
            return 46;
        }
        if (keyCode.equalsIgnoreCase("d")) {
            return 32;
        }
        if (keyCode.equalsIgnoreCase("e")) {
            return 18;
        }
        if (keyCode.equalsIgnoreCase("f")) {
            return 33;
        }
        if (keyCode.equalsIgnoreCase("g")) {
            return 34;
        }
        if (keyCode.equalsIgnoreCase("h")) {
            return 35;
        }
        if (keyCode.equalsIgnoreCase("i")) {
            return 23;
        }
        if (keyCode.equalsIgnoreCase("j")) {
            return 36;
        }
        if (keyCode.equalsIgnoreCase("k")) {
            return 37;
        }
        if (keyCode.equalsIgnoreCase("l")) {
            return 38;
        }
        if (keyCode.equalsIgnoreCase("m")) {
            return 50;
        }
        if (keyCode.equalsIgnoreCase("n")) {
            return 49;
        }
        if (keyCode.equalsIgnoreCase("o")) {
            return 24;
        }
        if (keyCode.equalsIgnoreCase("p")) {
            return 25;
        }
        if (keyCode.equalsIgnoreCase("q")) {
            return 16;
        }
        if (keyCode.equalsIgnoreCase("r")) {
            return 19;
        }
        if (keyCode.equalsIgnoreCase("s")) {
            return 31;
        }
        if (keyCode.equalsIgnoreCase("t")) {
            return 20;
        }
        if (keyCode.equalsIgnoreCase("u")) {
            return 22;
        }
        if (keyCode.equalsIgnoreCase("v")) {
            return 47;
        }
        if (keyCode.equalsIgnoreCase("w")) {
            return 17;
        }
        if (keyCode.equalsIgnoreCase("x")) {
            return 45;
        }
        if (keyCode.equalsIgnoreCase("y")) {
            return 21;
        }
        if (keyCode.equalsIgnoreCase("z")) {
            return 44;
        }
        if (keyCode.equalsIgnoreCase("0")) {
            return 11;
        }
        if (keyCode.equalsIgnoreCase("1")) {
            return 2;
        }
        if (keyCode.equalsIgnoreCase("2")) {
            return 3;
        }
        if (keyCode.equalsIgnoreCase("3")) {
            return 4;
        }
        if (keyCode.equalsIgnoreCase("4")) {
            return 5;
        }
        if (keyCode.equalsIgnoreCase("5")) {
            return 6;
        }
        if (keyCode.equalsIgnoreCase("6")) {
            return 7;
        }
        if (keyCode.equalsIgnoreCase("7")) {
            return 8;
        }
        if (keyCode.equalsIgnoreCase("8")) {
            return 9;
        }
        if (keyCode.equalsIgnoreCase("9")) {
            return 10;
        }
        if (keyCode.equalsIgnoreCase("f1")) {
            return 59;
        }
        if (keyCode.equalsIgnoreCase("f2")) {
            return 60;
        }
        if (keyCode.equalsIgnoreCase("f3")) {
            return 61;
        }
        if (keyCode.equalsIgnoreCase("f4")) {
            return 62;
        }
        if (keyCode.equalsIgnoreCase("f5")) {
            return 63;
        }
        if (keyCode.equalsIgnoreCase("f6")) {
            return 64;
        }
        if (keyCode.equalsIgnoreCase("f7")) {
            return 65;
        }
        if (keyCode.equalsIgnoreCase("f8")) {
            return 66;
        }
        if (keyCode.equalsIgnoreCase("f9")) {
            return 67;
        }
        if (keyCode.equalsIgnoreCase("f10")) {
            return 68;
        }
        if (keyCode.equalsIgnoreCase("f11")) {
            return 87;
        }
        if (keyCode.equalsIgnoreCase("f12")) {
            return 88;
        }
        if (keyCode.equalsIgnoreCase("numpad0")) {
            return 82;
        }
        if (keyCode.equalsIgnoreCase("numpad1")) {
            return 79;
        }
        if (keyCode.equalsIgnoreCase("numpad2")) {
            return 80;
        }
        if (keyCode.equalsIgnoreCase("numpad3")) {
            return 81;
        }
        if (keyCode.equalsIgnoreCase("numpad4")) {
            return 75;
        }
        if (keyCode.equalsIgnoreCase("numpad5")) {
            return 76;
        }
        if (keyCode.equalsIgnoreCase("numpad6")) {
            return 77;
        }
        if (keyCode.equalsIgnoreCase("numpad7")) {
            return 71;
        }
        if (keyCode.equalsIgnoreCase("numpad8")) {
            return 72;
        }
        if (keyCode.equalsIgnoreCase("numpad9")) {
            return 73;
        }
        if (keyCode.equalsIgnoreCase("up")) {
            return 200;
        }
        if (keyCode.equalsIgnoreCase("down")) {
            return 208;
        }
        if (keyCode.equalsIgnoreCase("right")) {
            return 205;
        }
        if (keyCode.equalsIgnoreCase("left")) {
            return 203;
        }
        if (keyCode.equalsIgnoreCase("del")) {
            return 211;
        }
        if (keyCode.equalsIgnoreCase("insert")) {
            return 210;
        }
        if (keyCode.equalsIgnoreCase("end")) {
            return 207;
        }
        if (keyCode.equalsIgnoreCase("home")) {
            return 199;
        }
        if (keyCode.equalsIgnoreCase("rshift")) {
            return 54;
        }
        if (keyCode.equalsIgnoreCase("lshift")) {
            return 42;
        }
        if(keyCode.equalsIgnoreCase("lcontrol")) {
            return 29;
        }
        if (keyCode.equalsIgnoreCase("tab")) {
            return 15;
        }
        if (keyCode.equalsIgnoreCase(".")) {
            return 52;
        }
        if (keyCode.equalsIgnoreCase("strg")) {
            return 29;
        }
        if (keyCode.equalsIgnoreCase("alt")) {
            return 56;
        }
        if (keyCode.equalsIgnoreCase("hashtag")) {
            return 53;
        }
        return 0;
    }
}
