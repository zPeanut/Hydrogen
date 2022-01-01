package me.peanut.hydrogen.command.commands;

import me.peanut.hydrogen.command.Command;

/**
 * Created by peanut on 01/01/2022
 */
public class EasterEggCommand extends Command {
    @Override
    public void execute(String[] args) {
        if(args.length != 1) {
            msg("OH BELLA CIAO BELLA CIAO BELLA CIAO CIAO CIAO");
        }
    }

    @Override
    public String getName() {
        return "oh";
    }

    @Override
    public String getSyntax() {
        return ".oh";
    }

    @Override
    public String getDesc() {
        return "Tribute to one of the greatest shows I've ever seen.";
    }
}
