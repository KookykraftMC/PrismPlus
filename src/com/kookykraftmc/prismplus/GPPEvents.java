package com.kookykraftmc.prismplus;

import com.kookykraftmc.prismplus.actions.ClaimCreateAction;
import com.kookykraftmc.prismplus.actions.ClaimEnterAction;
import com.kookykraftmc.prismplus.actions.ClaimExitAction;
import com.kookykraftmc.prismplus.actions.ClaimRemoveAction;
import net.kaikk.mc.gpp.events.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

/**
 * Created by TimeTheCat on 3/8/2016.
 */
public class GPPEvents implements Listener {

    public Prismplus plugin;
    public GPPEvents(Prismplus plugin){
        this.plugin = plugin;
    }


    @EventHandler
    public void ClaimEnter(ClaimEnterEvent e) {
        Player p = e.getPlayer();
        if (e.getClaim().getOwnerName() == p.getName()) {
            //Don't want to log if the player enters their own claim for now
        } else {
            ClaimEnterAction.claimEnterAction(plugin, p);
        }
    }
    public void ClaimExit(ClaimExitEvent e) {
        Player p = e.getPlayer();
        if (e.getClaim().getOwnerName() == p.getName()) {
            //Don't log
        } else {
            ClaimExitAction.claimExitAction(plugin, p);
        }
    }
    public void ClaimCreate(ClaimCreateEvent e) {
        Player p = e.getPlayer();
        ClaimCreateAction.claimCreateAction(plugin, p);
    }
    public void ClaimDelete(ClaimDeleteEvent e) {
        Player p = e.getPlayer();
        ClaimRemoveAction.claimDeleteAction(plugin, p);
    }
}
