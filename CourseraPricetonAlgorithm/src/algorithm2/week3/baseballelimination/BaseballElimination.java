package algorithm2.week3.baseballelimination;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import edu.princeton.cs.algs4.FlowEdge;
import edu.princeton.cs.algs4.FlowNetwork;
import edu.princeton.cs.algs4.FordFulkerson;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
/**
 * If all edges in the maxflow that are pointing from s are full, 
 * then this corresponds to assigning winners to all of the remaining games in such a way that no team wins more games than x. 
 * If some edges pointing from s are not full, 
 * then there is no scenario in which team x can win the division. 
 * @author GaoYuan
 *
 */
public class BaseballElimination {
	final private String[] teams;
	final private int[] w;
	final private int[] l;
	final private int[] r;
	final private int[][] g;
	
	public BaseballElimination(String filename)  // create a baseball division from given filename in format specified below
	{
		if (filename == null) throw new IllegalArgumentException();
		In in = new In(filename);
		int n = in.readInt();
		teams = new String[n];
		w = new int[n];
		l = new int[n];
		r = new int[n];
		g = new int[n][n];
		for (int i = 0; !in.isEmpty(); i++) {
			teams[i] = in.readString();
			w[i] = in.readInt();
			l[i] = in.readInt();
			r[i] = in.readInt();
			for (int j = 0; j < n; j++) {
				g[i][j] = in.readInt();
			}
		}
	}

	public int numberOfTeams()  // number of teams
	{
		return teams.length;
	}

	public Iterable<String> teams()   // all teams
	{		
		return Arrays.asList(teams);
	}

	public int wins(String team)  // number of wins for given team
	{
		validate(team);
		return w[getIndex(team)];
	}

	public int losses(String team)  // number of losses for given team
	{
		validate(team);
		return l[getIndex(team)];
	}

	public int remaining(String team)  // number of remaining games for given team
	{
		validate(team);
		return r[getIndex(team)];
	}

	public int against(String team1, String team2)  // number of remaining games between team1 and team2
	{
		validate(team1);
		validate(team2);
		return g[getIndex(team1)][getIndex(team2)];
	}

	public boolean isEliminated(String team)  // is given team eliminated?
	{
		validate(team);
		if (certificateOfElimination(team) != null) return true;
		return false;
	}

	public Iterable<String> certificateOfElimination(String team)  // subset R of teams that eliminates given team; null if not eliminated
	{
		validate(team);
		Set<String> R = new HashSet<String>();
		if (trivialElimination(team) != null) {
			R.add(trivialElimination(team));
		} else {
			int n = numberOfTeams();
			// 我们要关注的队伍的index
			int interestIndex = getIndex(team);
			// 利用maxflow解决问题
			// 一共有 1 + (n-1)(n-2)/2 + n-1 + 1个节点
			int V = (n - 1) * (n - 2) / 2 + n + 1;
			FlowNetwork G = new FlowNetwork(V);
			// 添加边，s顶点编号为0，连接的(n-1)(n-2)/2个节点的顶点gameVertice编号从 n- 2 到 v-2
			// 连接t顶点teamVertice的n-1个节点编号从1 到 n-1, t顶点编号为 v - 1
			// 在network中的vertice如果小于等于interstIndex，则 需要 - 1才能得到w,l,r,teamsName
			// 如果大于interestIndex，直接就可以取w，l，r等
			
			// 连接s和gameVertice,连接gameVertice和teamVertice
			for (int gameVertice = n, teamVertice1 = 1, teamVertice2 = teamVertice1 + 1; 
					gameVertice < V - 1 && teamVertice1 <= n -2; gameVertice++, teamVertice2++) {
				// 这个条件语句使两个teamVertice如下这样遍历
				// 1-2，1-3，1-4，2-3，2-4，3-4
				if (teamVertice2 > n - 1) {
					teamVertice1++; 
					teamVertice2 = teamVertice1 + 1;
				}
				// 得到的是可以查询w, r, l的index
				int i = teamVerticeToIndex(teamVertice1, interestIndex);
				int j = teamVerticeToIndex(teamVertice2, interestIndex);
				// 1. 连接所有s的边			
				G.addEdge(new FlowEdge(0, gameVertice, g[i][j]));
				// 2. 连接gameVertice和 teamVertice
				G.addEdge(new FlowEdge(gameVertice, teamVertice1, Double.POSITIVE_INFINITY));
				G.addEdge(new FlowEdge(gameVertice, teamVertice2, Double.POSITIVE_INFINITY));
			}
			
			// 连接teamVertice和t
			for (int teamVertice = 1; teamVertice < n; teamVertice++) {
				// 得到的是可以查询w, r, l的index
				int teamIndex = teamVerticeToIndex(teamVertice, interestIndex);
				double capacity = w[interestIndex] + r[interestIndex] - w[teamIndex];
				G.addEdge(new FlowEdge(teamVertice, V - 1, capacity));
			}
			// System.out.println(G.toString());
			// System.out.println("---------------------------------------");
			// 构造并计算maxflow
			FordFulkerson ff = new FordFulkerson(G, 0, V - 1); 
			// 找到inCut的gameVertice对应的TeamVertice
			for (int gameVertice = n; gameVertice < V - 1; gameVertice++) {
				// 如果inCut为真，说明顶点和s在一侧，说明 s连接到gameVertice没有full
				if (ff.inCut(gameVertice)) {
					for (FlowEdge e: G.adj(gameVertice)) {
						int teamVertice = e.other(gameVertice);
						// adj(v)返回的是指向和支出说有的顶点，因此要把s排除掉
						if (teamVertice != 0) {
							int index = teamVerticeToIndex(teamVertice, interestIndex);
							R.add(teams[index]);
						}
					}
				}
			}
		}
		if (R.isEmpty()) return null;
		else return R;
	}
	
	// 辅助函数
	// 在network中的vertice如果小于等于interstIndex，则 需要 - 1才能得到w,l,r,teamsName
	// 如果大于interestIndex，直接就可以取w，l，r等
	private int teamVerticeToIndex(int teamVertice, int interestIndex) {
		 if (teamVertice <= interestIndex) {
			 teamVertice -= 1;
		 }
		 return teamVertice;
	}
	
	private String trivialElimination(String team) {
		String maxWinTeam = null;
		// 先找到最大胜场
		int maxWinTeamIndex = -1;
		int maxWin = 0;
		for (int i = 0; i < w.length; i++) {
			if (w[i] > maxWin) {
				maxWin = w[i];
				maxWinTeamIndex = i;
			}
		}
		// 不可能比最大胜场赢的还多
		if (maxWinTeamIndex >= 0 && w[getIndex(team)] + r[getIndex(team)] < maxWin) {
			maxWinTeam = teams[maxWinTeamIndex];
		}
		return maxWinTeam;
	}
	
	private void validate(String team) {
		if (team == null) throw new IllegalArgumentException("输入队伍名为null");
		if (getIndex(team) == -1) throw new IllegalArgumentException("队伍名"+ team +"不存在");
	}
	
	private int getIndex(String team) {
		for (int i = 0; i < teams.length; i++) {
			if (teams[i].equals(team)) {
				return i;
			}
		}
		return -1;
	}

	public static void main(String[] args) {
	    BaseballElimination division = new BaseballElimination(args[0]);
	    for (String team : division.teams()) {
	        if (division.isEliminated(team)) {
	            StdOut.print(team + " is eliminated by the subset R = { ");
	            for (String t : division.certificateOfElimination(team)) {
	                StdOut.print(t + " ");
	            }
	            StdOut.println("}");
	        }
	        else {
	            StdOut.println(team + " is not eliminated");
	        }
	    }
	}
}
