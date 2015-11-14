
import java.util.HashMap;

public class ClosedList {

	private HashMap<String, Integer> closedList;
	
	public ClosedList(){
		closedList = new HashMap<String, Integer>();
	}
	
	public void add(Point p, Integer step){
		closedList.put(Integer.toString(p.col) + " " + Integer.toString(p.row), step );
	}
	
	public boolean includes(Point p){
		return closedList.containsKey(Integer.toString(p.col) + " " + Integer.toString(p.row));
	}
	
	public boolean hasChecked(Point p, int value){
		Integer v = closedList.get(Integer.toString(p.col) + " " + Integer.toString(p.row));
		if (v == null) return false;
		else return (v == value);
	}
	
}
