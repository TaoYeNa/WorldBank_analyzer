import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Scanner;

import com.google.gson.JsonArray;
import com.google.gson.JsonParser;

public class getEnergy {
private HashMap<Integer,Double> Energy;
	
	public HashMap<Integer,Double> getEnergy(String country,int year1,int year2) {
		Energy = new HashMap<Integer,Double>();
		getData(country,year1,year2);
		return Energy;
		
	}
	//enter country,and years wanted
	private void getData(String country,int year1,int year2) {
		
		String urlString = String.format("http://api.worldbank.org/v2/country/%s/indicator/EG.USE.PCAP.KG.OE?date=%d:%d&format=json", country,year1,year2);//request data 
		double ene = 0;
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
						ene = -1;
					else
						ene = jsonArray.get(1).getAsJsonArray().get(i).getAsJsonObject().get("value")
								.getAsDouble();
						Energy.put(year, ene);
				}
			}
		}
		catch(IOException e) {
			
		}
		
		
	}

}
