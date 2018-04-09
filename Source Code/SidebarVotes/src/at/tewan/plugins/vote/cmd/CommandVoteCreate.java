package at.tewan.plugins.vote.cmd;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import at.tewan.plugins.vote.Permissions;
import at.tewan.plugins.vote.VoteApplication;
import at.tewan.plugins.vote.managers.VoteManager;
import at.tewan.plugins.vote.util.Msg;

public class CommandVoteCreate implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(sender instanceof Player) {
			if(sender.hasPermission(Permissions.VOTE_CREATE)) {
				if(cmd.getName().equalsIgnoreCase("vote-create") && args.length >= 1) {
					VoteApplication v = VoteManager.findVote(args[0]);
								
					if(v != null) {
									
						if(VoteManager.getCurrentVote() == null) {
							VoteApplication newVote = v;
							newVote.clear();
							newVote.setOrigin((Player) sender);
							
							String[] newArgs = new String[args.length - 1];
							System.arraycopy(args, 1, newArgs, 0, args.length - 1);
							
							// Check if vote works
							if(newVote.onCreated(newArgs)) {
								VoteManager.setCurrentVote(newVote);
							}else {
								sender.sendMessage(Msg.filter(v.getInfo()));
								return true;
							}
							
							Msg.toPlayer((Player) sender, "you-started-the-vote");
							VoteManager.notifyEverybody();
						}else {
							Msg.toPlayer((Player) sender, "vote-in-progress");
						}
									
					}else {
						Msg.toPlayer((Player) sender, "vote-doesnt-exist");
					}
								
					return true;
				}
							
			}else {
				Msg.toPlayer((Player) sender, "no-permission");
				return true;
			}
		}
		return false;
	}

}
