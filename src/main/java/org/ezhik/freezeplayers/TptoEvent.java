package org.ezhik.freezeplayers;


import org.bukkit.Location;
import org.bukkit.entity.Player;

public class TptoEvent {
    public static Location eventloc = new Location(null, 0, 0, 0);

    public static void tptoLoc(Player player){
        player.sendMessage("Погнали на место!");
        player.teleport(eventloc);
    }
}
