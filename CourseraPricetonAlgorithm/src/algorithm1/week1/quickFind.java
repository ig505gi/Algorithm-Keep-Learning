package algorithm1.week1;

public class quickFind { 
	private int[] qf;
	
	public quickFind(int N) {
		qf = new int[N];
		for(int i =0; i<qf.length;i++) {
			qf[i] = i;
		}
	}

	public boolean connected(int p, int q) {
		return qf[p] == qf[q];
	}
	
	public void union(int p, int q) {
		if(qf[p] != qf[q]) {
			int pval = qf[p];
			//将 与p相连的全部都变成 p的出口，也就是qf[q]
			for(int i =0; i< qf.length;i++) {
				if (qf[i] == pval)  qf[i] = qf[q];
			}
		}
	}
	
	public static void main(String[] args) {
		quickFind demo = new quickFind(10);
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
