package me.peanut.hydrogen.command;

import me.peanut.hydrogen.command.commands.Bind;
import me.peanut.hydrogen.command.commands.Help;
import me.peanut.hydrogen.command.commands.Toggle;
import net.minecraft.client.network.NetHandlerPlayClient;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by peanut on 13/03/2021
 */
public class CommandManager {

    public NetHandlerPlayClient sendQueue;
    private static CommandManager me = new CommandManager();
    private List<Command> commands = new ArrayList();
    private String prefix = ".";

    public CommandManager() {
        add(new Bind());
        add(new Help());
        add(new Toggle());
    }

    public void add(Command command) {
        this.commands.add(command);
    }

    public static CommandManager get() {
        return me;
    }

    public String getPrefix() {
        return this.prefix;
    }

    public List<Command> getCommands() {
        return this.commands;
    }

    public boolean execute(String text) {
        if (!text.startsWith(prefix)) {
            return false;
        }

        text = text.substring(1);

        String[] arguments = text.split(" ");
        String ranCmd = arguments[0];
        for (Command cmd : this.commands) {
            if (cmd.getName().equalsIgnoreCase(arguments[0])) {
                String[] args = (String[]) Arrays.copyOfRange(arguments, 1, arguments.length);
                String[] args1 = text.split(" ");
                cmd.execute(args);
                return true;
            }
        }
        Command.msg("The command \"§9" + ranCmd + "§7\" has not been found!");

        return false;
    }
}
