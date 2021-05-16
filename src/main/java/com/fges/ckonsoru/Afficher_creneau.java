package com.fges.ckonsoru;
import java.sql.*;

public class Afficher_creneau extends Menu {
    Connection connexion = Gestion_BDD.connexion_a_bdd ();
    public void afficher_creneau_rdv_dispo ( ){
        
        //super.connexion_a_bdd();
        System.out.println("Créneaux disponibles");
        System.out.println("Entrer une date : ");
        String date = super.f_choix_str();
        try {
            String my_request = "WITH creneauxDisponibles AS " +
                            "	(SELECT vet_nom, generate_series(?::date+dis_debut," +
                            "						  ?::date+dis_fin-'00:20:00'::time," +
                            "						   '20 minutes'::interval) debut" +
                            "	FROM disponibilite" +
                            "		INNER JOIN veterinaire" +
                            "			ON veterinaire.vet_id = disponibilite.vet_id" +
                            "	WHERE dis_jour = EXTRACT('DOW' FROM ?::date)" +
                            "	ORDER BY vet_nom, dis_id)," +
                            "	creneauxReserves AS " +
                            "	(SELECT vet_nom, rv_debut debut" +
                            "	 FROM rendezvous" +
                            "		INNER JOIN veterinaire" +
                            "		ON veterinaire.vet_id = rendezvous.vet_id" +
                            "		WHERE rv_debut " +
                            "		BETWEEN ?::date " +
                            "		AND ?::date +'23:59:59'::time)," +
                            "	creneauxRestants AS" +
                            "	(SELECT * FROM creneauxDisponibles" +
                            "	EXCEPT" +
                            "	SELECT * FROM creneauxReserves)" +
                            "SELECT * FROM creneauxRestants " +
                            "ORDER BY vet_nom, debut;";
            
            PreparedStatement statement = connexion.prepareStatement(my_request);
            statement.setString(1, date);
            statement.setString(2, date);
            statement.setString(3, date);
            statement.setString(4, date);
            statement.setString(5, date);
            ResultSet res = statement.executeQuery();
                
            while (res.next()) {
                System.out.println(res.getString(1) + " disponible le : " + res.getString(2) );
            }
            
           //connexion = Gestion_BDD.deconnexion_de_bdd();
        } catch (Exception e) {
            System.out.println("une erreur est apparue en ouvrant la liste des creneaux disponible : " + e);
        }
    } 
}
