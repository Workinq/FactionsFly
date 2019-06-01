package kr.kieran.factionsfly.utilities;

import com.massivecraft.factions.Board;
import com.massivecraft.factions.FPlayer;
import com.massivecraft.factions.FPlayers;
import com.massivecraft.factions.Faction;
import com.massivecraft.factions.struct.Relation;
import kr.kieran.factionsfly.FactionsFly;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class PlayerChecks {

    private FactionsFly plugin;

    public PlayerChecks(FactionsFly plugin) {
        this.plugin = plugin;
    }

    public boolean enemyPlayerNear(final Player player) {
        for (Entity entity : player.getNearbyEntities(Messages.RADIUS, 256.0, Messages.RADIUS)) {
            if (entity instanceof Player) {
                Player enemyPlayer = (Player) entity;
                FPlayer fPlayer = FPlayers.getInstance().getByPlayer(player);
                FPlayer fEnemy = FPlayers.getInstance().getByPlayer(enemyPlayer);
                return !plugin.getManagers().isStealth(enemyPlayer) && fPlayer.getFaction().getRelationTo(fEnemy.getFaction()) == Relation.ENEMY;
            }
        }
        return false;
    }

    public boolean playerInAllowedLand(Player player) {
        FPlayer fPlayer = FPlayers.getInstance().getByPlayer(player);
        Faction faction = Board.getInstance().getFactionAt(fPlayer.getLastStoodAt());
        if (fPlayer.getFaction().isWilderness() || faction.getTag().contains("Warzone") || faction.getTag().contains("Safezone")) {
            return false;
        }
        return (fPlayer.isInOwnTerritory() && player.hasPermission("ffx.faction") || fPlayer.isInAllyTerritory() && player.hasPermission("ffx.ally") || fPlayer.isInNeutralTerritory() && player.hasPermission("ffx.neutral"));
    }

    public void startRunnable(Player player) {
        new BukkitRunnable() {
            int countdown = Messages.COUNTDOWN_TIME;

            @Override
            public void run() {
                if (countdown > 0) {
                    if (playerInAllowedLand(player) && !enemyPlayerNear(player)) {
                        cancel();
                    }
                    player.sendMessage(Messages.FLIGHT_REMOVED.replaceAll("%seconds%", Integer.toString(countdown)));
                    countdown--;
                } else {
                    player.setAllowFlight(false);
                    player.setFlying(false);
                    cancel();
                }
            }
        }.runTaskTimer(plugin, 20, 20);
    }

    public void checkPlayersRunnable() {
        new BukkitRunnable() {
            @Override
            public void run() {
                if (!Messages.FLY_ENABLED) return;
                for (Player online : plugin.getServer().getOnlinePlayers()) {
                    if (!online.isFlying()) return;
                    if (enemyPlayerNear(online)) {
                        plugin.getManagers().countdownRemoveFly(online, "enemy");
                    }
                }
            }
        }.runTaskTimerAsynchronously(plugin, 200, 200);
    }

}
