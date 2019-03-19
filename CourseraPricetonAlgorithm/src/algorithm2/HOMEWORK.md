## 作业

### WEEK1 WordNet

#### 1. 理解
>a hyponym (more specific synset) to a hypernym (more general synset)  

下义词和上义词！第一遍读题没有理解。。
hypernyms.txt中： 第一列是下义词，后面几列是上义词，因为一个具体的词，有可能有多种更广泛的意思  
因此在DAG中，hypernyms.txt的一行，是多个指向，第一列分别指向后几列  
WordNet类建立key和value的表，和一个DAG ，在计算distance和sap的时候可以调用SAP类，因此也可以加一个SAP成员变量

#### 1. 思路
WordNet类通过读取两个txt，
1. 建立一个DAG，DAG保存的是数字，
2. 再建立一个Map，key是数字/行号, 值是一个set，有N个String，也就是noun，因为一行有可能有好几个名词
2.1 如果set保存 Synsets， 查找的时候要遍历Map，再看set是否包含，这样不可能是对数时间复杂度
2.2 用比较首字母的方式搜索，也不太合适，比如 29,AB type_AB....如果给的type_AB,直接去比。。就找不到
2.3 因为要搜索，key是noun，value是一个set，有多个int，读取的时候，如果有key，则加一个int，没key，则创建 查找logn，遍历n，时间复杂度nlogn
3. 因此，构造函数要做如下事情：
3.1 读取文件名字， 遍历WordNet.txt, 创建如上说的set
3.2 遍历hypernyms.txt 创建DAG
