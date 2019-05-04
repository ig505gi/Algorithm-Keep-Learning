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

#### introduction
这两个问题是实际上是一个问题！结果是一样的
![image](https://github.com/CoderOrigin/Algorithm-Keep-Learning/blob/master/CourseraPricetonAlgorithm/Images/MaxFlowMinCut.png)  

#### Ford-Fulkerson Algorithm
>Augmenting path. Find an undirected path from s to t such that:  
・Can increase flow on forward edges (not full).  
・Can decrease flow on backward edge (not empty).  

可加路径（自己翻译的）的概念，任意一条不限方向的路径，要满足一下要求：
1. 含有正向（从s到t的）且没有满的边的路径，我们需要将其变成full
2. 有回边的（与s到t路径相反的）且不为空的路径，我们需要减小，并且使正向的变成full

直到图中没有可加路径，即任意一条路径上，forward edges达到了最满，而backward edge不为空。

Java代码实现，首先要构造residual network
>Key point. Augmenting path in original network is equivalent to directed path in residual network.  

![image](https://github.com/CoderOrigin/Algorithm-Keep-Learning/blob/master/CourseraPricetonAlgorithm/Images/ResidualNetWork.png)  
主循环中，判断是否有可加路径，没有就终结，得到结果；有的话，从t开始遍历，找到bottle（最小的可加值），更新所有flow  
如何判断是否有Augmenting Path:  
![image](https://github.com/CoderOrigin/Algorithm-Keep-Learning/blob/master/CourseraPricetonAlgorithm/Images/AugmentingPath.png)  

#### Applications
>・Data mining.  
・Open-pit mining.  
・Bipartite matching.  
・Network reliability.  
・Baseball elimination.  
・Image segmentation.  
・Network connectivity.  
・Distributed computing.  
・Security of statistical data.  
・Egalitarian stable matching.  
・Multi-camera scene reconstruction.  
・Sensor placement for homeland security.  
・liver and hepatic vascularization segmentation

#### Key-Indexed Counting 
>Goal. Sort an array a[] of N integers between 0 and R - 1.  
・Count frequencies of each letter using key as index.  
・Compute frequency cumulates which specify destinations.  
・Access cumulates using key as index to move items.  
・Copy back into original array.  

基数排序的基本原则

#### LSD Radix Sort
>LSD string (radix) sort.  
・Consider characters from right to left.  
・Stably sort using dth character as the key (using key-indexed counting).  

Sort one million 32-bit integers.  
用LSD排序最快，把其中后几位当做第一个loop，然后后第二个几位。。。。

#### MSD Radix Sort
>MSD string (radix) sort.  
・Partition array into R pieces according to first character (use key-indexed counting).  
・Recursively sort all strings that start with each character (key-indexed counts delineate subarrays to sort).  

和LSD比，MSD是从第一位往后面比，然后递归每部分，比第二位。。。

问题：对于小序列，也需要建立count\[]数组，很慢，需要cutoff解决，递归到很小的时候，直接用插入排序

#### 排序算法对比
![image](https://github.com/CoderOrigin/Algorithm-Keep-Learning/blob/master/CourseraPricetonAlgorithm/Images/LSDMSDperfermance.png)  

MSD和Quick Sort对比：
>Disadvantages of MSD string sort.  
・Extra space for aux\[].  
・Extra space for count\[].  
・Inner loop has a lot of instructions.  
・Accesses memory "randomly" (cache inefficient). 

>Disadvantage of quicksort.  
・Linearithmic number of string compares (not linear).  
・Has to rescan many characters in keys with long prefix matches.  

#### 3-way string quicksort
![image](https://github.com/CoderOrigin/Algorithm-Keep-Learning/blob/master/CourseraPricetonAlgorithm/Images/3wayStringQuickSort.png)

#### suffix sort  
什么是suffix  
![image](https://github.com/CoderOrigin/Algorithm-Keep-Learning/blob/master/CourseraPricetonAlgorithm/Images/suffixsort.png)

查找非常快，但是也有缺陷：  
比如有20million的数据，重复的是10million，就查找不出来，因为重复比较的次数是1+2+3.。。+10million，是n2时间复杂度了  

#### Manber-Myers MSD algorithm
是一个nlogn复杂度的算法，非常amazing 
>・Phase 0: sort on first character using key-indexed counting sort.  
・Phase i: given array of suffixes sorted on first 2^(i-1) characters,  
   create array of suffixes sorted on first 2^i characters.  

最坏情况下的时间复杂度也是nlogn  
![image](https://github.com/CoderOrigin/Algorithm-Keep-Learning/blob/master/CourseraPricetonAlgorithm/Images/MMsuffixsort.png)  
假如前4个字母比较完了，直接比较第5-8个字母，因为这些已经对比过了，只要把他的index+4，就是之前比较过的顺序  
可以注意下面英文注释。  

### 2.4 Week4 Tries & Substring searching
#### what's try?
![image](https://github.com/CoderOrigin/Algorithm-Keep-Learning/blob/master/CourseraPricetonAlgorithm/Images/tries.png)  

#### Ternary search tries (TST)
特征：
>・Store characters and values in nodes (not keys).  
・Each node has 3 children: smaller (left), equal (middle), larger (right).  

Node 结构：
```
private class Node {
    private Value val; 
    private char c;
    private Node left, mid, right;
}
```
实现比较简单：
>Follow links corresponding to each character in the key.  
・If less, take left link; if greater, take right link.  
・If equal, take the middle link and move to the next key character.  
Search hit. Node where search ends has a non-null value.  
Search miss. Reach a null link or node where search ends has null value.  

#### Character-based operations
>Prefix match. Keys with prefix sh: she, shells, and shore.  
Wildcard match. Keys that match .he: she and the.  
Longest prefix. Key that is the longest prefix of shellsort: shells.  

#### Cost summary compared to Hashing
![image](https://github.com/CoderOrigin/Algorithm-Keep-Learning/blob/master/CourseraPricetonAlgorithm/Images/CostSummaryWithTST.png)  

>Hashing.  
・Need to examine entire key.  
・Search hits and misses cost about the same.  
・Performance relies on hash function.  
・Does not support ordered symbol table operations.  

TSTs.
・Works only for strings (or digital keys).  
・Only examines just enough key characters.  
・Search miss may involve only a few characters.   
・Supports ordered symbol table operations (plus others!).  

Bottom line. TSTs are:
・Faster than hashing (especially for search misses).  
・More flexible than red-black BSTs.  


#### String symbol tables summary
>Red-black BST.  
・Performance guarantee: log N key compares.  
・Supports ordered symbol table API.  
Hash tables.  
・Performance guarantee: constant number of probes.  
・Requires good hash function for key type.  
Tries. R-way, TST.  
・Performance guarantee: log N characters accessed.  
・Supports character-based operations.   

#### Knuth-Morris-Pratt substring search (重点！！！)  
当时自学数据结构的时候，啃书弄明白了点，就对这个算法记忆犹新。后来蹭软件学院的数据结构课，那个老师也讲了这个算法，但是我个人觉得那个老师讲的不好，当时也是一知半解。一直想把这个算法弄得清楚明白，终于来到了这里。第三次学习。  

先奉上Priceton大学的Sedgewick老教授的评价：
>But I just want to start by saying this is one of the coolest algorithms that we'll cover in this course. And it's not an algorithm that anyone would come up with without a lot of hard work, but understanding this algorithm really gives somebody an appreciation for what's possible with careful algorithmic thinking, even for such a simple problem as this. It's a quite ingenious method.  

![image](https://github.com/CoderOrigin/Algorithm-Keep-Learning/blob/master/CourseraPricetonAlgorithm/Images/DFA.png)  

利用DFA，时间复杂度能达到线性，如果和构造DFA呢，  
首先是match的情况  
![image](https://github.com/CoderOrigin/Algorithm-Keep-Learning/blob/master/CourseraPricetonAlgorithm/Images/DFAmatch.png) 
然后是mismatch的情况，把pat\[1...j-1]用已经建成的部分DFA模拟，得到的X，就是目前j需要参照的state  
因此，构造的时候，我们要始终维持一个X，是模拟的pat\[1...j-1]  
![image](https://github.com/CoderOrigin/Algorithm-Keep-Learning/blob/master/CourseraPricetonAlgorithm/Images/DFAmismatch.png)  

代码：  
![image](https://github.com/CoderOrigin/Algorithm-Keep-Learning/blob/master/CourseraPricetonAlgorithm/Images/KMPcode.png)  
非常简短  R是pat中字符出现的情况个数，如果过于大的话，占得空间就太大，当R很大的时候，可以使用NFA，额外空间只有M，用来记录每个j返回到前面的某个状态，而不是返回0.  


### 2.5 Week5 Regular Expressions & Data Compression

#### REs & DFAs
>RE. Concise way to describe a set of strings.  
DFA. Machine to recognize whether a given string is in a given set.  

#### NFA implements
![image](https://github.com/CoderOrigin/Algorithm-Keep-Learning/blob/master/CourseraPricetonAlgorithm/Images/NFAsimu.png)  

#### RE --> NFA
从Re到NFA需要处理的，状态，联系，字符，匹配字符，括号，星号，或者  
>States. Include a state for each symbol in the RE, plus an accept state.  
Concatenation.  Add match-transition edge from state corresponding to characters in the alphabet to next state.  
Alphabet. A B C D  
Metacharacters. ( ) . * |  
Parentheses. Add ε-transition edge from parentheses to next state.  
Closure. Add three ε-transition edges for each * operator.  

![image](https://github.com/CoderOrigin/Algorithm-Keep-Learning/blob/master/CourseraPricetonAlgorithm/Images/Closure.png)  
>Or. Add two ε-transition edges for each | operator.  

![image](https://github.com/CoderOrigin/Algorithm-Keep-Learning/blob/master/CourseraPricetonAlgorithm/Images/Or.png)  
用stack来记录括号的信息  
>Challenges. Remember left parentheses to implement closure and or; remember | to implement or.  
Solution. Maintain a stack.  
・( symbol: push ( onto stack.  
・| symbol: push | onto stack.  
・) symbol: pop corresponding ( and any intervening |; add ε-transition edges for closure/or.  


#### Java Re
Java中的正则表达式怎么用：  
![image](https://github.com/CoderOrigin/Algorithm-Keep-Learning/blob/master/CourseraPricetonAlgorithm/Images/JavaRe.png)  

### 2.6 Week6 Reduce & Lineal Progam

#### 线性编程的应用场景： 思想是线性规划：  
>Agriculture. Diet problem.  
Computer science. Compiler register allocation, data mining.  
Electrical engineering. VLSI design, optimal clocking.  
Energy. Blending petroleum products.  
Economics. Equilibrium theory, two-person zero-sum games.  
Environment. Water quality management.  
Finance. Portfolio optimization.  
Logistics. Supply-chain management.  
Management. Hotel yield management.  
Marketing. Direct mail advertising.  
Manufacturing. Production line balancing, cutting stock.  
Medicine. Radioactive seed placement in cancer treatment.   
Operations research. Airline crew assignment, vehicle routing.   
Physics. Ground states of 3-D Ising spin glasses.  
Telecommunication. Network design, Internet routing.   
Sports. Scheduling ACC basketball, handicapping horse races.  

#### Pivot 
详细见PDF  

#### P=NP？  
涉及到的知识比较遥远，再需要的时候再去学习  
