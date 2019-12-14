
public enum QuestionNumber {
	Q1(1),
	Q21a(1),
	Q21b(1),
	Q22a(1),
	Q22b(1),
	Q22c(1),
	Q23a(1);
	
	// TODO : Changer le bareme 
	private int bareme;
	   
	QuestionNumber(int bareme){
	    this.bareme = bareme;
	}
	
	public int getBareme() {
		return this.bareme;
	}
}
