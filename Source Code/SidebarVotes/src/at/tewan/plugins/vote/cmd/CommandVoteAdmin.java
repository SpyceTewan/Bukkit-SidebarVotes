package at.tewan.plugins.vote.cmd;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import at.tewan.plugins.vote.Permissions;
import at.tewan.plugins.vote.managers.VoteManager;
import at.tewan.plugins.vote.util.Msg;

public class CommandVoteAdmin implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(sender instanceof Player) {
			// Commands for admins
			if(sender.hasPermission(Permissions.VOTE_ADMIN)) {
				if(cmd.getName().equalsIgnoreCase("vote-pass")) {
					VoteManager.voteCompleted(true);
				}
							
				if(cmd.getName().equalsIgnoreCase("vote-deny")) {
					VoteManager.voteCompleted(false);
				}
				
				return true;
			}else {
				Msg.toPlayer((Player) sender, "no-permission");
				return true;
			}
		}
		return false;
	}

}
