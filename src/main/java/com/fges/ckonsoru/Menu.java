package com.fges.ckonsoru;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Menu {
    public String en_tete;
    public ArrayList<String> mes_choix;
    public Integer choix_utilisateur;

    public Menu() {
        this.en_tete = "Actions Disponibles :";
        
        ArrayList<String> liste = new ArrayList<>();
        liste.add("1 - Afficher les créneaux disponibles pour une date donnée");
        liste.add("2 - Lister les rdv passés, présents et à venir d'un client");
        liste.add("3 - Prendre un rdv");
        liste.add("4 - Supprimer un rdv");
        liste.add("9 - Quitter");
        this.mes_choix = liste;

        
        
    }
    //dans ma classe menu , je voudrais une fonctions qui ecoute la reponse de l' utilisateur et la retourne : 
    public Integer f_choix_utilisateur(){
        Scanner sc = new Scanner(System.in);
        choix_utilisateur = sc.nextInt();
        return choix_utilisateur;
    } 




    @Override
    public String toString() {
        String msge = this.en_tete + '\n';
        for (int i=0; i<this.mes_choix.size(); i++) {
            msge += "\t" + this.mes_choix.get(i) + "\n";
        }

        return msge;
    };

}

