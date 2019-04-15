package us.blockcade.microstructures.util.menu;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.*;

public class MenuGUI implements Menu {

    private final Inventory MENU;

    private ArrayList<Integer> lockedSlots = new ArrayList<>();
    public Map<ItemStack, String> commandBindsLeft = new HashMap<>();
    public Map<ItemStack, String> commandBindsRight = new HashMap<>();

    private UUID uuid = UUID.randomUUID();

    public MenuGUI(Inventory inv) {
        MENU = inv;
    }
    public MenuGUI(String title) { MENU = Bukkit.createInventory(null, 27, title); }
    public MenuGUI(String title, int lines) { MENU = Bukkit.createInventory(null, lines * 9, title); }

    public UUID getUniqueId() { return uuid; }

    private boolean isSlotLocked(int slot) {
        return lockedSlots.contains(slot);
    }

    public MenuGUI set(int slot, ItemStack item) {
        if (!isSlotLocked(slot))
            MENU.setItem(slot, item);
        return this;
    }

    public MenuGUI set(int slot, ItemStack item, boolean override) {
        if (!isSlotLocked(slot) || override)
            MENU.setItem(slot, item);
        return this;
    }

    public MenuGUI set(int line, int slot, ItemStack item) {
        int vanillaSlot = (line * 9) + slot;
        set(vanillaSlot, item);
        return this;
    }

    public MenuGUI set(int line, int slot, ItemStack item, boolean override) {
        int vanillaSlot = (line * 9) + slot;
        set(vanillaSlot, item, override);
        return this;
    }

    public MenuGUI add(ItemStack item) {
        for (int i = 0; i < MENU.getSize(); i++) {
            try {
                ItemStack itemAt = MENU.getItem(i);
                if (itemAt.getType().equals(Material.AIR) && !isSlotLocked(i)) {
                    MENU.setItem(i, item);
                    return this;
                }
            } catch (Exception e) {
                if (!isSlotLocked(i)) {
                    MENU.setItem(i, item);
                    return this;
                }
            }
        } return this;
    }

    public MenuGUI fill(ItemStack item) {
        for (int i = 0; i < MENU.getSize(); i++) {
            if (!isSlotLocked(i)) MENU.setItem(i, item);
        } return this;
    }

    public MenuGUI fillRow(int row, ItemStack item) {
        int orig = (row - 1) * 9;
        for (int i = orig; i < (orig + 9); i++) {
            if (!isSlotLocked(i)) MENU.setItem(i, item);
        } return this;
    }

    public MenuGUI fillColumn(int column, ItemStack item) {
        for (int i = 0; i < (MENU.getSize() / 9); i++) {
            int slot = (column - 1) + (i * 9);
            if (!isSlotLocked(slot)) MENU.setItem(slot, item);
        } return this;
    }

    public MenuGUI fillFrom(int start, int finish, ItemStack item) {
        for (int i = start; i <= finish; i++)
            if (!isSlotLocked(i))
                MENU.setItem(i, item);
        return this;
    }

    public MenuGUI lockSlot(int slot) {
        lockedSlots.add(slot);
        return this;
    }

    public MenuGUI lockSlots(Integer[] slots) {
        for (int slot : slots)
            lockedSlots.add(slot);
        return this;
    }

    public MenuGUI lockRow(int row) {
        int orig = (row - 1) * 9;
        for (int i = orig; i < (orig + 9); i++) {
            lockedSlots.add(i);
        } return this;
    }

    public MenuGUI lockColumn(int column) {
        for (int i = 0; i < (MENU.getSize() / 9); i++) {
            int slot = (column - 1) + (i * 9);
            lockedSlots.add(slot);
        } return this;
    }

    public MenuGUI lockFrom(int start, int finish) {
        for (int i = start; i <= finish; i++)
            lockedSlots.add(i);
        return this;
    }

    public MenuGUI bindLeft(ItemStack item, String command) {
        commandBindsLeft.putIfAbsent(item, command);
        if (!MenuHandler.boundMenus.contains(this))
            MenuHandler.boundMenus.add(this);
        return this;
    }

    public MenuGUI bindLeft(int slot, String command) {
        ItemStack item = MENU.getItem(slot);
        commandBindsLeft.putIfAbsent(item, command);
        if (!MenuHandler.boundMenus.contains(this))
            MenuHandler.boundMenus.add(this);
        return this;
    }

    public MenuGUI bindRight(ItemStack item, String command) {
        commandBindsRight.putIfAbsent(item, command);
        if (!MenuHandler.boundMenus.contains(this))
            MenuHandler.boundMenus.add(this);
        return this;
    }

    public MenuGUI bindRight(int slot, String command) {
        ItemStack item = MENU.getItem(slot);
        commandBindsRight.putIfAbsent(item, command);
        if (!MenuHandler.boundMenus.contains(this))
            MenuHandler.boundMenus.add(this);
        return this;
    }

    public MenuGUI freezeItems(boolean value) {
        Inventory inv = build();
        if (value) MenuHandler.lockedInventories.add(inv);
        else MenuHandler.lockedInventories.remove(inv);
        return this;
    }

    public void display(Player player) {
        player.openInventory(build());
    }

    public Inventory build() {
        return MENU;
    }

    public List<ItemStack> getContents() {
        List<ItemStack> contents = new ArrayList<>();

        for (ItemStack item : MENU.getContents()) contents.add(item);
        return contents;
    }

    public int getSize() {
        return MENU.getSize();
    }

    public String getTitle() {
        return MENU.getTitle();
    }

}
