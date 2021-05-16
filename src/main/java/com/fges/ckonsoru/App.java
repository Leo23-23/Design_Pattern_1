/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fges.ckonsoru;
import java.util.Properties;
import java.sql.*;

/**
 * Launch the App
 * @author julie.jacques
 */
public class App {
    
    
    public static void main(String args[])throws Exception{
            
        System.out.println("Bienvenue sur Clinique Konsoru !");
        //chargement de la configuration de la persistence
        ConfigLoader cf = new ConfigLoader();
        Properties properties = cf.getProperties();
        System.out.println("Mode de persistence : "
            +properties.getProperty("persistence"));
         Gestion_BDD.connexion_a_bdd ();


        //Gestion_BDD ma_base_D = new Gestion_BDD();
        Afficher_creneau mes_creneaux = new Afficher_creneau();
        Rendez_vous_client les_rdv_client = new Rendez_vous_client();
        Prendre_rdv mon_rdv = new Prendre_rdv();
        Sup_rendez_vous sup_mon_rdv = new  Sup_rendez_vous();
        Menu menu_principal = new Menu();
        Integer choix_utilisateur;
        do {
            System.out.println(menu_principal);
            System.out.println("votre choix :");
             choix_utilisateur =  menu_principal.f_choix_utilisateur();
             //ma_base_D. connexion_a_bdd ();
            switch (choix_utilisateur) {
                case 1:
                    //afficher les créneaux de rendez-vous disponibles pour une date donnée
                    mes_creneaux.afficher_creneau_rdv_dispo();
                    break;
                case 2: //lister les rendez-vous d’un client
                    les_rdv_client.Get_client_rdv();
                    break;
                case 3://prendre un rendez-vous (date, créneau, nom vétérinaire, nom client) 
                    mon_rdv.PrendreRdv();
                    break;
                case 4://supprimer un rendez-vous
                    sup_mon_rdv.SupprimerRdv();
                    break;
                case 9: // quitter
                    System.out.println("Au revoir");
                    Gestion_BDD.deconnexion_de_bdd();
                    break;
                default:
                    System.out.println("Je ne connais pas cette commande");
                    break;
            }       
        } while (choix_utilisateur != 9);      
    }  
}
