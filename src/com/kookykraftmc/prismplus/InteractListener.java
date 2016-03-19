package com.kookykraftmc.prismplus;

import java.util.List;
import java.util.logging.Logger;

import me.botsko.prism.actionlibs.RecordingQueue;
import me.botsko.prism.actions.ItemStackAction;
import me.botsko.prism.events.PrismCustomPlayerActionEvent;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class InteractListener implements Listener
{
	public static List<String> badList;
	public static Prismplus plugin;
	static Logger log = Prismplus.l;
	static String itemName;
	static PrismCustomPlayerActionEvent prismEvent;
	static Player p;
	static ItemStack i;

	public InteractListener(Prismplus prp)
	{
		plugin = prp;
		loadCfg();
	}
	
	@EventHandler(priority = EventPriority.MONITOR)
	public void onClick(PlayerInteractEvent e)
	{
		p = e.getPlayer();
		i = p.getItemInHand();
		itemName = i.getType().name().toLowerCase();
		if(badList.contains(itemName))
		{
			ItemStackAction a = new ItemStackAction();
			a.setLoc(p.getLocation());
			a.setActionType("block-use");
			a.setPlayerName(p.getName());
			a.setItem(i, 1, 0, null);
			RecordingQueue.addToQueue(a);

		}
	}
	
	public static void loadCfg()
	{
		badList = plugin.getConfig().getStringList("LogItems");
		for(String s:badList)
			badList.set(badList.indexOf(s), s.toLowerCase());
	}
}
