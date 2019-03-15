package algorithm1.week3;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Stopwatch;

public class BruteCollinearPoints {
	private int num = 0;
	private LineSegment[] ls;
	
	public BruteCollinearPoints(Point[] points) // finds all line segments containing 4 points
	{
		
		if (points.length < 4) { throw new IllegalArgumentException(); }
		//排序
		for (int i = 0; i < points.length - 1; i++) {
			for (int j = i + 1; j < points.length; j++) {
				if (points[i].compareTo(points[j]) > 0) {
					Point temp = points[i];
					points[i] = points[j];
					points[j] = temp;
				}
			}
		}
		ls = new LineSegment[points.length];
		
		for (int i1 = 0; i1 < points.length - 3; i1++) {
			// System.out.println("-->第一个点：" + points[i1]);
			for (int i2 = i1 + 1; i2 < points.length - 2; i2++) {
				// System.out.println("-->-->第二个点：" + points[i2]);
				for (int i3 = i2 + 1; i3 < points.length - 1; i3++) {
					// System.out.println("-->-->-->第三个点：" + points[i3]);
					if (points[i1].slopeTo(points[i2]) != points[i2].slopeTo(points[i3])) {
						// System.out.println("-->-->-->-->这三个点不共线，直接跳过扫描第四个点");
						continue;
					}
					for (int i4 = i3 + 1; i4 < points.length; i4++) {
						// System.out.println("-->-->-->-->第四个点：" + points[i4]);
						if (points[i3].slopeTo(points[i4]) == points[i2].slopeTo(points[i3])) {
							this.ls[this.num++] = new LineSegment(points[i1], points[i4]);
							// System.out.print("-->-->-->-->-->成功找到");
							// System.out.println(points[i1].toString() + points[i2] + points[i3] + points[i4]);
							break;
						}
						
					}
				}
			}
		}
	}

	public int numberOfSegments() // the number of line segments
	{
		return num;
	}

	public LineSegment[] segments() // the line segments
	{
		LineSegment[] newls = new LineSegment[num];
		for (int i = 0; i < num; i++) {
			newls[i] = this.ls[i];
		}
		return newls;
	}
	
	public static void main(String[] args) {
		In in = new In("src/coursera/algorithm1/week3/input20.txt"); 
        int n = in.readInt();
        StdOut.println("total "+n+" points");
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            StdOut.println("("+x+","+y+")"); 
            points[i] = new Point(x,y);
        }
		
		StdDraw.enableDoubleBuffering();
	    StdDraw.setXscale(0, 32768);
	    StdDraw.setYscale(0, 32768);
	    for (Point p : points) {
	        p.draw();
	    }
	    StdDraw.show();
	    
		Stopwatch stopwatch = new Stopwatch();
		BruteCollinearPoints bcp = new BruteCollinearPoints(points);	
		
		for (LineSegment s: bcp.segments()) {
			System.out.println(s.toString());
			s.draw();
		}
		System.out.println(stopwatch.elapsedTime());
		StdDraw.show();
	}
}
