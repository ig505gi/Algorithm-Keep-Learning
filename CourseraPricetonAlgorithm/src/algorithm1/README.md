# Part1

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
![image](https://github.com/ig505gi/Algorithm-Keep-Learning/blob/master/CourseraPricetonAlgorithm/Images/unionFind.png)
图为Union-Find的各种实现算法的时间复杂度

#### 算法分析
Typical orders of growth  

![image](https://github.com/ig505gi/Algorithm-Keep-Learning/blob/master/CourseraPricetonAlgorithm/Images/typicalOrdersOfGrowth.png)

![image](https://github.com/ig505gi/Algorithm-Keep-Learning/blob/master/CourseraPricetonAlgorithm/Images/typicalOrdersOfGrowth2.png)

时间复杂度表示方法：
> Big Theta: Θ(N2)是指复杂度等于N2  
Big Oh: O(N2)是指复杂度等于N2或者小于N2  
Big Omega: Ω(N2)是指复杂度等于N2或者大于N2 

#### Java中对象内存用法

>Object overhead. 16 bytes.  
Reference. 8 bytes.  
Padding. Each object uses a multiple of 8 bytes.  

![image](https://github.com/ig505gi/Algorithm-Keep-Learning/blob/master/CourseraPricetonAlgorithm/Images/JavaMemory.png)

#### 作业 Percolation

### 1.2 Week2 Stacks and Queue & Elementary Sorts

#### 简介
>Stack. Examine the item most recently added.  LIFO = "last in first out"  
 Queue. Examine the item least recently added.  FIFO = "first in first out"  

#### Java中的generic
不能用 `s = new Item[capacity];` 因为 generic array creation not allowed in Java  
应该这样： `s = (Item[]) new Object[capacity];`

#### Iterator
![image](https://github.com/ig505gi/Algorithm-Keep-Learning/blob/master/CourseraPricetonAlgorithm/Images/Iterator.png)

#### Shell Sort
>Idea. Move entries more than one position at a time by h-sorting the array.  
which increment sequence to use?  
Powers of two minus one. 1, 3, 7, 15, 31, 63, ... Maybe.  
3x + 1. 1, 4, 13, 40, 121, 364, ... OK. Easy to compute.  
Sedgewick. 1, 5, 19, 41, 109, 209, 505, 929, 2161,... Good. Tough to beat in empirical studies.  
（merging of (9 ⨉ 4i) – (9 ⨉ 2i) + 1 and 4i – (3 ⨉ 2i) + 1）(i为指数)

![image](https://github.com/ig505gi/Algorithm-Keep-Learning/blob/master/CourseraPricetonAlgorithm/Images/Shellsort1.png)
![image](https://github.com/ig505gi/Algorithm-Keep-Learning/blob/master/CourseraPricetonAlgorithm/Images/Shellsort2.png)

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
![image](https://github.com/ig505gi/Algorithm-Keep-Learning/blob/master/CourseraPricetonAlgorithm/Images/threeWayQuickSort.png)

#### Java System Sorts
>Arrays.sort().  
・Has different method for each primitive type.  
・Has a method for data types that implement Comparable.  
・Has a method that uses a Comparator.  
・Uses tuned quicksort for primitive types; tuned mergesort for objects.  

#### Sorting Summary
![image](https://github.com/ig505gi/Algorithm-Keep-Learning/blob/master/CourseraPricetonAlgorithm/Images/SortingSummary.png)

加上Heapsort后的表：  
![image](https://github.com/ig505gi/Algorithm-Keep-Learning/blob/master/CourseraPricetonAlgorithm/Images/SortingSummary2.png)

#### 作业 Collinear

### 1.4 Week4 Priority Queue & Elementary Symbol Tables

#### Binary Heap
数组实现如图：![image](https://github.com/ig505gi/Algorithm-Keep-Learning/blob/master/CourseraPricetonAlgorithm/Images/Heap.png)

主要操作依靠swim和sink来完成

#### Immutability: implementing in Java
>Immutable data type. Can't change the data type value once created.  

![image](https://github.com/ig505gi/Algorithm-Keep-Learning/blob/master/CourseraPricetonAlgorithm/Images/Immutability.png)

#### HeapSort
>First pass.  
>・Build heap using bottom-up method.  保证父亲一定比孩子大
>Second pass.   
>・Remove the maximum,one at a time.   将root(最大)移到尾部，并删除，然后将root给sink下去  
>・Leave in array, instead of nulling out.  循环遍历剩下的，同样策略，直到没有元素

#### Symbol Table
>Key-value pair abstraction.  
>・**Insert** a value with specified key.  
>・Given a key, **search** for the corresponding value.  

#### 作业 8Puzzle

### 1.5 Week5

#### 2-3 tree
>Allow 1 or 2 keys per node.  
・2-node: one key, two children.  
・3-node: two keys, three children.  

在讲红白树之前讲了2-3 tree，对理解红白树起到了至关重要的作用  

>Search.
・Compare search key against keys in node.  
・Find interval containing search key.  
・Follow associated link (recursively).

>Insertion into a 3-node at bottom.  
・Add new key to 3-node to create temporary 4-node.  
・Move middle key in 4-node into parent.  
・Repeat up the tree, as necessary.  
・If you reach the root and it's a 4-node, split it into three 2-nodes.  

数学证明，2-3tree有着很好的balance

#### Left-leaning red-black BSTs 

(Guibas-Sedgewick 1979 and Sedgewick 2007)  
用两个2-node来取代2-3树中的 3-node：![image](https://github.com/ig505gi/Algorithm-Keep-Learning/blob/master/CourseraPricetonAlgorithm/Images/RedBlackTree.png)  
性质：
>A BST such that:  
・No node has two red links connected to it.  
・Every path from root to null link has the same number of black links.  
・Red links lean left. 

实现的主要三个子操作：
1. Rotation left: 将左倾的红线变为右倾  
2. Rotation right: 将右倾的红线变为左倾
3. Color flip： 将有两条红线节点的父亲节点变为红线

情况很多，但是最后的代码很简单，因为很多情况可以相互转化！如图：  
![image](https://github.com/ig505gi/Algorithm-Keep-Learning/blob/master/CourseraPricetonAlgorithm/Images/RedBlackTree2.png)  

根据情况图可以判断，先进行坐旋转，再右旋转，再改变颜色。

#### SearchTree Summary
![image](https://github.com/ig505gi/Algorithm-Keep-Learning/blob/master/CourseraPricetonAlgorithm/Images/SearchTree.png)  

#### B-tree
![image](https://github.com/ig505gi/Algorithm-Keep-Learning/blob/master/CourseraPricetonAlgorithm/Images/B-Tree.png)  
有着很广泛的应用：
>Red-black trees are widely used as system symbol tables.  
・Java: java.util.TreeMap, java.util.TreeSet.  
・C++ STL: map, multimap, multiset.  
・Linux kernel: completely fair scheduler, linux/rbtree.h.  
・Emacs: conservative stack scanning.  

>B-tree variants. B+ tree, B*tree, B# tree, ...  

>B-trees (and variants) are widely used for file systems and databases.  
・Windows: NTFS.  
・Mac: HFS, HFS+.  
・Linux: ReiserFS, XFS, Ext3FS, JFS.  
・Databases: ORACLE, DB2, INGRES, SQL, PostgreSQL.  

#### kd-tree
广泛应用于图形学领域，将图形学的搜索提高了很高的效率，个人觉得也是分治思想的应用。

### 1.6 Week6 Hash Table

#### Java’s hash code conventions
![image](https://github.com/ig505gi/Algorithm-Keep-Learning/blob/master/CourseraPricetonAlgorithm/Images/JavaHash.png)  

用户自定义hash code  
可以采用 31x + y rule

#### Java中的位运算符：  
 \>\>表示右移，如果该数为正，则高位补0，若为负数，则高位补1；  
 \>\>\>表示无符号右移，也叫逻辑右移，即若该数为正，则高位补0，而若该数为负数，则右移后高位同样补0。 

#### hash collision
解决办法： 
1. separate chaining  
>・Easier to implement delete.  
・Performance degrades gracefully.  
・Clustering less sensitive to poorly-designed hash function.  
2. linear probing  
>・Less wasted space.  
・Better cache performance. 

#### Java system: Red-black BSTs & Hash tables
>・Red-black BSTs: java.util.TreeMap, java.util.TreeSet.  
・Hash tables: java.util.HashMap, java.util.IdentityHashMap. 
