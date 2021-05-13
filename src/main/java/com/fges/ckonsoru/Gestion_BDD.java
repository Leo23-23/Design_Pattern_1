package com.fges.ckonsoru;
import java.sql.*;
import java.util.Properties;

public class Gestion_BDD {
    public String jdbcUrl;
    public String  username;
    public String password;

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
            Connection connexion = DriverManager.getConnection(this.jdbcUrl, this.username,this.password);
            System.out.println("connected");
            connexion.close();
        }
        catch(SQLException e){ 
            System.out.println("erreur de connexion a la base de donnée");
            e.printStackTrace();
        }
    }
}
