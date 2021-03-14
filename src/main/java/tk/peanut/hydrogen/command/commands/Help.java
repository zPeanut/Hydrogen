package tk.peanut.hydrogen.command.commands;

import tk.peanut.hydrogen.Hydrogen;
import tk.peanut.hydrogen.command.Command;

/**
 * Created by peanut on 14/03/2021
 */
public class Help extends Command {

    @Override
    public String getName() {
        return "help";
    }

    @Override
    public String getDesc() {
        return "Gives you the syntax of all commands and what they do.";
    }

    @Override
    public String getSyntax() {
        return ".help";
    }

    @Override
    public void execute(String[] args) {
        if(args.length != 1) {
            for(Command c : Hydrogen.getClient().commandManager.getCommands()) {
                msg(c.getSyntax() + " §7- " + c.getDesc());

            }
        }

    }
}
