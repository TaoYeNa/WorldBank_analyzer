import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Scanner;
import com.google.gson.*;

public class getPop {
	//a hashmap that contain population for the country in format of (year,population)
	private HashMap<Integer,Double> Pop;
	
	public HashMap<Integer,Double> getPop(String country,int year1,int year2) {
		Pop = new HashMap<Integer,Double>();
		getData(country,year1,year2);
		return Pop;
		
	}
	//enter country,and years wanted
	private void getData(String country,int year1,int year2) {
		
		String urlString = String.format("http://api.worldbank.org/v2/country/%s/indicator/SP.POP.TOTL?date=%d:%d&format=json", country,year1,year2);//request data 
		int population=0;
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
						population = -1;
					else
						population = jsonArray.get(1).getAsJsonArray().get(i).getAsJsonObject().get("value")
								.getAsInt();
						Pop.put(year, (double) population);
				}
			}
		}
		catch(IOException e) {
			
		}
		
		
	}
}
