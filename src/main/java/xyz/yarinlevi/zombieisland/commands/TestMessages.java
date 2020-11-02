package xyz.yarinlevi.zombieisland.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;
import xyz.yarinlevi.zombieisland.ZombieIsland;

public class TestMessages implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        ZombieIsland.getInstance().getMessageHandler().testLoadedMessages();
        sender.sendMessage("DEBUG -> Please check console for more info!");
        return true;
    }
}
