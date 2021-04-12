package fr.flowsqy.stelyflags.listeners;

import com.sk89q.worldedit.bukkit.BukkitWorld;
import com.sk89q.worldguard.LocalPlayer;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.protection.ApplicableRegionSet;
import fr.flowsqy.stelyflags.StelyFlags;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.plugin.Plugin;

public class VillagerInteractListener implements Listener {

    public VillagerInteractListener(Plugin plugin) {
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onInteractAtVillager(PlayerInteractEntityEvent event) {
        final Entity entity = event.getRightClicked();
        if (!(entity instanceof Villager)) {
            return;
        }
        final Location location = entity.getLocation();
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
        final LocalPlayer player = WorldGuardPlugin.inst().wrapPlayer(event.getPlayer());
        if (!set.isMemberOfAll(player) && !set.testState(player, StelyFlags.INTERACT_VILLAGER)) {
            event.setCancelled(true);
        }
    }


}
