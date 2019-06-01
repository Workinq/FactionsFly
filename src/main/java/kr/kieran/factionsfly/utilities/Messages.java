package kr.kieran.factionsfly.utilities;

import kr.kieran.factionsfly.FactionsFly;
import org.bukkit.ChatColor;

public class Messages {

    public static String NO_PERMISSION = ChatColor.translateAlternateColorCodes('&', FactionsFly.getInstance().getConfig().getString("MESSAGES.NO_PERMISSION"));
    public static String NOT_A_PLAYER = ChatColor.translateAlternateColorCodes('&', FactionsFly.getInstance().getConfig().getString("MESSAGES.NOT_A_PLAYER"));
    public static String FLIGHT_ENABLED = ChatColor.translateAlternateColorCodes('&', FactionsFly.getInstance().getConfig().getString("MESSAGES.FLIGHT_ENABLED"));
    public static String FLIGHT_DISABLED = ChatColor.translateAlternateColorCodes('&', FactionsFly.getInstance().getConfig().getString("MESSAGES.FLIGHT_DISABLED"));
    public static String STEALTH_ENABLED = ChatColor.translateAlternateColorCodes('&', FactionsFly.getInstance().getConfig().getString("MESSAGES.STEALTH_ENABLED"));
    public static String STEALTH_DISABLED = ChatColor.translateAlternateColorCodes('&', FactionsFly.getInstance().getConfig().getString("MESSAGES.STEALTH_DISABLED"));
    public static String NOT_IN_FACTION = ChatColor.translateAlternateColorCodes('&', FactionsFly.getInstance().getConfig().getString("MESSAGES.NOT_IN_FACTION"));
    public static String DISALLOWED_LAND = ChatColor.translateAlternateColorCodes('&', FactionsFly.getInstance().getConfig().getString("MESSAGES.DISALLOWED_LAND"));
    public static String ENDERPEARL = ChatColor.translateAlternateColorCodes('&', FactionsFly.getInstance().getConfig().getString("MESSAGES.ENDERPEARL"));
    public static String ENEMY_NEARBY = ChatColor.translateAlternateColorCodes('&', FactionsFly.getInstance().getConfig().getString("MESSAGES.ENEMY_NEARBY"));
    public static String AUTO_FLIGHT = ChatColor.translateAlternateColorCodes('&', FactionsFly.getInstance().getConfig().getString("MESSAGES.AUTO_FLIGHT"));
    public static String FLIGHT_REMOVED = ChatColor.translateAlternateColorCodes('&', FactionsFly.getInstance().getConfig().getString("MESSAGES.FLIGHT_REMOVED"));
    public static String INVALID_ADMIN_USAGE = ChatColor.translateAlternateColorCodes('&', FactionsFly.getInstance().getConfig().getString("MESSAGES.INVALID_ADMIN_USAGE"));
    public static String FLIGHT_TEMPORARILY_DISABLED = ChatColor.translateAlternateColorCodes('&', FactionsFly.getInstance().getConfig().getString("MESSAGES.FLIGHT_TEMPORARILY_DISABLED"));
    public static String CONFIG_RELOADED = ChatColor.translateAlternateColorCodes('&', FactionsFly.getInstance().getConfig().getString("MESSAGES.CONFIG_RELOADED"));
    public static String SERVER_FLIGHT_ENABLED = ChatColor.translateAlternateColorCodes('&', FactionsFly.getInstance().getConfig().getString("MESSAGES.SERVER_FLIGHT_ENABLED"));
    public static String SERVER_FLIGHT_DISABLED = ChatColor.translateAlternateColorCodes('&', FactionsFly.getInstance().getConfig().getString("MESSAGES.SERVER_FLIGHT_DISABLED"));
    public static int COUNTDOWN_TIME = FactionsFly.getInstance().getConfig().getInt("COUNTDOWN_TIME");
    public static double RADIUS = FactionsFly.getInstance().getConfig().getInt("RADIUS");
    public static boolean ENDERPEARL_CHECK = FactionsFly.getInstance().getConfig().getBoolean("ENDERPEARL_CHECK");
    public static boolean FLY_ENABLED = FactionsFly.getInstance().getConfig().getBoolean("ENABLED");

}
