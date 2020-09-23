package server;

import java.sql.SQLException;

public class Start {
    public static void main(String[] args) {
//        try {
//            DataBase.connectDB();
////            System.out.println("connect DataBase Ok");
////            DataBase.clearTableDB();
////            DataBase.insertDefaultUsersDB();
//            DataBase.readDataDB();
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            DataBase.disconnectDB();
//        }

        new Server();

    }
}
