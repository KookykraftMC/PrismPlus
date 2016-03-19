package com.kookykraftmc.prismplus;

import me.botsko.prism.Prism;

import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.logging.Logger;

/**
 * Created by TimeTheCat on 3/8/2016.
 */
public class Prismplus extends JavaPlugin {
    //get Prism
    private Prism pri;
    //public logger
    static public Logger l = Logger.getLogger("PrismPlus");
    public static String prefix = "[Prism+]";

    //config options commenting out for later
    /*
    * public boolean claimCreate = getConfig().getBoolean("Events.griefPreventionPlus.claimCreate");
    * public boolean claimDelete = getConfig().getBoolean("Events.griefPreventionPlus.claimDelete");
    * public boolean claimEnter = getConfig().getBoolean("Events.griefPreventionPlus.claimEnter");
    * public boolean claimExit = getConfig().getBoolean("Events.griefPreventionPlus.claimExit");
    */
    //get enabled plugin events

    public void onEnable() {
        pri = (Prism) this.getServer().getPluginManager().getPlugin("Prism");
		File cfg = new File(getDataFolder(), "config.yml");
		PluginDescriptionFile pdf = getDescription();

        if (!cfg.exists())
        {
            l.info(prefix + "Config not found, creating.");
            saveDefaultConfig();
        }
        else if(this.getConfig().getString("Version").isEmpty()||pdf.getVersion()!=this.getConfig().getString("Version"))
        {
        	l.info(prefix + "Outdated config found, saving new version.");
        	saveDefaultConfig();
        }
        
        //Register listeners
        addGPP();
        addFactions();
        addInteractions();
    }

    public void onDisable() {
        l.info(prefix + " Disabling PrismPlus! Goodbye!");
    }

    public void addGPP() {
        if (!(getServer().getPluginManager().getPlugin("GriefPreventionPlus") == null)
        		&& this.getConfig().getBoolean("Plugins.griefPreventionPlus")) {
            l.info(prefix + " GriefPreventionPlus event logging enabled!");
            this.getServer().getPluginManager().registerEvents(new GPPEvents(this), this);
            //GP+ Instance
        } else {
            l.info(prefix + " GriefPreventionPlus support disabled or not found");
        }
    }

    public void addFactions() {
        if (!(getServer().getPluginManager().getPlugin("Factions") == null)
        		&& this.getConfig().getBoolean("Plugins.factions")) {
            l.info(prefix + " Factions event logging enabled!");
            this.getServer().getPluginManager().registerEvents(new FactionEvents(this), this);
        } else {
            l.info(prefix + " Factions support disabled or not found");
        }
    }
    
    public void addInteractions() {
        if (!this.getConfig().getStringList("LogItems").isEmpty()) {
            l.info(prefix + " Interaction logging enabled!");
            this.getServer().getPluginManager().registerEvents(new InteractListener(this), this);
        } else {
            l.info(prefix + " Interaction logging not found!");
        }
    }

    public Object getPrism() {
        return pri;
    }
    
    public void reloadCfg()
    {
    	//For later
    	this.reloadConfig();
    	InteractListener.loadCfg();
    }
}
