package August20th.bilibili;

import java.io.InputStream;
import java.io.StringBufferInputStream;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

/**
 * @author Gao Yuan
 * @date 2019-08-20 - 19:53
 * 数量5
 * 称重10
 * 重量 2 2 6 5 4
 * 价值 6 3 5 4 6
 * 输出15
 */
public class First {

    public static int dp(InputStream input) {
        Scanner in = new Scanner(input);
        int n = in.nextInt(); // Integer.parseInt(in.nextLine());
        int m = in.nextInt(); // Integer.parseInt(in.nextLine());
        int[] w = new int[n+1];
        int[] v = new int[n+1];
        for (int i = 0; i < n; i++) {
            w[i+1] = in.nextInt();
        }
        for (int i = 0; i < n; i++) {
            v[i+1] = in.nextInt();
        }
        //dp数组
        int[][] dp = new int[n + 1][m + 1];
        for (int i = 0; i < n + 1; i++) {
            for(int j = 0; j < m + 1; j++){
                if(i==0){
                    dp[i][j] = 0;
                }else if(j == 0){
                    dp[i][j] = 0;
                }else {
                    if (j < w[i]) {
                        dp[i][j] = dp[i-1][j];
                    }else{
                        dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-w[i]]+v[i]);
                    }
                }
            }
        }
        return dp[n][m];
    }

    public static int greed(InputStream input) {
        // 读取信息
        Scanner in = new Scanner(input);
        int n = in.nextInt();
        int m = in.nextInt();
        Node[] valPriRatio = new Node[n];
        for (int i = 0; i < n; i++) {
            valPriRatio[i] = new Node();
            valPriRatio[i].w = in.nextInt();
        }
        for (int i = 0; i < n; i++) {
            valPriRatio[i].v = in.nextInt();
        }
        // 处理问题 贪婪算法 只拿性价比最高的

        Arrays.sort(valPriRatio); // 从小到大排列，因此需要从后往前遍历
        int sum = 0;
        for (int i = n - 1; i >= 0; i--) {
            Node node = valPriRatio[i];
            if (node.w <= m) {
                sum += node.v;
                m -= node.w;
            }
        }
        return sum;
    }

    /*
    15
    11 10 5
    22 19 9
    这种情况 贪婪算法就失败了
     */
    public static void main(String[] args) {
        int count = 0;
        int N = 10000;
        for (int i = 0; i < N; i++) {
            int n = (int)(Math.random() * 100) + 1;
            int m = (int)(Math.random() * 200) + 1;
            StringBuffer sb = new StringBuffer();
            sb.append(n).append(" ").append(m).append(" ");
            for (int j = 0; j < n; j++) {
                sb.append((int)(Math.random() * 10) + 1).append(" ");
                sb.append((int)(Math.random() * 10) + 1).append(" ");
            }
            String str = sb.toString();
            InputStream input = new StringBufferInputStream(str);
            InputStream input2 = new StringBufferInputStream(str);
            int dp = dp(input);
            int gre = greed(input2);
            if (dp == gre) {
                System.out.printf("第%d次成功：%d", i, dp);
                System.out.println();
            } else {
                System.out.printf("第%d次不成功：%d != %d", i, dp, gre);
                System.out.println();
                count++;
            }
        }
        System.out.println("失败率：" + (1.0 * count / N));

    }


}

class Node implements Comparable<Node>{
    int w;
    int v;
    Node(){}
    @Override
    public int compareTo(Node that) {
        double vpThis = 1.0 * this.v / this.w;
        double vpThat = 1.0 * that.v / that.w;
        if (vpThis > vpThat) {
            return 1;
        } else if(vpThis < vpThat) {
            return  -1;
        } else {
            return this.w - that.w; // 性价比一样的时候 重量大的优先考虑
        }
    }
}
