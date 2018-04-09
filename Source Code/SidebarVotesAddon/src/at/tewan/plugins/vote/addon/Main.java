package at.tewan.plugins.vote.addon;

import org.bukkit.plugin.java.JavaPlugin;

import at.tewan.plugins.vote.managers.VoteManager;

public class Main extends JavaPlugin {
	
	// Addons to SidebarVotes are entire stand-alone plugins (that require SidebarVotes of course)
	
	@Override
	public void onEnable() {
		VoteManager.register(new VoteExample()); // This is how you register the VoteApplication to the VoteManager
	}
}
