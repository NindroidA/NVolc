package com.github.nindroida.nvolc.blocks.listeners;

import com.github.nindroida.nvolc.NVolc;
import com.github.nindroida.nvolc.blocks.SpecialObsidian;
import org.bukkit.NamespacedKey;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

public class BlockPlaceListener implements Listener {
    private final NVolc plugin;

    public BlockPlaceListener(NVolc plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        ItemStack item = event.getItemInHand();
        if (item.hasItemMeta()) {
            ItemMeta meta = item.getItemMeta();
            if (meta.getPersistentDataContainer().has(new NamespacedKey(plugin, "click_count"), PersistentDataType.INTEGER)) { // check if the block has our persistent data shit
                plugin.getPlacedBlocks().put(event.getBlock().getLocation(), item); // shove the data in the map
            }
        }
    }
}
