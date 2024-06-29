package org.ezhik.freezeplayers;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.ezhik.freezeplayers.commands.*;

public final class FreezePlayers extends JavaPlugin {

    @Override
    public void onEnable() {
        getCommand ("freeze").setExecutor(new FrCMD());
        getCommand ("unfreeze").setExecutor(new UfrCMD());
        getCommand("invclear").setExecutor(new InvClearCMD());
        getCommand("invreturn").setExecutor(new InvReturnCMD());
        getCommand("setiventlocation").setExecutor(new SeteventlocCMD());
        getCommand("tptuevent").setExecutor(new TptueventCMD());
        Bukkit.getPluginManager().registerEvents(new Freezer(), this);
        Bukkit.getPluginManager().registerEvents(new Damexec(), this);
        getCommand("offkill").setExecutor(new OffkillCMD());
        getCommand("onkill").setExecutor(new OnkillCMD());
        // Plugin startup logic

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
