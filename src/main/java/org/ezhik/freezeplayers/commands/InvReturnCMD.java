package org.ezhik.freezeplayers.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.ezhik.freezeplayers.InvMagic;

public class InvReturnCMD implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (strings.length == 0) {
            commandSender.sendMessage("Использование: /invreturn <игрок>");
        } else {
            for (int i = 0; i < strings.length; i++) {
                Player player = commandSender.getServer().getPlayer(strings[i]);
                if (player == null) {
                    commandSender.sendMessage("Игрок не найден");
                } else {
                    InvMagic.returnitems(player);
                }
            }

        }
        return true;
    }
}
