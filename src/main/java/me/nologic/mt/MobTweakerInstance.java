package me.nologic.mt;

import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class MobTweakerInstance extends JavaPlugin {
  private SimpleConfigurationManager cfg;
  
  public void onEnable() {
    init();
    load();
    launch();
  }
  
  public void init() {
    this.cfg = new SimpleConfigurationManager(this);
  }
  
  public void load() {
    saveDefaultConfig();
    this.cfg.load();
  }
  
  public void launch() {
    if (this.cfg.isCleanerEnabled()) {
      this.cfg.getEnabledWorlds().forEach(w -> (new UselessEntityCleanerTask(this, w)).runTaskTimer((Plugin)this, this.cfg.getRepeatingTime(), 0L));
      if (this.cfg.isMultiplierEnabled())
        this.cfg.getEnabledWorlds().forEach(w -> getServer().getPluginManager().registerEvents(new HostileEntityMultiplier(this, w), (Plugin)this)); 
      if (this.cfg.isAESCEnabled())
        this.cfg.getEnabledWorlds().forEach(w -> getServer().getPluginManager().registerEvents(new AggressiveEntitySpawnCancellation(this, w), (Plugin)this)); 
    } 
  }
  
  public SimpleConfigurationManager getConfigurationManager() {
    return this.cfg;
  }
}
