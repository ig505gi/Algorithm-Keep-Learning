package September14th.xiaohongshu;

import java.util.*;

/**
 * @author Gao Yuan
 * @date 2019-09-14 - 14:57
 */
public class First {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[][] relations = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                relations[i][j] = sc.nextInt();
            }
        }

        int[] parents = new int[n];
        for (int i = 0; i < n; i++) {
            parents[i] = i;
        }
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (relations[i][j] == 1) {
                    int pi = i;
                    int pj = j;
                    while (parents[pi] != -1 && parents[pi] != pi)parents[pi] = parents[parents[pi]];
                    while (parents[pj] != -1 && parents[pj] != pj)parents[pj] = parents[parents[pj]];
                    if (pi != pj) {
                        for (int k = 0; k < n; k++) {
                            if (parents[k] == pj) {
                                parents[k] = pi;
                            }
                        }
                    }
                }
            }
        }
        Set<Integer> groups = new HashSet<>();
        for (int i = 0; i < n; i++) {
            groups.add(parents[i]);
        }

        System.out.println(groups.size());
    }

    public static void main2(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[][] relations = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                relations[i][j] = sc.nextInt();
            }
        }
        Map<Integer, Set<Integer>> groups = new HashMap<>();
        for (int i = 0; i < n; i++) {
            flag:
            for (int j = i; j < n; j++) {
                if (relations[i][j] == 1) {
                    if (groups.containsKey(i)) {
                        groups.get(i).add(j);
                    } else {
                        for (int p : groups.keySet()) {
                            if (groups.get(p).contains(i)) {
                                groups.get(p).add(j);
                                continue flag;
                            }
                        }
                        // 新建一个圈子
                        groups.put(i, new HashSet<Integer>());
                        groups.get(i).add(j);
                    }
                }
            }
        }
        System.out.println(groups.size());
    }
}
