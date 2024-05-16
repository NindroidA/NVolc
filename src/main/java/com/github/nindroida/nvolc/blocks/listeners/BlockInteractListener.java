package com.github.nindroida.nvolc.blocks.listeners;

import com.github.nindroida.nvolc.NVolc;
import com.github.nindroida.nvolc.blocks.SpecialObsidian;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.HashMap;
import java.util.Map;

public class BlockInteractListener implements Listener {
    private final NVolc plugin;
    private final Map<Location, Integer> thresholds; // map to store random threshold

    /* method that generated a random number between 5 and 10 */
    private int generateRandomThreshold() {
        return 5 + (int) (Math.random() * 6);
    }
    public BlockInteractListener(NVolc plugin) {
        this.plugin = plugin;
        this.thresholds = new HashMap<>();
    }

    @EventHandler
    public void onBlockInteract(PlayerInteractEvent e) {
        if (e.getAction() == Action.LEFT_CLICK_BLOCK) {
            Block block = e.getClickedBlock(); // get the clicked block
            Player player = e.getPlayer(); // get the mf who's clicking the block
            if (block != null) { // make sure it isn't null
                Location loc = block.getLocation(); // block location
                Map<Location, ItemStack> placedBlocks = plugin.getPlacedBlocks();
                Map<Location, Integer> clickCounts = plugin.getClickCounts();

                if (placedBlocks.containsKey(loc)) { // check if we have the block in our hash map
                    int clickCount = clickCounts.getOrDefault(loc, 0); // get the click count for the block loc
                    clickCount++; // increment it

                    if (!thresholds.containsKey(loc)) {
                        // generate new threshold
                        int threshold = generateRandomThreshold();
                        thresholds.put(loc, threshold);
                    }

                    int threshold = thresholds.get(loc);
                    if (clickCount >= threshold) { // if we click past or equal to the threshold
                        block.setType(Material.CRYING_OBSIDIAN); // turn into crying obsidian
                        player.playSound(player.getLocation(), Sound.ENTITY_GHAST_HURT, 10.0F, 1.0F); // play ghast hurt cause funny hee hee hee hah

                        // forget block from the maps (so we don't keep adding and adding lol)
                        placedBlocks.remove(loc);
                        clickCounts.remove(loc);
                        thresholds.remove(loc);
                    } else {
                        clickCounts.put(loc, clickCount); // update click count
                        player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_HURT, 10.0F, 1.0F); // play villager "oof"
                    }
                }
            }
        }
    }
}
