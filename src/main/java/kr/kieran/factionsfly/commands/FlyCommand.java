package kr.kieran.factionsfly.commands;

import kr.kieran.factionsfly.FactionsFly;
import kr.kieran.factionsfly.utilities.Messages;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class FlyCommand implements CommandExecutor {

    private FactionsFly plugin;

    public FlyCommand(FactionsFly plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(Messages.NOT_A_PLAYER);
            return true;
        }
        if (!Messages.FLY_ENABLED && sender.hasPermission("ffx.admin")) {
            sender.sendMessage(Messages.FLIGHT_TEMPORARILY_DISABLED);
            return true;
        }
        Player player = (Player) sender;
        if (!player.hasPermission("ffx.user")) {
            player.sendMessage(Messages.NO_PERMISSION);
            return true;
        }
        if (args.length == 0) {
            if (plugin.getChecks().playerInAllowedLand(player)) {
                if (plugin.getChecks().enemyPlayerNear(player)) {
                    player.sendMessage(Messages.ENEMY_NEARBY);
                    return true;
                }
                if (!plugin.getManagers().canFly(player)) {
                    plugin.getManagers().addFly(player);
                    player.sendMessage(Messages.FLIGHT_ENABLED);
                    return true;
                }
                plugin.getManagers().removeFly(player);
                player.sendMessage(Messages.FLIGHT_DISABLED);
            } else {
                player.sendMessage(Messages.DISALLOWED_LAND);
            }
        }
        return true;
    }

}
