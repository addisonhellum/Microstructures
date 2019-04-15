package us.blockcade.microstructures.util;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class BlockData {

    private Material material;
    private byte data;

    public BlockData(Material material, byte data) {
        this.material = material;
        this.data = data;
    }

    public Material getMaterial() {
        return material;
    }

    public byte getData() {
        return data;
    }

    public ItemStack getItem() {
        return new ItemStack(getMaterial(), 1, getData());
    }

}
