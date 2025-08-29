package com.app.financiero.ejemplojdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EjemploJDBC {

    public static void main(String[] args) {

        try {
            String url = "jdbc:postgresql://ep-cold-violet-a8gifs75-pooler.eastus2.azure.neon.tech/netflix";//"jdbc:postgresql://ep-cold-violet-a8gifs75-pooler.eastus2.azure.neon.tech/netflix";
            String usuario = "neondb_owner";
            String contrasena = "npg_2wvn1IVuiyFl";

            Connection conexion = DriverManager.getConnection(url, usuario, contrasena);
            Statement consulta = conexion.createStatement();

            //ResultSet rs = consulta.executeQuery("SELECT * FROM netflix_shows ");
            ResultSet rs = consulta.executeQuery("SELECT * FROM pg_catalog.pg_tables;");

            while (rs.next()) {
                // System.out.println("ID: " + rs.getString("show_id") + ", Titulo: " + rs.getString("title"));
                System.out.println(rs.getString(1) + " - " + rs.getString(2) + " - " + rs.getString(3));

            }
        } catch (SQLException ex) {
            Logger.getLogger(EjemploJDBC.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}