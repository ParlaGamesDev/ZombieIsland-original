package xyz.yarinlevi.zombieisland.player.data;

import lombok.Getter;
import org.bukkit.entity.Player;
import xyz.yarinlevi.zombieisland.ZombieIsland;
import xyz.yarinlevi.zombieisland.player.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class Data {
    @Getter
    private static final HashMap<Player, User> players = new HashMap<>();

    public static void updateAllUsers() {
        for (Player player : players.keySet()) {
            User user = players.get(player);
            try {
                updateUser(player, user);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    public static void forceUpdateUser(Player player) {
        try {
            updateUser(player, players.get(player));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void updateUser(Player player, User user) throws SQLException {
        String sql = String.format("UPDATE `%s` SET `PlayerName`=\"%s\", `COMBAT_EXPERIENCE`=%s WHERE `PlayerUUID`=\"%s\"", ZombieIsland.getInstance().getTable(), player.getName(), user.getCurrentCombatEXP(), user.getPlayerUUID().toString());

        Connection connection = ZombieIsland.getInstance().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.executeUpdate();
    }

    public static User getUser(Player player) { return players.get(player); }

    public static void setUser(Player player) throws SQLException {
        String sql = String.format("SELECT * FROM `%s` WHERE `PlayerUUID`=\"%s\"", ZombieIsland.getInstance().getTable(), player.getUniqueId().toString());

        Connection connection = ZombieIsland.getInstance().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet result = preparedStatement.executeQuery();

        if(!result.next()) {
            sql = String.format("INSERT INTO `%s` (`PlayerName`, `PlayerUUID`, `COMBAT_EXPERIENCE`) VALUES ('%s', '%s', '%s')", ZombieIsland.getInstance().getTable(), player.getName(), player.getUniqueId(), 0);
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeUpdate();
            players.put(player, new User(player.getUniqueId(), 0));
        } else {
            players.put(player, new User(player.getUniqueId(), result.getInt("COMBAT_EXPERIENCE")));
        }
    }
}
