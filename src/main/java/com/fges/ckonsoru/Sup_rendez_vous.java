package com.fges.ckonsoru;
import java.sql.*;


public class Sup_rendez_vous  extends Gestion_BDD{


    public Boolean verifier_rdv_exist (String nom_client, String horaire, String date) {
        connexion_a_bdd();         
        try {
            
            String My_request = "SELECT * FROM rendezvous"
                            + " WHERE rv_debut = '"+ date +" "+ horaire + "' AND rv_client = '" + nom_client + "' ;";
            
            PreparedStatement statement = connexion.prepareStatement(My_request);
            ResultSet res = statement.executeQuery();
            
            Integer compteur = 0;
                
            while (res.next()) {
                compteur ++;
            }
            
            //connexion.close();
            return compteur > 0; // si true, alors, le rendez vous existe, si false, alors le rendez vous n' existe pas
            
        } catch (SQLException e) {
            System.out.println("une erreur est apparue lors de la verifiacation de l 'existence du rendez_vous : "+e);
            return false;
        }   
    }

    public int SupprimerRdv() {
        
       super. connexion_a_bdd();
        System.out.println("verifier qu' un rendez_vous existe : ");
        System.out.println("Entrer le nom du client : ");
        String choix_nom_client_rdv_sup = super.f_choix_str();
        System.out.println("Entrer une date : ");
        String choix_date_sup = super.f_choix_str();
        System.out.println("Entrer un horaire : ");
        String choix_horaire_sup = super.f_choix_str();
        if (!this.verifier_rdv_exist(choix_nom_client_rdv_sup, choix_horaire_sup, choix_date_sup)) {
            System.out.println("Le rendez_vous n' existe pas");
            return 0;   
        }
        try {
            
            String My_request = "DELETE FROM rendezvous"
                    + " WHERE rv_client = '" + choix_nom_client_rdv_sup + "' AND rv_debut = '"+choix_date_sup+" "+ choix_horaire_sup + "';";
            
            PreparedStatement statement = connexion.prepareStatement(My_request);
            statement.executeUpdate();
            
            super.deconnexion_de_bdd();
            
            System.out.println("Suppression du rdv avec succes");
            return  1;
            
        } catch (SQLException e) {
            System.out.println("Une erreur est apparue lors de la tentative de suppression du rdv : " + e);
            return -1;
        }
    } 
}
