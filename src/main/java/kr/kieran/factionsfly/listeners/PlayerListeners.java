package kr.kieran.factionsfly.listeners;

import kr.kieran.factionsfly.FactionsFly;
import kr.kieran.factionsfly.utilities.Messages;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.*;

public class PlayerListeners implements Listener {

    private FactionsFly plugin;

    public PlayerListeners(FactionsFly plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        if (!Messages.FLY_ENABLED) return;
        Player player = event.getPlayer();
        if (plugin.getChecks().playerInAllowedLand(player)) {
            player.sendMessage(Messages.AUTO_FLIGHT);
            plugin.getManagers().addFly(player);
        }
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        if (!Messages.FLY_ENABLED) return;
        Player player = event.getPlayer();
        plugin.getManagers().removeFly(player);
    }

    @EventHandler
    public void onPlayerKick(PlayerKickEvent event) {
        if (!Messages.FLY_ENABLED) return;
        Player player = event.getPlayer();
        plugin.getManagers().removeFly(player);
    }

    @EventHandler
    public void onPlayerPearl(PlayerInteractEvent event) {
        if (!Messages.FLY_ENABLED) return;
        if (!Messages.ENDERPEARL_CHECK) return;
        Player player = event.getPlayer();
        if (!player.isFlying()) return;
        if (event.getAction().equals(Action.RIGHT_CLICK_AIR)) {
            if (event.getMaterial() == Material.ENDER_PEARL) {
                event.setUseItemInHand(Event.Result.DENY);
                player.sendMessage(Messages.ENDERPEARL);
            }
        }
    }

    @EventHandler
    public void onUpdateLand(PlayerMoveEvent event) {
        if (!Messages.FLY_ENABLED) return;
        Player player = event.getPlayer();
        if (player.getGameMode() != GameMode.SURVIVAL || player.hasPermission("ffx.admin") || player.hasPermission("ffx.all"))
            return;
        if (!plugin.getChecks().playerInAllowedLand(player)) {
            plugin.getManagers().countdownRemoveFly(player, "land");
        } else if (plugin.getChecks().enemyPlayerNear(player)) {
            plugin.getManagers().countdownRemoveFly(player, "enemy");
        } else {
            plugin.getManagers().addFly(player);
        }
    }

    @EventHandler
    public void onPlayerFlightDamage(PlayerMoveEvent event) {
        if (!Messages.FLY_ENABLED) return;
        Player player = event.getPlayer();
        if (plugin.getManagers().hasNoFall(player) && player.isOnGround()) {
            plugin.getManagers().removeNoFall(player);
        }
    }

    @EventHandler
    public void onTakeDamage(EntityDamageEvent event) {
        if (!Messages.FLY_ENABLED) return;
        if (!(event.getEntity() instanceof Player)) return;
        Player player = (Player) event.getEntity();
        if (event.getCause() == EntityDamageEvent.DamageCause.FALL && plugin.getManagers().hasNoFall(player)) {
            event.setCancelled(true);
            plugin.getManagers().removeNoFall(player);
        }
    }

}
