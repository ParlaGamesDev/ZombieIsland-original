package xyz.yarinlevi.zombieisland;

import io.lumine.xikage.mythicmobs.MythicMobs;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import xyz.yarinlevi.zombieisland.handlers.Settings;
import xyz.yarinlevi.zombieisland.handlers.custom.customspawns.MythicMobsHandler;
import xyz.yarinlevi.zombieisland.handlers.custom.customspawns.spawnersv2.commands.PointCommand;
import xyz.yarinlevi.zombieisland.handlers.custom.newswords.ZiSwordsHandler;
import xyz.yarinlevi.zombieisland.handlers.custom.newswords.listeners.FireSwordListener;
import xyz.yarinlevi.zombieisland.handlers.custom.newswords.listeners.KopakaListener;
import xyz.yarinlevi.zombieisland.handlers.custom.newswords.listeners.PoisonBladeListener;
import xyz.yarinlevi.zombieisland.handlers.custom.newswords.listeners.StormBreakerListener;
import xyz.yarinlevi.zombieisland.handlers.custom.swords.ZiSwordsCommand;
import xyz.yarinlevi.zombieisland.handlers.custom.swords.listeners.PlayerItemHeldChange;
import xyz.yarinlevi.zombieisland.handlers.custom.swords.listeners.PlayerItemHeldChange2;
import xyz.yarinlevi.zombieisland.handlers.listeners.EntityCombust;
import xyz.yarinlevi.zombieisland.handlers.listeners.OnPlayerJoin;
import xyz.yarinlevi.zombieisland.handlers.messages.MessageHandler;
import xyz.yarinlevi.zombieisland.handlers.messages.PlaceholderHandler;
import xyz.yarinlevi.zombieisland.handlers.permissions.PermissionHandler;
import xyz.yarinlevi.zombieisland.commands.AdminOnlyCommands;
import xyz.yarinlevi.zombieisland.commands.DebugCommands;
import xyz.yarinlevi.zombieisland.commands.TestMessages;

public final class ZombieIsland extends JavaPlugin {
    @Getter private static ZombieIsland instance;
    @Getter private PermissionHandler permissionHandler;
    @Getter private MessageHandler messageHandler;
    @Getter private Settings settings;
    @Getter private ZiSwordsHandler ziSwordsHandler;
    @Getter private MythicMobsHandler mythicMobsHandler;

    @Getter private final String version = getDescription().getVersion();
    @Getter private final String serverVersion = getServerVersion();

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
        //this.initializeData();

        //PlaceholderAPI registration
        if(Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) new PlaceholderHandler(this).register();

        registerSwordData();
        ziSwordsHandler = new ZiSwordsHandler();

        Listener[] swordListeners = new Listener[] {
                new StormBreakerListener(),
                new PoisonBladeListener(),
                new KopakaListener(),
                new FireSwordListener()
        };
        for (Listener listener : swordListeners) {
            ZombieIsland.getInstance().getServer().getPluginManager().registerEvents(listener, ZombieIsland.getInstance());
        }

        Listener[] listeners = new Listener[] {
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
        getCommand("zipoints").setExecutor(new PointCommand());

        if (Bukkit.getPluginManager().getPlugin("MythicMobs") != null) {
            MythicMobs mm = (MythicMobs) Bukkit.getPluginManager().getPlugin("MythicMobs");

            mythicMobsHandler = new MythicMobsHandler(mm);


        }

        //SpawnerManager.registerSpawners();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    private void initializeData() {
        /*
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
         */
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
