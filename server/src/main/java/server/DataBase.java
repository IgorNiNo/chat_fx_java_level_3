package server;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DataBase {
    private static Connection connection;
    private static Statement stmt;
    private static PreparedStatement psInsert;

    public static void connectDB() throws ClassNotFoundException, SQLException {
        Class.forName("org.sqlite.JDBC");
        connection = DriverManager.getConnection("jdbc:sqlite:main.db");
        stmt = connection.createStatement();
    }

    public static void disconnectDB() {
        try {
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void clearTableDB() throws SQLException {
        stmt.executeUpdate("DELETE FROM users;");
    }

    public static void insertDefaultUsersDB() throws SQLException {
        stmt.executeUpdate("INSERT INTO users (nickname, login, password) VALUES ('qwe', 'qwe', 'qwe'), ('asd', 'asd', 'asd'), ('zxc', 'zxc', 'zxc');");
    }

    public static void insertNewUsersDB(String nickname, String login, String password) throws SQLException {
        String str = "INSERT INTO users (nickname, login, password) VALUES ('" + nickname + "', '" + login + "', '" + password + "');";
        stmt.executeUpdate(str);
    }

    public static void changeNicknameUserDB(String newNickname, String login, String password, String nickname) throws SQLException {
        String str = "UPDATE users SET nickname = '" + newNickname + "' WHERE nickname ='" + nickname + "' AND login = '" + login + "' AND password = '" + password + "';";
        stmt.executeUpdate(str);
    }

    public static List<SimpleAuthService.UserData> readUsersFromDB() throws SQLException {
        ResultSet rs = stmt.executeQuery("SELECT nickname, login, password FROM users;");
        List<SimpleAuthService.UserData> usersDB;
        usersDB = new ArrayList<>();
        String nickname;
        String login;
        String password;
        while (rs.next()) {
            nickname = rs.getString("nickname");
            login = rs.getString("login");
            password = rs.getString("password");
            usersDB.add(new SimpleAuthService.UserData(login, password, nickname));
        }
        rs.close();
        return usersDB;
    }

}



/*
CREATE TABLE users (
    id       INTEGER PRIMARY KEY AUTOINCREMENT,
    nickname TEXT    UNIQUE,
    login    TEXT    UNIQUE,
    password TEXT
);
 */
