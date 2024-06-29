package org.ezhik.freezeplayers.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.ezhik.freezeplayers.Freezer;

public class UfrCMD implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        String playername = strings[0];
        Freezer.freezedplayers.remove(playername);
        return true;
    }
}
