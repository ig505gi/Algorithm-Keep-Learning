package September9th.qianxin;

import java.util.Scanner;

/**
 * @author Gao Yuan
 * @date 2019-09-09 - 19:36
 */
public class Second {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = 1;
        while (n-->0) { m *= 2; }
        int[] tree = new int[m];
        for (int i = 1; i < m; i++) {
            tree[i] = sc.nextInt();   // i的孩子就是2i和2i+1
        }
        int c1 = sc.nextInt();
        int c2 = sc.nextInt();
        if (c1 > c2) { // 保证 c1 是小的
            int temp = c1; c1 = c2; c2 = temp;
        }
        int p = 1;
        int parent = -1;
        while (p < m) {
            if (c1 <= tree[p] && c2 >= tree[p]) {
                if (find(c1, p, tree) && find(c2, p, tree)) {
                    parent = tree[p];
                }
                break;
            } else if (c1 > tree[p]){ // 应该往右孩子找
                p = p * 2 + 1;
            } else { // 左孩子
                p *= 2;
            }
        }
        System.out.println(parent);
    }

    private static boolean find(int tar, int cur, int[] tree) {
        while (cur < tree.length) {
            if (tar == tree[cur]) return true;
            else if (tar > tree[cur]) {
                cur = cur * 2 + 1;
            } else { cur *= 2; }
        }
        return false;
    }
}
