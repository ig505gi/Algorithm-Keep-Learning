package algorithm1.week1;

public class quickUnion {
	private int[] id;
	
	public quickUnion(int N) {
		id = new int[N];
		for(int i= 0; i<id.length; i++) {
			id[i] = i;
		}
	}
	
	public int root(int i) {
		while (id[i] != i) i=id[i];
		return i;
	}
	
	public boolean connected(int p, int q) {
		return root(p) == root(q);
	}
	
	public void union(int p, int q) {
		int proot = root(p);
		int qroot = root(q);
		id[proot] = qroot;
	}

	public static void main(String[] args) {
		quickUnion demo = new quickUnion(10);
		demo.union(4, 3);
		demo.union(3, 8);
		demo.union(6, 5);
		demo.union(9, 4);
		demo.union(2, 1);
		System.out.println(demo.connected(0, 7));
		System.out.println(demo.connected(8, 9));
		demo.union(5, 0);
		demo.union(7, 2);
		demo.union(6, 1);
		demo.union(1, 0);
		System.out.println(demo.connected(0, 7));

	}

}
