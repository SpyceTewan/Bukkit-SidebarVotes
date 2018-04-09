package at.tewan.plugins.vote.util;

import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import at.tewan.plugins.vote.managers.VoteManager;
import net.minecraft.server.v1_8_R3.PacketPlayOutChat;
import net.minecraft.server.v1_8_R3.IChatBaseComponent.ChatSerializer;

public class Msg {
	public static void broadcast(String text) {
		Bukkit.broadcastMessage(filter(Cfg.LANG.get(text)));
	}
	
	public static void toPlayer(Player p, String text) {
		p.sendMessage(filter(Cfg.LANG.get(text)));
	}
	
	
	public static String filter(String text) {
		if(text != null) {
			if(VoteManager.getCurrentVote() != null) {
				
				if(text.contains("%votename")) {
					text = text.replace("%votename", VoteManager.getCurrentVote().getName());
				}
				
				if(text.contains("%votedescription")) {
					text = text.replace("%votedescription", VoteManager.getCurrentVote().getDescription());
				}
				
				if(text.contains("%voteorigin")) {
					text = text.replace("%voteorigin", VoteManager.getCurrentVote().getOrigin().getDisplayName());
				}
			}
			
			text = Cfg.COLOR_PRIMARY + Cfg.LABEL_PREFIX + Cfg.COLOR_SECONDARY + Cfg.LABEL_TEXT + Cfg.COLOR_PRIMARY + Cfg.LABEL_SUFFIX + " " + Cfg.COLOR_TEXT + text;
		}
		return text;
	}
	
	public static void sendJSONMessage(Player p, String text) {
		PacketPlayOutChat packet = new PacketPlayOutChat(ChatSerializer.a(text));
		((CraftPlayer) p).getHandle().playerConnection.sendPacket(packet);
	}
}
