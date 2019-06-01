package kr.kieran.factionsfly.commands;

import kr.kieran.factionsfly.FactionsFly;
import kr.kieran.factionsfly.utilities.Messages;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class UtilCommand implements CommandExecutor {

    private FactionsFly plugin;

    public UtilCommand(FactionsFly plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!sender.hasPermission("ffx.admin")) {
            sender.sendMessage(Messages.NO_PERMISSION);
            return true;
        }
        if (args.length == 0) {
            sender.sendMessage(Messages.INVALID_ADMIN_USAGE);
            return true;
        }
        if (args[0].equalsIgnoreCase("setflight") && args.length == 2) {
            if (args[1].equalsIgnoreCase("true")) {
                plugin.getConfig().set("ENABLED", true);
                plugin.saveConfig();
                sender.sendMessage(Messages.SERVER_FLIGHT_ENABLED);
            } else if (args[1].equalsIgnoreCase("false")) {
                plugin.getConfig().set("ENABLED", false);
                plugin.saveConfig();
                sender.sendMessage(Messages.SERVER_FLIGHT_DISABLED);
            } else {
                sender.sendMessage(Messages.INVALID_ADMIN_USAGE);
            }
        } else if (args[0].equalsIgnoreCase("reload") && args.length == 1) {
            plugin.reloadConfig();
            sender.sendMessage(Messages.CONFIG_RELOADED);
        } else {
            sender.sendMessage(Messages.INVALID_ADMIN_USAGE);
        }
        return true;
    }

}
