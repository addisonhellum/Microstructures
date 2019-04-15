package us.blockcade.microstructures.util.chat;

import com.google.common.collect.Lists;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommandHelp {

    private String name;
    private String base;

    private Map<String, String> arguments = new HashMap<>();

    private String divider = ChatUtil.format("&6----------------------------------------------------&r");

    public CommandHelp(String name, String base) {
        this.name = name;
        this.base = base;
    }

    public String getName() {
        return name;
    }

    public String getBase() {
        return base;
    }

    public Map<String, String> getArgumentData() {
        return arguments;
    }

    public List<String> getArguments() {
        List<String> args = new ArrayList<>();
        args.addAll(getArgumentData().keySet());

        return Lists.reverse(args);
    }

    public String getDescription(String argument) {
        return arguments.get(argument);
    }

    public String formatCommand(String argument) {
        return "/" + getBase() + " " + argument;
    }

    public void register(String argument, String description) {
        arguments.put(argument, description);
    }

    public void display(Player player) {
        player.sendMessage(divider);
        player.sendMessage(ChatUtil.format(" &a" + getName() + " Commands:"));
        player.sendMessage("");

        for (String arg : getArguments()) {
            TextComponent command = new TextComponent("");

            TextComponent argument = new TextComponent(formatCommand(arg));
            argument.setColor(ChatColor.YELLOW);
            if (!arg.contains("<") && !arg.contains(">") && !arg.contains("[") && !arg.contains("]"))
                argument.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, formatCommand(arg)));
            else argument.setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, formatCommand(arg)));
            command.addExtra(argument);

            TextComponent dash = new TextComponent(" - ");
            dash.setColor(ChatColor.GRAY);
            command.addExtra(dash);

            TextComponent desc = new TextComponent(getDescription(arg));
            desc.setColor(ChatColor.AQUA);
            command.addExtra(desc);

            player.spigot().sendMessage(command);
        }

        player.sendMessage(divider);
    }

}
