/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fges.ckonsoru;
import java.util.Properties;

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


        Gestion_BDD ma_base_D = new Gestion_BDD();
        Afficher_creneau mes_creneaux = new Afficher_creneau();
        Rendez_vous_client les_rdv_client = new Rendez_vous_client();
        Menu menu_principal = new Menu();
        Integer choix_utilisateur ; 
        String choix_nom_client ; 
        do {
            System.out.println(menu_principal);
            System.out.println("votre choix :");
            choix_utilisateur =  menu_principal.f_choix_utilisateur();
            switch (choix_utilisateur) {
                case 1:
                    //afficher les créneaux de rendez-vous disponibles pour une date donnée
                    mes_creneaux.afficher_creneau_rdv_dispo();
                    break;
                case 2: //lister les rendez-vous d’un client
                    les_rdv_client.Get_client_rdv();
                    break;
                case 3://prendre un rendez-vous (date, créneau, nom vétérinaire, nom client)
                    System.out.println("Prendre un rendez-vous : ");
                    System.out.println("Entrer le nom du veterinaire : ");
                    String choix_nom_veto = menu_principal.f_choix_str();
                    System.out.println("Entrer le nom du client qui souhaite prendre rdv : ");
                    String choix_nom_client_rdv = menu_principal.f_choix_str();
                    System.out.println("Entrer un creneau : ");
                    String choix_horaire = menu_principal.f_choix_str();
                    if (!ma_base_D.Rdv_Disponible_veterinaire(choix_nom_veto, choix_horaire )) {
                        System.out.println("Le rdv n' existe pas / n 'est pas disponible ");
                        break;   
                    }
                    ma_base_D.PrendreRdv(choix_nom_veto, choix_nom_client_rdv, choix_horaire);
                    break;
                case 4://supprimer un rendez-vous
                    System.out.println("verifier qu' un redez_vous existe : ");
                    System.out.println("Entrer le nom du client : ");
                    String choix_nom_client_rdv_sup = menu_principal.f_choix_str();
                    System.out.println("Entrer un horaire : ");
                    String choix_horaire_sup = menu_principal.f_choix_str();
                    if (!ma_base_D.verifier_rdv_exist(choix_nom_client_rdv_sup, choix_horaire_sup)) {
                        System.out.println("Le rendez_vous n' existe pas");
                        break;    
                    }
                    ma_base_D.SupprimerRdv(choix_nom_client_rdv_sup,choix_horaire_sup);
                    break;
                case 9: // quitter
                    System.out.println("Au revoir");
                    break;
                default:
                    System.out.println("Je ne connais pas cette commande");
                    break;
            }       
        } while (choix_utilisateur != 9);      
    }  
}
