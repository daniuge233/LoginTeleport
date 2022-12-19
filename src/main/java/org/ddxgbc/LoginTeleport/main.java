package org.ddxgbc.LoginTeleport;

import com.sun.istack.internal.NotNull;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class main extends JavaPlugin {

    Listener listener = null;

    String notPlayer = "";
    String setPos = "";
    String unknownParameter = "";

    @Override
    public void onEnable() {
        listener = new Listener();

        notPlayer = getConfig().getString("languages.notPlayer");
        setPos = getConfig().getString("languages.setpos");
        unknownParameter = getConfig().getString("languages.unknownParameter");

        double x = getConfig().getDouble("Position.x");
        double y = getConfig().getDouble("Position.y");
        double z = getConfig().getDouble("Position.z");
        if (x != 0 && y != 0 && z != 0) {
            listener.loc = new Location(getServer().getWorld("main"), x, y, z);
        }

        Bukkit.getPluginCommand("lt").setExecutor(this);
        Bukkit.getPluginManager().registerEvents(listener, this);
        saveDefaultConfig();
    }

    @Override
    public void onDisable() {
        FileConfiguration conf = getConfig();
        conf.set("Position.x", listener.loc.getX());
        conf.set("Position.y", listener.loc.getY());
        conf.set("Position.z", listener.loc.getZ());
        saveConfig();
    }

    @Override
    @NotNull
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equals("lt")) {
            if (sender instanceof Player) {
                if (args[0].equals("add")) {
                    listener.loc = ((Player) sender).getLocation();
                    sender.sendMessage(setPos);
                    return true;
                } else {
                    sender.sendMessage(unknownParameter);
                }
                return false;
            } else {
                sender.sendMessage(notPlayer);
                return true;
            }
        }

        return false;
    }
}
