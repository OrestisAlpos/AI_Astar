
public class Point {
	public int col;
	public int row;
		
	public Point(int col_pos, int row_pos){
		col = col_pos;
		row = row_pos;
	}
	
	public static int calcDistanceBetweenPoints(Point p1, Point p2){
		return Math.abs(p1.col - p2.col) + Math.abs(p1.row - p2.row);
	}
	
	public static Double calcHDistance(Point p){
		//return new Double( (Math.sqrt( Math.pow(p.col - AStar.targetPoint.col,2) + Math.pow(p.row - AStar.targetPoint.row,2)))); //eucledian
		return new Double((Math.abs(p.col - AStar.targetPoint.col) + Math.abs(p.row - AStar.targetPoint.row)));
	}
	
	//public static Double calcHDistance2(Point p){
		//return new Double(Math.abs(p.col - AStar.targetPoint.col) + Math.abs(p.row - AStar.targetPoint.row));
	//}
	
	public static boolean isTarget(Point p) {
		return (p.col == AStar.targetPoint.col) && (p.row == AStar.targetPoint.row); 
	}
}
