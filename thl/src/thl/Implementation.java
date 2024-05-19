package thl;
import java.util.Scanner;

public class Implementation {
    public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
        int choix = 0;
        boolean choixValide = false;
        while (!choixValide) {
            System.out.print("Voulez-vous créer un automate deterministe (1) ou non deterministe (2) : ");
            if (scanner.hasNextInt()) {
                choix = scanner.nextInt();
                if (choix == 1 || choix == 2) {
                    choixValide = true;
                } else { // NI 1 NI 2
                    System.out.println("Choix invalide. Veuillez choisir 1 pour deterministe ou 2 pour non deterministe.");
                    scanner.nextLine();
                }
            } else { // PAS UN ENTIER
                System.out.println("Entree invalide. Veuillez entrer un nombre entier.");
                scanner.nextLine();
            }
        }
     // NON DETERMINISTE
        if (choix == 2) {
            Alphabet a = new Alphabet(); //NB ALPHABETS
            a.AjoutAlphabet();
            Etat e = new Etat(); //NB ETATS
            e.ajouterEtat();
            AutomateND automate = new AutomateND(a, e);
            automate.SetAuto(a, e);
            while (true) {
                System.out.print("Nombre de transitions à ajouter : ");
                if (scanner.hasNextInt()) {
                    int n = scanner.nextInt();
                    scanner.nextLine();
                    if (n > 0) { //NB ENTIER >0 
                        for (int i = 0; i < n; i++) {
                            automate.TransitionAutomate(automate, e, a);
                            System.out.println();
                            automate.AffichageAutomate();
                        }
                        break; 
                    } else { //NB ENTIER <0
                        System.out.println("Erreur : Veuillez entrer un nombre entier strictement positif.");
                    }
                } else { //NB EST PAS UN ENTIER 
                    System.out.println("Erreur : Veuillez entrer un nombre entier.");
                    scanner.nextLine();
                }
            }
         // DETERMINISTE 
        } else {
            Alphabet a = new Alphabet(); //NB ALPHABETS
            a.AjoutAlphabet();
            Etat e = new Etat(); //NB ETATS
            e.ajouterEtat();
            AutomateD automate = new AutomateD(a, e);
            automate.SetAuto(a, e);
            while (true) {
                System.out.print("Nombre de transitions à ajouter : ");
                if (scanner.hasNextInt()) {//NB EST UN ENTIER
                    int n = scanner.nextInt();
                    scanner.nextLine();
                    if (n > 0) {//NB UN ENTIER >0
                        for (int i = 0; i < n; i++) {
                            automate.TransitionAutomate(automate, e, a);
                            System.out.println();
                            automate.AffichageAutomate();
                        }
                        automate.LireMOT();
                        boolean continuer = true;
                        do {
                            System.out.println("Voulez-vous lire un mot a nouveau ? (O/N)");
                            String choice = scanner.nextLine().trim();

                            while (!choice.equalsIgnoreCase("o") && !choice.equalsIgnoreCase("n")) {
                                System.out.println("Réponse invalide. Veuillez entrer 'O' pour Oui ou 'N' pour Non : ");
                                choice = scanner.nextLine().trim();
                            }

                            if (choice.equalsIgnoreCase("o")) {
                                automate.LireMOT();
                            } else {
                                continuer = false;
                            }
                        } while (continuer);

                        break;

                    } else { //NB UN ENTIER <0
                        System.out.println("Erreur : Veuillez entrer un nombre entier strictement positif.");
                    }
                } else { //NB EST PAS UN ENTIER
                    System.out.println("Erreur : Veuillez entrer un nombre entier.");
                    scanner.nextLine();
                }
            }
        }
    }
}
