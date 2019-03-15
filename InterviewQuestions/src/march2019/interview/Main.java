package march2019.interview;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.Stack;
/**
 * 牛客网的模拟面试题，当天刚写了8puzzle，学了下A*算法，一个小时写完一道题。。剩下两道没时间了
 * 但是运行成功率只有30%
 * @author GaoYuan
 *
 */
public class Main {
    /*今天刚学的A*搜索算法，
    fx = gn + hn
    一个是树的深度越小 优先级越高，另外一个是与结果的差值越小 优先级越高
    */
    private Node current;
    private static int k;
    private static int n;
    
    private class Node implements Comparable<Node>{
        private int index;
        private int result;
    	private int depth;
        private Node parent;
        private boolean isAdd;
        private int priority;
		
        public Node(int index, Node parent, boolean isAdd) {
        	this.index = index;
        	this.parent = parent;
        	this.isAdd = isAdd;
        	if (parent != null) {
        		this.depth = parent.depth + 1;
        		if (isAdd) this.result = parent.result + index;
        		else this.result = parent.result - index;
        	} else {
        		this.depth = 1;
        		if (isAdd) this.result = 0 + index;
        		else this.result = 0 - index;
        	}
        	this.priority = Math.abs(n - this.depth) + Math.abs(k - result);
        }
        @Override
		public int compareTo(Node that) {
			return this.priority - that.priority;
		}
    }
    
    public Main(int n, int k) {
    	Main.n = n;
    	Main.k = k;
    	Node root1 = new Node(1, null, true);
    	Node root2 = new Node(1, null, false);
    	PriorityQueue<Node> pq = new PriorityQueue<Main.Node>();
    	pq.add(root1);
    	pq.add(root2);
    	while(true) {
    		current = pq.remove(); //先进行搜索权值高的
    		if (current.depth == k && current.result == n) break;
    		pq.add(new Node(current.index * 2, current, true));
    		pq.add(new Node(current.index * 2, current, false));
    		pq.add(new Node(current.index * 2 + 1, current, true));
    		pq.add(new Node(current.index * 2 + 1, current, false));
    	}
    }
    
    public Stack<Node> result(){
    	Stack<Node> result = new Stack<Node>();
    	for(Node temp = current; temp != null; temp = temp.parent) {
    		result.add(temp);
    	}
    	return result;
    }
    
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int k = in.nextInt();
        Main solution = new Main(n, k);
        
        Stack<Node> stack = solution.result();
        while (!stack.isEmpty()) {
        	Node node = stack.pop();
        	if (node.isAdd) {
        		System.out.println("" + node.index + " +");
        	} else {
        		System.out.println("" + node.index + " -");
        	}
        }
        in.close();
    }
}