package com.github.nindroida.nvolc.commands;

import com.github.nindroida.nvolc.NVolc;
import com.github.nindroida.nvolc.blocks.SpecialObsidian;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class CommandManager implements CommandExecutor {

    public CommandManager() {
        NVolc plugin = NVolc.getInstance();
        plugin.getCommand("volc").setExecutor(this); // this is our main command
        new TabCompleteManager(); // initialize tab manager
    }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (label.equalsIgnoreCase("volc") && args.length > 0) { // "/volc"
            if (args[0].equalsIgnoreCase("get")) { // "/volc get"
                if (sender instanceof Player) {
                    Player player = (Player) sender; // get the mf who sent the command
                    ItemStack specialObsidian = new SpecialObsidian(); // new SpecialObsidian instance
                    player.getInventory().addItem(specialObsidian); // shove it in player's inventory
                    player.sendMessage("You received a special obsidian block!"); // send em a sweet lil message
                    return true;
                }
                return false;
            }
        }
        return false;
    }
}
