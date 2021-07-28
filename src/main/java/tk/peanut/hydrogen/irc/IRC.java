package tk.peanut.hydrogen.irc;

import tk.peanut.hydrogen.Hydrogen;
import tk.peanut.hydrogen.utils.PlayerUtils;
import net.minecraft.client.Minecraft;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class IRC {

    public static BufferedReader reader;
    public static Socket socket;
    public static PrintWriter pw;


    public static boolean connect(String ip, int port) {
        try {
            socket = new Socket(ip, port);
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream(),"GBK"));
            pw=new PrintWriter(socket.getOutputStream(), true);

            pw.println(Minecraft.getMinecraft().thePlayer.getName() + Hydrogen.FGF + "Connected To The Server");

            new IRCThread().start();

            return true;
        } catch (Exception e) {
            System.exit(0);
        }
        return false;
    }


    public static void sendMessage(String msg) {
        System.out.println(msg);
        pw.println(Minecraft.getMinecraft().thePlayer.getName() + Hydrogen.FGF + msg);

    }

    public static void shutdownirc() {
        try {
            pw.close();
            reader.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void handleInput() {
        try {
            String ircMessage = reader.readLine();

            System.out.println(ircMessage);

            if (ircMessage == null) {
                return;
            }

            switch (ircMessage) {
                case "[SERVER][Command]CLOSE":

                    if (Minecraft.getMinecraft().theWorld != null) {
                        PlayerUtils.ERROR("§4IRC Server have benn shutdown");
                    }
                    else{
                        JOptionPane.showMessageDialog(null,"IRC Server have benn shutdown",Hydrogen.name,JOptionPane.ERROR);
                    }

                    return;
                case "[SERVER][Command]KICK":

                    if (Minecraft.getMinecraft().theWorld != null) {
                        PlayerUtils.ERROR("§4You Have Been Kick From The IRCChat");
                    }
                    else{
                        JOptionPane.showMessageDialog(null,"You Have Been Kick From The IRCChat",Hydrogen.name,JOptionPane.ERROR);
                    }

                    return;
                default:
                    break;
            }

            String[] ircback = ircMessage.split(Hydrogen.FGF);

            if (Minecraft.getMinecraft().thePlayer != null) {
                PlayerUtils.INFO("§6 "+ircback[0]+"§d :"+"§f "+ircback[1]);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
