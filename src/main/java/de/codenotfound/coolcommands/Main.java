package de.codenotfound.coolcommands;

import de.codenotfound.coolcommands.Commands.GiveCommand;
import de.codenotfound.coolcommands.Commands.HealCommands;
import de.codenotfound.coolcommands.Commands.TimeCommand;
import de.codenotfound.coolcommands.Commands.WeatherCommand;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        // Register command executors
        getCommand("heal").setExecutor(new HealCommands());
        getCommand("weather").setExecutor(new WeatherCommand());
        getCommand("time").setExecutor(new TimeCommand());
        getCommand("give").setExecutor(new GiveCommand());

        // Other initialization code if needed
    }

    @Override
    public void onDisable() {
        // Cleanup code if needed
    }
}
