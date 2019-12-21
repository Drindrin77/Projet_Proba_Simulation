import java.util.ArrayList;
import java.util.Random;

public class RandomGenerator {

	private Random random;
	
	public RandomGenerator() {
		this.random = new Random();
	}
	
	public double getDoubleWithStepsAndLimits() {
		
		ArrayList<Double> doubles = new ArrayList<Double>();
		for(double i = -10 ; i <= 10 ; i +=0.5) {
			doubles.add(i);
		}
		double randomElement = doubles.get(random.nextInt(doubles.size()));
		
		return randomElement;
	}
	
	// 0 = borne inf, 1 = borne sup 
	public ArrayList<Double> getLimitsIntegral() {
		ArrayList<Double> limits = new ArrayList<Double>();
		double inf = getDoubleWithStepsAndLimits();
		double supp;
		do {
			supp = getDoubleWithStepsAndLimits();
		} while(inf >= supp);
		
		limits.add(inf);
		limits.add(supp);
		
		return limits;
	}
	
	public double getRandomDoubleWithException(double exception) {
		double result;
		do {
			result = getDoubleWithStepsAndLimits();
		} while(result == exception);
		
		return result;
	}
	
	public double getRandomDoubleWithExceptions(ArrayList<Double> exceptions) {
		double result;
		do {
			result = getDoubleWithStepsAndLimits();
		} while(exceptions.contains(result));
		
		return result;
	}
}
