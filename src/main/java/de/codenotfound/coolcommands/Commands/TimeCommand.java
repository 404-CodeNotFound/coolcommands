package de.codenotfound.coolcommands.Commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TimeCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (strings.length == 0) {
            commandSender.sendMessage(ChatColor.RED + "Usage: /time <day/night/midnight/morning/afternoon> OR /time <ticks>");
        } else if (strings.length == 1) {
            if (commandSender instanceof Player) {
                Player player = (Player) commandSender;
                World world = player.getWorld();

                if (strings[0].equalsIgnoreCase("day")) {
                    setTime(world, 0);
                    player.sendMessage(ChatColor.GREEN + "Time set to day!");
                } else if (strings[0].equalsIgnoreCase("night")) {
                    setTime(world, 13000);
                    player.sendMessage(ChatColor.GREEN + "Time set to night!");
                } else if (strings[0].equalsIgnoreCase("midnight")) {
                    setTime(world, 18000);
                    player.sendMessage(ChatColor.GREEN + "Time set to midnight!");
                } else if (strings[0].equalsIgnoreCase("morning")) {
                    setTime(world, 0);
                    player.sendMessage(ChatColor.GREEN + "Time set to morning!");
                } else if (strings[0].equalsIgnoreCase("afternoon")) {
                    setTime(world, 6000);
                    player.sendMessage(ChatColor.GREEN + "Time set to afternoon!");
                } else if (isInteger(strings[0])) {
                    int time = Integer.parseInt(strings[0]);
                    setTime(world, time);
                    player.sendMessage(ChatColor.GREEN + "Time set to " + time + "!");
                } else {
                    player.sendMessage(ChatColor.RED + "Invalid time type. Use /time <day/night/midnight/morning/afternoon> OR /time <ticks>");
                }
            } else {
                commandSender.sendMessage(ChatColor.RED + "Only players can use this command!");
            }
        }
        return true;
    }

    private void setTime(World world, long time) {
        world.setTime(time);
    }

    private boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}

