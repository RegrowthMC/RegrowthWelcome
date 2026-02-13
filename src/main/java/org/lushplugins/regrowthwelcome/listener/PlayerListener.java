package org.lushplugins.regrowthwelcome.listener;

import io.papermc.paper.event.player.AsyncChatEvent;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.lushplugins.lushlib.libraries.chatcolor.ChatColorHandler;
import org.lushplugins.lushlib.libraries.chatcolor.ModernChatColorHandler;
import org.lushplugins.regrowthwelcome.RegrowthWelcome;

import java.time.Instant;
import java.util.HashSet;
import java.util.UUID;

public class PlayerListener implements Listener {
    private final HashSet<UUID> rewardedPlayers = new HashSet<>();
    private String playerName;
    private Long timeout;

    @EventHandler(priority = EventPriority.LOWEST)
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        if (!player.hasPlayedBefore()) {
            if (RegrowthWelcome.getInstance().getConfigManager().areRewardsEnabled()) {
                this.rewardedPlayers.clear();
                this.playerName = player.getName();
                this.timeout = Instant.now().getEpochSecond() + 30;
            }

            event.joinMessage(ModernChatColorHandler.translate(RegrowthWelcome.getInstance().getConfigManager().getFirstJoinMessage()
                .replace("%player%", player.getName()), player));
        } else {
            event.joinMessage(ModernChatColorHandler.translate(RegrowthWelcome.getInstance().getConfigManager().getJoinMessage()
                .replace("%player%", player.getName()), player));
        }
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onPlayerQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        event.quitMessage(ModernChatColorHandler.translate(RegrowthWelcome.getInstance().getConfigManager().getQuitMessage()
            .replace("%player%", player.getName()), player));
    }

    @EventHandler
    public void onPlayerChat(AsyncChatEvent event) {
        if (timeout == null) {
            return;
        }

        Player player = event.getPlayer();
        UUID uuid = player.getUniqueId();
        if (rewardedPlayers.contains(uuid)) {
            return;
        }

        if (timeout < Instant.now().getEpochSecond()) {
            timeout = null;
            return;
        }

        String message = PlainTextComponentSerializer.plainText().serialize(event.message());
        if (message.toLowerCase().contains("welcome")) {
            rewardedPlayers.add(uuid);
            player.giveExp(RegrowthWelcome.getInstance().getConfigManager().getRewardsConfig().getExperience());

            ChatColorHandler.sendMessage(player, RegrowthWelcome.getInstance().getConfigManager().getRewardsConfig().getMessage()
                .replace("%player%", playerName));
        }
    }
}
