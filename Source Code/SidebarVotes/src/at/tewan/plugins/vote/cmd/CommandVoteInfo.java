package at.tewan.plugins.vote.cmd;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import at.tewan.plugins.vote.VoteApplication;
import at.tewan.plugins.vote.managers.VoteManager;
import at.tewan.plugins.vote.util.Cfg;
import at.tewan.plugins.vote.util.ColorUtils;
import at.tewan.plugins.vote.util.Msg;

public class CommandVoteInfo implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		// Command to view all possible votes
		if(cmd.getName().equalsIgnoreCase("vote-list")) {
			
			Msg.toPlayer((Player) sender, "vote-list-hint");
			for(int i = 0; i < VoteManager.getVoteList().size(); i++) {
				VoteApplication v = VoteManager.getVoteList().get(i);
				
				String s = "{\r\n" + 
						"	\"text\": \"\",\r\n" + 
						"	\"extra\": [\r\n" + 
						"		{\r\n" + 
						"			\"text\": \"%name: \",\r\n" + 
						"			\"color\": \"%color1\",\r\n" + 
						"			\"clickEvent\": {\r\n" + 
						"				\"action\": \"suggest_command\",\r\n" + 
						"				\"value\": \"/vote-create %name\"\r\n" + 
						"			},\r\n" + 
						"			\"hoverEvent\": {\r\n" + 
						"				\"action\": \"show_text\",\r\n" + 
						"				\"value\": \"%hover\"\r\n" + 
						"			}\r\n" + 
						"		},\r\n" + 
						"		{\r\n" + 
						"			\"text\": \"%description\",\r\n" + 
						"			\"color\": \"%color2\"\r\n" + 
						"		}\r\n" + 
						"	]\r\n" + 
						"}";
				
				s = s.replace("%index", i + ": ");
				s = s.replace("%name", v.getName());
				s = s.replace("%description", v.getDescription());
				s = s.replace("%hover", Cfg.LANG.get("info-hover"));
				s = s.replace("%color1", ColorUtils.convertCodeToName(Cfg.COLOR_PRIMARY));
				s = s.replace("%color2", ColorUtils.convertCodeToName(Cfg.COLOR_TEXT));
				
				Msg.sendJSONMessage((Player) sender, s);
			}
									
			return true;
		}
					
		if(cmd.getName().equalsIgnoreCase("vote-info")) {
			if(args.length == 1) {
				VoteApplication v = VoteManager.findVote(args[0]);
				
				if(v != null) {
					
					sender.sendMessage(Msg.filter(Cfg.LANG.get("info-title") + v.getName()));
					sender.sendMessage(Msg.filter(Cfg.COLOR_SECONDARY + Cfg.LANG.get("info-name") + Cfg.COLOR_TEXT + v.getName()));
					sender.sendMessage(Msg.filter(Cfg.COLOR_SECONDARY + Cfg.LANG.get("info-description") + Cfg.COLOR_TEXT + v.getDescription()));
					sender.sendMessage(Msg.filter(Cfg.COLOR_SECONDARY + Cfg.LANG.get("info-info") + Cfg.COLOR_TEXT + v.getInfo()));
					
				}else {
					Msg.toPlayer((Player) sender, "vote-doesnt-exist");
				}
				
				return true;
			}
		}
		return false;
	}

}
