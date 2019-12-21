import java.util.Locale;
import java.util.Scanner;

public class ClientAnswer {
	
	private Scanner scanner;
	
	private double probaP1, probaP21, probaP22;
	
	public ClientAnswer() {
		scanner = new Scanner(System.in).useLocale(Locale.US);
	}
	
	public double getP1() {
		return probaP1;
	}
	public double getP21() {
		return probaP21;
	}
	public double getP22() {
		return probaP22;
	}
	
	public double getDoubleAnswer() {
		
		double answer = 0;
		boolean stop = false;
		do {
			System.out.println("Veuillez saisir un double avec un point comme delimiteur");
			if(scanner.hasNextDouble()) {
				answer = scanner.nextDouble();
				stop = true;
			}
			else {
				scanner.nextLine();
			}
			
		} while(!stop);
	    
		return answer;
		
	}
	
	public int getIntAnswer() {
		try {
			int answer = Integer.parseInt(scanner.nextLine());
			return answer;
		} catch (Exception e) {
			System.out.println("Veuillez saisir un entier");
			return getIntAnswer();
		}
		
	}
	
	//Demande à l'étudiant de fixer les probabilité d'obtenir les questions pour l'ensemble de l'examen
		public void askPounds(){
			System.out.println("Choisissez les poids d'etre interoger sur l'exercice 1 (equations du second degre)\n" +
					"ou sur l'exercice 2 (integration sur R)\n\n" +
					"Attention : les poids doivent toujours etre positifs!\n" +
					"-----------------------------------------------------------------------------------------------\n");
			//poids pour l'exercice 1
			int poidP1, poidP2;
			poidP1 = this.getWeight("Entrez le poids pour l'exercice 1:");
			poidP2 = this.getWeight("Entrez le poids pour l'exercice 2:");

			probaP1 = poidP1 / (double) (poidP1 + poidP2);

			//proba de la question dans le cas de l'exo 2
			if(probaP1 != 1.0){
				int  poidP21, poidP22, poidP23;
				System.out.println("-------------------------------------------------------------------------------------\n" +
						"Dans le cas ou vous tomberiez sur l'exercice 2, choisissez les poids d'etre interroge \n" +
						"sur la question 21 (fonction puissance), sur la question 22 (fonction trigonometrique) \n" +
						"ou sur la question 23 (fonction logarithmique)\n\n" +
						"Attention : les poids doivent toujouts etre positifs!\n" +
						"-----------------------------------------------------------------------------------------------\n");
				poidP21 = this.getWeight("Entrez le poids pour la question 21");
				poidP22 = this.getWeight("Entrez le poids pour la question 22");
				poidP23 = this.getWeight("Entrez le poids pour la question 23");

				probaP21 = poidP21 / (double) (poidP21 + poidP22 + poidP23);
				probaP22 = poidP22 / (double) (poidP21 + poidP22 + poidP23);

			}
		}

	public int getWeight(String demand){
		int answer;

		do{
			System.out.println(demand);
			answer = getIntAnswer();
		}while(answer < 0);

		return answer;
	}
	
	public void closeScanner() {
		scanner.close();
	}

}
