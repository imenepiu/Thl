package thl;
import java.util.Scanner;
import java.util.Vector;
public class Etat {
    int nbEtat;
    String EtatI;
    Vector<String>Etat;
    Vector<String>EtatF;
    
    public Etat() {
        Scanner scanner = new Scanner(System.in);

        do {
            System.out.print("Entrer le nombre d'états : ");
            while (!scanner.hasNextInt()) {
                System.out.print("Erreur : Veuillez entrer un nombre entier : ");
                scanner.next();
            }
            this.nbEtat = scanner.nextInt();
            scanner.nextLine();

            if (nbEtat <= 0) {
                System.out.println("Erreur : Veuillez entrer un nombre entier strictement positif.");
            }
        } while (nbEtat <= 0);

        Etat = new Vector<String>(nbEtat);
    } 

    public void ajouterEtat() {
        EtatF = new Vector<String>();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Entrer les états :");
        for (int i = 0; i < nbEtat; i++) {
            String etat = scanner.nextLine().toLowerCase(); // Convertir l'état en minuscules
            while (etat.isEmpty() || Etat.contains(etat)) {
                if (etat.isEmpty()) {
                    System.out.println("Vous ne pouvez pas entrer un état vide. Veuillez réessayer.");
                } else {
                    System.out.println("L'état " + etat + " est déjà présent. Veuillez entrer un état différent.");
                }
                etat = scanner.nextLine().toLowerCase(); // Demander à nouveau et convertir en minuscules
            }
            Etat.add(etat);
        }
        
        System.out.print("Entrer l'état initial : ");
        String v = scanner.nextLine().toLowerCase(); // Convertir l'état initial en minuscules
        while (!Etat.contains(v)) {
            System.out.println("L'état initial " + v + " n'existe pas. Veuillez réessayer :");
            v = scanner.nextLine().toLowerCase(); // Demander à nouveau et convertir en minuscules
        }
        EtatI = v;
        
        int n;
        do {
            System.out.print("Entrer le nombre d'états finaux : ");
            while (!scanner.hasNextInt()) {
                System.out.print("Erreur : Veuillez entrer un nombre entier :");
                scanner.next();
            }
            n = scanner.nextInt();
            scanner.nextLine(); // consommer la nouvelle ligne restante
            
            if (n <= 0) {
                System.out.println("Le nombre d'états finaux doit être strictement supérieur à zéro. Veuillez réessayer : ");
            } else if (n > nbEtat) {
                System.out.println("Le nombre d'états finaux ne peut pas être supérieur au nombre total d'états (" + nbEtat + "). Veuillez réessayer : ");
            }
            
        } while (n <= 0 || n > nbEtat);

        for (int i = 0; i < n; i++) {
            System.out.println("Entrer l'état final " + (i + 1) + " :");
            String e = scanner.nextLine().toLowerCase(); // Convertir l'état final en minuscules
            while (!Etat.contains(e)||EtatF.contains(e)) {
            	if(!Etat.contains(e)) {
            	System.out.println("L'état final " + e + " n'existe pas. Veuillez reessayer :");}
            	else {System.out.println("L'état final " + e + " est deja final.Veuillez reessayer :");}
                e = scanner.nextLine().toLowerCase(); // Demander à nouveau et convertir en minuscules
            }
            EtatF.add(e);
        }
    }


}