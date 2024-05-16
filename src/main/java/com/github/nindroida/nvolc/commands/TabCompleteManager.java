package com.github.nindroida.nvolc.commands;

import com.github.nindroida.nvolc.NVolc;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class TabCompleteManager implements TabCompleter {

    private static final List<String> COMMANDS = new ArrayList<>();

    static {
        COMMANDS.add("get");
    }

    public TabCompleteManager() {
        NVolc plugin = NVolc.getInstance();
        plugin.getCommand("volc").setTabCompleter(this);
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        // if no args, return an empty list
        if (args.length == 0) {
            return Collections.emptyList();
        }

        // if one arg, filter main commands based on permissions and partial input
        if (args.length == 1) {
            List<String> tabs = new ArrayList<>();
            if (sender instanceof Player player) {
                String partialInput = args[0].toLowerCase();
                tabs.addAll(COMMANDS.stream()
                        .filter(cmd -> cmd.toLowerCase().startsWith(partialInput))
                        .filter(cmd -> {
                            if (cmd.equals("get")) return player.hasPermission("nvolc.main");
                            return false;
                        })
                        .toList());
            }
            return tabs;
        }

        // if two args, filter subcommands based on partial input
        if (args.length == 2) {
            if (args[0].equalsIgnoreCase("get")) {
                String partialInput = args[1].toLowerCase();
                // return BlockManager.getBlocks().stream()
                //  .filter(block -> block.toLowerCase().startsWith(partialInput))
                //  .collect(Collectors.toList())
            }
        }

        return Collections.emptyList();
    }
}
