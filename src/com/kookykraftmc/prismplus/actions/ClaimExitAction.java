package com.kookykraftmc.prismplus.actions;

import com.kookykraftmc.prismplus.Prismplus;
import me.botsko.prism.events.PrismCustomPlayerActionEvent;
import org.bukkit.entity.Player;

/**
 * Created by TimeTheCat on 3/11/2016.
 */
public class ClaimExitAction {
    public static void claimExitAction(Prismplus plugin, Player player){
        if ( plugin.getPrism() != null) {
            PrismCustomPlayerActionEvent prismEvent = new PrismCustomPlayerActionEvent( plugin, "gpp-event", player, null );
            plugin.getServer().getPluginManager().callEvent(prismEvent);
        }
    }
}
