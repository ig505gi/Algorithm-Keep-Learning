# 2.Part2

### 2.1 Week1 Graphs

#### Design pattern for graph processing
>Design pattern. Decouple graph data type from graph processing.  
・Create a Graph object.  
・Pass the Graph to a graph-processing routine.  
・Query the graph-processing routine for information.  

解耦图对象和图处理，是很重要的，分开进行实现。  

#### Graph-processing challenge
![image](https://github.com/CoderOrigin/Algorithm-Keep-Learning/blob/master/CourseraPricetonAlgorithm/Images/GraphProcessingProblems.png)   

***bipartite***  
指顶点可以分成两个不相交的集使得在同一个集内的顶点不相邻（没有共同边）的图。  

***Euler Circle***  
>Euler tour. Is there a (general) cycle that uses each edge exactly once?  
Answer. A connected graph is Eulerian iff all vertices have even degree.  

如果相连的点度为偶数，就能解，有Euler Circle存在  
如果为奇数，则不可能。  **可以变成解决**  

***Hamilton tour***   
> classial NP-complete problem  

一次遍历所有的点仅一次，很难解决  

***Graph isomorphism***   
> Do two adjacency lists represent the same graph?   

指的是如果Graph中的点的标号变一下，两个Graph相同吗  
目前还无解  

### Topological sort
DAG： Directed acyclic graph. 有向无环图  
>Topological sort: Redraw DAG so all edges point upwards.  
・Run depth-first search.  
・Return vertices in reverse postorder.  

每遍历完成一个点加入stack，加入stack的顺序就是postorder  
最后一个遍历完的就是初始的点，stack反过来的顺序就是：topological order  
在dfs中加入这行代码：  
![image](https://github.com/CoderOrigin/Algorithm-Keep-Learning/blob/master/CourseraPricetonAlgorithm/Images/postorder.png) 

### Connected components vs. strongly-connected components
![image](https://github.com/CoderOrigin/Algorithm-Keep-Learning/blob/master/CourseraPricetonAlgorithm/Images/strongly-connectedComponents.png)  

>Strong components algorithms: brief history  
 1960s: Core OR problem.  
・Widely studied; some practical algorithms.  
・Complexity not understood.  
1972: linear-time DFS algorithm (Tarjan).  
・Classic algorithm.  
・Level of difficulty: Algs4++.  
・Demonstrated broad applicability and importance of DFS.  
1980s: easy two-pass linear-time algorithm (Kosaraju-Sharir).  
・Forgot notes for lecture; developed algorithm in order to teach it!   
・Later found in Russian scientific literature (1972).  
1990s: more easy linear-time algorithms.  
・Gabow: fixed old OR algorithm.  
・Cheriyan-Mehlhorn: needed one-pass algorithm for LEDA.  

***Kosaraju-Sharir algorithm***  
>Simple (but mysterious) algorithm for computing strong components.  
・Phase 1: run DFS on GR to compute reverse postorder.  
・Phase 2: run DFS on G, considering vertices in order given by first DFS.  

(GR指：Reverse graph，且 GR和G的Strong components相同)
### 2.2 Week2 Minumum Spaning Tree & Shortest Paths

#### Minumum Spaning Tree
定义：  
>A spanning tree of G is a subgraph T that is both a tree (connected and acyclic) and spanning (includes all of the vertices).  
 
#### greed algorithm实现  
好像有个前提，weight is distinct，有重复就不止一个解  
>Cut property. Given any cut, the crossing edge of min weight is in the MST.

![image](https://github.com/CoderOrigin/Algorithm-Keep-Learning/blob/master/CourseraPricetonAlgorithm/Images/cutProperty.png)  
>steps:
・Start with all edges colored gray.  
・Find cut with no black crossing edges; color its min-weight edge black.  
・Repeat until V - 1 edges are colored black.  

因此重点是如何选择cut！
>Efficient implementations. Choose cut? Find min-weight edge?  
Ex 1. Kruskal's algorithm.   
Ex 2. Prim's algorithm.   
Ex 3. Borüvka's algorithm.  

Ex 1. 是贪婪算法的一种特殊情况  
把边的权值排序，从小到大，如果加入MST不产生环，则就是需要的边，如果产生环，去除，直到所有点都在MST中。  
Ex 1.1 如何找环：1. DFS遍历。 2.Union Find，如果v和w在一个Union中，则产生了环

#### Prim's Algorithm 
Ex 2. 是贪婪算法的一种特殊情况   
>・Start with vertex 0 and greedily grow tree T.  
・Add to T the min weight edge with exactly one endpoint in T.  
・Repeat until V - 1 edges.  

Ex 2.1 Lazy 维持一个MinPQ<Edge>, 所有的边的顶点都是在T中的  
Ex 2.2 eager 位置一个MinPQ<Vertices(Integer)>, edgeTo[], weight[], 保存的是能靠近T的所有点的最短路径，每次从最近的所有点中选出一个加入T  

![image](https://github.com/CoderOrigin/Algorithm-Keep-Learning/blob/master/CourseraPricetonAlgorithm/Images/eager.png)  
decrease priority操作：提高或者降低priority操作，需要重新排序  
不能用MinPQ实现，需要新的数据结构IndexMinPQ来完成  
![image](https://github.com/CoderOrigin/Algorithm-Keep-Learning/blob/master/CourseraPricetonAlgorithm/Images/IndexMinPQ.png)  

#### shortest path
>Goal. Find the shortest path from s to every other vertex.  

这节课考虑的SP都是：single-source shortest paths

#### Edge Relaxation

>Relax edge e = v→w.
・ distTo\[v] is length of shortest known path from s to v.  
・ distTo\[w] is length of shortest known path from s to w.  
・ edgeTo\[w] is last edge on shortest known path from s to w.  
・If e = v→w gives shorter path to w through v, update both distTo\[w] and edgeTo\[w].  

![image](https://github.com/CoderOrigin/Algorithm-Keep-Learning/blob/master/CourseraPricetonAlgorithm/Images/relaxation.png)  
加入新的edge需要进行Relaxation操作，判断是否更新  

因此有一个通用的解法：从没有edge开始，每加一条边就进行Relaxation操作，直到所有边加入  
>Efficient implementations. How to choose which edge to relax?  
Ex 1. Dijkstra's algorithm (nonnegative weights).  
Ex 2. Topological sort algorithm (no directed cycles).  
Ex 3. Bellman-Ford algorithm (no negative cycles).  

**Ex 1. Dijkstra's algorithm**  
>・Consider vertices in increasing order of distance from s (non-tree vertex with the lowest distTo[] value).  
・Add vertex to tree and relax all edges pointing from that vertex.   

![image](https://github.com/CoderOrigin/Algorithm-Keep-Learning/blob/master/CourseraPricetonAlgorithm/Images/dijkstrasAlgorithm.png)  
![image](https://github.com/CoderOrigin/Algorithm-Keep-Learning/blob/master/CourseraPricetonAlgorithm/Images/dijkstrasRelaxation.png)  

**Ex 2. Topological sort algorithm**
>Acyclic shortest paths demo  
 ・Consider vertices in topological order.  
 ・Relax all edges pointing from that vertex.  
 
**Ex 3. Bellman-Ford algorithm**
>Initialize distTo\[s] = 0 and distTo\[v] = ∞ for all other vertices.
 Repeat V times: - Relax each edge.  
 
 虽然最坏的情况是EV的复杂度，但实际用起来，平均是E+V的复杂度  
 
#### Cost Summary
![image](https://github.com/CoderOrigin/Algorithm-Keep-Learning/blob/master/CourseraPricetonAlgorithm/Images/CostSummary.png)  

### 2.3 Week3  Maximum Flow & Minimum Cut & Radix Sorts

#### 
### 2.4 Week4
### 2.5 Week5
