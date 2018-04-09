package at.tewan.plugins.vote.util;

public class ColorUtils {
	public static String convertCodeToName(String code) {
		switch(code) {
			
			case "§0": return "black";
			case "§1": return "dark_blue";
			case "§2": return "dark_green";
			case "§3": return "dark_aqua";
			case "§4": return "dark_red";
			case "§5": return "dark_purple";
			case "§6": return "gold";
			case "§7": return "gray";
			case "§8": return "light_gray";
			case "§9": return "blue";
			case "§a": return "green";
			case "§b": return "aqua";
			case "§c": return "red";
			case "§d": return "light_purple";
			case "§e": return "yellow";
			case "§f": return "white";
			
			default: return null;
		}
	}
}
