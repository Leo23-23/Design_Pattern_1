package com.fges.ckonsoru;
import java.sql.*;

public class Liste_annulation extends Menu  {
    Connection connexion = Gestion_BDD.connexion_a_bdd ();

    public void lister(){
        System.out.println("Liste des annulations :");
        try {
            
            String my_request = "SELECT ann_client, ann_creneau, ann_delai FROM annulation;";

            PreparedStatement statement = connexion.prepareStatement(my_request);
            ResultSet res = statement.executeQuery();
            
            
            Integer compteur = 0; // pour qu on puisse savoir si il y a eu plus de 0 resultat trouvé
            
            while (res.next()) {
                System.out.println(res.getString(1) + " le " + res.getString(2) + " ( " + res.getString(3) + " avant)" );
                compteur++;
            }
            // a la fin de la boucle si compteur est toujour = 0 => pas de resultat
            if (compteur == 0) {
                System.out.println("Il n' y a pas d' annulation.");
            }
            System.out.println( compteur + " annulation(s) trouvés ");
            //super.deconnexion_de_bdd();
           
            
        } catch (SQLException e) {
            System.out.println("Une erreur s' est produit durant l' affichage des annulations : " + e);
        }    
    }
}
