package com.kookykraftmc.prismplus.customevents;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

/**
 * Created by TimeTheCat on 3/22/2016.
 */
public class TestEvent extends Event {

    private static final HandlerList handlers = new HandlerList();
    private static Player player;
    public TestEvent(Player p) {
        player = p;
    }
    @Override
    public HandlerList getHandlers() {
        return handlers;
    }
    public static HandlerList getHandlerList() {
        return handlers;
    }
    public static Player getPlayer() {
        return player;
    }

}
