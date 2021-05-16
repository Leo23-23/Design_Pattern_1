package com.fges.ckonsoru;
import java.sql.*;

public class Prendre_rdv extends Gestion_BDD {

    public  Boolean Rdv_Indisponible_veterinaire (String nom_veterinaire, String horaire , String date) {
        connexion_a_bdd();        
        try {
            
            String My_request = "SELECT * FROM rendezvous"
                            + " WHERE rv_debut = '"+date +" "+ horaire + "' AND vet_id = (SELECT vet_id FROM veterinaire WHERE vet_nom = '" + nom_veterinaire + "') ;";
            
            PreparedStatement statement = connexion.prepareStatement(My_request);
            ResultSet res = statement.executeQuery();
            
            Integer compteur = 0;
                
            while (res.next()) {
                compteur ++;
            }
            
            //connexion.close();
            
            if (compteur > 0 ) {
                return true;
            }else{
                return false; // le rdv disponible 
            } 
            
        } catch (SQLException e) {
            System.out.println("Une erreur est apparue lors de l' affichage de la disponibilté du medecin : "+e);
        } 
        return false;
    }


    public int PrendreRdv() {
        
        super.connexion_a_bdd();  
        System.out.println("Prendre un rendez-vous : ");
        System.out.println("Entrer le nom du veterinaire : ");
        String choix_nom_veto = super.f_choix_str();
        System.out.println("Entrer le nom du client qui souhaite prendre rdv : ");
        String nom_client = super.f_choix_str();
        System.out.println("Entrer la date du rdv : ");
        String date_rdv = super.f_choix_str();
        System.out.println("Entrer un creneau : ");
        String choix_horaire = super.f_choix_str();
        if (this.Rdv_Indisponible_veterinaire(choix_nom_veto ,choix_horaire , date_rdv )) {
            System.out.println("Le rdv n' existe pas / n 'est pas disponible ");   
            return 0;
        }
        try {
            
            String My_request = "INSERT INTO rendezvous (vet_id, rv_debut, rv_client)" +
                            "    VALUES ((SELECT vet_id FROM veterinaire WHERE vet_nom = ?)," +
                            "        '"+date_rdv +" "+ choix_horaire + "'," +
                            "       ?);";
            
            PreparedStatement statement = connexion.prepareStatement(My_request);
            statement.setString(1, choix_nom_veto);
            statement.setString(2, nom_client);
            statement.executeUpdate();
            
            super.deconnexion_de_bdd();
            System.out.println("Rendez vous pris avec succes");
            return 1;
            
        } catch (SQLException e) {
            System.out.println("Erreur lors de la prise de rendez vous : " + e);
            return -1;
        }
       
    }

}
