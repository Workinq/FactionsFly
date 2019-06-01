package kr.kieran.factionsfly.managers;

import kr.kieran.factionsfly.FactionsFly;
import kr.kieran.factionsfly.utilities.Messages;
import org.bukkit.entity.Player;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class PlayerManager {

    private FactionsFly plugin;
    private Set<UUID> stealthPlayers;
    private Set<UUID> canFlyPlayers;
    private Set<UUID> noFallPlayers;

    public PlayerManager(FactionsFly plugin) {
        this.plugin = plugin;
        this.stealthPlayers = new HashSet<>();
        this.canFlyPlayers = new HashSet<>();
        this.noFallPlayers = new HashSet<>();
    }

    public void addStealth(Player player) {
        if (!isStealth(player)) {
            stealthPlayers.add(player.getUniqueId());
            player.sendMessage(Messages.STEALTH_ENABLED);
        }
    }

    public void removeStealth(Player player) {
        if (isStealth(player)) {
            player.sendMessage(Messages.STEALTH_DISABLED);
            stealthPlayers.remove(player.getUniqueId());
        }
    }

    public void addFly(Player player) {
        if (!canFly(player)) {
            canFlyPlayers.add(player.getUniqueId());
            player.setAllowFlight(true);
            player.setFlying(true);
        }
    }

    public void removeFly(Player player) {
        if (canFly(player)) {
            player.setAllowFlight(false);
            player.setFlying(false);
            addNoFall(player);
            canFlyPlayers.remove(player.getUniqueId());
        }
    }

    public void countdownRemoveFly(Player player, String reason) {
        if (canFly(player)) {
            if (reason.equals("land")) {
                player.sendMessage(Messages.DISALLOWED_LAND);
            } else {
                player.sendMessage(Messages.ENEMY_NEARBY);
            }
            plugin.getChecks().startRunnable(player);
            addNoFall(player);
            canFlyPlayers.remove(player.getUniqueId());
        }
    }

    private void addNoFall(Player player) {
        if (!hasNoFall(player)) {
            noFallPlayers.add(player.getUniqueId());
        }
    }

    public void removeNoFall(Player player) {
        if (hasNoFall(player)) {
            noFallPlayers.remove(player.getUniqueId());
        }
    }

    public boolean isStealth(Player player) {
        return stealthPlayers.contains(player.getUniqueId());
    }

    public boolean hasNoFall(Player player) {
        return noFallPlayers.contains(player.getUniqueId());
    }

    public boolean canFly(Player player) {
        return canFlyPlayers.contains(player.getUniqueId());
    }

    public void onDisable() {
        stealthPlayers.clear();
        canFlyPlayers.clear();
        noFallPlayers.clear();
    }

}
