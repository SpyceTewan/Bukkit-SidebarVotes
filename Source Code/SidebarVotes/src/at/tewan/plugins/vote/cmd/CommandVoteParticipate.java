package at.tewan.plugins.vote.cmd;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import at.tewan.plugins.vote.Permissions;
import at.tewan.plugins.vote.managers.VoteManager;
import at.tewan.plugins.vote.util.Cfg;
import at.tewan.plugins.vote.util.Msg;

public class CommandVoteParticipate implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(sender instanceof Player) {
			
			if(sender.hasPermission(Permissions.VOTE_PARTICIPATE)) {								// PERMISSION CHECK
				if(VoteManager.getCurrentVote() != null) {											// EXISTANCE CHECK
					if(!VoteManager.getCurrentVote().getVotedPlayers().contains((Player) sender)) {	// DOUBLE VOTE CHECK
						
						// Yes voted
						if(cmd.getName().equalsIgnoreCase("vote-yes")) {
							sender.sendMessage(Msg.filter(Cfg.LANG.get("you-voted") + Cfg.COLOR_POSITIVE + Cfg.LANG.get("string-yes")));
							VoteManager.getCurrentVote().vote((Player) sender, true);
						}
						
						// No voted
						if(cmd.getName().equalsIgnoreCase("vote-no")) {
							sender.sendMessage(Msg.filter(Cfg.LANG.get("you-voted") + Cfg.COLOR_NEGATIVE + Cfg.LANG.get("string-no")));
							VoteManager.getCurrentVote().vote((Player) sender, false);
						}
						
					}else {
						Msg.toPlayer((Player) sender, "already-voted");	// ERROR MESSAGE: Already voted
					}
				}else {
					Msg.toPlayer((Player) sender, "no-vote");	// ERROR MESSAGE: No vote in progress
				}
			}else {
				Msg.toPlayer((Player) sender, "no-permission");			// ERROR MESSAGE: No permission
			}
		}
		
		return true;
	}

}
