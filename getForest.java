import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Scanner;

import com.google.gson.JsonArray;
import com.google.gson.JsonParser;

public class getForest {
private HashMap<Integer,Double> Forest;
	
	public HashMap<Integer,Double> getForest(String country,int year1,int year2) {
		Forest = new HashMap<Integer,Double>();
		getData(country,year1,year2);
		return Forest;
		
	}
	//enter country,and years wanted
	private void getData(String country,int year1,int year2) {
		
		String urlString = String.format("http://api.worldbank.org/v2/country/%s/indicator/AG.LND.FRST.ZS?date=%d:%d&format=json", country,year1,year2);//request data 
		double forest = 0;
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
						forest = -1;
					else
						forest = jsonArray.get(1).getAsJsonArray().get(i).getAsJsonObject().get("value")
								.getAsDouble();
						Forest.put(year, forest);
				}
			}
		}
		catch(IOException e) {
			
		}
		
		
	}
}
