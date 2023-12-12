package de.codenotfound.coolcommands.Commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.WeatherType;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class WeatherCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (strings.length == 0) {
            commandSender.sendMessage(ChatColor.RED + "Use /weather <sun/rain/storm>");
        } else if (strings.length == 1) {
            if (commandSender instanceof Player) {
                Player player = (Player) commandSender;
                World world = player.getWorld();

                if (strings[0].equalsIgnoreCase("sun")) {
                    world.setStorm(false);
                    world.setThundering(false);
                    player.sendMessage(ChatColor.GREEN + "Weather set to sunny!");
                    notifyWeatherChange(player.getDisplayName(), "sunny", world);
                } else if (strings[0].equalsIgnoreCase("rain")) {
                    world.setStorm(true);
                    world.setThundering(false);
                    player.sendMessage(ChatColor.GREEN + "Weather set to rainy!");
                    notifyWeatherChange(player.getDisplayName(), "rainy", world);
                } else if (strings[0].equalsIgnoreCase("storm")) {
                    world.setStorm(true);
                    world.setThundering(true);
                    player.sendMessage(ChatColor.GREEN + "Weather set to stormy!");
                    notifyWeatherChange(player.getDisplayName(), "stormy", world);
                } else {
                    player.sendMessage(ChatColor.RED + "Invalid weather type. Use /weather <sun/rain/storm>");
                }
            } else {
                commandSender.sendMessage(ChatColor.RED + "Only players can use this command!");
            }
        }
        return true;
    }

    private void notifyWeatherChange(String playerName, String weatherType, World world) {
        for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
            if (onlinePlayer.hasPermission("coolcommands.weather.notify") || onlinePlayer.hasPermission("coolcommands.notify")) {
                onlinePlayer.sendMessage(playerName + " changed the weather to " + weatherType + " in world " + world.getName());
            }
        }
    }
}
