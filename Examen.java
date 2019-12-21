import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class Examen {

	private ClientAnswer client;
	private Functions functions;
	private RandomGenerator random;
	private QuestionNumber q;

	private double probaP1, probaP21, probaP22;

	private double noteEleve;
	private double noteExam;
	
	public Examen(ClientAnswer client) {
		this.client = client;
		this.functions = new Functions();
		this.random = new RandomGenerator();
	}

	public double getNoteExam() {
		return this.noteExam;
	}
	
	public double getNoteEleve() {
		return this.noteEleve;
	}
	
	public void choseQuestion() {
		double randomNumber = Math.random();

		if (randomNumber <= probaP1){ //Cas exo 1
			this.q = QuestionNumber.Q1;
		}
		else{ //Cas exo 2
			randomNumber = Math.random();
			if(randomNumber <= probaP21){  //Cas question 21
				randomNumber = Math.random();

				if(randomNumber < 0.5)
					this.q = QuestionNumber.Q21a;
				else{
					this.q = QuestionNumber.Q21b;
				}

			}
			else if(randomNumber <=probaP21 + probaP22){ //Cas question 22
				randomNumber = Math.random();
				if(randomNumber < 1./3)
					this.q = QuestionNumber.Q22a;
				else if(randomNumber < 2./3){
					this.q = QuestionNumber.Q22b;
				}
				else {
					this.q = QuestionNumber.Q22c;
				}
			}
			else{ //Cas question 23
				this.q = QuestionNumber.Q23a;
			}
		}


		this.noteExam += this.q.getBareme();
	}



	//Demande à l'étudiant de fixer les probabilité d'obtenir les questions pour l'ensemble de l'examen
	public void askProbabilities(){
		System.out.println("Choisissez les poids d'etre interoger sur l'exercice 1 (equations du second degre)\n" +
				"ou sur l'exercice 2 (integration sur R)\n\n" +
				"Attention : les poids doivent toujouts etre positifs!\n" +
				"-----------------------------------------------------------------------------------------------\n");
		//poids pour l'exercice 1
		int poidP1, poidP2;
		poidP1 = client.getWeight("Entrez le poids pour l'exercice 1:");
		poidP2 = client.getWeight("Entrez le poids pour l'exercice 2:");

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
			poidP21 = client.getWeight("Entrez le poids pour la question 21");
			poidP22 = client.getWeight("Entrez le poids pour la question 22");
			poidP23 = client.getWeight("Entrez le poids pour la question 23");

			probaP21 = poidP21 / (double) (poidP21 + poidP22 + poidP23);
			probaP22 = poidP22 / (double) (poidP21 + poidP22 + poidP23);

		}
	}
	
	
	public void startQuestion() {

		if(q.equals(QuestionNumber.Q1)) {
			askQuestionSecondDegreeEquations();
		}else{
			ArrayList<Double> limits = random.getLimitsIntegral();
			double a = limits.get(0);
			double b = limits.get(1);
			System.out.print("Resoudre l'integrale de "+ a + "a�" + b + "de la fonction ");
			
			if(q.equals(QuestionNumber.Q22a) || q.equals(QuestionNumber.Q22b) ||q.equals(QuestionNumber.Q22c) ||q.equals(QuestionNumber.Q23a)) {
				double c = random.getRandomDoubleWithException(0);
				askQuestionIntegration22And23(a, b,c);
			}
			else if(q.equals(QuestionNumber.Q21a)) {
				askQuestionIntegration21a(a, b);				
			}
			else{
				ArrayList<Double> exceptions = new ArrayList<Double>();
				exceptions.add(a);
				exceptions.add(b);
				double c = random.getRandomDoubleWithExceptions(exceptions);
				
				askQuestionIntegration21b(a, b, c);
			}
		}
	}
	
	public void askQuestionIntegration21b(double a, double b, double c) {
		System.out.println("f(x) = (1/(x-"+c+")");

		System.out.println("Quelle est votre reponse ? ");
		double answer = client.getDoubleAnswer();
		double response = functions.pow2(a, b, c);
		
		if(goodAnswer(answer, response))
			this.noteEleve += this.q.getBareme();

	}
	
	public void askQuestionIntegration21a(double a, double b) {
		double c = random.getRandomDoubleWithException(0);
		double d = random.getRandomDouble();
		double alpha = random.getRandomDoubleWithException(-1);
		
		System.out.println("f(x) = ("+c+"x-"+d+")^"+alpha);
		
		System.out.println("Quelle est votre reponse ? ");
		double answer = client.getDoubleAnswer();
		double response = functions.pow1(a, b, c, d, alpha);
		
		if(goodAnswer(answer, response))
			this.noteEleve += this.q.getBareme();
	}
	
	public void askQuestionIntegration22And23(double a, double b, double c) {
	
		double response = 0;
		
		switch(q) {
			case Q22a:
				System.out.println("f(x) = cos("+c+"x)");
				response = functions.trigo1(a, b, c);
				break;
			case Q22b:
				System.out.println("f(x) = sin("+c+"x)");
				response = functions.trigo2(a, b, c);
				break;
			case Q22c:
				System.out.println("f(x) = tan("+c+"x)");
				response = functions.trigo3(a, b, c);
				break;				
			case Q23a:
				System.out.println("f(x) = ln("+c+"x)");
				response = functions.log(a, b, c);
				break;
			default:
				break;
		}
		
		System.out.println("Quelle est votre reponse ?");
		double answer = client.getDoubleAnswer();
		
		if(goodAnswer(answer, response))
			this.noteEleve += this.q.getBareme();
	}
	
	//TODO GERER LES REPONSES, x1 ou x2 ?
	//TODO gerer les reponses double
	public void askQuestionSecondDegreeEquations() {
		
		double a = random.getDoubleWithStepsAndLimits(-10, 10, 0.5);
		double b = random.getDoubleWithStepsAndLimits(-10, 10, 0.5);
		double c = random.getDoubleWithStepsAndLimits(-10, 10, 0.5);
		
		ArrayList<Double> results = functions.secondDegreEquations(a, b, c);
		int resultsSize = results.size();
		
		System.out.println("Resoudre l'equation de second degre : "+a+"x�+"+b+"x+"+c);
		System.out.println("Combien de solutions ?");
		
		int nbAnswers = client.getIntAnswer();
		
		if(nbAnswers == resultsSize) {
			double x1;
			
			//Si dicriminant est egal a 0
			if(resultsSize == 1) {
				System.out.println("Quelle est la solution ?");
				x1 = client.getDoubleAnswer();
				if(goodAnswer(x1, results.get(0)))
					this.noteEleve += q.getBareme();			
			}
			
			//Si le discriminant est > 0
			else if(resultsSize == 2){
				double x2;
				System.out.println("Quelle sont les solutions (arrondis a 0.1) ?");
				System.out.println("Solution 1:");
				x1 = client.getDoubleAnswer();
				System.out.println("Solution 2:");
				x2 = client.getDoubleAnswer();
				if(goodAnswerSecond(results,x1,x2))
					this.noteEleve += q.getBareme();
			}
			
			//Si le discriminant est < 0
			else
				this.noteEleve += q.getBareme();
		}				
	}
	
	public boolean goodAnswerSecond(ArrayList<Double> response, double x1, double x2) {
		DecimalFormat df = new DecimalFormat("#.#");
		df.setRoundingMode(RoundingMode.CEILING);
		
		if(df.format(x1) == df.format(response.get(0)))
			return df.format(x2) == df.format(response.get(1));
		
		if(df.format(x1) == df.format(response.get(1)))
			return df.format(x2) == df.format(response.get(0));
			
		return false;
	}
	
	public boolean goodAnswer(double a, double b) {
		DecimalFormat df = new DecimalFormat("#.#");
		df.setRoundingMode(RoundingMode.CEILING);
		return df.format(a) == df.format(b);
	}
}
