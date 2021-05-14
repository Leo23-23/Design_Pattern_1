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



    // je voudrais une fonction quime renvoi TRUE / FALSE en fonction de la disponibilité d' un rendez vous => il me faut le nom du veterinaire et un horraire pour avoir sa disponibilité ou non
    public  Boolean Rdv_Disponible_veterinaire (String nom_veterinaire, String horaire) {
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
            
            String My_request = "SELECT * FROM disponibilite"
                            + " WHERE dis_debut = '"+ horaire + "' AND vet_id = (SELECT vet_id FROM veterinaire WHERE vet_nom = '" + nom_veterinaire + "') ;";
            
            String My_request2 = "SELECT * FROM rendezvous"
                            + " WHERE rv_debut = '2021-03-18 " + horaire + "' AND vet_id = (SELECT vet_id FROM veterinaire WHERE vet_nom = '" + nom_veterinaire + "') ;";
            
            PreparedStatement statement = connexion.prepareStatement(My_request);
            ResultSet res = statement.executeQuery();
            
            PreparedStatement statement2 = connexion.prepareStatement(My_request2);
            ResultSet res2 = statement2.executeQuery();
            
            Integer compteur = 0;
            Integer compteur2 = 0;
                
            while (res.next()) {
                compteur ++;
            }
                
            while (res2.next()) {
                compteur2 ++;
            }
            
            connexion.close();
            
            return compteur > 0 && compteur2 == 0;
            
        } catch (SQLException e) {
            System.out.println("Une erreur est apparue lors de l' affichage de la disponibilté du medecin : "+e);
        }
        
        return false;
    }



    public void PrendreRdv(String nom_veterinaire, String nom_client, String horaire) {
        
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
            
            String My_request = "INSERT INTO rendezvous (vet_id, rv_debut, rv_client)" +
                            "    VALUES ((SELECT vet_id FROM veterinaire WHERE vet_nom = '" + nom_veterinaire + "')," +
                            "        '18-03-2021 " + horaire + "'," +
                            "        '" + nom_client + "');";
            
            PreparedStatement statement = connexion.prepareStatement(My_request);
            statement.executeUpdate();
            
            connexion.close();
            System.out.println("Rendez vous pris avec succes");
            
            
        } catch (SQLException e) {
            System.out.println("Erreur lors de la prise de rendez vous : " + e);
        
        }
       
    }
    
    

    // pour pouvoir supprimer un rendez vous , je dopis deja commencer par veridier qu' il existe bien avec la fonction suivante : verifier_rdv_exist()
    public Boolean verifier_rdv_exist (String nom_client, String horaire) {
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
            
            String My_request = "SELECT * FROM rendezvous"
                            + " WHERE rv_debut = '2021-03-18 " + horaire + "' AND rv_client = '" + nom_client + "' ;";
            
            PreparedStatement statement = connexion.prepareStatement(My_request);
            ResultSet res = statement.executeQuery();
            
            Integer compteur = 0;
                
            while (res.next()) {
                compteur ++;
            }
            
            connexion.close();

            if(compteur == 0){
                System.out.println("Le rendez_vous n' existe pas");
            }
            return compteur > 0; // si true, alors, le rendez vous existe, si false, alors le rendez vous n' existe pas
            
        } catch (SQLException e) {
            System.out.println("une erreur est apparue lors de la verifiacation de l 'existence du rendez_vous : "+e);
            return false;
        }   
    }
    
    
    /*public static int SupprimerRdv(String nom_client, String horaire) {
        
        // Vérification de la validité des informations
        
        if (!DatabaseManager.RdvExists(nom_client, horaire)) {
            System.out.println("This rdv doesn't exist");
            return -1;
        }
        
        try {
            DatabaseManager.connection = DriverManager.getConnection(jdbcUrl, username, password);
            System.out.println("Connection to database succeeded");
        } catch (SQLException ex) {
            System.out.println("Error occured while connecting to database : "+ex);
        }
        
        try {
            
            String request = "DELETE FROM rendezvous"
                    + " WHERE rv_client = '" + nom_client + "' AND rv_debut = '2021-03-18 " + horaire + "';";
            
            PreparedStatement statement = DatabaseManager.connection.prepareStatement(request);
            statement.executeQuery();
            
            DatabaseManager.connection.close();
            
            return 1;
            
        } catch (SQLException ex) {
            System.out.println("An error occured while deleting the client's rdv : " + ex);
        }
        
        return 0;
    }*/
}
