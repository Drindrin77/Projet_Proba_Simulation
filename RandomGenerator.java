import java.util.ArrayList;
import java.util.Random;

public class RandomGenerator {

	
	public double getDoubleWithStepsAndLimits(int min, int max, double steps) {
		
		ArrayList<Double> doubles = new ArrayList<Double>();
		for(double i = min ; i <= max ; i +=steps) {
			doubles.add(i);
		}
		Random rand = new Random();
		double randomElement = doubles.get(rand.nextInt(doubles.size()));
		
		return randomElement;
	}
	
	// 0 = borne inf, 1 = borne sup 
	public ArrayList<Double> getLimitsIntegral() {
		ArrayList<Double> limits = new ArrayList<Double>();
		Random random = new Random();
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
		Random random = new Random();
		return random.nextDouble();
	}
	
	public double getRandomDoubleWithException(double exception) {
		Random random = new Random();
		double result;
		do {
			result = random.nextDouble();
		} while(result == exception);
		
		return result;
	}
	
	public double getRandomDoubleWithExceptions(ArrayList<Double> exceptions) {
		Random random = new Random();
		double result;
		do {
			result = random.nextDouble();
		} while(exceptions.contains(result));
		
		return result;
	}
}
