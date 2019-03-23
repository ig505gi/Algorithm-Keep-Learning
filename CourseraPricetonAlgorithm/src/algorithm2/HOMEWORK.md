## 作业

### WEEK1 WordNet
经过两个小时的debug和修改，终于拿到第一个满分！。。不过这个作业预计3小时。。  
***一定要提前看checklist，有很大帮助！！***  
![image](https://github.com/CoderOrigin/Algorithm-Keep-Learning/blob/master/CourseraPricetonAlgorithm/Images/Part2Week1.png)
#### 1. 理解
>a hyponym (more specific synset) to a hypernym (more general synset)  

下义词和上义词！第一遍读题没有理解。。
hypernyms.txt中： 第一列是下义词，后面几列是上义词，因为一个具体的词，有可能有多种更广泛的意思  
因此在DAG中，hypernyms.txt的一行，是多个指向，第一列分别指向后几列  
WordNet类建立key和value的表，和一个DAG ，在计算distance和sap的时候可以调用SAP类，因此也可以加一个SAP成员变量  

#### 2. WordNet
WordNet类通过读取两个txt， 
1. 建立一个DAG，DAG保存的是数字， 
2. 再建立一个Map，key是数字/行号, 值是一个set，有N个String，也就是noun，因为一行有可能有好几个名词  
2.1 如果set保存 Synsets， 查找的时候要遍历Map，再看set是否包含，这样不可能是对数时间复杂度  
2.2 用比较首字母的方式搜索，也不太合适，比如 29,AB type_AB....如果给的type_AB,直接去比。。就找不到  
2.3 因为要搜索，key是noun，value是一个set，有多个int，读取的时候，如果有key，则加一个int，没key，则创建 查找logn，遍历n，时间复杂度nlogn  
3. 因此，构造函数要做如下事情：  
3.1 读取文件名字， 遍历WordNet.txt, 创建如上说的set  
3.2 遍历hypernyms.txt 创建DAG  
4. 建立一个String 数组，保存每行信息。。这样由vertices找synset很快  

#### 3. SAP
1. 传入的是不一定是DAG，因此需要考虑有环的情况  
2. 计算length的时候，开始想的bfs先遍历v，再遍历w，这样每次都要遍历完  
3. 想法改进：v和w同时遍历，但每走一步进行判断，是否该点是否已经被对方遍历，如果对方遍历了，此时不一定最短！3 + 3 也有 1 + 4的可能  
3.1 想法不对！  
3.2 直接用自带的可以传入 iteratable<T> 的多个点为参数的 BreadthFirstDirectedPaths.java  

#### 4. immutable
需要在构造的时候复制过去，不应该直接指向


### WEEK2 SeamCarver
![image](https://github.com/CoderOrigin/Algorithm-Keep-Learning/blob/master/CourseraPricetonAlgorithm/Images/Part2Week2.png)  
debug最少的一次！也是因为提前看了checklist，避免了很多问题  

####  1.理解
思路很简单：  
1. 找到图片中能量最低的一条缝  
2. 删除这条缝  

#### 2. 成员变量  
这道题花时间最长的就是在定义成员变量上，可选有： 
1. Picture类 currentPicture  
2. int\[]\[] 二维数组 rbgs  
3. energy\[]\[] 二维数组 又每个点计算得到的能量  

思考1：1、2、3都是成员变量，能量由所有的rgb来计算获得，因为删除了一条缝之后，energy就会变，要更新，而且energy作为成员变量还会导致内存增加，根据checklist，energy不作为成员变量更好  
思考2：更节省空间，只有currentPicture作为成员变量，那么每次删除操作都需要生成Picture对象，时间会大大增加  
最终方案：1、2为成员变量，每次查询、删除、反转操作都对rgbs数组进行，避免Picture对象的操作，只有在最后picture()操作的时候更新currentPicture  

#### 3. findMinEnergy

1. 使用拓扑排序，正常的拓扑排序是需要BFS的，但是对于图片矩阵，比较特殊，每行的energyTo只能从上一行得到， 
2. 因此遍历的时候，更像是BFS，遍历每行计算energyTo，并记录PathTo即可， 
3. 遍历到最后一行的时候，选出energyTo最小的，然后一直找edgeTo, 返回即可  

#### 4. transpose操作
这个是纠结了很长时间的点，在checklist上说：  
>Don’t explicitly transpose the Picture or int[][] until you need to do so. For example, if you perform a sequence of 50 consecutive horizontal seam removals, you should need only two transposes (not 100).   

最终还是没有实现这条，因为会导致energy()取不到正确的值，说明自己构造的数据结构还是有一些小问题。。  
最后实现还是在find和remove前后分别做了transpose操作，时间复杂度还是达到了要求！  

### WEEK3
