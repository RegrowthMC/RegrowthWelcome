package org.lushplugins.regrowthwelcome.command;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.command.CommandSender;
import org.lushplugins.regrowthwelcome.RegrowthWelcome;
import revxrsal.commands.annotation.Command;
import revxrsal.commands.annotation.Subcommand;
import revxrsal.commands.bukkit.annotation.CommandPermission;

@Command("regrowthwelcome")
@SuppressWarnings("unused")
public class RegrowthWelcomeCommand {

    @Subcommand("reload")
    @CommandPermission("regrowthwelcome.reload")
    public void reload(CommandSender sender) {
        RegrowthWelcome.getInstance().getConfigManager().reload();
        sender.sendMessage(Component.text()
            .content("RegrowthWelcome reloaded!")
            .color(TextColor.fromHexString("#b7faa2"))
            .build());
    }
}
