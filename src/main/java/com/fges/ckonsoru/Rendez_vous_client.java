package com.fges.ckonsoru;
import java.sql.*;

public class Rendez_vous_client  extends Menu{
    Connection connexion = Gestion_BDD.connexion_a_bdd ();

    public void Get_client_rdv (){
        //super.connexion_a_bdd();
        System.out.println("Affichage des rendez vous par client");
        System.out.println("Entrer le nom du client : ");
        String Nom = super.f_choix_str();
        try {
            
            String my_request = "SELECT rv_id, rv_debut, rv_client, vet_nom" +
                                " FROM rendezvous" +
                                " INNER JOIN veterinaire ON rendezvous.vet_id = veterinaire.vet_id" +
                                " WHERE rv_client = ?" +
                                " ORDER BY rv_debut DESC;";
            
            PreparedStatement statement = connexion.prepareStatement(my_request);
            statement.setString(1,  Nom);
            ResultSet res = statement.executeQuery();
            
            
            Integer compteur = 0; // pour qu on puisse savoir si il y a eu plus de 0 resultat trouvé
            
            while (res.next()) {
                System.out.println(res.getString(1) + ": " + res.getString(2) + " avec " + res.getString(4) );
                compteur++;
            }
            // a la fin de la boucle si compteur est toujour = 0 => pas de resultat
            if (compteur == 0) {
                System.out.println("Le client n' a pas pris de rendez vous.");
            }
            System.out.println( compteur + " resultat(s) trouvés ");
            //super.deconnexion_de_bdd();
           
            
        } catch (SQLException e) {
            System.out.println("Une erreur s' est produit pendant pour afficher les rendez vous du client : " + e);
        }
    }
}
