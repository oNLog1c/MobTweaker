package me.nologic.mt;

import java.util.ArrayList;
import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.EntityType;

public class SimpleConfigurationManager {

    private final MobTweakerInstance instance;
    private FileConfiguration config;
    public SimpleConfigurationManager(MobTweakerInstance instance) {
        this.instance = instance;
    }

    public void load() {
        this.config = this.instance.getConfig();
    }

    public List<World> getEnabledWorlds() {
        List<World> worlds = new ArrayList<>();
        this.config.getStringList("EnabledWorlds").forEach(s -> worlds.add(Bukkit.getWorld(s)));
        return worlds;
    }

    public int getX() {
        return this.config.getInt("Radius.X");
    }

    public int getY() {
        return this.config.getInt("Radius.Y");
    }

    public int getZ() {
        return this.config.getInt("Radius.Z");
    }

    public int getMinTicksLived() {
        return this.config.getInt("HostileEntityCleaner.MinTicksLived");
    }

    public List<EntityType> getAllowedEntityTypes() {
        List<EntityType> entities = new ArrayList<>();
        this.config.getStringList("HostileEntityCleaner.AllowedEntityTypes").forEach(s -> entities.add(EntityType.valueOf(s)));
        return entities;
    }

    public int getRepeatingTime() {
        return this.config.getInt("HostileEntityCleaner.RepeatTime");
    }

    public boolean isCleanerEnabled() {
        return this.config.getBoolean("HostileEntityCleaner.Enabled");
    }

    public boolean isMultiplierEnabled() {
        return this.config.getBoolean("HostileEntityMultiplier.Enabled");
    }

    public int getMultiplyChance() {
        return this.config.getInt("HostileEntityMultiplier.Chance");
    }

    public int getMultiplier() {
        return this.config.getInt("HostileEntityMultiplier.Multiplier");
    }

    public boolean isAESCEnabled() {
        return this.config.getBoolean("AggressiveEntitySpawnCancellation.Enabled");
    }

    public List<String> getExtraMobs() {
        return this.config.getStringList("HostileEntityMultiplier.ExtraMobs");
    }
}
