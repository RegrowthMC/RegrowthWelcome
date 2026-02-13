package org.lushplugins.regrowthwelcome.config;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.lushplugins.regrowthwelcome.RegrowthWelcome;

public class ConfigManager {
    private String firstJoinMessage;
    private String joinMessage;
    private String quitMessage;
    private RewardsConfig rewards;

    public ConfigManager() {
        RegrowthWelcome.getInstance().saveDefaultConfig();
    }

    public void reload() {
        RegrowthWelcome.getInstance().reloadConfig();
        FileConfiguration config = RegrowthWelcome.getInstance().getConfig();

        this.firstJoinMessage = config.getString("first-join-message");
        this.joinMessage = config.getString("join-message");
        this.quitMessage = config.getString("quit-message");

        if (config.isConfigurationSection("rewards") && config.getBoolean("rewards.enabled")) {
            this.rewards = new RewardsConfig(config.getConfigurationSection("rewards"));
        }
    }

    public String getFirstJoinMessage() {
        return firstJoinMessage;
    }

    public String getJoinMessage() {
        return joinMessage;
    }

    public String getQuitMessage() {
        return quitMessage;
    }

    public boolean areRewardsEnabled() {
        return rewards != null;
    }

    public RewardsConfig getRewardsConfig() {
        return rewards;
    }

    public static class RewardsConfig {
        private int experience;
        private String rewardMessage;

        public RewardsConfig(ConfigurationSection config) {
            this.experience = config.getInt("experience");
            this.rewardMessage = config.getString("reward-message");
        }

        public int getExperience() {
            return experience;
        }

        public String getMessage() {
            return rewardMessage;
        }
    }
}
