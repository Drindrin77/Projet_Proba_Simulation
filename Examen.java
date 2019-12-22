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
	
	public void choseQuestion() {
		double randomNumber = Math.random();

		if (randomNumber <= client.getP1()) //Cas exo 1
			this.q = QuestionNumber.Q1;
		
		else { //Cas exo 2
			randomNumber = Math.random();
			if(randomNumber <= client.getP21()){  //Cas question 21
				randomNumber = Math.random();
				
				if(randomNumber < 0.5)
					this.q = QuestionNumber.Q21a;
				else
					this.q = QuestionNumber.Q21b;
			}
			else if(randomNumber <= client.getP21() + client.getP22()){ //Cas question 22
				randomNumber = Math.random();
				if(randomNumber < 1./3)
					this.q = QuestionNumber.Q22a;
				
				else if(randomNumber < 2./3)
					this.q = QuestionNumber.Q22b;
				
				else 
					this.q = QuestionNumber.Q22c;
			}
			else //Cas question 23
				this.q = QuestionNumber.Q23a;
		}
		this.noteExam += this.q.getBareme();
	}

	public void startQuestion() {

		System.out.println("Question : " + this.q);

		double response = 0;
		ArrayList<Double> limits = random.getLimitsIntegral();
		double borneInf = limits.get(0);
		double borneSup = limits.get(1);
		double c = random.getRandomDoubleWithException(0);

		if(q.equals(QuestionNumber.Q1))
			askQuestionSecondDegreeEquations();
		else {
			System.out.print("Resoudre l'integrale de "+ borneInf + " a " + borneSup + " de la fonction ");
			switch(q) {
				case Q21a:
					c = random.getRandomDoubleWithException(0);
					double d = random.getDoubleWithStepsAndLimits();
					double alpha = random.getRandomDoubleWithException(-1);
					System.out.println("f(x) = ("+c+"x-"+d+")^"+alpha);
					response = functions.pow1(borneInf, borneSup, c, d, alpha);
					break;
					
				case Q21b:
					ArrayList<Double> exceptions = new ArrayList<Double>();
					exceptions.add(borneInf);
					exceptions.add(borneSup);
					c = random.getRandomDoubleWithExceptions(exceptions);	
					System.out.println("f(x) = 1/(x-"+c+")");
					response = functions.pow2(borneInf, borneSup, c);
					break;
					
				case Q22a:
					System.out.println("f(x) = cos("+c+"x)");
					response = functions.trigo1(borneInf, borneSup, c);
					break;
					
				case Q22b:
					System.out.println("f(x) = sin("+c+"x)");
					response = functions.trigo2(borneInf, borneSup, c);
					break;
					
				case Q22c:
					System.out.println("f(x) = tan("+c+"x)");
					response = functions.trigo3(borneInf, borneSup, c);
					break;	
					
				case Q23a:
					response = functions.log(random);
					break;
					
				default:
					break;
			}
			
			System.out.println("Quelle est votre reponse (tronquer a 0.01)?");
			double answer = client.getDoubleAnswer();
			
			if(goodAnswer(answer, response))
				this.noteEleve += this.q.getBareme();
		}
		
	}
		
	public void askQuestionSecondDegreeEquations() {
		
		double a = random.getDoubleWithStepsAndLimits();
		double b = random.getDoubleWithStepsAndLimits();
		double c = random.getDoubleWithStepsAndLimits();
		
		ArrayList<Double> results = functions.secondDegreEquations(a, b, c);
		int resultsSize = results.size();
		System.out.println("Resoudre l'equation de second degre : "+a+"x^2+"+b+"x+"+c);
		System.out.println("Combien de solutions ?");
		
		int nbAnswers = client.getIntAnswer();
		
		if(nbAnswers == resultsSize) {
			double x1;
			
			//Si dicriminant est egal a 0
			if(resultsSize == 1) {
				System.out.println("Quelle est la solution (tronquer a 0.01) ?");
				x1 = client.getDoubleAnswer();
				if(goodAnswer(x1, results.get(0)))
					this.noteEleve += q.getBareme();			
			}
			
			//Si le discriminant est > 0
			else if(resultsSize == 2){
				double x2;
				System.out.println("Quelle sont les solutions (tronquer a 0.01) ?");
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
		
		if(goodAnswer(x1,response.get(0)))
			return goodAnswer(x2,response.get(1));
		
		if(goodAnswer(x1,response.get(1)))
			return goodAnswer(x2,response.get(0));
			
		return false;
	}
	
	public boolean goodAnswer(double a, double b) {
		return (int)(a*100) == (int)(b*100);
	}
}
