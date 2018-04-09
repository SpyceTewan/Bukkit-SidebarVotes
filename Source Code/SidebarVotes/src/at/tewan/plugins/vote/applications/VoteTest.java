package at.tewan.plugins.vote.applications;

import org.bukkit.Bukkit;

import at.tewan.plugins.vote.VoteApplication;

public class VoteTest extends VoteApplication {
	
	
	public VoteTest() {
		super("Test", "Debug plugin", "/vote-create test <Message to echo back>");
	}
	
	@Override
	public boolean onCreated(String[] args) {
		if(args.length >= 1) {
			Bukkit.broadcastMessage(args[0]);
			
			return true;
		}else {
			return false;
		}
	}
	
	@Override
	public void onAccepted() {
		Bukkit.broadcastMessage("Hello, World!");
	}
}
