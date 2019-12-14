import java.util.Locale;
import java.util.Scanner;

public class ClientAnswer {
	
	private Scanner scanner;
	
	public ClientAnswer() {
		scanner = new Scanner(System.in).useLocale(Locale.US);
	}
	
	public double getDoubleAnswer() {
		
		double answer = 0;
		boolean stop = false;
		do {
			System.out.println("Veuillez saisir un double avec un point comme délimiteur");
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
	
	public void closeScanner() {
		scanner.close();
	}


}
