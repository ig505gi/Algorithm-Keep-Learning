# Coursera上普林斯顿大学的算法课

本项目主要由一些数据结构和简单算法的**JAVA代码实现**以及**每周作业**组成

### 课程包括Part1和Part2两部分


## 1.Algorithm Part1

### 1.1 Week1 Union Find & Analysis of Algrorithms

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

### 1.2 Week2 Stacks and Queue & Elementary Sorts

#### 简介
>Stack. Examine the item most recently added.  LIFO = "last in first out"  
 Queue. Examine the item least recently added.  FIFO = "first in first out"  

#### Java中的generic
不能用 `s = new Item[capacity];` 因为 generic array creation not allowed in Java  
应该这样： `s = (Item[]) new Object[capacity];`

#### Iterator
![image](https://github.com/CoderOrigin/Algorithm-Keep-Learning/blob/master/CourseraPricetonAlgorithm/Images/Iterator.png)

#### Shell Sort
>Idea. Move entries more than one position at a time by h-sorting the array.  
which increment sequence to use?  
Powers of two minus one. 1, 3, 7, 15, 31, 63, ... Maybe.  
3x + 1. 1, 4, 13, 40, 121, 364, ... OK. Easy to compute.  
Sedgewick. 1, 5, 19, 41, 109, 209, 505, 929, 2161,... Good. Tough to beat in empirical studies.  
（merging of (9 ⨉ 4i) – (9 ⨉ 2i) + 1 and 4i – (3 ⨉ 2i) + 1）(i为指数)

![image](https://github.com/CoderOrigin/Algorithm-Keep-Learning/blob/master/CourseraPricetonAlgorithm/Images/Shellsort1.png)
![image](https://github.com/CoderOrigin/Algorithm-Keep-Learning/blob/master/CourseraPricetonAlgorithm/Images/Shellsort2.png)

#### Shuffling
策略：  
>In iteration i, pick integer r between 0 and i uniformly at random.  
Swap a\[i] and a\[r].

#### 作业 Dequeue

### 1.3 Week3 Merge Sort & Quick Sort

#### Merge Sort
>Basic plan.  
>1. Divide array into two halves.  
>2. Recursively sort each half.  
>3. Merge two halves.

改进1： 当一些子序列很小的时候，可以直接采用插入排序 (实际应用，当长度大约为7的时候，效果不错)

#### 循环实现MergeSort

>Basic plan.
>1. Pass through array, merging subarrays of size 1.  
>2. Repeat for subarrays of size 2, 4, 8, 16, ....

大约比递归实现慢10%

#### stable & in-place
stable: 不改变原序列前后顺序的排列是稳定的（对于那些相同key的变量来说）  
in-place: A sorting algorithm is in-place if it uses ≤ clogN extra memory.

#### Quick Sort
>Basic plan.
>1. Shuffle the array.  
>2. Partition so that, for some j
>   – entry a\[j] is in place
>   – no larger entry to the left of j
>   – no smaller entry to the right of j
>3. Sort each piece recursively.

一开始的洗牌很关键！能确保减小遇到最坏情况的概率  
改进1： 将mid，lo，hi中中等的取为flag

#### 3-way QuickSort
当序列中有很多重复的值时，才会这种方法效率很高
![image](https://github.com/CoderOrigin/Algorithm-Keep-Learning/blob/master/CourseraPricetonAlgorithm/Images/3wayQuickSort.png)

#### Java System Sorts
>Arrays.sort().  
・Has different method for each primitive type.  
・Has a method for data types that implement Comparable.  
・Has a method that uses a Comparator.  
・Uses tuned quicksort for primitive types; tuned mergesort for objects.  

### 1.4 Week4
### 1.5 Week5
## 2.Part2
### 2.1 Week1
### 2.2 Week2
### 2.3 Week3
### 2.4 Week4
### 2.5 Week5
