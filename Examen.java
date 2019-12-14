import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class Examen {

	private ClientAnswer client;
	private Functions functions;
	private RandomGenerator random;
	private QuestionNumber q;
	
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
	
	//TODO : Realiser les demandes de poids et de trouver la question a pose. Affecter q a la question
	
	public void getQuestion() {
		
		this.q = QuestionNumber.Q1;
		this.noteExam += this.q.getBareme();
	}
	
	
	public void startQuestion() {

		if(q.equals(QuestionNumber.Q1)) {
			askQuestionSecondDegreeEquations();
		}else{
			ArrayList<Double> limits = random.getLimitsIntegral();
			double a = limits.get(0);
			double b = limits.get(1);
			System.out.print("Resoudre l'integrale de "+ a + "à" + b + "de la fonction ");
			
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

		System.out.println("Quelle est votre réponse ? ");
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
		
		System.out.println("Quelle est votre réponse ? ");
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
		
		System.out.println("Resoudre l'équation de second degré : "+a+"x²+"+b+"x+"+c);
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
				System.out.println("Quelle sont les solutions (arrondis à 0.1) ?");
				System.out.println("Solution 1:");
				x1 = client.getDoubleAnswer();
				System.out.println("Solution 2:");
				x2 = client.getDoubleAnswer();
				if(goodAnswer(x1, results.get(0)) && goodAnswer(x2, results.get(1)))
					this.noteEleve += q.getBareme();
			}
			
			//Si le discriminant est < 0
			else
				this.noteEleve += q.getBareme();
		}				
	}
	
	public boolean goodAnswer(double a, double b) {
		DecimalFormat df = new DecimalFormat("#.#");
		df.setRoundingMode(RoundingMode.CEILING);
		return df.format(a) == df.format(b);
	}
}
