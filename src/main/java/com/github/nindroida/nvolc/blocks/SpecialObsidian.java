package com.github.nindroida.nvolc.blocks;

import com.github.nindroida.nvolc.NVolc;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

public class SpecialObsidian extends ItemStack {

    private static final NVolc plugin = NVolc.getInstance();

    /* THE SPECIAL OBSIDIAN BLOCK THAT DOES IT ALL YESSSS */
    public SpecialObsidian() {
        super(Material.OBSIDIAN); // what material is it? OBSIDIAN OFC YA DINGUS
        ItemMeta meta = getItemMeta(); // get meta
        meta.setDisplayName("Special Obsidian Block"); // our special lil name :3
        meta.getPersistentDataContainer().set(new NamespacedKey(plugin, "click_count"), PersistentDataType.INTEGER, 0); // OMG I LOWKEY HAVE HATED LEARNING PERSISTENT DATA LIKE BRRRUUUHHHH
        setItemMeta(meta); // set item meta
    }

    /* take a wild guess what this method does ... I dare you. */
    @Override
    public ItemMeta getItemMeta() {
        return super.getItemMeta();
    }
}
