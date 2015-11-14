

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

// Class for implementing the reading of the input file.
public class Reader {
	
		private int hSize;
		private int vSize;
		private int robot1Col;
		private int robot1Row;
		private int robot2Col;
		private int robot2Row;
		private int targetCol;
		private int targetRow;
		private char[][] map;
		
		public Reader() {
			try{
				int i,j;
				BufferedReader in = new BufferedReader(new FileReader("src/test3.txt"));
				String line = in.readLine();
				String[] this_line = line.split(" ");
				hSize = Integer.parseInt(this_line[0]);
				vSize = Integer.parseInt(this_line[1]);
				line = in.readLine();
				this_line = line.split(" ");
				robot1Row = Integer.parseInt(this_line[0]);
				robot1Col = Integer.parseInt(this_line[1]);
				line = in.readLine();
				this_line = line.split(" ");
				robot2Row = Integer.parseInt(this_line[0]);
				robot2Col = Integer.parseInt(this_line[1]);
				line = in.readLine();
				this_line = line.split(" ");
				targetRow = Integer.parseInt(this_line[0]);
				targetCol = Integer.parseInt(this_line[1]);
				map = new char[vSize+1][hSize+1];
				for(i=1; i<vSize+1; i++){
					line = in.readLine();
					char[] line_char = line.toCharArray();
					for(j=1; j<hSize+1; j++)
						map[i][j] = line_char[j-1];
				}
				in.close();
			} catch(IOException e){
                System.out.println("ERROR. File missing or unreadable.");
			  }
		}
		
		//Returns a Point object containing the size of the field map. 
		public Point getTotalSize(){
			Point totalSize = new Point(hSize, vSize);
			return totalSize;
		}
		
		//Returns a Point object containing the starting Position of the 1st Robot.
		public Point getRobot1Point(){
			Point robot1Point = new Point(robot1Col, robot1Row);
			return robot1Point;
		}
		
		//Returns a Point object containing the starting Position of the 2nd Robot.
		public Point getRobot2Point(){
			Point robot2Point = new Point(robot2Col, robot2Row);
			return robot2Point;
		}
		
		//Returns a Point object containing the coordinates of the target.
		public Point getTargetPoint(){
			Point targetPoint = new Point(targetCol, targetRow);
			return targetPoint;
		}
		
		//Returns the map in the format of a 2-dimensional Char array.
		public char[][] getMap(){
			return map;
		}
}
