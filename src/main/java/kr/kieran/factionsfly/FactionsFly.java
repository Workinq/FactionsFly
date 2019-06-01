package kr.kieran.factionsfly;

import kr.kieran.factionsfly.commands.FlyCommand;
import kr.kieran.factionsfly.commands.StealthCommand;
import kr.kieran.factionsfly.commands.UtilCommand;
import kr.kieran.factionsfly.listeners.PlayerListeners;
import kr.kieran.factionsfly.managers.PlayerManager;
import kr.kieran.factionsfly.utilities.PlayerChecks;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Level;

public class FactionsFly extends JavaPlugin {

    private static FactionsFly instance;
    private PlayerManager managers;
    private PlayerChecks checks;

    @Override
    public void onEnable() {
        instance = this;
        saveDefaultConfig();
        registerCommands();
        registerListeners();
        registerManagers();
        if (getServer().getPluginManager().getPlugin("Factions") == null) {
            getLogger().log(Level.SEVERE, "Disabling due to no Factions dependency.");
            getServer().getPluginManager().disablePlugin(this);
            return;
        }
        checks.checkPlayersRunnable();
        getLogger().log(Level.INFO, "FactionsFly has been enabled.");
    }

    @Override
    public void onDisable() {
        managers.onDisable();
        getServer().getScheduler().cancelTasks(this);
        getLogger().log(Level.INFO, "FactionsFly has been disabled.");
        instance = null;
    }

    private void registerCommands() {
        getCommand("fly").setExecutor(new FlyCommand(this));
        getCommand("flyadmin").setExecutor(new UtilCommand(this));
    }

    private void registerListeners() {
        getServer().getPluginManager().registerEvents(new StealthCommand(this), this);
        getServer().getPluginManager().registerEvents(new PlayerListeners(this), this);
    }

    private void registerManagers() {
        managers = new PlayerManager(this);
        checks = new PlayerChecks(this);
    }

    public PlayerManager getManagers() {
        return managers;
    }

    public PlayerChecks getChecks() {
        return checks;
    }

    public static FactionsFly getInstance() {
        return instance;
    }

}
