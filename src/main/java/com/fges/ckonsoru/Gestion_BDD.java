package com.fges.ckonsoru;
import java.sql.*;
import java.util.Properties;

public class Gestion_BDD extends Menu{
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
        }
        catch(SQLException e){ 
            System.out.println("erreur de connexion a la base de donnée");
            e.printStackTrace();
        }
    }
    public void deconnexion_de_bdd(){
        try {
            connexion.close();
            System.out.println("dis-connected");
        } catch (Exception e) {
            System.out.println("Erreur de fermeture de la connexion a la base de donnée");
        }
        
    }
}
