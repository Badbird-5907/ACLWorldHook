package net.badbird5907.aclworldhook;

import net.badbird5907.anticombatlog.api.events.CombatLogNPCSpawnEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public final class ACLIndefWorldHook extends JavaPlugin implements Listener {
    private List<String> indefWorlds = new ArrayList<>();

    @Override
    public void onEnable() {
        if (!getDataFolder().exists())
            getDataFolder().mkdir();
        if (!new File(getDataFolder(), "config.yml").exists()) {
            saveDefaultConfig();
        }
        reloadConfig();

        getServer().getPluginManager().registerEvents(this,this);
    }


    @Override
    public void reloadConfig() {
        indefWorlds.clear();
        indefWorlds = getConfig().getStringList("indefinite-worlds");
    }

    @EventHandler
    public void onNPCSpawn(CombatLogNPCSpawnEvent event) {
        if (indefWorlds.contains(event.getPlayer().getWorld().getName())) {
            event.setIndefinite(true);
        }
    }
}
