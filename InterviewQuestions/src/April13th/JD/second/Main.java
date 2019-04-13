package April13th.JD.second;
/**
 * 寻找子串
时间限制：C/C++语言 1000MS；其他语言 3000MS
内存限制：C/C++语言 65536KB；其他语言 589824KB
题目描述：
给出m个字符串S1，S2，...，Sm和一个单独的字符串T。请在T中选出尽可能多的子串同时满足：

1）这些子串在T中互不相交。

2）这些子串都是S1，S2，...，Sm中的某个串。

问最多能选出多少个子串。

输入
第一行一个数m（1≤m≤10），接下来m行，每行一个串。最后一行输入一个串T。输入中所有单个串的长度不超过100000，串中只会出现小写字母。

输出
输出一个数，最多能选出多少串。


样例输入
3
aa
b
ac
bbaac
样例输出
3

提示
样例解释：把T中字符从1开始编号，用[L,R]表示从第L个字符到第R个字符所构成的子串。一种选择子串的方法是[1,1],[2,2],[3,4]一共3个串，另一种选法是[1,1],[2,2],[4,5]一共也是3个串。注意不能同时选择子串[3,4],[4,5]因为它们相交了。
 
确实思路我也觉得是这样。。看来还是刷题刷的少，快速写出来的能力太差
 作者：.Ｓ
链接：https://www.nowcoder.com/discuss/177857?type=0&order=0&pos=23&page=1
来源：牛客网
74%ac
 */


import java.util.Arrays;
import java.util.Scanner;
 
public class Main {  public static void main(String[] args) {  
		Scanner scanner = new Scanner(System.in);  
		int n = scanner.nextInt();  
		scanner.nextLine();  
		String[] strings = new String[n];
        for(int i = 0 ; i < n ; i ++) {  
        	String s = scanner.nextLine();  
        	strings[i] = s;
        }
        String tar = scanner.nextLine();
        StringBuilder sb = new StringBuilder(tar);
        Arrays.sort(strings, (s1,s2)->{  return s1.length()-s2.length();});
        int time = 0 ;
        for(String s : strings) {  
        	int start = sb.indexOf(s);  
        	for(;start!=-1;) {  
        		sb.delete(start, start+s.length());  
        		sb.insert(start, ',');  
        		time++;  
        		start = sb.indexOf(s);  
        	}
        }
        System.out.println(time);  
    }
}
