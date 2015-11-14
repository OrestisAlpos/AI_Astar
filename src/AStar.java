

import java.util.Iterator;
import java.util.LinkedList;
	
public class AStar {

	static Reader inFile = new Reader();
	static Point totalSize = inFile.getTotalSize();
	static Point robot1Point = inFile.getRobot1Point();
	static Point robot2Point = inFile.getRobot2Point();
	static Point targetPoint = inFile.getTargetPoint();
	static char[][] map = inFile.getMap();

	
	private static LinkedList<Point> findNeighbors(ClosedList closedList, Point p){
		LinkedList<Point> neighbors = new LinkedList<Point>();
		if(p.col > 1){ //testing (x-1,y) point
			Point neigh =  new Point(p.col - 1, p.row);
			if (!closedList.includes(neigh) && (map[p.row][p.col - 1]=='O' || map[p.row][p.col- 1]=='|'|| map[p.row][p.col- 1]=='*')) {
				neighbors.add(neigh);
			}	
		}
		if(p.col < totalSize.col){ //testing (x+1,y) point
			Point neigh =  new Point(p.col + 1, p.row);
			if (!closedList.includes(neigh) && (map[p.row][p.col + 1]=='O' || map[p.row][p.col + 1]=='|'|| map[p.row][p.col + 1]=='*')) {
				neighbors.add(neigh);
			}	
		}
		if(p.row > 1){ //testing (x,y-1) point
			Point neigh =  new Point(p.col, p.row - 1);
			if (!closedList.includes(neigh) && (map[p.row - 1][p.col]=='O' || map[p.row - 1][p.col]=='|' || map[p.row - 1][p.col]=='*')) {
				neighbors.add(neigh);
			}	
		}
		if(p.row < totalSize.row){ //testing (x,y+1) point
			Point neigh =  new Point(p.col, p.row + 1);
			if (!closedList.includes(neigh) && (map[p.row + 1][p.col]=='O' || map[p.row + 1][p.col]=='|' || map[p.row + 1][p.col]=='*')) {
				neighbors.add(neigh);
			}	
		}
		return neighbors;
	}
	
	/*private static void printPath(Node child, char c){
		LinkedList<Point> robotPath = new LinkedList<Point>();
		while (child != null){
			robotPath.add(child.point);
			child = child.parent;
		}
		Iterator<Point> iter = robotPath.descendingIterator();
		System.out.println("*** PRINTING PATH: ***");
		while (iter.hasNext()) {
			Point next = iter.next();
			System.out.println("      --> " + next.row + "," + next.col);
			map[next.row][next.col] = c;
		}
	}*/
	
	public static void printMap(){
		int i,j;
		for(i=1; i<totalSize.row+1; i++){
			for(j=1; j<totalSize.col+1; j++){
				System.out.print(map[i][j]);
			}
			System.out.println();
		}
	}
	
	private static LinkedList<Point> returnPath(Node child){
		LinkedList<Point> robotPath = new LinkedList<Point>();
		while (child != null){
			robotPath.add(child.point);
			child = child.parent;
		}
		return robotPath;
	}
	
	public static Node AStarAlgorithm(int robotId, Point robotPoint){
		OpenList openList = new OpenList();
		ClosedList closedList = new ClosedList();
		Node initial = new Node(null, robotPoint, 0.0, Point.calcHDistance(robotPoint), 1);
		openList.add(initial);
		Node min = null;
		int reps = 1;
		while(true){
			min = openList.getMin(); if (min==null) System.out.println("!!!");
			System.out.print(">Robot " + robotId + " considering new position <" + min.point.row + "," + min.point.col + "> at step " + min.step);
			if (Point.isTarget(min.point)){
				System.out.println("*** ROBOT " + robotId + " HAS FOUND A PATH. ***");
				System.out.println("*** Nodes checked: " + reps + " ***\n");
				return min;
			}	
			closedList.add(min.point, min.step);
			LinkedList<Point> neighbors = findNeighbors(closedList, min.point);
			for(Point p : neighbors){
				System.out.println("neigh"+ p.row + " " + p.col);
				if (closedList.includes(p)) break;
				Double gNew = min.g_distance + Point.calcDistanceBetweenPoints(min.point, p);
				Node nOld = openList.includes(p);
				if (nOld != null){
					if (nOld.g_distance < gNew) continue;
					else{
						Node nNew = new Node(min, p, gNew, gNew + Point.calcHDistance(p), min.step + 1);
						openList.update(nOld, nNew);
					}
				}
				else{
					Node nNew = new Node(min, p, gNew, gNew + Point.calcHDistance(p), min.step + 1);
					openList.add(nNew);
				}
			}
			reps++;
		}
	}
	
	public static void main(String[] args) {
		Node min1 = AStarAlgorithm(1,robot1Point);
		LinkedList<Point> robot1Path = returnPath(min1);
		Iterator<Point> iter1 = robot1Path.descendingIterator();
		int steps1 = 0;
		
		Node min2 = AStarAlgorithm(2,robot2Point);
		LinkedList<Point> robot2Path = returnPath(min2);
		Iterator<Point> iter2 = robot2Path.descendingIterator();
		int steps2 = 0;
		
		System.out.println("Path of Robot 1:");
		while (iter1.hasNext()) {
			steps1++;
			Point next1 = iter1.next();
			System.out.print(" --> " + next1.row + "," + next1.col);
		}
		System.out.println("\n");
		
		System.out.println("Path of Robot 2:");
		while (iter2.hasNext()) {
			steps2++;
			Point next2 = iter2.next();
			System.out.print(" --> " + next2.row + "," + next2.col);
		}
		System.out.println("\n");
		
		
		
		
		iter1 = robot1Path.descendingIterator();
		iter2 = robot2Path.descendingIterator();
		int i=0;
		while (iter2.hasNext()||iter1.hasNext()) {//for conflict
			i++;
			Point next1 = null;
			Point next2 = null;
			
			if(iter1.hasNext()){//print symbol for robot1(1 or *)
				next1 = iter1.next();
				if (map[next1.row][next1.col] != '2'){
					map[next1.row][next1.col] = '1';
				}
				else{
					map[next1.row][next1.col] = '*';
				}
			}
			
			if(iter2.hasNext()){//print symbol for robot2(2 or *)
				next2 = iter2.next();
				if (map[next2.row][next2.col] != '1'){
					map[next2.row][next2.col] = '2';
				}
				else{
					map[next2.row][next2.col] = '*';
				}
			}
			
			if (iter1.hasNext()&&iter2.hasNext()&&(next1.row==next2.row)&&(next1.col==next2.col)){
				System.out.println("***Conflict at step "+i+"! ***");
				System.out.println("Robot 2 pauses at point ("+next1.row+","+next1.col+") at step "+i+" to avoid conflict!");
				System.out.println("Robot 2 recalculates path after pause and follows the path of robot 1!\n");
				map[next1.row][next1.col] = '@';
				while (iter1.hasNext()){
					next1 = iter1.next();
					map[next1.row][next1.col] = '*';
				}
				break;
			}
		}
		
		if (steps2>=steps1){//to print the one that has reached the target first
			map[targetPoint.row][targetPoint.col] = '1';
		}
		else{
			map[targetPoint.row][targetPoint.col] = '2';
		}
		
		printMap();
	}
}

