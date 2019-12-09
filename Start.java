import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class Start {

	public static void main(String[] args) {

		RandomGenerator generator = new RandomGenerator();
		generator.getDoubleWithStepsAndLimits(-10, 10, 0.5);
		
		double noteFinal;
		double noteUser;
		
		askAndCompareAnswer(QuestionNumber.Q1);
	}
	
	
	
	//return point gagne
	public static int askAndCompareAnswer(QuestionNumber q) {
		ClientAnswer client = new ClientAnswer();
		Functions funct = new Functions();
		RandomGenerator random = new RandomGenerator();

		if(q.equals(QuestionNumber.Q1)) {
			return askSecondDegreeEquations(client,random,funct);
		}else{
			ArrayList<Double> limits = random.getLimitsIntegral();
			double a = limits.get(0);
			double b = limits.get(1);
			System.out.print("Resoudre l'integrale de "+ a + "�" + b + "de la fonction ");
			
			if(q.equals(QuestionNumber.Q22a) || q.equals(QuestionNumber.Q22b) ||q.equals(QuestionNumber.Q22c) ||q.equals(QuestionNumber.Q23a)) {
				double c = random.getRandomDoubleWithException(0);
				return askQuestionIntegration22And23(q,client, funct,a, b,c);
			}
			else if(q.equals(QuestionNumber.Q21a)) {
				return askQuestionIntegration21a(random, client, funct, a, b);				
			}
			else{
				ArrayList<Double> exceptions = new ArrayList<Double>();
				exceptions.add(a);
				exceptions.add(b);
				double c = random.getRandomDoubleWithExceptions(exceptions);
				
				return askQuestionIntegration21b(client, funct, a, b, c);
			}
		}
	}
	
	public static int askQuestionIntegration21b(ClientAnswer client, Functions funct, double a, double b, double c) {
		System.out.println("f(x) = (1/(x-"+c+")");

		System.out.println("Quelle est votre r�ponse ? ");
		double answer = client.getDoubleAnswer();
		double response = funct.pow2(a, b, c);
		return compareDoubleAnswers(answer, response)?1:0;

	}
	
	public static int askQuestionIntegration21a(RandomGenerator random, ClientAnswer client, Functions funct, double a, double b) {
		double c = random.getRandomDoubleWithException(0);
		double d = random.getRandomDouble();
		double alpha = random.getRandomDoubleWithException(-1);
		
		System.out.println("f(x) = ("+c+"x-"+d+")^"+alpha);
		
		System.out.println("Quelle est votre r�ponse ? ");
		double answer = client.getDoubleAnswer();
		double response = funct.pow1(a, b, c, d, alpha);
		
		return compareDoubleAnswers(answer, response)?1:0;
	}
	
	public static int askQuestionIntegration22And23(QuestionNumber q, ClientAnswer client, Functions funct, double a, double b, double c) {
	
		double response = 0;
		
		switch(q) {
			case Q22a:
				System.out.println("f(x) = cos("+c+"x)");
				response = funct.trigo1(a, b, c);
				break;
			case Q22b:
				System.out.println("f(x) = sin("+c+"x)");
				response = funct.trigo2(a, b, c);
				break;
			case Q22c:
				System.out.println("f(x) = tan("+c+"x)");
				response = funct.trigo3(a, b, c);
				break;				
			case Q23a:
				System.out.println("f(x) = ln("+c+"x)");
				response = funct.log(a, b, c);
				break;
			default:
				break;
		}
		
		System.out.println("Quelle est votre reponse ?");
		double answer = client.getDoubleAnswer();
		return compareDoubleAnswers(response, answer)?1:0;
	}
	
	//TODO GERER LES REPONSES, x1 ou x2 ?
	//TODO gerer les reponses double
	public static int askSecondDegreeEquations(ClientAnswer client, RandomGenerator random, Functions funct) {
		double a = random.getDoubleWithStepsAndLimits(-10, 10, 0.5);
		double b = random.getDoubleWithStepsAndLimits(-10, 10, 0.5);
		double c = random.getDoubleWithStepsAndLimits(-10, 10, 0.5);
		ArrayList<Double> results = funct.secondDegreEquations(a, b, c);
		int resultsSize = results.size();
		System.out.println(results);
		System.out.println("Resoudre l'�quation de second degr� : "+a+"x�+"+b+"x+"+c);
		System.out.println("Combien de solutions ?");
		
		int nbAnswers = client.getIntAnswer();
		
		if(nbAnswers == resultsSize) {
			double x1;
			
			if(resultsSize == 1) {
				System.out.println("Quelle est la solution ?");
				x1 = client.getDoubleAnswer();
				if(compareDoubleAnswers(x1, results.get(0)))
					return 1;
					
			}
			else if(resultsSize == 2){
				double x2;
				System.out.println("Quelle sont les solutions (arrondis � 0.1) ?");
				System.out.println("Solution 1:");
				x1 = client.getDoubleAnswer();
				System.out.println("Solution 2:");
				x2 = client.getDoubleAnswer();
				if(compareDoubleAnswers(x1, results.get(0)) && compareDoubleAnswers(x2, results.get(1)))
					return 1;
			}
			else
				return 1;
		}
		else 
			System.out.println("Mauvaise r�ponse");
			
		return 0;
		
	}
	
	public static boolean compareDoubleAnswers(double a, double b) {
		DecimalFormat df = new DecimalFormat("#.#");
		df.setRoundingMode(RoundingMode.CEILING);
		return df.format(a) == df.format(b);
			
	}

}
