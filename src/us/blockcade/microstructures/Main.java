package us.blockcade.microstructures;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import us.blockcade.microstructures.api.Microblock;
import us.blockcade.microstructures.api.events.handler.EventAssigner;
import us.blockcade.microstructures.commands.MicrostructureCommand;
import us.blockcade.microstructures.util.menu.MenuHandler;
import us.blockcade.microstructures.util.schematic.SchematicUtils;

public class Main extends JavaPlugin implements Listener {

    private static Main main;
    public static Plugin getInstance() { return main; }

    @Override
    public void onEnable() {
        main = this;
        getServer().getPluginManager().registerEvents(this, this);

        getConfig().options().copyDefaults(true);
        saveConfig();

        SchematicUtils.initSchematics();

        getServer().getPluginManager().registerEvents(new MenuHandler(), this);

        getServer().getPluginManager().registerEvents(new EventAssigner(), this);
        getCommand("microstructure").setExecutor(new MicrostructureCommand());
    }

    @Override
    public void onDisable() {
        for (Microblock mb : Microblock.getMicroblocks())
            mb.getStand().remove();
    }

    @EventHandler
    public void onSneak(PlayerToggleSneakEvent event) {
        if (event.isSneaking()) return;
        Player player = event.getPlayer();

        if (player.getName().equalsIgnoreCase("Araos")) {
            if (player.getGameMode().equals(GameMode.CREATIVE)) return;

            for (Microblock mb : Microblock.getMicroblocks())
                mb.getStand().remove();
        }
    }

}
