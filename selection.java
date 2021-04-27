
public class selection {
	String country;
	int year1,year2;
	String analysis;
	public void setCountry(String type1) {
		this.country = type1;
	}
	public String getCountry() {
		return country;
	}
	
	public String getAnalysis(){
		return analysis;
	}
	public void setAnalysis(String name){
		this.analysis = name;
	}
	public int getYearStart(){
		return year1;
	}
	public void setYearStart(int num1){
		this.year1 = num1;
	}
	public int getYearEnd(){
		return year2;
	}
	public void setYearEnd(int num2){
		this.year2 = num2;
	}
	public String toString() {
		return ("year: "+ this.getYearStart()+" , "+this.getYearEnd()+"\n"+
	"analysis: "+ this.getAnalysis()+"\n"+"country: " + this.getCountry());
				
	}
	/*test case
	 * public static void main(String[] args) {
	 
		selection s = new selection();
		s.setAnalysis("pm2.5 vs gdp");
		s.setYearStart(2000);
		s.setYearEnd(2001);
		s.setCountry("canada");
		System.out.println(s.toString());
	}*/
	
}
