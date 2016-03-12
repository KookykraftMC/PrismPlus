package com.kookykraftmc.prismplus;

import me.botsko.prism.Prism;
import me.botsko.prism.actionlibs.ActionType;
import me.botsko.prism.exceptions.InvalidActionException;
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

    //get enabled plugin events
    public boolean gppEnabledCfg = this.getConfig().getBoolean("griefPreventionPlus");
    public boolean factionsEnabledCfg = this.getConfig().getBoolean("factions");

    public void onEnable() {
        l.info("Loading Prism+!");
        //create config
        getConfig().options().copyDefaults(true);
        saveConfig();
        //check if prism is enabled
        if (prismCheck() == true) {
            l.info("Prism found! Enabling plugin!");
            Plugin prism = this.getServer().getPluginManager().getPlugin("Prism");
            pri = (Prism) prism;
            //check for GP+
            if (gppEnabledCfg == true && gppCheck() == true) {

                Plugin GPP = this.getServer().getPluginManager().getPlugin("GriefPreventionPlus");
                
                l.info("GriefPreventionPlus event logging enabled!");

                this.getServer().getPluginManager().registerEvents(new GPPEvents(this), this);
                //add prism actions
                try {
                    Prism.getActionRegistry().registerCustomAction( this, new ActionType("gpp-claim-create", false, false, false, "ClaimCreateAction", "created claim"));
                    Prism.getActionRegistry().registerCustomAction( this, new ActionType("gpp-claim-remove", false, false, false, "ClaimRemoveAction", "remove/deleted claim"));
                    Prism.getActionRegistry().registerCustomAction( this, new ActionType("gpp-claim-enter", false, false, false, "ClaimEnterAction", "triggered"));
                    Prism.getActionRegistry().registerCustomAction( this, new ActionType("gpp-claim-exit", false, false, false, "ClaimExitAction", "triggered"));
                } catch (InvalidActionException e) {
                    e.printStackTrace();
                }
                //GP+ Instance
            } else {
                l.info("GriefPreventionPlus support disabled or not found");
            }
            //check for Factions
            if (factionsEnabledCfg == true && factionsCheck() == true) {
                l.info("Factions event logging enabled!");
                //Plugin GPP = this.getServer().getPluginManager().getPlugin("Factions");
                //this.getServer().getPluginManager().registerEvents(new FactionEvents(), this);
            } else {
                l.info("Factions support disabled or not found");
            }
        } else {
            l.info(("Prism not found! Disabling plugin!"));
        }
    }
    public boolean gppCheck() {
        if (getServer().getPluginManager().getPlugin("GriefPreventionPlus").isEnabled() == true) {
            return true;
        } else {
            return false;
        }
    }
    public boolean factionsCheck() {
        if (getServer().getPluginManager().getPlugin("Factions").isEnabled() == true) {
            return true;
        } else {
            return false;
        }
    }
    public boolean prismCheck() {
        if (getServer().getPluginManager().getPlugin("Prism").isEnabled() == true) {
            return true;
        } else {
            return false;
        }
    }

    public Object getPrism() {
        return pri;
    }
}
