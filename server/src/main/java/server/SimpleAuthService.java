package server;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SimpleAuthService implements AuthService {
    private static Statement stmt;

    public static class UserData {
        String login;
        String password;
        String nickname;

        public UserData(String login, String password, String nickname) {
            this.login = login;
            this.password = password;
            this.nickname = nickname;
        }
    }

    List<UserData> users;

    public SimpleAuthService() {
        users = new ArrayList<>();

        try {
            DataBase.connectDB();
            System.out.println("connect DataBase Ok");
//            DataBase.clearTableDB();
//            DataBase.insertDefaultUsersDB();
            users.addAll(DataBase.readUsersFromDB());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DataBase.disconnectDB();
        }

//        for (int i = 1; i <= 10; i++) {
//            users.add(new UserData("login" + i, "pass" + i, "nick" + i));
//        }
//
//        users.add(new UserData("qwe" , "qwe" , "qwe" ));
//        users.add(new UserData("asd" , "asd" , "asd" ));
//        users.add(new UserData("zxc" , "zxc" , "zxc" ));
    }

    @Override
    public String getNicknameByLoginAndPassword(String login, String password) {
        for (UserData user : users) {
            if (user.login.equals(login) && user.password.equals(password)) {
                return user.nickname;
            }
        }
        return null;
    }

    @Override
    public boolean registration(String login, String password, String nickname) {
        for (UserData user : users) {
            if (user.login.equals(login) || user.nickname.equals(nickname)) {
                return false;
            }
        }
        users.add(new UserData(login , password, nickname ));
        try {
            DataBase.connectDB();
            DataBase.insertNewUsersDB(nickname, login, password);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DataBase.disconnectDB();
        }
        return true;
    }

    @Override
    public boolean changeNickname(String login, String password, String nickname, String new_nickname) {
        int i = 0;
        for (UserData user : users) {
            if (user.login.equals(login) && user.password.equals(password) && user.nickname.equals(nickname)) {
                users.remove(i);
                users.add(new UserData(login , password, new_nickname ));
                try {
                    DataBase.connectDB();
                    DataBase.changeNicknameUserDB(new_nickname, login, password, nickname);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    DataBase.disconnectDB();
                }
                return true;
            }
            i++;
        }
        return false;
    }


}
