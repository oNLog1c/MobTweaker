package me.nologic.mt;

import java.util.Objects;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;

public class AggressiveEntitySpawnCancellation implements Listener {
    private final SimpleConfigurationManager configuration;

    private final World world;

    public AggressiveEntitySpawnCancellation(MobTweakerInstance instance, World world) {
        this.configuration = instance.getConfigurationManager();
        this.world = world;
    }

    @EventHandler
    public void onCreatureSpawn(CreatureSpawnEvent event) {
        if (!Objects.requireNonNull(event.getLocation().getWorld()).getName().equals(this.world.getName()))
            return;
        if (!this.configuration.getAllowedEntityTypes().contains(event.getEntityType()))
            return;
        if (isPlayerAround(event.getEntity()))
            return;
        event.setCancelled(true);
    }

    private boolean isPlayerAround(Entity entity) {
        for (Entity e : entity.getNearbyEntities(this.configuration.getX(), this.configuration.getY(), this.configuration.getZ())) {
            if (e instanceof Player)
                return true;
        }
        return false;
    }
}
