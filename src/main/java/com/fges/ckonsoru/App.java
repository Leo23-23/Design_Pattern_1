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
            Menu menu_principal = new Menu();
            Integer choix_utilisateur ; 
            do {
                System.out.println(menu_principal);
                System.out.println("votre choix :");
                choix_utilisateur =  menu_principal.f_choix_utilisateur();
                switch (choix_utilisateur) {
                    case 1:
                    System.out.println("Bienvenu dans le menu 1");
                    break;
                    case 2:
                        System.out.println("Bienvenu dans le menu 2");
                        break;
                    case 3:
                        System.out.println("Bienvenu dans le menu 3");
                        break;
                    case 4:
                        System.out.println("Bienvenu dans le menu 4");
                        break;
                    case 5:
                        System.out.println("Bienvenu dans le menu 5");
                        break;
                    case 6:
                        System.out.println("Bienvenu dans le menu 6");
                        break;
                    case 7:
                        System.out.println("Bienvenu dans le menu 7");
                        break;
                    case 8:
                        System.out.println("Bienvenu dans le menu 8");
                        break;
                    case 9:
                        System.out.println("Au revoir");
                        break;
                    default:
                        break;
            }
        } while (choix_utilisateur != 9);
        
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
        /*ConfigLoader cf = new ConfigLoader();
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
            }*/
        /*System.out.println("Url : "
                +properties.getProperty("bdd.url"));
        System.out.println("Login : "
                +properties.getProperty("bdd.login"));
        System.out.println("Mdp : "
                +properties.getProperty("bdd.mdp"));*/

    }
    
}