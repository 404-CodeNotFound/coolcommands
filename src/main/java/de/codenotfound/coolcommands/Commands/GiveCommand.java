package de.codenotfound.coolcommands.Commands;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class GiveCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (!(commandSender instanceof Player)) {
            commandSender.sendMessage("Only players can use this command.");
            return true;
        }

        Player player = (Player) commandSender;

        if (strings.length < 1) {
            player.sendMessage("Usage: /give <item> [amount]");
            return true;
        }

        Material material = Material.matchMaterial(strings[0]);

        if (material == null) {
            player.sendMessage("Invalid item name.");
            return true;
        }

        int amount = 64;  // Default stack size

        if (strings.length > 1) {
            try {
                amount = Integer.parseInt(strings[1]);
            } catch (NumberFormatException e) {
                player.sendMessage("Invalid amount. Please enter a valid number.");
                return true;
            }
        }

        ItemStack itemStack = new ItemStack(material, amount);
        player.getInventory().addItem(itemStack);

        player.sendMessage("You received " + amount + " " + material.name().toLowerCase() + "(s).");

        return true;
    }
}
