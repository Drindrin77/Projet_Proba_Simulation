public class Start {

	public static void main(String[] args) {

		ClientAnswer client = new ClientAnswer();
		Examen exam = new Examen(client);
		int restart = 1;
		
		System.out.println("Controle de probabilite");
		do {
			
			exam.getQuestion();
			exam.startQuestion();
		
			System.out.println("Voulez-vous recommencer? 1 pour oui, 0 pour non");
			restart = client.getIntAnswer();
			
		} while (restart == 1);
		
		System.out.println("Vous avez eu "+exam.getNoteEleve()+" sur "+exam.getNoteExam());
		
		client.closeScanner();
		
	}

}
