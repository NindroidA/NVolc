package com.github.nindroida.nvolc;

import com.github.nindroida.nvolc.blocks.listeners.BlockInteractListener;
import com.github.nindroida.nvolc.blocks.listeners.BlockPlaceListener;
import com.github.nindroida.nvolc.commands.CommandManager;
import org.bukkit.Location;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;

public final class NVolc extends JavaPlugin {

    private static NVolc instance;
    private Map<Location, ItemStack> placedBlocks; // map to store the blocks we placed (that haven't changed yet)
    private Map<Location, Integer> clickCounts; // map to store the current amount of times the block has been punched
    public static NVolc getInstance() { return instance; }

    @Override
    public void onEnable() {
        instance = this;

        // initialize hash maps
        placedBlocks = new HashMap<>();
        clickCounts = new HashMap<>();

        // register commands
        new CommandManager();

        // register listeners
        getServer().getPluginManager().registerEvents(new BlockInteractListener(this), this);
        getServer().getPluginManager().registerEvents(new BlockPlaceListener(this), this);
    }

    @Override
    public void onDisable() {
    }

    /* "buT coUldN'T yOu jUSt mAkE thE MaPS pUBlic iNStead?!?!" NO BECAUSE FUCK YOU THATS WHY */
    public Map<Location, ItemStack> getPlacedBlocks() {
        return placedBlocks;
    }
    public Map<Location, Integer> getClickCounts() {
        return clickCounts;
    }
}
