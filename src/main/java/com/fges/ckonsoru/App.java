/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fges.ckonsoru;
import java.sql.*;

import java.util.Properties;

/**
 * Launch the App
 * @author julie.jacques
 */
public class App {
    
    public static void main(String args[])throws Exception{
        /*String jdbcURL = "jdbc:postgresql://localhost:5432/test";
        String username = "postgres";
        String password = "RcathoL(PostgreSQL)23;";*/
        
        System.out.println("Bienvenue sur Clinique Konsoru !");
        /*try { 
            Connection connexion = DriverManager.getConnection(jdbcURL, username, password);
            System.out.println("connected");
            connexion.close();
            }
            catch(SQLException e){ 
              System.out.println("erreur de connexion a la base de donnée");
              e.printStackTrace();
            }*/
        
        // chargement de la configuration de la persistence
        ConfigLoader cf = new ConfigLoader();
        Properties properties = cf.getProperties();
        System.out.println("Mode de persistence : "
                +properties.getProperty("persistence"));
        try { 
            Connection connexion = DriverManager.getConnection(properties.getProperty("bdd.url"), properties.getProperty("bdd.login"), properties.getProperty("bdd.mdp"));
            System.out.println("connected");
            connexion.close();
            }
            catch(SQLException e){ 
              System.out.println("erreur de connexion a la base de donnée");
              e.printStackTrace();
            }
        /*System.out.println("Url : "
                +properties.getProperty("bdd.url"));
        System.out.println("Login : "
                +properties.getProperty("bdd.login"));
        System.out.println("Mdp : "
                +properties.getProperty("bdd.mdp"));*/

    }
    
}
