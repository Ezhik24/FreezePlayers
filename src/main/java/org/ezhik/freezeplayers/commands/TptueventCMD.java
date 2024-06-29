package org.ezhik.freezeplayers.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.ezhik.freezeplayers.TptoEvent;

public class TptueventCMD implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        Player player = (Player) commandSender;
        player.sendMessage("Теперь ты в эвенте!");
        player.teleport(TptoEvent.eventloc);


        return true;
    }
}
