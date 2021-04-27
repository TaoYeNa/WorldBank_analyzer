public class co2pm25 implements strategy {
public result calculate(selection selection_b) throws nameException {
	result output = new result();
	output.set_result1(read_data(selection_b, "co2"));
	output.set_result2(read_data(selection_b, "pm2.5"));
	return output;
	}

	@Override
	public Data read_data(selection selection_b, String name) throws nameException {
		getCO2 p = new getCO2();
		getPM25 p1 = new getPM25();
		String country = selection_b.getCountry();
		int year1 = selection_b.getYearStart();
		int year2 = selection_b.getYearEnd();		
		Data reciver = new Data();
		if(name.contains("co2")){
			reciver.type = "co2";
			reciver.set_data_recieved(p.getCO2(country, year1, year2));
			reciver.set_unit("metric tons per capita");
		}else if (name.contains("pm")) {
			reciver.type = "pm2.5";
			reciver.set_data_recieved(p1.getPM25(country, year1, year2));
			reciver.set_unit("micrograms per cubic meter");
		}else {
			throw new nameException("wrong name for the name for data obj ");
		}
		return reciver;
		
	}
	
	
	/*public static void main(String[] args) {
		test_co2pm25();
	}*/
	}