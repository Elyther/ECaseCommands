package com.elyther.ecasecommands;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class ECaseCommands extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);

        getLogger().info("ECaseCommands aktif!");
        getLogger().info("Komutlar büyük/küçük harfe duyarsız hale getirildi.");
    }

    @Override
    public void onDisable() {
        getLogger().info("ECaseCommands kapatıldı.");
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onPlayerCommand(PlayerCommandPreprocessEvent event) {

        String message = event.getMessage();

        if (message == null || message.isEmpty()) {
            return;
        }

        if (!message.startsWith("/")) {
            return;
        }

        String commandPart = message.substring(1);

        if (commandPart.isEmpty()) {
            return;
        }

        int spaceIndex = commandPart.indexOf(' ');

        String commandName;
        String arguments = "";

        if (spaceIndex == -1) {
            commandName = commandPart;
        } else {
            commandName = commandPart.substring(0, spaceIndex);
            arguments = commandPart.substring(spaceIndex);
        }

        String lowerCommand = commandName.toLowerCase();

        if (commandName.equals(lowerCommand)) {
            return;
        }

        // Sadece komut adını küçültür.
        // Örnek: /SHOP Diamond -> /shop Diamond
        event.setMessage("/" + lowerCommand + arguments);
    }
}
