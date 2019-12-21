import java.util.ArrayList;
import java.util.Random;

public class RandomGenerator {

	private Random random;
	
	public double getDoubleWithStepsAndLimits(int min, int max, double steps) {
		
		ArrayList<Double> doubles = new ArrayList<Double>();
		for(double i = min ; i <= max ; i +=steps) {
			doubles.add(i);
		}
		double randomElement = doubles.get(random.nextInt(doubles.size()));
		
		return randomElement;
	}
	
	// 0 = borne inf, 1 = borne sup 
	public ArrayList<Double> getLimitsIntegral() {
		ArrayList<Double> limits = new ArrayList<Double>();
		double inf = random.nextDouble();
		double supp;
		do {
			supp = random.nextDouble();
		} while(inf == supp || inf > supp);
		
		limits.add(inf);
		limits.add(supp);
		
		return limits;
	}
	
	public double getRandomDouble() {
		return random.nextDouble();
	}
	
	public double getRandomDoubleWithException(double exception) {
		double result;
		do {
			result = random.nextDouble();
		} while(result == exception);
		
		return result;
	}
	
	public double getRandomDoubleWithExceptions(ArrayList<Double> exceptions) {
		double result;
		do {
			result = random.nextDouble();
		} while(exceptions.contains(result));
		
		return result;
	}
}
