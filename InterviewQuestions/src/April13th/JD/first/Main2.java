package April13th.JD.first;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
作者：.Ｓ
链接：https://www.nowcoder.com/discuss/177857?type=0&order=0&pos=23&page=1
来源：牛客网
**/
public class Main2 {     
	static class Node {
		Set<Node> nodes;
		int val;

		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((nodes == null) ? 0 : nodes.hashCode());
			result = prime * result + val;
			return result;
		}

		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Node other = (Node) obj;
			if (val != other.val)
				return false;
			return true;
		}
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		int time = 0;
		Node node = new Node();
		node.nodes = new HashSet<Node>();
		node.val = 1;
		for (int i = 0; i < n - 1; i++) {
			int a1 = scanner.nextInt();
			int a2 = scanner.nextInt();
			if (a2 == 1) {
				Node node2 = new Node();
				node2.nodes = new HashSet<Node>();
				Node node3 = new Node();
				node3.val = a1;
				node2.nodes.add(node3);
				node2.val = a1;
				node.nodes.add(node2);
			} else {
				Node tarNode = new Node();
				tarNode.val = a2;
				Node newNode = new Node();
				newNode.val = a1;
				for (Node n2 : node.nodes) {
					if (n2.nodes.contains(tarNode)) {
						n2.nodes.add(newNode);
					}
				}
			}
		}
		int max = Integer.MIN_VALUE;
		for (Node node2 : node.nodes) {
			if (max < node2.nodes.size()) {
				max = node2.nodes.size();
			}
		} // max++; System.out.println(max); }
	}
}
