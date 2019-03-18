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

### 2.2 Week2
### 2.3 Week3
### 2.4 Week4
### 2.5 Week5
