package org.lushplugins.regrowthwelcome;

import org.bukkit.plugin.java.JavaPlugin;

public final class RegrowthWelcome extends JavaPlugin {
    private static RegrowthWelcome plugin;

    @Override
    public void onLoad() {
        plugin = this;
    }

    @Override
    public void onEnable() {
        // Enable implementation
    }

    @Override
    public void onDisable() {
        // Disable implementation
    }

    public static RegrowthWelcome getInstance() {
        return plugin;
    }
}
