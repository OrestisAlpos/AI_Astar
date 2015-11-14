
public class Node  {
	public Point point;
	public Double g_distance;
	public Double f_distance;
	public Node parent;
	public int step;
	
	public Node(Node par, Point p, Double g, Double f, int s){
		parent = par;
		point = p;
		g_distance = g;
		f_distance = f;
		step = s;
	}
}
