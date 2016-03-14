package com.kookykraftmc.prismplus;

import me.botsko.prism.Prism;
import me.botsko.prism.actionlibs.ActionType;
import me.botsko.prism.events.PrismCustomPlayerActionEvent;
import me.botsko.prism.exceptions.InvalidActionException;
import net.kaikk.mc.gpp.events.*;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

/**
 * Created by TimeTheCat on 3/8/2016.
 */
public class GPPEvents implements Listener {

	PrismCustomPlayerActionEvent prismEvent;
    public Prismplus plugin;
    public GPPEvents(Prismplus plugin){
        this.plugin = plugin;
        try {
            Prism.getActionRegistry().registerCustomAction(plugin, new ActionType("gpp-claim-create", false, false, false, "GPPAction", "created claim"));
            Prism.getActionRegistry().registerCustomAction(plugin, new ActionType("gpp-claim-remove", false, false, false, "GPPAction", "removed claim"));
            Prism.getActionRegistry().registerCustomAction(plugin, new ActionType("gpp-claim-enter", false, false, false, "GPPAction", "entered"));
            Prism.getActionRegistry().registerCustomAction(plugin, new ActionType("gpp-claim-exit", false, false, false, "GPPAction", "exited"));
        } catch (InvalidActionException e) {
            e.printStackTrace();
        }
    }
    @EventHandler(priority = EventPriority.MONITOR)
    public void ClaimEnter(ClaimEnterEvent e) {
        Player p = e.getPlayer();
      //Don't log player in own claim
        if (e.getClaim().getOwnerName() == p.getName()) 
            return;
        registerEvent("gpp-claim-enter", p);
    }
    
    @EventHandler(priority = EventPriority.MONITOR)
    public void ClaimExit(ClaimExitEvent e) {
        Player p = e.getPlayer();
        //Don't log player in own claim
        if (e.getClaim().getOwnerName() == p.getName())
        	return;
        registerEvent("gpp-claim-exit", p);
    }
    
    @EventHandler(priority = EventPriority.MONITOR)
    public void ClaimCreate(ClaimCreateEvent e) {
        Player p = e.getPlayer();
        registerEvent("gpp-claim-create", p);
    }
    
    @EventHandler(priority = EventPriority.MONITOR)
    public void ClaimDelete(ClaimDeleteEvent e) {
        Player p = e.getPlayer();
        registerEvent("gpp-claim-remove", p);
    }
    
    void registerEvent(String type, Player p)
    {
    	prismEvent = new PrismCustomPlayerActionEvent(plugin, type, p, "this parameter does absolutely nothing lol");
        plugin.getServer().getPluginManager().callEvent(prismEvent);
    }
}
