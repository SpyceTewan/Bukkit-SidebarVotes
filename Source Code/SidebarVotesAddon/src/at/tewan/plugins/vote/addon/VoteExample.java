package at.tewan.plugins.vote.addon;

import org.bukkit.Bukkit;

import at.tewan.plugins.vote.VoteApplication;
import at.tewan.plugins.vote.util.Msg;

public class VoteExample extends VoteApplication { // The class needs to extend from VoteApplication

	public VoteExample() { // Constructor arguments should be deleted
		
		// In super() you give the VoteApplication info about itself. A name, description and info.
		// Name: The name of the vote app. Will be used for the command. So /vote-create example will run this vote
		// Description: Will show when you type /vote-list and in the sidebar
		// Info: Will show when you returned false in onCreated(), so you should write how to use this VoteApplication
		super("Example", "Example for GitHub", "Usage: /vote-create example 1234");
	}
	
	@Override
	public boolean onCreated(String[] args) {	// This method will be called before the Vote get live.
												// You must return true to activate it.
												// When you return false, the vote is being cancelled and the origin player get the message in the info
		if(args.length > 0) {
			return args[0].equals("1234"); 		// Returns true if the first argument is 1234
			
			/*if(args[0].equals("1234")) {		   New programmers may understand this better
				return true;					   <====
			}else {
				return false;
			}*/
		}else {
			return false;
		}
	}
	
	
	@Override
	public void onAccepted() {					// This method is being called, if the vote passes
		
		// Normal Bukkit broadast
		Bukkit.broadcastMessage("Example text without filtering");		
		
		// Msg.filter(); will add the [SidebarVotes] prefix with the according color codes
		// You may want to use Msg.filter(); to make your text more pretty
		Bukkit.broadcastMessage(Msg.filter("Example with text filtering"));	
	}
	
	@Override
		public void onFailed() {				// Is being called in case the vote fails.
												// This does not have a lot of practical use but is here just in case.
			getOrigin().sendMessage(Msg.filter("Better luck next time! :/"));
		}
	
	/* Some useful methods in the VoteApplication class
	 * getOrigin() Returns the Player that started the vote
	 * setDescription(String text) Changes the description. Can be used to change the text in the sidebar to give dynamic information 
	 * getVotedPlayers() Returns an array of the players that have voted (yes and no)
	 */

}
