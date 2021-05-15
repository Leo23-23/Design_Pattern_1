Adresse Git :  https://github.com/Leo23-23/Design_Pattern_1

Groupe : Leo - Ziad - Thibaut

Pour lancer le projet, nous utilisons mvn exec:java

Il faut changer les valeurs dans config.properties pour acceder a la base de donnée car nous les utilisons dans la classe Gestion BDD.


=>La classe principale App.Java contient notre main. 
On y affiche le menu principale dans une boucle do while.
On reprend les proprietés tel que " persistance" qui sont definies dans config.properties
On y instancie une nouvelle Gestion_BDD ma base avec laquelle on pourra appeler toutes les methodes defini dans cette classe.
On utilise un Switch pour conditionner sur le choix de l' utilisateur.


=>La classe ConfigLoader.java qui a eté fourni nous a permi de lire les information qui etait dans config.properties

=>La classe Gestion_BDD est dans laquelle nous allons connecter notre java à postgres en utilisant jdbc. On a du ajouter des dependance à JAVA_PROJECT / Maven_dependancies / postgresql 42
Pour raccourcir le nom des variables, on a mis les valeurs dont on avait besoins depuis config.properties dans des variables public comme jdbcUrl
La methode connexion m' a permi de me connecter à ma base de donné , Je prends soins de ne pas la .close pour pouvoir la reutiliser dans d' autre methodes.
la methode afficher_creneau_rdv_dispo() va afficher la disponibilité des medecin selon la date qu' on entre en parametre
la methode  Get_client_rdv()  affiche tout les rendez vous d' un client dont on connait le nom
la methode Rdv_Disponible_veterinaire nous permet de savoir si un veterinaire est disponible ou non
la methode PrendreRdv() permet d' inserer un nouveau rendez vous dans la base de donnée
la methode verifier_rdv_exist () permet de verifier si un rendez vous existe bien dans ntre base de donnée.
la methode SupprimerRdv() permet de deletre une ligne de notre table rendez vous dans la base de donnée.


=>La classe Menu est la classe dans laquelle nous allons nous occuper de notre menu principal.
On place dans une liste qu' on append a chaque fois les elements de notre menu.
la methode f_choix_utilisateur() permet a l' utilisateur d' entrer un int.
la methode  f_choix_str() permet à l' utilisateur d entrer une chaine de caractere
la methode toString permet de boucler sur les elements de notre liste en mettant à chaque fois dans msge la valeur en cours, et en terminant par un retour a la ligne.
