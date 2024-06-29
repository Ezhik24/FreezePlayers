package org.ezhik.freezeplayers.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.ezhik.freezeplayers.Damexec;

public class OffkillCMD implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        Damexec.removePlayer(strings[0]);
        commandSender.sendMessage("Игроку " + strings[0] + " разрешено наносить урон");
        Player player = commandSender.getServer().getPlayer(strings[0]);
        player.sendMessage("Вам разрешено наносить урон");
        return true;
    }
}
