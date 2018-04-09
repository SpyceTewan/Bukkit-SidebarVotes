package at.tewan.plugins.vote.applications;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import at.tewan.plugins.vote.VoteApplication;
import at.tewan.plugins.vote.util.Msg;

public class VoteKick extends VoteApplication {
	
	private Player p = null;

	public VoteKick() {
		super("VoteKick", "Kick a player", "Usage: /vote-create votekick <Player>");
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public boolean onCreated(String[] args) {
		try {
			p = Bukkit.getPlayer(args[0]);
			this.setDescription("Kick Player: " + p.getDisplayName());
			return true;
		}catch(NullPointerException e) {
			
			getOrigin().sendMessage(Msg.filter("Player " + args[0] + " does not exist"));
			return false;
		}
	}
	
	@Override
	public void onAccepted() {
		p.kickPlayer("You have been voted to be kicked");
	}

}
