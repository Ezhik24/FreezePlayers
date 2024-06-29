package org.ezhik.freezeplayers;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import java.util.HashMap;
import java.util.Map;

public class Freezer implements Listener {
    public static Map<String, Location> freezedplayers = new HashMap<>();
    @EventHandler
    public void onMove(PlayerMoveEvent event){
        Player player = event.getPlayer();
        String playername = player.getName();
        if (freezedplayers.containsKey(playername)) player.teleport(freezedplayers.get(playername));
        }
    }


