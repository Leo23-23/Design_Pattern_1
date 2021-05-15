package com.fges.ckonsoru;
import java.util.Scanner;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Menu {
    public String en_tete;
    public ArrayList<String> mes_choix;
    public Integer choix_utilisateur;
    public String choix_utilisateur2;
    /*public LocalDateTime choix_horaire;*/

    public Menu() {
        this.en_tete = "Actions Disponibles :";
        
        ArrayList<String> liste = new ArrayList<>();
        liste.add("1 - Afficher les creneaux disponibles pour une date donnee");
        liste.add("2 - Lister les rdv passes, presents et a venir d'un client");
        liste.add("3 - Prendre un rdv");
        liste.add("4 - Supprimer un rdv");
        liste.add("9 - Quitter");
        this.mes_choix = liste;

        
        
    }
    //dans ma classe menu , je voudrais une fonctions qui ecoute la reponse de l' utilisateur et la retourne : 
    public Integer f_choix_utilisateur(){
        try {
            Scanner sc = new Scanner(System.in);
            choix_utilisateur = sc.nextInt();
            return choix_utilisateur;
            
        } catch (Exception e) {
            System.out.println("Je m' attends a un nombre");
            return 0;
        }    
    }


    public String f_choix_str(){
        try {
            Scanner sc = new Scanner(System.in);
            choix_utilisateur2 = sc.nextLine();
            return choix_utilisateur2;
            
        } catch (Exception e) {
            System.out.println("Je m' attends a un mot");
            return  "NON";
        }
        
    } 

    @Override
    public String toString() {
        String msge = this.en_tete + '\n';
        for (int i=0; i<this.mes_choix.size(); i++) {
            msge +=  this.mes_choix.get(i) + "\n";
        }

        return msge;
    };

}

