import java.io.*;
import java.util.*;


public class readCSV{
	static HashMap<String, countryList> countryMap = new HashMap<String, countryList>(); 
	
	public readCSV () {
		
		String line = "";
		
		try {
			
			BufferedReader br = new BufferedReader(new FileReader("country_list.csv"));
			br.readLine();//跳过第一行
			
			while((line = br.readLine()) != null) {
				
				countryList countryList = new countryList();
				
				String[] values = line.split(",");//以逗号为准分割每一组数据，并生成8个array
				
				//将values中的每一组数据set成countryList中的特定内容（全程、简称、年份）
				countryList.setCountryCode(Integer.parseInt(values[0]));//因为默认的values是String形式 所以转换为Int
				countryList.setFullName(values[1]);//values1里面是国家全名。
				countryList.setAbbName(values[2]);//简称
				countryList.setCountryComments(values[3]);
				countryList.setIso2(values[4]);//二字简称
				countryList.setIso3(values[5]);//三字简称
				countryList.setStartYear(values[6]);
				countryList.setEndYear(values[7]);//起始年份
				
				countryMap.put(countryList.getFullName(), countryList);//将内容input到Map中
				
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	public HashMap<String, countryList> getHashMap() {
		// TODO Auto-generated method stub
		return countryMap;
	}

	
	public static void main(String[] args) {
		new readCSV();
		System.out.println(countryMap);
	}
	

 
}