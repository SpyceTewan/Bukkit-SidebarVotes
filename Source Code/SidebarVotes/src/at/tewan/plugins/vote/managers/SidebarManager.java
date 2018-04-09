package at.tewan.plugins.vote.managers;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;

import at.tewan.plugins.vote.VoteApplication;
import at.tewan.plugins.vote.util.Cfg;

public class SidebarManager {
	private static Scoreboard scoreboard;
	private static Objective objective;
	private static int line = 0;
	
	public static void display(Player p) {
		if(VoteManager.getCurrentVote() != null) {
			scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
			objective = scoreboard.registerNewObjective("a", "b");
			objective.setDisplayName(Cfg.COLOR_PRIMARY + Cfg.LANG.get("sidebar-title"));
			objective.setDisplaySlot(DisplaySlot.SIDEBAR);
			
			line = 3;
			VoteApplication v = VoteManager.getCurrentVote();
			addLine(Cfg.COLOR_SECONDARY + Cfg.LANG.get("sidebar-name") + Cfg.COLOR_TEXT + v.getName());
			addLine(Cfg.COLOR_SECONDARY + Cfg.LANG.get("sidebar-description") + Cfg.COLOR_TEXT + v.getDescription());
			addLine(Cfg.COLOR_SECONDARY + Cfg.LANG.get("sidebar-votes") + Cfg.COLOR_TEXT + v.getConfirmedVotes() + "/" + v.getNeededVotes());
			
			// Calculating minutes and seconds
			int min = (int) Math.floor(v.getTime() / 60);
			int sec = (int) Math.floor(v.getTime() % 60);
			addLine(Cfg.COLOR_SECONDARY + Cfg.LANG.get("sidebar-time") + Cfg.COLOR_TEXT + min + "m" + sec + "s" );
			
			p.setScoreboard(scoreboard);
		}else {
			p.setScoreboard(Bukkit.getScoreboardManager().getMainScoreboard());
		}
	}
	
	@SuppressWarnings("deprecation")
	private static void addLine(String text) {
		Score s = objective.getScore(Bukkit.getOfflinePlayer(text));
		s.setScore(line--);
	}
}
