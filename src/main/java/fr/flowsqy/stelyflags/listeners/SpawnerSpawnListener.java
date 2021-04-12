package fr.flowsqy.stelyflags.listeners;

import com.sk89q.worldedit.bukkit.BukkitWorld;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.protection.ApplicableRegionSet;
import fr.flowsqy.stelyflags.StelyFlags;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.SpawnerSpawnEvent;
import org.bukkit.plugin.Plugin;

public class SpawnerSpawnListener implements Listener {

    public SpawnerSpawnListener(Plugin plugin) {
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onMobSpawnerSpawn(SpawnerSpawnEvent event) {
        final Location location = event.getSpawner().getBlock().getLocation();
        final ApplicableRegionSet set = WorldGuard
                .getInstance()
                .getPlatform()
                .getRegionContainer()
                .createQuery()
                .getApplicableRegions(
                        new com.sk89q.worldedit.util.Location(
                                new BukkitWorld(location.getWorld()),
                                location.getX(),
                                location.getY(),
                                location.getZ()
                        )
                );
        if (!set.testState(null, StelyFlags.SPAWN_SPAWNER)) {
            event.setCancelled(true);
        }
    }


}
