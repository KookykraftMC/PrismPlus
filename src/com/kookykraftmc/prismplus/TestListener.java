package com.kookykraftmc.prismplus;

import com.kookykraftmc.prismplus.customevents.TestEvent;
import me.botsko.prism.Prism;
import me.botsko.prism.actionlibs.ActionType;
import me.botsko.prism.events.PrismCustomPlayerActionEvent;
import me.botsko.prism.exceptions.InvalidActionException;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

/**
 * Created by TimeTheCat on 3/22/2016.
 */
public class TestListener implements Listener {
    PrismCustomPlayerActionEvent prismEvent;
    public Prismplus plugin;
    public TestListener(Prismplus plugin){
        this.plugin = plugin;
        try {
            Prism.getActionRegistry().registerCustomAction(plugin, new ActionType("test-event", false, false, false, "TestingAction", "test successful"));
        } catch (InvalidActionException e) {
            e.printStackTrace();
        }
    }

    @EventHandler
    public void onTestEvent(TestEvent e) {
        Player p = e.getPlayer();
        registerEvent("test-event", p);
    }
    void registerEvent(String type, Player p)
    {
        prismEvent = new PrismCustomPlayerActionEvent(plugin, type, p, "this parameter does absolutely nothing lol");
        plugin.getServer().getPluginManager().callEvent(prismEvent);
    }
}
