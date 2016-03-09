package com.kookykraftmc.prismplus;

import com.massivecraft.factions.Factions;
import com.massivecraft.factions.entity.Faction;
import com.massivecraft.factions.entity.FactionColl;
import com.massivecraft.factions.event.EventFactionsChunksChange;
import com.massivecraft.factions.event.EventFactionsCreate;
import com.massivecraft.factions.event.EventFactionsDisband;
import org.bukkit.Chunk;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

/**
 * Created by TimeTheCat on 3/8/2016.
 */
public class FactionEvents implements Listener {
    public Prismplus plugin;
    public FactionEvents(Prismplus plugin){
        this.plugin = plugin;
    }
    @EventHandler
    public void factionsCreate(EventFactionsCreate e) {
        Faction f = FactionColl.get().get(e.getFactionId());
        Player p = (Player) e.getMPlayer();
    }
    public void factionsDispand(EventFactionsDisband e) {
        Faction f = FactionColl.get().get(e.getFactionId());
        Player p = (Player) e.getMPlayer();
    }
    public void factionClaim(EventFactionsChunksChange e) {
        Faction f = Faction.get(e.getNewFaction());
        Faction fold = Faction.get(e.getOldChunkFaction());
        Player p = (Player) e.getMPlayer();
    }
}
