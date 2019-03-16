# Coursera上普林斯顿大学的算法课

本项目主要由一些数据结构和简单算法的**JAVA代码实现**以及**每周作业**组成

### 课程包括Part1和Part2两部分

## 1.Part1
### 1.1 Week1
#### Quick Find 
数组实现： index表示point，value表示出口，同一集合的point的value相同
#### Quick Union
数组实现原理：  
i.  id\[i] is parent of i.   
ii.  root: 当id\[i] == i时，该点即为根  
iii. union: 将两点的根设置为相同即可
#### Weighted quick-union with path compression
Quick Union改进了 Quick Find，平均时间复杂度变成了lgN，但是特殊情况下，树会越来越深  
改进1：  Weighted quick-union： 把子树加上size，总选择size小的添加  
改进2：  Path Compression： `id[i] = id[id[i]];` 因为我们只是找相同的root，不在意过程，可以直接寻找parent的parent来压缩路径；  
#### Union-Find Summary
![image](https://github.com/CoderOrigin/Algorithm-Keep-Learning/blob/master/CourseraPricetonAlgorithm/Images/unionFind.png)
图为Union-Find的各种实现算法的时间复杂度
#### 算法分析
Typical orders of growth  

![image](https://github.com/CoderOrigin/Algorithm-Keep-Learning/blob/master/CourseraPricetonAlgorithm/Images/typicalOrdersOfGrowth.png)

![image](https://github.com/CoderOrigin/Algorithm-Keep-Learning/blob/master/CourseraPricetonAlgorithm/Images/typicalOrdersOfGrowth2.png)

时间复杂度表示方法：
> Big Theta: Θ(N2)是指复杂度等于N2  
Big Oh: O(N2)是指复杂度等于N2或者小于N2  
Big Omega: Ω(N2)是指复杂度等于N2或者大于N2 

#### Java中对象内存用法

>Object overhead. 16 bytes.  
Reference. 8 bytes.  
Padding. Each object uses a multiple of 8 bytes.  

![image](https://github.com/CoderOrigin/Algorithm-Keep-Learning/blob/master/CourseraPricetonAlgorithm/Images/JavaMemory.png)

#### 作业 Percolation
见week1.
### 1.2 Week2
### 1.3 Week3
### 1.4 Week4
### 1.5 Week5
## 2.Part2
### 2.1 Week1
### 2.2 Week2
### 2.3 Week3
### 2.4 Week4
### 2.5 Week5
