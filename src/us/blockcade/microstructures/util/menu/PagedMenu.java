package us.blockcade.microstructures.util.menu;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.*;

public class PagedMenu implements Menu {

    private static Map<String, UUID> pagedMenuData = new HashMap<>();
    public static Map<String, UUID> getPagedMenuData() { return pagedMenuData; }

    private static List<PagedMenu> pagedMenus = new ArrayList<>();
    public static List<PagedMenu> getPagedMenus() { return pagedMenus; }

    public static UUID getUUID(String title) {
        return getPagedMenuData().get(title);
    }

    public static PagedMenu fromUUID(UUID uuid) {
        for (PagedMenu menu : getPagedMenus())
            if (menu.getUniqueId().equals(uuid)) return menu;

        return null;
    }

    private UUID uuid = UUID.randomUUID();

    private String title = uuid.toString();
    private List<ItemStack> content = new ArrayList<>();

    private ItemStack icon = new ItemStackBuilder(Material.SIGN).withName("&aMenu Icon")
            .withLore(new String[] {"Edit this in the code", "with &bmenu.setIcon()"}).build();

    public PagedMenu() {
        pagedMenus.add(this);
        pagedMenuData.put(getTitle(), getUniqueId());
    }

    public PagedMenu(String title) {
        this.title = title;

        pagedMenus.add(this);
        pagedMenuData.put(getTitle(), getUniqueId());
    }

    public PagedMenu(List<ItemStack> content) {
        this.content = content;

        pagedMenus.add(this);
        pagedMenuData.put(getTitle(), getUniqueId());
    }

    public PagedMenu(String title, List<ItemStack> content) {
        this.title = title;
        this.content = content;
    }

    public UUID getUniqueId() { return uuid; }

    public String getTitle() { return title; }

    public List<ItemStack> getContents() { return content; }

    public List<ItemStack> getContents(int pageIndex) {
        List<ItemStack> pageContent = new ArrayList<>();

        int val = 27;
        if (pageIndex + 1 == getSize()) val = getContents().size() % 27;

        int index = pageIndex * 27;

        pageContent.addAll(getContents().subList(index, index + val));
        return pageContent;
    }

    public int getSize() {
        return Math.floorDiv(getContents().size(), 27) + 1;
    }

    public ItemStack getIcon() { return icon; }

    public PagedMenu add(ItemStack item) {
        content.add(item);
        return this;
    }

    public void display(Player player, int pageIndex) {
        int pageDisplay = pageIndex + 1;

        MenuGUI menu = new MenuGUI(getTitle() + " (Page " + pageDisplay + "/" + getSize() + ")", 6);
        menu.lockRow(1).set(5, 4, getIcon())
                .set(5, 3, new ItemStackBuilder(Material.INK_SACK).withName("&cClose Menu")
                        .withLore("Click to Close Menu.").withData(1));

        if (pageDisplay < getSize())
            menu.set(5, 8, new ItemStackBuilder(Material.ARROW)
                    .withName("&aNext Page").withLore("Continue to Page " + (pageDisplay + 1)).build());

        if (pageDisplay > 1)
            menu.set(5, 0, new ItemStackBuilder(Material.ARROW)
                    .withName("&aPrevious Page").withLore("Return to Page " + (pageDisplay - 1)).build());

        for (ItemStack item : getContents(pageIndex))
            menu.add(item);

        menu.freezeItems(true);
        menu.display(player);
    }

    public void display(Player player) {
        display(player, 0);
    }

}
