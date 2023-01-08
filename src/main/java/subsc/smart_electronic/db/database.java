/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package subsc.smart_electronic.db;

import java.sql.*;

/**
 *
 * @author Admin
 */
public class database {

    private static String urlConnection = "jdbc:sqlserver://115.73.212.222:8888;databaseName=smartelectric;encrypt=true;trustServerCertificate=true;";
//    Connection conn;
    private static String UserName = "omega";
    private static String password = "123";

    public database() {
    }

    public static Connection ConnectDB() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection conn = DriverManager.getConnection(urlConnection, UserName, password);
            return conn;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
}
   
//    private static String urlConnection = "jdbc:sqlserver://ADMIN;databaseName=smartelectric;encrypt=true;trustServerCertificate=true;";
////    Connection conn;
//    private static String UserName = "";
//    private static String password = "";
//
//    public database() {
//    }
//
//    public static Connection ConnectDB() {
//        try {
//            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
//            Connection conn = DriverManager.getConnection(urlConnection, UserName, password);
//            return conn;
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//    
//}
   

