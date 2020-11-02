package xyz.yarinlevi.zombieisland;

import com.zaxxer.hikari.HikariDataSource;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;
import xyz.yarinlevi.zombieisland.classes.messages.MessageHandler;
import xyz.yarinlevi.zombieisland.classes.messages.PlaceholderHandler;
import xyz.yarinlevi.zombieisland.classes.permissions.PermissionHandler;
import xyz.yarinlevi.zombieisland.commands.TestMessages;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public final class ZombieIsland extends JavaPlugin {
    @Getter private static ZombieIsland instance;
    @Getter private PermissionHandler permissionHandler;
    @Getter private MessageHandler messageHandler;

    @Getter private final String version = getDescription().getVersion();
    @Getter private static String prefix;
    private HikariDataSource dataSource;
    @Getter private String table;
    @Getter private Connection connection;


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

        // MySql Initialization
        initializeMySql();

        //PlaceholderAPI registration
        if(Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) new PlaceholderHandler(this).register();




        this.getCommand("testmessages").setExecutor(new TestMessages());
    }


    private void initializeMySql() {
        String hostName = getConfig().getString("MySql_Hostname");
        table = getConfig().getString("MySql_TableName");
        String database = getConfig().getString("MySql_Database");
        int port = getConfig().getInt("MySql_Port");
        String user = getConfig().getString("MySql_User");
        String pass = getConfig().getString("MySql_Password");
        boolean ssl = getConfig().getBoolean("MySql_UseSSL");

        prefix = ChatColor.translateAlternateColorCodes('&', getConfig().getString("Prefix"));

        dataSource = new HikariDataSource();

        dataSource.setDataSourceClassName("com.mysql.jdbc.jdbc2.optional.MysqlDataSource");
        dataSource.addDataSourceProperty("serverName", hostName);
        dataSource.addDataSourceProperty("port", port);
        dataSource.addDataSourceProperty("databaseName", database);
        dataSource.addDataSourceProperty("user", user);
        dataSource.addDataSourceProperty("password", pass);
        dataSource.addDataSourceProperty("useSSL", ssl);

        String sql = String.format("CREATE TABLE IF NOT EXISTS `%s` (`PlayerName` TEXT, `PlayerUUID` TEXT, `COMBAT_EXPERIENCE` INT);", table);

        Bukkit.getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
            @Override
            public void run() {
                try {
                    getLogger().warning("Please await mysql hook...");
                    connection = dataSource.getConnection();
                    Statement statement = connection.createStatement(); {
                        statement.executeUpdate(sql);
                    }
                    getLogger().info("MySQL Hooked!");
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        });
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
