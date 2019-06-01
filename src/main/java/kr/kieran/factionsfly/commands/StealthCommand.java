package kr.kieran.factionsfly.commands;

import com.massivecraft.factions.FPlayer;
import com.massivecraft.factions.FPlayers;
import com.massivecraft.factions.Faction;
import kr.kieran.factionsfly.FactionsFly;
import kr.kieran.factionsfly.utilities.Messages;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class StealthCommand implements Listener {

    private FactionsFly plugin;

    public StealthCommand(FactionsFly plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onFStealth(PlayerCommandPreprocessEvent event) {
        if (!Messages.FLY_ENABLED) return;
        if (event.getMessage().startsWith("/f stealth")) {
            event.setCancelled(true);
            Player player = event.getPlayer();
            FPlayer fPlayer = FPlayers.getInstance().getByPlayer(player);
            Faction faction = fPlayer.getFaction();
            if (faction.isWilderness()) {
                player.sendMessage(Messages.NOT_IN_FACTION);
                return;
            }
            if (!plugin.getManagers().isStealth(player)) {
                plugin.getManagers().addStealth(player);
                player.sendMessage(Messages.STEALTH_ENABLED);
                return;
            }
            plugin.getManagers().removeStealth(player);
            player.sendMessage(Messages.STEALTH_DISABLED);
        }
    }

}
