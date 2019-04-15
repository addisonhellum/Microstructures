package us.blockcade.microstructures.commands;

import com.google.common.collect.Lists;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import us.blockcade.microstructures.Main;
import us.blockcade.microstructures.api.Microblock;
import us.blockcade.microstructures.api.Microstructure;
import us.blockcade.microstructures.api.drawing.MSDrawing;
import us.blockcade.microstructures.api.drawing.MSDrawingLine;
import us.blockcade.microstructures.api.preset.MicroblockLetter;
import us.blockcade.microstructures.api.preset.MicrostructurePreset;
import us.blockcade.microstructures.api.preset.shapes.MicroCreeper;
import us.blockcade.microstructures.api.preset.shapes.MicroHeart;
import us.blockcade.microstructures.api.preset.shapes.MicroPenis;
import us.blockcade.microstructures.util.chat.ChatUtil;
import us.blockcade.microstructures.util.chat.CommandHelp;
import us.blockcade.microstructures.util.chat.PagedChatList;
import us.blockcade.microstructures.util.menu.ItemStackBuilder;
import us.blockcade.microstructures.util.menu.PagedMenu;
import us.blockcade.microstructures.util.rotation.MSRotation;
import us.blockcade.microstructures.util.texture.BlockTexture;
import us.blockcade.microstructures.util.schematic.SchematicUtils;

import java.util.ArrayList;
import java.util.List;

public class MicrostructureCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender s, Command cmd, String label, String[] args) {
        if (!(s instanceof Player)) {
            s.sendMessage(ChatColor.RED + "You must be a player to do this!");
            return false;
        }

        Player player = (Player) s;
        CommandHelp help = new CommandHelp("Microstructure", "ms");
        help.register("list", "List all of the existing Microstructures");
        help.register("preset", "Display a list of available presets");
        help.register("preset [Preset]", "Build a preset structure");
        help.register("text <Message>", "Display a message made of Microblocks");
        help.register("schematic [File]", "Load in a schematic using Microblocks");

        if (args.length == 0) {
            help.display(player);

        } else if (args.length == 1) {
            String ctl = args[0];

            if (ctl.equalsIgnoreCase("?") || ctl.equalsIgnoreCase("help")) {
                help.display(player);

            } else if (ctl.equalsIgnoreCase("list")) {
                List<String> lines = new ArrayList<>();
                for (int i = 1; i <= Microstructure.getGrandMicrostructures().size(); i++) {
                    Microstructure ms = Microstructure.getGrandMicrostructures().get(i - 1);

                    if (!ms.isChild()) {
                        Location o = ms.getOrigin();

                        lines.add(ChatUtil.format("&a" + ms.getName() + " &e[" + ms.getMicroblocks().size() + "] &7x: " +
                                o.getBlockX() + ", y: " + o.getBlockY() + ", z: " + o.getBlockZ()));
                    }
                }

                PagedChatList pcl = new PagedChatList("Microstructures", lines);
                pcl.setListCommand("/microstructure list");
                pcl.display(player, 0);

            } else if (ctl.equalsIgnoreCase("preset")) {
                List<String> presets = new ArrayList<>();
                presets.add(ChatUtil.format("&aPenis &eSpawns a Microblock penis. &7[Stone]"));
                presets.add(ChatUtil.format("&aCreeper &eSpawns a Creeper Face design. &7[Blue Wool]"));
                presets.add(ChatUtil.format("&aHeart &eSpawns a classic Heart shape. &7[Red Wool]"));

                PagedChatList pcl = new PagedChatList("Micro Presets", presets);
                pcl.setListCommand("/microstructure preset");
                pcl.display(player, 0);

            } else if (ctl.equalsIgnoreCase("schematic") || ctl.equalsIgnoreCase("schem")) {
                player.sendMessage(ChatUtil.format("[Dev] &cSchematic selection menu coming soon!"));

            } else if (ctl.equalsIgnoreCase("dev")) {
                PagedMenu menu = new PagedMenu("Dev. Testing");

                for (int i = 1; i < 100; i++) {
                    ItemStack item = new ItemStackBuilder(Material.STONE).withName("&b" + i).build();
                    menu.add(item);
                }

                menu.display(player, 3);

            }

        } else if (args.length == 2) {
            String ctl = args[0];
            String arg = args[1];

            if (ctl.equalsIgnoreCase("list")) {
                List<String> lines = new ArrayList<>();
                for (int i = 1; i <= Microstructure.getGrandMicrostructures().size(); i++) {
                    Microstructure ms = Microstructure.getGrandMicrostructures().get(i - 1);

                    if (!ms.isChild()) {
                        Location o = ms.getOrigin();

                        lines.add(ChatUtil.format("&a" + ms.getName() + " &e[" + ms.getMicroblocks().size() + "] &7x: " +
                                o.getBlockX() + ", y: " + o.getBlockY() + ", z: " + o.getBlockZ()));
                    }
                }

                PagedChatList pcl = new PagedChatList("Microstructures", lines);
                pcl.setListCommand("/microstructure list");
                pcl.display(player, arg);

            } else if (ctl.equalsIgnoreCase("preset")) {
                MicrostructurePreset structure = null;
                BlockTexture texture = null;

                if (arg.equalsIgnoreCase("heart")) {
                    structure = new MicroHeart();
                    texture = BlockTexture.RED_WOOL;

                } else if (arg.equalsIgnoreCase("creeper")) {
                    structure = new MicroCreeper();
                    texture = BlockTexture.BLUE_WOOL;

                } else if (arg.equalsIgnoreCase("penis")) {
                    structure = new MicroPenis();
                    texture = BlockTexture.STONE;

                } else {
                    player.sendMessage(ChatUtil.format("[Oops] &cThat preset does not exist!"));
                    player.sendMessage(ChatUtil.format(" > &cUse '/microstructure preset' for a complete list."));
                    return false;
                }

                try {
                    structure.build(texture, player.getLocation());
                    player.sendMessage(ChatUtil.format("[Success] &aBuilt microstructure preset: &e" + ChatUtil.toTitleCase(arg)));
                } catch (NullPointerException e) {
                    player.sendMessage(ChatUtil.format("&cSomething went wrong. You may have used an invalid preset."));
                }

            } else if (ctl.equalsIgnoreCase("schematic") || ctl.equalsIgnoreCase("schem")) {
                Microstructure ms = SchematicUtils.loadSchematic(arg).buidMicrostructure(arg + ".schematic", player.getLocation(), MSRotation.getRotation(player.getLocation().getYaw()));
                player.sendMessage(ChatUtil.format("[Microstructure] &aPasted &b" + arg + ".schematic &aat your location."));

            }

        } if (args.length > 1) {
            String ctl = args[0];

            if (ctl.equalsIgnoreCase("text")) {
                String text = "";
                for (int i = 1; i < args.length; i++) {
                    text += args[i] + " ";
                }

                text = text.substring(0, text.length() - 1);

                Microstructure microstructure = MicroblockLetter.buildText(player.getLocation(), BlockTexture.WHITE, text);
                player.sendMessage("[Success] " + ChatColor.GREEN + "Spawned text: \"" + text + "\".");

            }
        }

        return false;
    }

}
