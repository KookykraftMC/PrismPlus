package com.kookykraftmc.prismplus;

import me.botsko.prism.Prism;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

/**
 * Created by TimeTheCat on 3/8/2016.
 */
public class Prismplus extends JavaPlugin {
    //get Prism
    private Prism pri;
    //public logger
    public Logger l = Logger.getLogger("PrismPlus");
    public static String prefix = "[Prism+]";

    //get enabled plugin events
    public boolean gppEnabledCfg = this.getConfig().getBoolean("Plugins.griefPreventionPlus");
    public boolean factionsEnabledCfg = this.getConfig().getBoolean("Plugins.factions");

    public void onEnable() {
        l.info(prefix + "Loading!");
        //create config
        getConfig().options().copyDefaults(true);
        saveConfig();
        //check if prism is enabled
        if (prismCheck()) {
            l.info(prefix + "Prism found! Enabling plugin!");
            Plugin prism = this.getServer().getPluginManager().getPlugin("Prism");
            pri = (Prism) prism;
            //check for GP+
            if (gppEnabledCfg && gppCheck()) {

                //Plugin GPP = this.getServer().getPluginManager().getPlugin("GriefPreventionPlus");

                l.info(prefix + "GriefPreventionPlus event logging enabled!");

                this.getServer().getPluginManager().registerEvents(new GPPEvents(this), this);
                //add prism actions

                //GP+ Instance
            } else {
                l.info(prefix + "GriefPreventionPlus support disabled or not found");
            }
            //check for Factions
            if (factionsEnabledCfg && factionsCheck()) {
                l.info(prefix + "Factions event logging enabled!");
                //Plugin GPP = this.getServer().getPluginManager().getPlugin("Factions");
                //this.getServer().getPluginManager().registerEvents(new FactionEvents(), this);
            } else {
                l.info(prefix + "Factions support disabled or not found");
            }
        } else {
            l.info((prefix + "Prism not found! Disabling plugin!"));
        }
    }
    public boolean gppCheck() {
        return getServer().getPluginManager().getPlugin("GriefPreventionPlus").isEnabled();
    }
    
    public boolean factionsCheck() {
        return getServer().getPluginManager().getPlugin("Factions").isEnabled(); 
    }
    
    public boolean prismCheck() {
        return getServer().getPluginManager().getPlugin("Prism").isEnabled();
    }

    public Object getPrism() {
        return pri;
    }
}
