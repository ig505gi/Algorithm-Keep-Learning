# 2.Part2

### 2.1 Week1

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
### 2.2 Week2
### 2.3 Week3
### 2.4 Week4
### 2.5 Week5
