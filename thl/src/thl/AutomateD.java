package thl;
import java.util.Scanner; 
public class AutomateD {	
	String[][] automate;
    Alphabet alphabet;
    Etat etat;
    Scanner scanner = new Scanner(System.in);
	public AutomateD(Alphabet alphabet,Etat etat) {
        this.etat=etat;
        this.alphabet=alphabet;
        automate = new String[etat.nbEtat + 1][alphabet.nbAlphabet + 1];       
        automate[0][0]=("É/A"); 
	}
    void SetAuto(Alphabet alphabet,Etat etat)
    { 	
    	for(int i=1; i<etat.nbEtat+1; i++) { 
            automate[i][0]=etat.Etat.get(i-1); } 

            for(int j=1; j<alphabet.nbAlphabet+1; j++) { 
                   automate[0][j]=alphabet.Alphabet.get(j-1); 
            	}
    }

 
    void TransitionAutomate(AutomateD auto, Etat etat, Alphabet alphabet) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Donner l'état présent pour la transition : ");
        String etatPresent = scanner.nextLine().toLowerCase();
        while (!etat.Etat.contains(etatPresent)) {
            System.out.println("Vous devez entrer un état existant : ");
            etatPresent = scanner.nextLine().toLowerCase();
        }
        
        System.out.println("Donner l'alphabet pour la transition : ");
        String transitionAlphabet = scanner.nextLine().toLowerCase();
        while (!alphabet.Alphabet.contains(transitionAlphabet)) {
            System.out.println("Vous devez entrer un alphabet existant : ");
            transitionAlphabet = scanner.nextLine().toLowerCase();
        }
        
        System.out.println("Donner l'état future pour la transition : ");
        String etatFuture = scanner.nextLine().toLowerCase();
        while (!etat.Etat.contains(etatFuture)) {
            System.out.println("Vous devez entrer un état existant : ");
            etatFuture = scanner.nextLine().toLowerCase();
        }
        
        int alphabetIndex = alphabet.Alphabet.indexOf(transitionAlphabet) + 1;
        int etatIndex = etat.Etat.indexOf(etatPresent) + 1;

        if (etatIndex >= 0 && etatIndex < auto.automate.length && alphabetIndex >= 0 && alphabetIndex < auto.automate[0].length) {
            auto.automate[etatIndex][alphabetIndex] = etatFuture;
        } else {
            System.out.println("Indices hors des limites de la matrice.");
        }
    }

    boolean LireMOT() {
        System.out.print("Entrer le mot : ");
        String mot = scanner.nextLine().toLowerCase();
        // Vérifier si le mot appartient à l'alphabet
        for (int i = 0; i < mot.length(); i++) {
            String lettre = mot.substring(i, i + 1);
            if (!alphabet.Alphabet.contains(lettre)) {
                System.out.println("Le mot contient des lettres qui ne font pas partie de l'alphabet.");
                return false;
            }
        }

        // Commencer par l'état initial
        String etatCourant = etat.EtatI;
        for (int i = 0; i < mot.length(); i++) {
            String lettre = mot.substring(i, i + 1);
            int alphabetIndex = alphabet.Alphabet.indexOf(lettre) + 1;
            int etatIndex = etat.Etat.indexOf(etatCourant) + 1;
            
            // Obtenir l'état suivant à partir de la table de transition
            String etatSuivant = automate[etatIndex][alphabetIndex];
            if (etatSuivant == " " ) {
            	 System.out.println("La transition pour la lettre '" + lettre + "' à partir de l'etat " + etatCourant + " actuel est incorrecte.");
            	 System.out.println("Le mot '" + mot + "' n'est pas accepte par l'automate.");
                return false;
            }


            // Mettre à jour l'état actuel
            etatCourant = etatSuivant;
        }

        // Vérifier si l'état final est un état acceptant
        if (etat.EtatF.contains(etatCourant)) {
            System.out.println("Le mot '" + mot + "' est accepte par l'automate.");
            return true; // Mot accepté
        } else {
        	 System.out.println("L'etat '" + etatCourant + "' n'est pas un etat acceptant.");
            System.out.println("Le mot '" + mot + "' n'est pas accepte par l'automate.");
            return false; // Mot non accepté
        }
    }



   void AffichageAutomate() {
    
       for (int i = 0; i < etat.nbEtat + 1; i++) {
        
           for (int j = 0; j < alphabet.nbAlphabet+ 1; j++) {
        	if(automate[i][j]==null)   	
        	{
        		automate[i][j]=" ";
               

        	}
            System.out.printf("%-15s", automate[i][j]);
            
               }
           System.out.println();
           }  
   }
   
   
}