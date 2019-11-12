
package com.kpf.admistrativo.util;

import java.sql.*;


public class Conexion {

    static Connection cn = null;

    public static Connection conexionBD() throws SQLException {

        String url = "jdbc:mysql://localhost:3306/administrativo";
        String user = "root";
        String password = "";

        if (cn == null) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                cn = DriverManager.getConnection(url, user, password);
                System.out.println("si");
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        return cn;
    }

    public static void cerrarBD() throws SQLException {
        if (cn != null) {
            cn.close();
        }
    }
    
//    public static void main(String[] args) throws SQLException {
//        conexionBD();
//    }

}
