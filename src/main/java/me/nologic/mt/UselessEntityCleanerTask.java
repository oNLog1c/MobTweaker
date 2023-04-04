package me.nologic.mt;

import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.scheduler.BukkitRunnable;

public class UselessEntityCleanerTask extends BukkitRunnable implements Listener {

    private final SimpleConfigurationManager cfg;
    private final World world;

    private final int x, y, z;

    public UselessEntityCleanerTask(MobTweakerInstance instance, World world) {
        this.cfg = instance.getConfigurationManager();
        this.world = world;
        this.x = this.cfg.getX();
        this.y = this.cfg.getY();
        this.z = this.cfg.getZ();
    }

    public void run() {
        for (Entity e : this.world.getLivingEntities()) {
            if (!this.cfg.getAllowedEntityTypes().contains(e.getType()) || e.getTicksLived() < this.cfg.getMinTicksLived() ||
                    isPlayerAround(e))
                continue;
            if (e.getCustomName() != null)
                continue;
            e.remove();
        }
    }

    private boolean isPlayerAround(Entity entity) {
        for (Entity e : entity.getNearbyEntities(this.x, this.y, this.z)) {
            if (e instanceof Player)
                return true;
        }
        return false;
    }
}
