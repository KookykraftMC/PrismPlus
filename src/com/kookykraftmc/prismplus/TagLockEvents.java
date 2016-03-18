package com.kookykraftmc.prismplus;

import me.botsko.prism.Prism;
import me.botsko.prism.actionlibs.ActionType;
import me.botsko.prism.events.PrismCustomPlayerActionEvent;
import me.botsko.prism.exceptions.InvalidActionException;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;

/**
 * Created by TimeTheCat on 3/18/2016.
 */
public class TagLockEvents implements Listener {
    PrismCustomPlayerActionEvent prismEvent;
    public Prismplus plugin;
    public TagLockEvents(Prismplus prismplus) {
        this.plugin = plugin;
        try {
            Prism.getActionRegistry().registerCustomAction(plugin, new ActionType("taglock", false, false, false, "TagLockAction", "taglocked someone"));
        } catch (InvalidActionException e) {
            e.printStackTrace();
        }
    }
    @EventHandler (priority = EventPriority.MONITOR)
    public void onTagLock(PlayerInteractEntityEvent e) {
        if (e.getRightClicked().getType() == EntityType.PLAYER && e.getPlayer().getItemInHand().getType().toString() == "WITCHERY_TAGLOCKKIT" && e.isCancelled() == false) {
            Player p = e.getPlayer();
            registerEvent("taglock", p);
        }
    }
    void registerEvent(String type, Player p)
    {
        prismEvent = new PrismCustomPlayerActionEvent(plugin, type, p, "TagLocked");
        plugin.getServer().getPluginManager().callEvent(prismEvent);
    }
}
