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
        
            System.out.println("Bienvenue sur Clinique Konsoru !");
            Gestion_BDD ma_base_D = new Gestion_BDD();
            //ma_base_D.connexion_a_bdd();
            Menu menu_principal = new Menu();
            Integer choix_utilisateur ; 
            do {
                System.out.println(menu_principal);
                System.out.println("votre choix :");
                choix_utilisateur =  menu_principal.f_choix_utilisateur();
                switch (choix_utilisateur) {
                    case 1:
                    //afficher les créneaux de rendez-vous disponibles pour une date donnée
                        System.out.println("Créneaux disponibles");
                        ma_base_D.afficher_creneau_rdv_dispo();
                        break;
                    case 2: //lister les rendez-vous d’un client
                        System.out.println("Affichage des rendez vous par client");
                        break;
                    case 3://prendre un rendez-vous (date, créneau, nom vétérinaire, nom client)
                        System.out.println("Bienvenu dans le menu 3");
                        break;
                    case 4://supprimer un rendez-vous
                        System.out.println("Bienvenu dans le menu 4");
                        break;
                    case 9: // quitter
                        System.out.println("Au revoir");
                        break;
                    default:
                        System.out.println("Je ne connais pas cette commande");
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
