package fr.flowsqy.stelyflags;

import com.sk89q.worldguard.WorldGuard;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Level;

public class StelyFlagsPlugin extends JavaPlugin {

    @Override
    public void onLoad() {
        try {
            StelyFlags.load(WorldGuard.getInstance().getFlagRegistry());
        } catch (RuntimeException exception) {
            getLogger().log(Level.SEVERE, "Can not load flags, disable the plugin", exception);
            Bukkit.getPluginManager().disablePlugin(this);
        }
    }

    @Override
    public void onEnable() {
        new VillagerInteractListener(this);
    }
}
