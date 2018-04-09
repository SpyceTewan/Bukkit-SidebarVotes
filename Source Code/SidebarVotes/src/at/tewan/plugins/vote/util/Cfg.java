package at.tewan.plugins.vote.util;

import java.util.HashMap;

import at.tewan.plugins.vote.Main;

public class Cfg {
	
	public static String CONSOLE_PREFIX = "[SidebarVotes] ";
	public static String COLOR_PRIMARY;
	public static String COLOR_SECONDARY;
	public static String COLOR_TEXT;
	public static String COLOR_POSITIVE;
	public static String COLOR_NEGATIVE;
	public static String LABEL_PREFIX;
	public static String LABEL_SUFFIX;
	public static String LABEL_TEXT;
	public static int VOTE_TIME;
	public static HashMap<String, String> LANG;
	
	private static Main main;
	
	public static void init(Main main) {
		Cfg.main = main;
		LANG = new HashMap<String, String>();
		
		// Loading several things from config.yml
		COLOR_PRIMARY = load("appearance.color.primary");
		COLOR_SECONDARY = load("appearance.color.secondary");
		COLOR_TEXT = load("appearance.color.text");
		COLOR_POSITIVE = load("appearance.color.positive");
		COLOR_NEGATIVE = load("appearance.color.negative");
		
		LABEL_PREFIX = load("appearance.label.prefix");
		LABEL_SUFFIX = load("appearance.label.suffix");
		LABEL_TEXT = load("appearance.label.name");
		
		VOTE_TIME = main.getConfig().getInt("settings.vote.time");
		
		// Loading language string from config.yml
		loadLang("already-voted");
		loadLang("vote-in-progress");
		loadLang("string-yes");
		loadLang("string-no");
		loadLang("you-voted");
		loadLang("you-started-the-vote");
		loadLang("person-voted");
		loadLang("do-you-accept");
		loadLang("vote-failed");
		loadLang("vote-accepted");
		loadLang("vote-doesnt-exist");
		loadLang("vote-list-hint");
		loadLang("no-permission");
		loadLang("sidebar-title");
		loadLang("sidebar-name");
		loadLang("sidebar-description");
		loadLang("sidebar-votes");
		loadLang("sidebar-time");
		loadLang("info-title");
		loadLang("info-name");
		loadLang("info-description");
		loadLang("info-info");
		loadLang("info-hover");
		loadLang("no-vote");
	}
	
	private static String load(String str) {
		return main.getConfig().getString(str);
	}
	
	private static void loadLang(String str) {
		LANG.put(str, load("lang." + str));
		
		// System.out.println(str + " = " + load("lang." + str));
	}
}
