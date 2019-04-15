package us.blockcade.microstructures.util.menu;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.List;
import java.util.UUID;

public interface Menu {

    UUID getUniqueId();

    String getTitle();
    int getSize();

    List<ItemStack> getContents();

    void display(Player player);

}
