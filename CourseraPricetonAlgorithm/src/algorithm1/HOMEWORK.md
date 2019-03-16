# 作业

## WEEK1 Percolation
最后得分应该是有些计算上精度的误差  
![image](https://github.com/CoderOrigin/Algorithm-Keep-Learning/blob/master/CourseraPricetonAlgorithm/Images/Part1Week1.png)

刚开始84分，没有处理backwash问题：
>关于backwash问题：有了一个虚拟底层位置，如果网格已经渗透了。
这时判断其中一个位置（与底部相连，并且底部是打开的，与虚拟底层位置相连）是否满（即与虚拟顶层位置是相连的），
那么不管这个位置是不是真的满，结果总会是满的。因为网格已经渗透了，那么虚拟顶层位置与虚拟底层位置总是相连的，
而这个位置与虚拟顶层位置是相连的，这时候再判断它是不是满（与虚拟顶层位置相连），结果当然总是为真。  
--------------------- 
作者：5262yz 
来源：CSDN 
原文：https://blog.csdn.net/zhangyuzuishuai/article/details/58664228 
版权声明：本文为博主原创文章，转载请附上博文链接！ 

改进：
1. 用了两个uf来维持状态  
2. 每次开启都要判断是否连通顶部和底部

## WEEK2 Dqueue
![image](https://github.com/CoderOrigin/Algorithm-Keep-Learning/blob/master/CourseraPricetonAlgorithm/Images/Part1Week2.png)

难度不大，学习到了怎么写迭代器
## WEEK3 Collinear
一遍提交93，不太有印象是自己做的。。好像抄了下代码。。  
大概知道怎么实现，不是很难，但是繁琐，就没写。
## WEEK4 8-Puzzle
一遍一遍修改终于完成，学到了好多，做完第五周作业回来做的这次作业。  
![image](https://github.com/CoderOrigin/Algorithm-Keep-Learning/blob/master/CourseraPricetonAlgorithm/Images/Part1Week4.png)

经验和教训：开始用了递归，导致栈溢出！开始就没好好思考，直接循环即可，不需要递归；
学习：学习了A*搜索算法，立马应用在了笔试题上然后出错。。

## WEEK5 kd-Tree
这周作业实现结果很好，可视化结果很有趣  
![image](https://github.com/CoderOrigin/Algorithm-Keep-Learning/blob/master/CourseraPricetonAlgorithm/Images/Part1Week5.png)

自己写的最后两次修改代码只能说顾此失彼，没有采用更好的策略  
代码中，最好策略是，先比较left和right，决定出first，next，按次序遍历，需要将wholeRect作为kdtree的成员，重构代码

学习：
1. 把树的递归写的很熟练了，也是如此，再写第四周作业直接就用递归。。  
2. 递归返回值，一般需要回溯的时候，可以用父亲/根作为返回值，如果不需要记录，一条路走到黑，就用void，在尽头return。

## 总结
![image](https://github.com/CoderOrigin/Algorithm-Keep-Learning/blob/master/CourseraPricetonAlgorithm/Images/Part1.png)

2月19-2月16的26天大约13天有学习算法课，总小时数68小时，终于完成，包括花了一天时间记录和总结，希望对以后的学习有帮助。  
看着这个结果还是很有成就感的，因为时间关系也不可能一些细节一直揪着不放，move on! 接下来学Part2
