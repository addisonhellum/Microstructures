package us.blockcade.microstructures.util.chat;

import com.google.common.collect.Lists;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class PagedChatList {

    private List<String> contents;
    private int size = 8;

    private String name;
    private String listCommand = "";

    private String divider = ChatUtil.format("&6----------------------------------------------------&r");

    public PagedChatList(String name, List<String> contents) {
        this.name = name;
        this.contents = Lists.reverse(contents);
    }

    public String getName() {
        return name;
    }

    public List<String> getContents() {
        return contents;
    }

    public int getSize() {
        return size;
    }

    public String getListCommand() {
        return listCommand;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPages() {
        return Math.toIntExact(Math.round(Math.ceil(contents.size() / 8))) + 1;
    }

    public void setListCommand(String listCommand) {
        this.listCommand = listCommand;
    }

    public List<String> contentsAsList() {
        List<String> list = new ArrayList<>();

        for (String c : contents) list.add(c);
        return list;
    }

    public List<String> getPageContents(int page) {
        int beginIndex = page * size;
        int endIndex = beginIndex + size;

        if (endIndex > contents.size()) endIndex = contents.size();

        return contentsAsList().subList(beginIndex, endIndex);
    }

    public TextComponent getHeader(int page) {
        TextComponent header = new TextComponent(divider + "\n");
        if (page < 0) page = 0;

        TextComponent spacing = new TextComponent("                    ");
        header.addExtra(spacing);

        if (page != 0) {
            TextComponent previous = new TextComponent("<< ");
            previous.setColor(ChatColor.YELLOW);
            previous.setBold(true);
            previous.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(ChatUtil.format("&ePrevious Page")).create()));
            previous.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, getListCommand() + " " + (page - 1)));
            header.addExtra(previous);
        }

        TextComponent info = new TextComponent(getName() + " (Page " + (page + 1) + "/" + getPages() + ")");
        info.setColor(ChatColor.GREEN);
        header.addExtra(info);

        if (page <= getPages() && (page + 1) != getPages()) {
            TextComponent next = new TextComponent(" >>");
            next.setColor(ChatColor.YELLOW);
            next.setBold(true);
            next.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(ChatUtil.format("&eNext Page")).create()));
            next.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, getListCommand() + " " + (page + 1)));
            header.addExtra(next);
        }

        return header;
    }

    public void display(Player player, int page) {
        player.spigot().sendMessage(getHeader(page));
        player.sendMessage("");

        List<String> pageContents = getPageContents(page);

        if (pageContents.size() == 0) {
            player.sendMessage(ChatUtil.format(" &cThere's nothing here!"));
        }

        for (int i = 1; i <= pageContents.size(); i++) {
            String line = pageContents.get(i - 1);
            player.sendMessage(ChatUtil.format("&7" + i + ". &e" + line));
        }

        player.sendMessage(divider);
    }

    public void display(Player player, String pageArg) {
        int page = 0;

        try {
            page = Integer.valueOf(pageArg) - 1;
        } catch (Exception e) {
            player.sendMessage(ChatUtil.format("&cPage numbers must be numerical!"));
        }

        display(player, page);
    }

}
