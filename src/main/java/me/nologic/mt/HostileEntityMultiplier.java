package me.nologic.mt;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;

import java.security.SecureRandom;

public class HostileEntityMultiplier implements Listener {

    private final SimpleConfigurationManager cfg;
    private final World world;
    private final SecureRandom rnd;

    public HostileEntityMultiplier(MobTweakerInstance instance, World world) {
        this.cfg = instance.getConfigurationManager();
        this.world = world;
        this.rnd = new SecureRandom();
    }

    @EventHandler
    public void onCreatureSpawn(CreatureSpawnEvent event) {
        if (event.getSpawnReason() != CreatureSpawnEvent.SpawnReason.NATURAL)
            return;
        if (this.rnd.nextInt(100) > this.cfg.getMultiplyChance())
            return;
        if (event.getEntityType() == EntityType.ZOMBIE || event.getEntityType() == EntityType.SKELETON || event.getEntityType() == EntityType.CREEPER || event.getEntityType() == EntityType.SPIDER)
            for (int i = 0; i < this.cfg.getMultiplier(); i++) {
                if (!event.isCancelled()) {
                    this.spawnRandomEntityAt(event.getLocation());
                }
            }
    }

    private void spawnRandomEntityAt(Location loc) {
        int dice = this.rnd.nextInt(100);
        if (dice > 0 && dice < 33) {
            world.spawn(loc, Zombie.class);
            return;
        }
        if (dice > 33 && dice < 50) {
            world.spawn(loc, Skeleton.class);
            return;
        }
        if (dice > 50 && dice < 75) {
            world.spawn(loc, Creeper.class);
            return;
        }
        if (dice > 75 && dice < 95) {
            world.spawn(loc, Spider.class);
            return;
        }
        world.spawn(loc, Enderman.class);
    }
}
