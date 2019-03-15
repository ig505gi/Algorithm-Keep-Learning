package algorithm1.week5;

import java.util.ArrayList;
import java.util.List;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;

public class KdTree {
	// private final static boolean X = true;
	// private final static boolean Y = false;
	private Node root;
	
	private class Node {
		Point2D point;
		Node left, right;
		boolean dividedBy;
		private int count;
		
		public Node(Point2D p, boolean dividedBy) {
			this.point = p;
			this.dividedBy = dividedBy;
		}
	}
	
	public KdTree() // construct an empty set of points
	{
	}

	public boolean isEmpty() // is the set empty?
	{
		return root == null; 
	}

	public int size() // number of points in the set
	{
		return size(root);
	}
	private int size(Node x) {
		if (x == null) return 0;
		return x.count;
	}
	
	public void insert(Point2D p) // add the point to the set (if it is not already in the set)
	{
		if (p == null) throw new IllegalArgumentException();
		// 假设root的父亲为devidedByY，则创建root节点时一定是devidedByX
		if (!contains(p)) root = insert(root, p, false); 
	}
	
	private Node insert(Node x, Point2D p, boolean dividedBy) {
		// 如果x为空，要创建新节点，继承父亲的
		if (x == null) x = new Node(p, !dividedBy);
		else {
			double cmp;
			if (x.dividedBy) { // 按照X划分
				cmp = p.x() - x.point.x();
			} else { // 按照Y划分
				cmp = p.y() - x.point.y();
			}
			if (cmp < 0) { // 小于0
				x.left = insert(x.left, p, x.dividedBy);
			} else { // 大于等于0
				x.right = insert(x.right, p, x.dividedBy);
			}
		}
		x.count = 1 + size(x.right) + size(x.left); 
		return x;
	}

	public boolean contains(Point2D p) // does the set contain point p?
	{
		if (p == null) throw new IllegalArgumentException();
		// 返回为null 说明没找到，没有
		if (search(root, p) == null) return false;
		else return true;
	}
	
	private Node search(Node x, Point2D p) {
		if (x == null) return null;
		double cmp;
		if (x.dividedBy) { // 按照X划分
			cmp = p.x() - x.point.x();
		} else { // 按照Y划分
			cmp = p.y() - x.point.y();
		}
		if (cmp < 0) { // 小于0
			return search(x.left, p);
		} else if (x.point.compareTo(p) == 0) { 
			// 如果cmp不<0,有可能相同，则比较是否为此点，如果不是，就遍历右边
			return x;
		} else { // 包含大于等于0
			return search(x.right, p);
		}
	}
	
	
	public void draw() // draw all points to standard draw
	{
		draw(root);
	}
	
	public void drawLine() {
		RectHV wholeRect = new RectHV(0.0, 0.0, 1.0, 1.0);
		drawLine(root, wholeRect);
	}

	private void drawLine(Node x, RectHV wholeRect) {
		if (x == null) return;
		StdDraw.setPenRadius(0.02);
        StdDraw.setPenColor(StdDraw.BLUE);
		x.point.draw();
		StdDraw.setPenRadius(0.005);
		if (x.dividedBy) {
			StdDraw.setPenColor(StdDraw.RED);
			StdDraw.line(x.point.x(), wholeRect.ymin(), x.point.x(), wholeRect.ymax());
		} else {
			StdDraw.setPenColor(StdDraw.BLACK);
			StdDraw.line(wholeRect.xmin(), x.point.y(), wholeRect.xmax(), x.point.y());
		}
		RectHV leftRect = leftRect(x, wholeRect);
		RectHV rightRect = rightRect(x, wholeRect);
		drawLine(x.left, leftRect);
		drawLine(x.right, rightRect);
	}

	private void draw(Node x) {
		if (x == null) return;
		x.point.draw();
		draw(x.left);
		draw(x.right);
	}

	public Iterable<Point2D> range(RectHV rect) // all points that are inside the rectangle (or on the boundary)
	{
		if (rect == null) throw new IllegalArgumentException();
		RectHV wholeRect = new RectHV(0.0, 0.0, 1.0, 1.0);
		List<Point2D> results = new ArrayList<Point2D>();
		range(root, results, rect, wholeRect);
		return results;
	}
	 
	private void range(Node x, List<Point2D> results, RectHV rect, RectHV wholeRect) {
		if (x == null) return;
		if (rect.contains(x.point)) results.add(x.point);
		RectHV leftRect = leftRect(x, wholeRect);
		RectHV rightRect = rightRect(x, wholeRect);
		if (rect.intersects(leftRect)) range(x.left, results, rect, leftRect);
		if (rect.intersects(rightRect)) range(x.right, results, rect, rightRect);
	}
	
	private RectHV leftRect(Node x, RectHV wholeRect) {
		double xminl, yminl, xmaxl, ymaxl;
		if (x.dividedBy) {
			// 如果按照x划分，定义左矩形的坐标
			xminl = wholeRect.xmin();
			yminl = wholeRect.ymin();
			xmaxl = x.point.x();
			ymaxl = wholeRect.ymax();
		} else {
			// 如果按照y划分，定义下矩形的坐标
			xminl = wholeRect.xmin();
			yminl = wholeRect.ymin();
			xmaxl = wholeRect.xmax();
			ymaxl = x.point.y();
		}
		return new RectHV(xminl, yminl, xmaxl, ymaxl);
	}
	
	private RectHV rightRect(Node x, RectHV wholeRect) {
		double xminr, yminr, xmaxr, ymaxr;
		if (x.dividedBy) {
			// 定义右矩形的坐标
			xminr = x.point.x();
			yminr = wholeRect.ymin();
			xmaxr = wholeRect.xmax();
			ymaxr = wholeRect.ymax();
		} else {			
			// 定义上矩形的坐标
			xminr = wholeRect.xmin();
			yminr = x.point.y();
			xmaxr = wholeRect.xmax();
			ymaxr = wholeRect.ymax();
		}
		return new RectHV(xminr, yminr, xmaxr, ymaxr);
	}

	public Point2D nearest(Point2D p) // a nearest neighbor in the set to point p; null if the set is empty
	{
		if (p == null) throw new IllegalArgumentException();
		if (isEmpty()) return null;
		/*
		 * 先把root.point作为最近点，开始递归搜索，
		 * 每次返回的都是最近点
		 * 先和x.point比较，如果更近，更新，反之不变
		 * 如果包含该点，一定先进行扫描，再扫描另一半
		 * 如果不包含，判断 矩形到点 和 最近点到点的距离
		 * -->如果前者小，扫描，
		 * -->如果前者大或者等于，不扫描，结束
		 */
		RectHV wholeRect = new RectHV(0.0, 0.0, 1.0, 1.0);
		Point2D nearestPoint = root.point;
		nearestPoint = nst(root, p, nearestPoint, wholeRect);
		return nearestPoint;
	}
	
	private Point2D nst(Node x, Point2D query, Point2D nearestPoint, RectHV wholeRect) {
		if (x != null) {
			if (query.distanceSquaredTo(x.point) < query.distanceSquaredTo(nearestPoint)) {
				nearestPoint = x.point;
			}
			RectHV leftRect = leftRect(x, wholeRect);
			RectHV rightRect = rightRect(x, wholeRect);
			// 是错误的，有可能都不包含
			// 注释代码是一些修正，但是仍然有错
			// 最好策略是，先比较left和right，决定出first，next，按次序遍历，需要将wholeRect作为kdtree的成员，重构代码
			if (leftRect.contains(query)) {
				nearestPoint = nst(x.left, query, nearestPoint, leftRect);
				// 最近点可能已经更新了，再决定是否扫描另一边
				if (nearestPoint.distanceSquaredTo(query) > rightRect.distanceSquaredTo(query)) {
					nearestPoint = nst(x.right, query, nearestPoint, rightRect);
				}
			}
			else /* if (rightRect.contains(query)) */ {
				// 最近点可能已经更新了，再决定是否扫描另一边
				nearestPoint = nst(x.right, query, nearestPoint, rightRect);
				if (nearestPoint.distanceSquaredTo(query) > leftRect.distanceSquaredTo(query)) {
					nearestPoint = nst(x.left, query, nearestPoint, leftRect);
				}
			} 
			/*
			 * 这段代码加上会少遍历一些部分, 包括上一处注释的一部分
			 * 
			else { // 都不包含的情况，只有小于最近点，才进行扫描
				if (rightRect.distanceSquaredTo(query) > leftRect.distanceSquaredTo(query)) {
					// 左比右小，但是只有小于最近点才扫描
					if (nearestPoint.distanceSquaredTo(query) > leftRect.distanceSquaredTo(query)) {
						nearestPoint = nst(x.left, query, nearestPoint, leftRect);
					}
				} else {
					// 左比右大，但是只有小于最近点才扫描
					if (nearestPoint.distanceSquaredTo(query) > rightRect.distanceSquaredTo(query)) {
						nearestPoint = nst(x.right, query, nearestPoint, rightRect);
					}
				}
			}
			*/
		}
		return nearestPoint;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
