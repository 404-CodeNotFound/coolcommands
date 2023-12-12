package de.codenotfound.coolcommands.Commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class HealCommands implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        if (strings.length == 0) {
            if (commandSender instanceof Player) {
                Player player = (Player) commandSender;
                player.setHealth(20);
                player.sendMessage("You successfully healed yourself!");

                for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
                    if (onlinePlayer.hasPermission("coolcommands.heal.notify") || onlinePlayer.hasPermission("coolcommands.notify")) {
                        onlinePlayer.sendMessage(player.getDisplayName() + " healed himself");
                    }
                }
            } else {
                commandSender.sendMessage("You can't heal the console! Use '/heal <player>' instead");
            }
        } else if (strings.length == 1) {
            if (commandSender instanceof Player) {
                Player player = (Player) commandSender;
                Player receiver = Bukkit.getPlayer(strings[0]);

                if (receiver != null) {
                    receiver.setHealth(20);
                    player.sendMessage("You successfully healed " + receiver.getDisplayName());

                    for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
                        if (onlinePlayer.hasPermission("coolcommands.heal.notify") || onlinePlayer.hasPermission("coolcommands.notify")) {
                            onlinePlayer.sendMessage(player.getDisplayName() + " healed " + receiver.getDisplayName());
                        }
                    }
                } else {
                    player.sendMessage("Player '" + strings[0] + "' not found or not online.");
                }
            } else {
                commandSender.sendMessage("Console cannot heal other players! Use '/heal <player>' instead");
            }
        }
        return true;
    }
}
