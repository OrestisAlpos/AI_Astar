

import java.util.HashMap;
import java.util.PriorityQueue;


public class OpenList {
	
	private PriorityQueue<Node> openList;
	private HashMap<String,Node> openListHash;
	
	public OpenList(){
		openList = new PriorityQueue<Node>(11, new NodeComparator());
		openListHash = new HashMap<String,Node>();
	}
	
	public void add(Node n){
		openList.add(n);
		openListHash.put(Integer.toString(n.point.col) + " " + Integer.toString(n.point.row), n);
	}
	
	public Node getMin(){ // returns and removes min element from openList
		Node min = openList.poll();
		//openListHash.remove(min);
		return min;
	}
	
	public Node includes(Point p){
		//if (openListHash.containsKey(p)){
			return openListHash.get(Integer.toString(p.col) + " " + Integer.toString(p.row));
		//}
		//else return null;
	}
	
	public void update(Node nOld, Node nNew){
		openList.remove(nOld);
		openList.add(nNew);
		//openListHash.remove(nOld.point);
		openListHash.put(Integer.toString(nNew.point.col) + " " + Integer.toString(nNew.point.row), nNew);
	}
		
}
