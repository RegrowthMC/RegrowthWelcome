package org.lushplugins.regrowthwelcome;

import org.lushplugins.lushlib.plugin.SpigotPlugin;
import org.lushplugins.regrowthwelcome.config.ConfigManager;
import org.lushplugins.regrowthwelcome.listener.PlayerListener;

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
    }

    public ConfigManager getConfigManager() {
        return configManager;
    }

    public static RegrowthWelcome getInstance() {
        return plugin;
    }
}
