package com.fges.ckonsoru;
import java.sql.*;


public class Gestion_BDD {
    public static Connection connexion ;
    public static String UrlJDBC = "jdbc:postgresql://localhost:5432/ckonsoru" ;
    public static String userlog= "postgres" ;
    public static String mdplog = "RcathoL(PostgreSQL)23;" ;
 
    private  Gestion_BDD (){}  // faire en sorte que le constructeur soit privé  qu' on ne puisse pas instancier une Gestion_BDD ailleurs

    public static Connection connexion_a_bdd (){ // faire une methode static + public + mm type que la classe pour qu on puisse l' utiliser ailleurs
        /*System.out.println(this.jdbcUrl);
        System.out.println(this.username);
        System.out.println(this.password);*/
            try { 
                if (connexion== null) {
                    connexion = DriverManager.getConnection(UrlJDBC, userlog,mdplog);
                    System.out.println("connected");
                    
                }
               
            }
            catch(SQLException e){ 
                System.out.println("erreur de connexion a la base de donnée");
                e.printStackTrace();
            } 
            return connexion;
    }
    public static void deconnexion_de_bdd(){
        try {
            connexion.close();
            System.out.println("dis-connected");
        } catch (Exception e) {
            System.out.println("Erreur de fermeture de la connexion a la base de donnée");
        } 
    }
}
