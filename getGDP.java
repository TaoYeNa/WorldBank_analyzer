import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Scanner;

import com.google.gson.JsonArray;
import com.google.gson.JsonParser;

public class getGDP {
private HashMap<Integer,Double> GDP;
	
	public HashMap<Integer,Double> getGDP(String country,int year1,int year2) {
		GDP = new HashMap<Integer,Double>();
		getData(country,year1,year2);
		return GDP;
		
	}
	//enter country,and years wanted
	private void getData(String country,int year1,int year2) {
		
		String urlString = String.format("http://api.worldbank.org/v2/country/%s/indicator/NY.GDP.PCAP.CD?date=%d:%d&format=json", country,year1,year2);//request data 
		double gdp = 0;
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
						gdp = -1;		//if no value was founded set value to -1
					else
						gdp = jsonArray.get(1).getAsJsonArray().get(i).getAsJsonObject().get("value")
								.getAsDouble();
						GDP.put(year, gdp);
				}
			}
		}
		catch(IOException e) {
			
		}
		
		
	}

}
