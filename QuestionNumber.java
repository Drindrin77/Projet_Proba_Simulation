
public enum QuestionNumber {
	Q1(1),
	Q21a(1.5),
	Q21b(1),
	Q22a(1),
	Q22b(1),
	Q22c(1.5),
	Q23a(1.5);
	
	private double bareme;
	   
	QuestionNumber(double bareme){
	    this.bareme = bareme;
	}
	
	public double getBareme() {
		return this.bareme;
	}
}
