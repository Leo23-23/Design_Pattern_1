package com.fges.ckonsoru;
import java.sql.*;
import java.time.*;



public class Sup_rendez_vous  extends Menu{
    Connection connexion = Gestion_BDD.connexion_a_bdd ();
    public Boolean verifier_rdv_exist (String nom_client, String horaire, String date) {
        //connexion_a_bdd();         
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
        
        Connection connexion = Gestion_BDD.connexion_a_bdd ();
        Gestion_time diff_time = new Gestion_time();
        System.out.println("verifier qu' un rendez_vous existe : ");
        System.out.println("Entrer le nom du client : ");
        String choix_nom_client_rdv_sup = super.f_choix_str();
        System.out.println("Entrer le nom du veterinaire : ");
        String choix_nom_veto_rdv_sup = super.f_choix_str();
        System.out.println("Entrer une date : ");
        String choix_date_sup = super.f_choix_str();
        System.out.println("Entrer un horaire : ");
        String choix_horaire_sup = super.f_choix_str();
        LocalTime time_now = LocalTime.now();
        if (!this.verifier_rdv_exist(choix_nom_client_rdv_sup, choix_horaire_sup, choix_date_sup)) {
            System.out.println("Le rendez_vous n' existe pas");
            return 0;   
        }
        int diff_hour = diff_time.Get_hour(time_now, choix_horaire_sup);
        int diff_minute = diff_time.Get_minute(time_now, choix_horaire_sup);
        int diff_seconde = diff_time.Get_seconde(time_now, choix_horaire_sup);
        try {
            
            String My_request = "DELETE FROM rendezvous"
                    + " WHERE rv_client = ? AND rv_debut = '"+choix_date_sup+" "+ choix_horaire_sup + "';";

            PreparedStatement statement = connexion.prepareStatement(My_request);
            statement.setString(1, choix_nom_client_rdv_sup);
            statement.executeUpdate();

            System.out.println("Suppression du rdv avec succes");
           

           try {
                String My_request2 = "INSERT INTO annulation (ann_client, ann_creneau, vet_id, ann_delai)"+
                    "  VALUES (? , "+
                    "         '"+choix_date_sup+" "+choix_horaire_sup+"' ," +
                    "          (SELECT vet_id FROM veterinaire WHERE vet_nom = ?) , "+
                    "          '"+diff_hour+":"+diff_minute+":"+diff_seconde+"');";
                
                PreparedStatement statement2 = connexion.prepareStatement(My_request2);
                statement2.setString(1, choix_nom_client_rdv_sup);
                statement2.setString(2, choix_nom_veto_rdv_sup );
               // statement2.setString(2, choix_nom_veto_rdv_sup);
                statement2.executeUpdate();
           } catch (SQLException e) {
                System.out.println("Une erreur est apparue lors de l' insertion dans annulation : " + e);
           }
           return  1; 

        } catch (SQLException e) {
            System.out.println("Une erreur est apparue lors de la tentative de suppression du rdv : " + e);
            return -1;
        }
    } 
}
