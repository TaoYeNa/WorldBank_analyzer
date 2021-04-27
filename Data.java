import java.util.HashMap;

/* for get_data(selection) -> return a data obj*/
public class Data {
	String type; //for represent the data name, example: pm2.5
	String unit; // for example: metric tons per capita
	HashMap<Integer,Double> data_recieved;
	public void setType(String type1) {
		this.type = type1;
	}
	public String getType() {
		return type;
	}
	public void set_data_recieved(HashMap<Integer,Double> data) {
		this.data_recieved = data;
	}
	public HashMap<Integer,Double> get_data_recieved(){
		return data_recieved;
	}
	public void set_unit(String unit1) {
		this.unit = unit1;
	}
	public String get_unit() {
		return unit;
	}
}
