package algorithm1.week5;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Stopwatch;

public class NearestNeighborVisualizer {

	public static void main(String[] args) {
		// initialize the two data structures with point from file
        String filename = args[0];
        In in = new In(filename);
        PointSET brute = new PointSET();
        KdTree kdtree = new KdTree();
        int count = 0;
        while (!in.isEmpty()) {
            double x = in.readDouble();
            double y = in.readDouble();
            if (!(count++ % 1 == 0)) continue;
            Point2D p = new Point2D(x, y);
            kdtree.insert(p);
            brute.insert(p);
        }

        // process nearest neighbor queries
        StdDraw.enableDoubleBuffering();
        while (true) {

            // the location (x, y) of the mouse
            double x = StdDraw.mouseX();
            double y = StdDraw.mouseY();
            Point2D query = new Point2D(x, y);

            // draw all of the points
            StdDraw.clear();
            StdDraw.setPenColor(StdDraw.BLACK);
            StdDraw.setPenRadius(0.01);
            brute.draw();

            // draw in red the nearest neighbor (using brute-force algorithm)
            StdDraw.setPenRadius(0.03);
            StdDraw.setPenColor(StdDraw.RED);
            Stopwatch sw = new Stopwatch();
            brute.nearest(query).draw();
            StdOut.println("brute time-----: " + sw.elapsedTime());
            StdDraw.setPenRadius(0.02);

            // draw in blue the nearest neighbor (using kd-tree algorithm)
            StdDraw.setPenColor(StdDraw.BLUE);
            Stopwatch sw2 = new Stopwatch();
            kdtree.nearest(query).draw();
            StdOut.println("kdtree time: " + sw2.elapsedTime());
            StdDraw.show();
            StdDraw.pause(40);
        }

	}

}
