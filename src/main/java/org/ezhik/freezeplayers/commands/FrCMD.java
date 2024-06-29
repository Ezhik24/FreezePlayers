package org.ezhik.freezeplayers.commands;

import org.ezhik.freezeplayers.Freezer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class FrCMD implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        String playername = strings[0];
        Player player = commandSender.getServer().getPlayer(playername);
        Freezer.freezedplayers.put(playername, player.getLocation());
        return true;
    }
}
