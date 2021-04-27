import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Scanner;

import com.google.gson.JsonArray;
import com.google.gson.JsonParser;

public class getPM25 {
private HashMap<Integer,Double> PM25;
	
	public HashMap<Integer,Double> getPM25(String country,int year1,int year2) {
		PM25 = new HashMap<Integer,Double>();
		getData(country,year1,year2);
		return PM25;
		
	}
	//enter country,and years wanted
	private void getData(String country,int year1,int year2) {
		
		String urlString = String.format("http://api.worldbank.org/v2/country/%s/indicator/EN.ATM.PM25.MC.M3?date=%d:%d&format=json", country,year1,year2);//request data 
		double pm = 0;
		try {
			URL url = new URL(urlString);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.connect();
			int responsecode = conn.getResponseCode();
			if (responsecode == 200) {
				String inline = "";
				Scanner sc = new Scanner(url.openStream());
				while (sc.hasNext()) {
					inline += sc.nextLine();
				}
				sc.close();
				JsonParser jsonParser = new JsonParser();
				JsonArray jsonArray = jsonParser.parseString(inline).getAsJsonArray();
				int size = jsonArray.size();
				int sizeOfResults = jsonArray.get(1).getAsJsonArray().size();
				int year;
				for (int i = 0; i < sizeOfResults; i++) {
					year = jsonArray.get(1).getAsJsonArray().get(i).getAsJsonObject().get("date").getAsInt();
					if (jsonArray.get(1).getAsJsonArray().get(i).getAsJsonObject().get("value").isJsonNull())
						pm = -1;
					else
						pm = jsonArray.get(1).getAsJsonArray().get(i).getAsJsonObject().get("value")
								.getAsDouble();
						PM25.put(year, pm);
				}
			}
		}
		catch(IOException e) {
			
		}
		
		
	}

}
