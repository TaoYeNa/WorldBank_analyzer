
public class popgdp implements strategy {
public result calculate(selection selection_b) throws nameException {
	result output = new result();
	output.set_result1(read_data(selection_b, "pop"));
	output.set_result2(read_data(selection_b, "gdp"));
	
	return output;
	}

	@Override
	public Data read_data(selection selection_b, String name) throws nameException {
		getPop p = new getPop();
		getGDP p2 = new getGDP();
		String country = selection_b.getCountry();
		int year1 = selection_b.getYearStart();
		int year2 = selection_b.getYearEnd();		
		Data reciver = new Data();
		if(name.contains("pop")){
			reciver.type = "pop";
			reciver.set_data_recieved(p.getPop(country, year1, year2));
			reciver.set_unit("people");
		}else if (name.contains("gdp")) {
			reciver.type = "gdp";
			reciver.set_data_recieved(p2.getGDP(country, year1, year2));
			reciver.set_unit("current US$");
		}else {
			throw new nameException("wrong name for the name for data obj ");
		}
		return reciver;
		
	}
	
	/*public static void main(String[] args) {
		test_co2Forestgdp();
	}*/
}
