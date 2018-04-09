package at.tewan.plugins.vote;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import at.tewan.plugins.vote.managers.VoteManager;
import at.tewan.plugins.vote.util.Cfg;

public class VoteApplication {
	private String name;
	private String description;
	private String info;
	private Player origin;
	private ArrayList<Player> votedPlayers;
	private int time;

	private int confirmedVotes;
	private int neededVotes;
	
	/**Create a new vote Application */
	public VoteApplication(String name, String description, String info) {
		this.name = name;
		this.description = description;
		this.info = info;
		
		clear();
	}
	
	/**Resets all of the variables */
	public void clear() {
		this.time = Cfg.VOTE_TIME;
		this.confirmedVotes = 0;
		this.votedPlayers = new ArrayList<Player>();
		this.neededVotes = (int) Math.ceil((float)Bukkit.getOnlinePlayers().size() / 2);
	}
	
	/**Is being called when someone tries to create this vote. Return true if successful and false to stop the vote. Arguments are the arguments passed through the /vote-create command. So [0] will be "test" if the input was /vote-create name test. */
	public boolean onCreated(String[] args) {
		return true;
	}
	
	/**Is being called if the vote passes. Probably the most important method */
	public void onAccepted() {
		
	}
	
	/**Is being called if the vote fails. Doesn't have lots of use but you may need it */
	public void onFailed() {
		
	}
	
	/**Confirms that a specific player voted. The boolean describes if they voted yes or no */
	public void vote(Player p, boolean up) {
		if(!votedPlayers.contains(p)) {
			votedPlayers.add(p);
			if(up) {
				this.confirmedVotes++;
			}else {
				this.confirmedVotes--;
			}
			
			VoteManager.update();
			
			if(this.confirmedVotes >= this.neededVotes) {
				VoteManager.voteCompleted(true);
			}
		}
	}
	
	public String getName() {
		return name;
	}
	
	protected void setDescription(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}
	
	public void setOrigin(Player p) {
		this.origin = p;
	}

	public Player getOrigin() {
		return origin;
	}
	
	public ArrayList<Player> getVotedPlayers() {
		return votedPlayers;
	}

	public int getConfirmedVotes() {
		return confirmedVotes;
	}

	public int getNeededVotes() {
		return neededVotes;
	}
	
	public int getTime() {
		return time;
	}

	public void countTime() {
		this.time--;
	}
	
	protected void setInfo(String info) {
		this.info = info;
	}
	
	public String getInfo() {
		return info;
	}

}
