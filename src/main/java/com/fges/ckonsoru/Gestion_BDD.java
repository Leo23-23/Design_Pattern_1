package com.fges.ckonsoru;
import java.sql.*;
import java.util.Properties;

public class Gestion_BDD {
    public String jdbcUrl;
    public String  username;
    public String password;
    public Connection connexion;

    ConfigLoader cf = new ConfigLoader();
    Properties properties = cf.getProperties();


    public Gestion_BDD (){ 
        this.jdbcUrl = properties.getProperty("bdd.url");
        this.username = properties.getProperty("bdd.login");
        this.password = properties.getProperty("bdd.mdp");
        /*try { 
            Connection connexion = DriverManager.getConnection(properties.getProperty("bdd.url"), properties.getProperty("bdd.login"), properties.getProperty("bdd.mdp"));
            System.out.println("connected");
            connexion.close();
        }
        catch(SQLException e){ 
            System.out.println("erreur de connexion a la base de donnée");
            e.printStackTrace();
        }*/
    }
    public void connexion_a_bdd (){
        /*System.out.println(this.jdbcUrl);
        System.out.println(this.username);
        System.out.println(this.password);*/
        try { 
            connexion = DriverManager.getConnection(this.jdbcUrl, this.username,this.password);
            System.out.println("connected");
            connexion.close();
        }
        catch(SQLException e){ 
            System.out.println("erreur de connexion a la base de donnée");
            e.printStackTrace();
        }
    }

    public void afficher_creneau_rdv_dispo (){
        try { 
            connexion = DriverManager.getConnection(this.jdbcUrl, this.username,this.password);
            System.out.println("connected");
            /*connexion.close();*/
        }
        catch(SQLException e){ 
            System.out.println("erreur de connexion a la base de donnée");
            e.printStackTrace();
        }
        try {
            String my_request = "WITH creneauxDisponibles AS " +
                            "	(SELECT vet_nom, generate_series('18-03-2021'::date+dis_debut," +
                            "						   '18-03-2021'::date+dis_fin-'00:20:00'::time," +
                            "						   '20 minutes'::interval) debut" +
                            "	FROM disponibilite" +
                            "		INNER JOIN veterinaire" +
                            "			ON veterinaire.vet_id = disponibilite.vet_id" +
                            "	WHERE dis_jour = EXTRACT('DOW' FROM '18-03-2021'::date)" +
                            "	ORDER BY vet_nom, dis_id)," +
                            "	creneauxReserves AS " +
                            "	(SELECT vet_nom, rv_debut debut" +
                            "	 FROM rendezvous" +
                            "		INNER JOIN veterinaire" +
                            "		ON veterinaire.vet_id = rendezvous.vet_id" +
                            "		WHERE rv_debut " +
                            "		BETWEEN '18-03-2021'::date " +
                            "		AND '18-03-2021'::date +'23:59:59'::time)," +
                            "	creneauxRestants AS" +
                            "	(SELECT * FROM creneauxDisponibles" +
                            "	EXCEPT" +
                            "	SELECT * FROM creneauxReserves)" +
                            "SELECT * FROM creneauxRestants " +
                            "ORDER BY vet_nom, debut;";
            
            PreparedStatement statement = connexion.prepareStatement(my_request);
            ResultSet res = statement.executeQuery();
                
            while (res.next()) {
                System.out.println(res.getString(1) + " " + res.getString(2) );
            }
            
            connexion.close();
        } catch (Exception e) {
            System.out.println("une erreur est apparue en ouvrant la liste des creneaux disponible : " + e);
        }
    }

    
    
    
    //je voudrai une fonction qui affiche les rendez vous pris d' un client en donnant à la fonction le nom du client.
    public void Get_client_rdv (String Nom){
        try { 
            connexion = DriverManager.getConnection(this.jdbcUrl, this.username,this.password);
            System.out.println("connected");
            System.out.println("Voici la liste des rdv de "+ Nom );
            /*connexion.close();*/
        }
        catch(SQLException e){ 
            System.out.println("erreur de connexion a la base de donnée");
            e.printStackTrace();
        }
        try {
            
            String my_request = "SELECT rv_id, rv_debut, rv_client, vet_nom" +
                                " FROM rendezvous" +
                                " INNER JOIN veterinaire ON rendezvous.vet_id = veterinaire.vet_id" +
                                " WHERE rv_client = '" + Nom + "'" +
                                " ORDER BY rv_debut DESC;";
            
            PreparedStatement statement = connexion.prepareStatement(my_request);
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
            connexion.close();
           
            
        } catch (SQLException e) {
            System.out.println("Une erreur s' est produit pendant pour afficher les rendez vous du client : " + e);
        }
    }
}
