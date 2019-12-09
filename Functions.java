import java.util.ArrayList;

public class Functions {
		
	public static ArrayList<Double> secondDegreEquations(double a, double b, double c) {
		
		double discriminant = b*b - 4*a*c;
		ArrayList<Double> response = new ArrayList<Double>();
		
		if(discriminant == 0)
			response.add(-b/(2*a));
		
		else if (discriminant > 0) {
			double x1 = (-b-Math.sqrt(discriminant))/(2*a);
			double x2 = (-b+Math.sqrt(discriminant))/(2*a);
			response.add(x1); 
			response.add(x2);
		}
		
		return response;
	}

	public static double pow1(double a, double b, double c, double d, double alpha) {
	    return 1/(c*(alpha+1))*(Math.pow(b*c-d, alpha+1)-Math.pow(a*c-d,alpha+1));	    
	}
	
	public static double pow2(double a, double b, double c) {
		double ln1, ln2;
		ln1 = Math.abs(b-c);
        ln2 = Math.abs(a-c);

        return Math.log(ln1) - Math.log(ln2);
	}
	
	public static double trigo1(double a, double b, double c) {
		return (Math.sin(b*c) - Math.sin(a*c))/c;
	}
	
	public static double trigo2(double a, double b, double c) {
		return -(Math.cos(b*c) - Math.cos(a*c))/c;
	}
	
	public static double trigo3(double a, double b, double c) {
		double ln1, ln2;
		ln1 = Math.abs(Math.cos(b*c));
        ln2 = Math.abs(Math.cos(a*c));
        
        return -( Math.log(ln1) - Math.log(ln2) )/ c;
	}
	
	public static double log(double a, double b, double c) {
        return b * Math.log(b*c)-a*Math.log(a*c)-c*(b-a);

	}

}
