package thl;
import java.util.Scanner;
import java.util.Vector;
@SuppressWarnings("resource")

public class Alphabet {
    int nbAlphabet;
    Vector<String>Alphabet;
    
    public Alphabet() {
        Scanner scanner = new Scanner(System.in);

        do {
            System.out.print("Entrer le nombre d'alphabet : ");
            while (!scanner.hasNextInt()) {
                System.out.print("Erreur : Veuillez entrer un nombre entier : ");
                scanner.next();
            }
            this.nbAlphabet = scanner.nextInt();
            if (nbAlphabet <= 0) {
                System.out.println("Erreur : Veuillez entrer un nombre entier strictement positif.");
            }
        } while (nbAlphabet <= 0);

        Alphabet = new Vector<String>(nbAlphabet); 
    }

     
    public void AjoutAlphabet() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Entrer les alphabets :");
        
        for (int i = 0; i < nbAlphabet; i++) {
            String alphabet = scanner.nextLine().toLowerCase(); 
         // LOWER OU UPPER CASE
            if (!alphabet.isEmpty() && !Alphabet.contains(alphabet)) {
                Alphabet.add(alphabet);
            } else if (alphabet.isEmpty()) {
                System.out.println("Vous ne pouvez pas entrer un alphabet vide. Veuillez réessayer.");
                i--; // REFAIRE LE MEME INDICE
            } else {
                System.out.println("L'alphabet " + alphabet + " est déjà présent. Veuillez entrer un alphabet différent.");
                i--; // REFAIRE LE MEME INDICE
            }
        }
    }
} 