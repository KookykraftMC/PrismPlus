package com.kookykraftmc.prismplus;

import com.massivecraft.factions.event.EventFactionsCreate;
import com.massivecraft.factions.event.EventFactionsDisband;

import me.botsko.prism.Prism;
import me.botsko.prism.actionlibs.ActionType;
import me.botsko.prism.events.PrismCustomPlayerActionEvent;
import me.botsko.prism.exceptions.InvalidActionException;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;


/**
 * Created by TimeTheCat on 3/8/2016.
 */
public class FactionEvents implements Listener {

    PrismCustomPlayerActionEvent prismEvent;
    public Prismplus plugin;
    public FactionEvents(Prismplus plugin){
        this.plugin = plugin;
        try {
            Prism.getActionRegistry().registerCustomAction(plugin, new ActionType("factions-faction-create", false, false, false, "FactionAction", "created faction"));
            Prism.getActionRegistry().registerCustomAction(plugin, new ActionType("factions-faction-delete", false, false, false, "FactionAction", "disbanded faction"));
        } catch (InvalidActionException e) {
            e.printStackTrace();
        }
    }
    @EventHandler(priority = EventPriority.MONITOR)
    public void factionsCreate(EventFactionsCreate e) {
        Player p = Bukkit.getServer().getPlayer(e.getMPlayer().getUuid());
        registerEvent("factions-faction-create", p);
    }
    @EventHandler(priority = EventPriority.MONITOR)
    public void factionsDispand(EventFactionsDisband e) {
        Player p = Bukkit.getServer().getPlayer(e.getMPlayer().getUuid());
        registerEvent("factions-faction-delete", p);
    }
    void registerEvent(String type, Player p)
    {
        prismEvent = new PrismCustomPlayerActionEvent(plugin, type, p, "this parameter does absolutely nothing lol");
        plugin.getServer().getPluginManager().callEvent(prismEvent);
    }
}