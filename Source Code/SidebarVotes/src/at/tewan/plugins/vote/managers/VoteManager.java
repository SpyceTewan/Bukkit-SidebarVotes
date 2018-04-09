package at.tewan.plugins.vote.managers;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import at.tewan.plugins.vote.Main;
import at.tewan.plugins.vote.VoteApplication;
import at.tewan.plugins.vote.util.Cfg;
import at.tewan.plugins.vote.util.Msg;


public class VoteManager {
	
	private static ArrayList<VoteApplication> list;
	private static VoteApplication currentVote;
	
	public static void init(Main main) {
		list = new ArrayList<VoteApplication>();
		currentVote = null;
		
		Bukkit.getScheduler().scheduleSyncRepeatingTask(main, new Runnable() {
			
			@Override
			public void run() {
				if(currentVote != null) {
					currentVote.countTime();
				
					update();
				}
			}
			
		}, 0, 20);
	}
	
	public static void register(VoteApplication vote) {
		list.add(vote);
		
		System.out.println("[SidebarVotes] Added Vote Application: " + vote.getName());
	}
	
	public static ArrayList<VoteApplication> getVoteList() {
		return list;
	}
	
	public static VoteApplication findVote(String name) {
		for(VoteApplication v : list) {
			if(name.equalsIgnoreCase(v.getName())) {
				return v;
			}
		}
		
		return null;
	}
	
	public static void notifyEverybody() {
		Bukkit.broadcastMessage("==============================================");
		Msg.broadcast("person-voted");
		Msg.broadcast("do-you-accept");
		for(Player p : Bukkit.getOnlinePlayers()) {
			String message = "{\r\n" + 
					"	\"text\": \"                    \", \r\n" + 
					"	\"extra\": [\r\n" + 
					"		{\r\n" + 
					"			\"text\": \"[" + Cfg.LANG.get("string-yes") + "]\", \r\n" + 
					"			\"color\": \"green\", \r\n" + 
					"			\"clickEvent\": {\r\n" + 
					"				\"action\": \"run_command\", \r\n" + 
					"				\"value\": \"/vote-yes\"\r\n" + 
					"			}\r\n" + 
					"		}, \r\n" + 
					"		{\r\n" + 
					"			\"text\": \"    \"\r\n" + 
					"		}, \r\n" + 
					"		{\r\n" + 
					"			\"text\": \"[" + Cfg.LANG.get("string-no") + "]\", \r\n" + 
					"			\"color\": \"red\", \r\n" + 
					"			\"clickEvent\": {\r\n" + 
					"				\"action\": \"run_command\", \r\n" + 
					"				\"value\": \"/vote-no\"\r\n" + 
					"			}\r\n" + 
					"		}\r\n" + 
					"	]\r\n" + 
					"}";
			Msg.sendJSONMessage(p, message);
		}
		
		Bukkit.broadcastMessage("==============================================");
	}
	
	public static void voteCompleted(boolean status) {
		if(status) {
			Msg.broadcast("vote-accepted");
			currentVote.onAccepted();
		}else {
			Msg.broadcast("vote-failed");
			currentVote.onFailed();
		}
		
		// Reset the vote
		currentVote = null;
		
		// Update the sidebar for all of the players
		for(Player p : Bukkit.getOnlinePlayers()) {
			SidebarManager.display(p);
		}
	}
	
	public static void update() {
		for(Player p : Bukkit.getOnlinePlayers()) {
			SidebarManager.display(p);
		}
		
		if(currentVote.getTime() == 0) {
			voteCompleted(false);
		}
	}
	
	public static VoteApplication getCurrentVote() {
		return currentVote;
	}

	public static void setCurrentVote(VoteApplication v) {
		VoteManager.currentVote = v;
	}
	
	

}
