package com.db;

import java.sql.*;

public class jdbcUtil {

    public static void close(PreparedStatement pstmt) {
        try {
            pstmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void close(ResultSet rs) {
        try {
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static Connection getConnection() {
        Connection con;
        String url = "jdbc:sqlite:/C:\\DEV\\Sqlite_databases\\mission1_wifi.db";

        try {
            Class.forName("org.sqlite.JDBC");
            con = DriverManager.getConnection(url);
            return con;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void close(Connection con) {
        try {
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void commit(Connection con) {
        try {
            con.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void rollback(Connection con) {
        try {
            con.rollback();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
