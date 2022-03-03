package me.niz.thefloorislava.plugin.player.data.database;

import me.niz.thefloorislava.api.player.GamePlayer;
import me.niz.thefloorislava.api.player.data.GamePlayerData;
import me.niz.thefloorislava.api.player.data.database.GamePlayerDataDAO;
import me.niz.thefloorislava.api.exception.SQLPlayerDataException;
import me.niz.thefloorislava.plugin.player.CraftPlayer;
import me.niz.thefloorislava.api.tools.sql.DbConnection;
import org.jetbrains.annotations.NotNull;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.UUID;

public class SQLGamePlayerDataDAO implements GamePlayerDataDAO {

    private final DbConnection dbConnection;

    public SQLGamePlayerDataDAO(@NotNull DbConnection dbConnection) {
        this.dbConnection = dbConnection;
        this.initTables();
    }

    @Override
    public Optional<GamePlayerData> loadPlayer(@NotNull UUID playerUUID) throws SQLPlayerDataException {
        String stringSQL = "SELECT * FROM player_data WHERE player_uuid=?;";

        Connection connection = dbConnection.getConnection();

        try (PreparedStatement preparedStatement = connection.prepareStatement(stringSQL)) {
            preparedStatement.setString(1, playerUUID.toString());

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                String playerName = resultSet.getString("player_name");
                int playedGames = resultSet.getInt("player_playedgames");
                int wonGames = resultSet.getInt("player_wongames");

                GamePlayer player = new CraftPlayer(playerUUID, playerName);
                return Optional.of(new me.niz.thefloorislava.plugin.player.data.GamePlayerData(player, playedGames, wonGames));
            }

            return Optional.empty();
        } catch (SQLException troubles) {
            throw new SQLPlayerDataException(String.format("Cannot load player data in database. UUID: %s", playerUUID), troubles);
        }
    }

    @Override
    public void createPlayerData(@NotNull GamePlayerData gamePlayerData) throws SQLPlayerDataException {
        String stringSQL = "INSERT INTO player_data (player_uuid, player_name, player_playedgames, player_wongames)" +
                "VALUES (?,?,?,?);";

        Connection connection = dbConnection.getConnection();

        try (PreparedStatement preparedStatement = connection.prepareStatement(stringSQL)) {
            preparedStatement.setString(1, gamePlayerData.getNPlayer().getUUID().toString());
            preparedStatement.setString(2, gamePlayerData.getNPlayer().getName());
            preparedStatement.setInt(3, gamePlayerData.getPlayedGames());
            preparedStatement.setInt(4, gamePlayerData.getWonGames());

            preparedStatement.execute();
        } catch (SQLException troubles) {
            throw new SQLPlayerDataException(String.format("Cannot create player data in database. Name: %s", gamePlayerData.getNPlayer().getName()), troubles);
        }
    }

    @Override
    public void resetPlayerData(@NotNull GamePlayerData gamePlayerData) throws SQLPlayerDataException {
        String stringSQL = "UPDATE player_data " +
                "SET player_playedgames=?, player_wongames=? " +
                "WHERE player_uuid=?;";

        Connection connection = dbConnection.getConnection();

        try (PreparedStatement preparedStatement = connection.prepareStatement(stringSQL)) {
            preparedStatement.setInt(1, 0);
            preparedStatement.setInt(2, 0);
            preparedStatement.setString(3, gamePlayerData.getNPlayer().getUUID().toString());

            preparedStatement.execute();
        } catch (SQLException troubles) {
            throw new SQLPlayerDataException(String.format("Cannot reset player data in database. Name: %s", gamePlayerData.getNPlayer().getName()), troubles);
        }
    }

    @Override
    public void updateNickname(@NotNull GamePlayer player) throws SQLPlayerDataException {
        String stringSQL = "UPDATE player_data " +
                "SET player_name=? " +
                "WHERE player_uuid=?;";

        Connection connection = dbConnection.getConnection();

        try (PreparedStatement preparedStatement = connection.prepareStatement(stringSQL)) {
            preparedStatement.setString(1, player.getName());
            preparedStatement.setString(2, player.getUUID().toString());

            preparedStatement.execute();
        } catch (SQLException troubles) {
            throw new SQLPlayerDataException(String.format("Create player token data in database. Name: %s", player.getName()), troubles);
        }
    }

    @Override
    public void savePlayerData(@NotNull GamePlayerData gamePlayerData) throws SQLPlayerDataException {
        String stringSQL = "UPDATE player_data " +
                "SET player_playedgames=?, player_wongames=? " +
                "WHERE player_uuid=?;";

        Connection connection = dbConnection.getConnection();

        try (PreparedStatement preparedStatement = connection.prepareStatement(stringSQL)) {
            preparedStatement.setInt(1, gamePlayerData.getPlayedGames());
            preparedStatement.setInt(2, gamePlayerData.getWonGames());
            preparedStatement.setString(3, gamePlayerData.getNPlayer().getUUID().toString());

            preparedStatement.execute();
        } catch (SQLException troubles) {
            throw new SQLPlayerDataException(String.format("Cannot save player data in database. Name: %s", gamePlayerData.getNPlayer().getName()), troubles);
        }
    }

    @Override
    public boolean containsPlayerData(UUID playerUUID) throws SQLPlayerDataException {
        String stringSQL = "SELECT COUNT(1) FROM player_data WHERE player_uuid=?;";

        Connection connection = dbConnection.getConnection();

        try (PreparedStatement preparedStatement = connection.prepareStatement(stringSQL)) {

            preparedStatement.setString(1, playerUUID.toString());

            ResultSet resultSet = preparedStatement.executeQuery();

            return resultSet.getInt(1) == 1;

        } catch (SQLException troubles) {
            throw new SQLPlayerDataException(String.format("Cannot get player token data in database. UUID: %s", playerUUID.toString()), troubles);
        }
    }

    private void initTables() {
        String sqlQueryPlayers = "CREATE TABLE IF NOT EXISTS player_data " +
                "(" +
                "player_uuid CHAR(36) NOT NULL," +
                "player_name VARCHAR(16) NOT NULL," +
                "player_playedgames INT(255) NOT NULL," +
                "player_wongames INT(255) NOT NULL," +
                "PRIMARY KEY (player_uuid, player_name)" +
                ");";

        Connection connection = this.dbConnection.getConnection();

        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQueryPlayers)) {
            preparedStatement.execute();
        } catch (SQLException troubles) {
            troubles.printStackTrace();
        }

    }
}
