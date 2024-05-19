package thl;
import java.util.Scanner;
import java.util.Vector;


public class AutomateND {	
	Vector<String>[][] automate;
    Alphabet alphabet;
    Etat etat;
	
    @SuppressWarnings("unchecked") 
	public AutomateND(Alphabet alphabet,Etat etat) {
        this.etat=etat;
        this.alphabet=alphabet;
        automate = new Vector[etat.nbEtat + 1][alphabet.nbAlphabet + 1]; //FOR MATRICE
        
      for (int i = 0; i < automate.length; i++) {
            for (int j = 0; j < automate[i].length; j++) {   //FOR ELEMENT DE LA MATRICE
            	automate[i][j] = new Vector<String>();
            
            }} 
       automate[0][0].add("É/A"); 

    } 
    void SetAuto(Alphabet alphabet,Etat etat)
    {
        for(int i=1; i<etat.nbEtat+1; i++) { 
            automate[i][0].add(etat.Etat.get(i-1)); 
        	
        } 

            for(int j=1; j<alphabet.nbAlphabet+1; j++) { 
            	//automate[0][j]=etat.Etat.get(j-1);
                   automate[0][j].add(alphabet.Alphabet.get(j-1)); 
            	}

    }

 
    void TransitionAutomate(AutomateND auto, Etat e1, Alphabet a1) {   
        System.out.println("Donner l'état présent pour la transition : ");
        Scanner scanner = new Scanner(System.in);     
        String etatPresent = scanner.nextLine().toLowerCase();
        while (!e1.Etat.contains(etatPresent)) {        	
            System.out.println("Vous devez entrer un état existant : ");
            etatPresent = scanner.nextLine().toLowerCase();
        }
        
        System.out.println("Donner l'alphabet pour la transition : ");
        String trAlphabet = scanner.nextLine().toLowerCase();
        while (!a1.Alphabet.contains(trAlphabet)) {
            System.out.println("Vous devez entrer un alphabet existant : ");
            trAlphabet = scanner.nextLine().toLowerCase();
        } 	
        
        System.out.println("Donner l'état future pour la transition : ");
        String etatFutur = scanner.nextLine().toLowerCase();
        while (!e1.Etat.contains(etatFutur)) {
            System.out.println("Vous devez entrer un état existant : ");
            etatFutur = scanner.nextLine().toLowerCase();
        }
        
        int alphabetIndex = a1.Alphabet.indexOf(trAlphabet) + 1;
        int etatIndex = e1.Etat.indexOf(etatPresent) + 1;

        if (etatIndex >= 0 && etatIndex < auto.automate.length && alphabetIndex >= 0 && alphabetIndex < auto.automate[0].length) {
            if (!auto.automate[etatIndex][alphabetIndex].isEmpty()) {
                // Vérifier si l'état futur est déjà présent dans la liste des états d'arrivée
                if (!auto.automate[etatIndex][alphabetIndex].contains(etatFutur)) {
                    auto.automate[etatIndex][alphabetIndex].add(etatFutur);
                }
            } else {
                auto.automate[etatIndex][alphabetIndex].add(etatFutur);
            }
        } else {
            System.out.println("Indices hors des limites de la matrice.");
        }   
    }

 
  
	void AffichageAutomate() {
	    System.out.println("");
	    // Parcours de la matrice automate
	    for (int i = 0; i < etat.nbEtat + 1; i++) {
	        for (int j = 0; j < alphabet.nbAlphabet + 1; j++) {
	            // Récupérer le vecteur à la position (i, j)
	            Vector<String> vecteur = automate[i][j];
	            // Concaténer les états si le vecteur n'est pas vide
	            if (!vecteur.isEmpty()) {
	                StringBuilder transitionConcat = new StringBuilder();
	                for (String element : vecteur) {
	                    if (transitionConcat.length() > 0) {
	                        transitionConcat.append(",");
	                    }
	                    transitionConcat.append(element);
	                }
	                System.out.printf("%-15s", transitionConcat.toString());
	            } else {
	                // Afficher une cellule vide si le vecteur est vide
	            	 System.out.printf("%-15s", "");
	            }
	        }
	        // Saut de ligne après chaque ligne de la matrice
	        System.out.println();
	    }
	}
}