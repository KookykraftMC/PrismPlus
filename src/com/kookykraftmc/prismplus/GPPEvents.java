package com.kookykraftmc.prismplus;

import me.botsko.prism.Prism;
import net.kaikk.mc.gpp.Claim;
import net.kaikk.mc.gpp.events.*;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;

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
        Location lfrom = e.getFrom();
        Location lto = e.getTo();
        /*
        Not Sure to log claim or not
        Claim c = e.getClaim();
        */
    }
    public void ClaimExit(ClaimExitEvent e) {
        Player p = e.getPlayer();
        Location lfrom = e.getFrom();

        Location lto = e.getTo();
        /*
        Not Sure to log claim or not
        Claim c = e.getClaim();
        */
    }
    public void ClaimCreate(ClaimCreateEvent e) {
        Player p = e.getPlayer();
        /*
        Claim c = e.getClaim();
        */
    }
    public void ClaimDelete(ClaimDeleteEvent e) {
        Player p = e.getPlayer();
        /*
        Claim c = e.getClaim();
        */
    }
    public void ClaimTransfer(ClaimOwnerTransfer e) {
        Player org = e.getPlayer();
        Player newp = Bukkit.getServer().getPlayer(e.getNewOwnerUUID());
        /*
        Claim c = e.getClaim();
         */
    }
    //maybe more?
}
