package org.ezhik.freezeplayers.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.ezhik.freezeplayers.TptoEvent;

public class SeteventlocCMD implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        Player admin = (Player) commandSender;
        TptoEvent.eventloc = admin.getLocation();
        admin.sendMessage("Точка события установлена");
        return true;
    }
}
