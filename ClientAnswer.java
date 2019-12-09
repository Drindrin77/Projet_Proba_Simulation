import java.util.Scanner;

public class ClientAnswer {
	
	private Scanner scanner;
	
	public ClientAnswer() {
		scanner = new Scanner(System.in);
	}
	
	public double getDoubleAnswer() {
		try {
			double answer = scanner.nextDouble();
			return answer;
		} catch (Exception e) {
			System.out.println("Veuillez saisir un double");
			return getDoubleAnswer();
		}
		
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
