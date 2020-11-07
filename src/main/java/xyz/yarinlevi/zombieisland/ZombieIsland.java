package xyz.yarinlevi.zombieisland;

import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import xyz.yarinlevi.zombieisland.classes.FileManager;
import xyz.yarinlevi.zombieisland.classes.Settings;
import xyz.yarinlevi.zombieisland.classes.custom.customspawns.helpers.CustomMobs;
import xyz.yarinlevi.zombieisland.classes.custom.customspawns.helpers.CustomTiers;
import xyz.yarinlevi.zombieisland.classes.custom.customspawns.regions.RegionHandler;
import xyz.yarinlevi.zombieisland.classes.custom.customspawns.regions.SpawnerManager;
import xyz.yarinlevi.zombieisland.classes.custom.newswords.ZiSwordsHandler;
import xyz.yarinlevi.zombieisland.classes.custom.swords.ZiSwordsCommand;
import xyz.yarinlevi.zombieisland.classes.custom.swords.listeners.EntityDamagedEvent;
import xyz.yarinlevi.zombieisland.classes.custom.swords.listeners.PlayerItemHeldChange;
import xyz.yarinlevi.zombieisland.classes.custom.swords.listeners.PlayerItemHeldChange2;
import xyz.yarinlevi.zombieisland.classes.listeners.EntityCombust;
import xyz.yarinlevi.zombieisland.classes.listeners.OnPlayerJoin;
import xyz.yarinlevi.zombieisland.classes.messages.MessageHandler;
import xyz.yarinlevi.zombieisland.classes.messages.PlaceholderHandler;
import xyz.yarinlevi.zombieisland.classes.permissions.PermissionHandler;
import xyz.yarinlevi.zombieisland.commands.AdminOnlyCommands;
import xyz.yarinlevi.zombieisland.commands.DebugCommands;
import xyz.yarinlevi.zombieisland.commands.TestMessages;

import java.io.File;

public final class ZombieIsland extends JavaPlugin {
    @Getter private static ZombieIsland instance;
    @Getter private PermissionHandler permissionHandler;
    @Getter private MessageHandler messageHandler;
    @Getter private Settings settings;
    @Getter private ZiSwordsHandler ziSwordsHandler;

    @Getter private final String version = getDescription().getVersion();

    //Swords
    @Getter private int kopakaSlownessDuration, kopakaSlownessAmplifier, poisonWandDuration, poisonWandAmplifier, fireSwordBurn;
    @Getter private String stormBreaker, kopaka, fireSword, poisonWand, stormBreaker_Material, kopaka_Material, fireSword_Material, poisonWand_Material;

    @Override
    public void onEnable() {
        // Plugin startup logic
        instance = this;

        this.saveDefaultConfig();
        saveResource("messages.yml", false);
        saveResource("swords.yml", false);

        settings = new Settings();

        //Initialization of message handler
        messageHandler = new MessageHandler();

        //Initialization of permission handler
        permissionHandler = new PermissionHandler(instance);
        this.getServer().getPluginManager().registerEvents(permissionHandler, this);

        // Data initialization
        this.initializeData();

        //PlaceholderAPI registration
        if(Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) new PlaceholderHandler(this).register();

        registerSwordData();
        ziSwordsHandler = new ZiSwordsHandler();


        Listener[] listeners = new Listener[] {
                new EntityDamagedEvent(),
                permissionHandler,
                new OnPlayerJoin(),
                new EntityCombust(),
                new PlayerItemHeldChange2(),
                new PlayerItemHeldChange()
        };
        for (Listener listener : listeners) {
            getServer().getPluginManager().registerEvents(listener, this);
        }


        getCommand("ziadmin").setExecutor(new AdminOnlyCommands());
        getCommand("zidebug").setExecutor(new DebugCommands());
        getCommand("testmessages").setExecutor(new TestMessages());
        getCommand("ziswords").setExecutor(new ZiSwordsCommand());

        //SpawnerManager.registerSpawners();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    private void initializeData() {
        CustomTiers.setFile(new File(ZombieIsland.getInstance().getDataFolder(), "MobTiers.yml"));
        CustomTiers.setData(YamlConfiguration.loadConfiguration(CustomTiers.getFile()));

        FileManager.registerData(CustomTiers.getFile(), CustomTiers.getData());
        CustomTiers.save();

        CustomMobs.setFile(new File(ZombieIsland.getInstance().getDataFolder(), "CustomMobs.yml"));
        CustomMobs.setData(YamlConfiguration.loadConfiguration(CustomMobs.getFile()));

        FileManager.registerData(CustomMobs.getFile(), CustomMobs.getData());
        CustomMobs.save();

        RegionHandler.setFile(new File(ZombieIsland.getInstance().getDataFolder(), "Regions.yml"));
        RegionHandler.setData(YamlConfiguration.loadConfiguration(RegionHandler.getFile()));

        FileManager.registerData(RegionHandler.getFile(), RegionHandler.getData());
        RegionHandler.save();

        CustomTiers.loadTiers();
        CustomMobs.loadMobs();
        RegionHandler.loadRegions();
    }

    public void registerSwordData() {
        kopakaSlownessAmplifier = getConfig().getInt("kopaka_slowness_amplifier");
        kopakaSlownessDuration = getConfig().getInt("kopaka_slowness_duration");

        poisonWandAmplifier = getConfig().getInt("poisonWand_poison_amplifier");
        poisonWandDuration = getConfig().getInt("poisonWand_poison_duration");

        fireSwordBurn = getConfig().getInt("Fire_Sword_Burn");

        stormBreaker_Material = getConfig().getString("StormBreaker_Item");
        fireSword_Material = getConfig().getString("FireSword_Item");
        kopaka_Material = getConfig().getString("Kopaka_Item");
        poisonWand_Material = getConfig().getString("PoisonWand_Item");

        stormBreaker = net.md_5.bungee.api.ChatColor.translateAlternateColorCodes('&', getConfig().getString("StormBreaker"));
        fireSword = net.md_5.bungee.api.ChatColor.translateAlternateColorCodes('&',getConfig().getString("FireSword"));
        kopaka = net.md_5.bungee.api.ChatColor.translateAlternateColorCodes('&', getConfig().getString("Kopaka"));
        poisonWand = net.md_5.bungee.api.ChatColor.translateAlternateColorCodes('&', getConfig().getString("PoisonWand"));
    }

}
