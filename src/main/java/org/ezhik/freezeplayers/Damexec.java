package org.ezhik.freezeplayers;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import java.util.ArrayList;

public class Damexec implements Listener {
    public static ArrayList<String> playernames = new ArrayList<>();
    public static void addPlayer(String playername){
        playernames.add(playername);
    }
    public static void removePlayer(String playername){
        playernames.remove(playername);
    }
    @EventHandler
    public void onDamaged(EntityDamageByEntityEvent event){
        Player player = (Player) event.getDamager();
        String playername = player.getName();
        if (playernames.contains(playername)) player.setHealth(0.0D);
    }
}
