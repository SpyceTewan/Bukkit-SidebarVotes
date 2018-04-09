package at.tewan.plugins.vote;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import at.tewan.plugins.vote.applications.VoteKick;
import at.tewan.plugins.vote.applications.VoteTest;
import at.tewan.plugins.vote.cmd.CommandVoteAdmin;
import at.tewan.plugins.vote.cmd.CommandVoteCreate;
import at.tewan.plugins.vote.cmd.CommandVoteInfo;
import at.tewan.plugins.vote.cmd.CommandVoteParticipate;
import at.tewan.plugins.vote.cmd.CommandVoteWatchdog;
import at.tewan.plugins.vote.managers.VoteManager;
import at.tewan.plugins.vote.util.Cfg;

public class Main extends JavaPlugin {
	
	@Override
	public void onEnable() {
		System.out.println(Cfg.CONSOLE_PREFIX + "Initializing...");			
			for(Player p : Bukkit.getOnlinePlayers()) {
				p.setScoreboard(Bukkit.getScoreboardManager().getMainScoreboard());
			}
			CommandVoteCreate cmdCreate = new CommandVoteCreate();
			CommandVoteInfo cmdInfo = new CommandVoteInfo();
			CommandVoteParticipate cmdParticipate = new CommandVoteParticipate();
			CommandVoteAdmin cmdAdmin = new CommandVoteAdmin();
			CommandVoteWatchdog cmdWatchdog = new CommandVoteWatchdog();
			
			getCommand("vote-create").setExecutor(cmdCreate);
			getCommand("vote-list").setExecutor(cmdInfo);
			getCommand("vote-info").setExecutor(cmdInfo);;
			getCommand("vote-yes").setExecutor(cmdParticipate);
			getCommand("vote-no").setExecutor(cmdParticipate);
			getCommand("vote-pass").setExecutor(cmdAdmin);
			getCommand("vote-deny").setExecutor(cmdAdmin);
			getCommand("vote-watchdog").setExecutor(cmdWatchdog);
			
		System.out.println(Cfg.CONSOLE_PREFIX + "Done!");
		
		
		System.out.println(Cfg.CONSOLE_PREFIX + "Loading config...");
			getConfig().options().copyDefaults(true);
			saveConfig();
			Cfg.init(this);
		System.out.println(Cfg.CONSOLE_PREFIX + "Done!");
		
		
		System.out.println(Cfg.CONSOLE_PREFIX + "Loading Vote Applications...");
			VoteManager.init(this);
			
			// Adding default vote applications
			if(getConfig().getBoolean("settings.load-default-applications")) {
				System.out.println(Cfg.CONSOLE_PREFIX + "Loading default applications...");
				VoteManager.register(new VoteTest());
				VoteManager.register(new VoteKick());
			}
			
		System.out.println(Cfg.CONSOLE_PREFIX + "Done!");
	}
	
	@Override
	public void onDisable() {
		
	}
}
