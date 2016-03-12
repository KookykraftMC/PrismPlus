package com.kookykraftmc.prismplus.com.kookykraftmc.prismplus.Actions;

import com.kookykraftmc.prismplus.Prismplus;
import me.botsko.prism.events.PrismCustomPlayerActionEvent;
import org.bukkit.entity.Player;

/**
 * Created by TimeTheCat on 3/8/2016.
 */
public class ClaimCreateAction {
    public static void claimCreateAction(Prismplus plugin, Player player){

        if ( plugin.getPrism() != null) {
            PrismCustomPlayerActionEvent prismEvent = new PrismCustomPlayerActionEvent( plugin, "gpp-event", player, null );
            plugin.getServer().getPluginManager().callEvent(prismEvent);
        }
    }
}
