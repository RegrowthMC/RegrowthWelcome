package org.lushplugins.regrowthwelcome;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.Nullable;
import org.lushplugins.lushlib.utils.plugin.SpigotPlugin;
import org.lushplugins.regrowthwelcome.command.RegrowthWelcomeCommand;
import org.lushplugins.regrowthwelcome.config.ConfigManager;
import org.lushplugins.regrowthwelcome.listener.PlayerListener;
import revxrsal.commands.bukkit.BukkitLamp;

import java.util.List;

public final class RegrowthWelcome extends SpigotPlugin {
    private static RegrowthWelcome plugin;

    private ConfigManager configManager;

    @Override
    public void onLoad() {
        plugin = this;
    }

    @Override
    public void onEnable() {
        this.configManager = new ConfigManager();
        this.configManager.reload();

        registerListener(new PlayerListener());

        BukkitLamp.builder(this)
            .build()
            .register(new RegrowthWelcomeCommand());
    }

    public void runCommands(List<String> commands, @Nullable Player player) {
        CommandSender console = Bukkit.getConsoleSender();
        for (String command : commands) {
            if (command.startsWith("player:")) {
                command = command.substring("player:".length())
                    .strip();

                if (player != null) {
                    Bukkit.dispatchCommand(player, command);
                }
            } else {
                Bukkit.dispatchCommand(console, command);
            }
        }
    }

    public ConfigManager getConfigManager() {
        return configManager;
    }

    public static RegrowthWelcome getInstance() {
        return plugin;
    }
}
