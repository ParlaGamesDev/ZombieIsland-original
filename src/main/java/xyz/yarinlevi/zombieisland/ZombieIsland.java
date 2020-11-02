package xyz.yarinlevi.zombieisland;

import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import xyz.yarinlevi.zombieisland.commands.TestMessages;
import xyz.yarinlevi.zombieisland.messages.MessageHandler;
import xyz.yarinlevi.zombieisland.messages.PlaceholderHandler;
import xyz.yarinlevi.zombieisland.permissions.PermissionHandler;

public final class ZombieIsland extends JavaPlugin {
    @Getter private static ZombieIsland instance;
    @Getter private PermissionHandler permissionHandler;
    @Getter private MessageHandler messageHandler;

    @Override
    public void onEnable() {
        // Plugin startup logic
        instance = this;

        this.saveDefaultConfig();
        saveResource("messages.yml", false);


        //Initialization of message handler
        messageHandler = new MessageHandler();

        //Initialization of permission handler
        permissionHandler = new PermissionHandler(instance);
        this.getServer().getPluginManager().registerEvents(permissionHandler, this);

        //PlaceholderAPI registration
        if(Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) new PlaceholderHandler(this).register();

        this.getCommand("testmessages").setExecutor(new TestMessages());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
