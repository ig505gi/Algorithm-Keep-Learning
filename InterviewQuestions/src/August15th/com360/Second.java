package August15th.com360;

import java.util.*;

/**
 * @author Gao Yuan
 * @date 2019-08-15 - 20:10
 */
public class Second {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        int[][] nums= new int[2][n];
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < n; j++) {
                nums[i][j] = in.nextInt(); // 第一行n位是第一个数，第二行是第二个数
            }
        }
        // int[][] sum = new int[n][n];
        HashMap<Integer, List<int[]>> map = new HashMap<>();
        boolean[] num1 = new boolean[n]; // 储存是否使用信息
        boolean[] num2 = new boolean[n]; // 全部为false没有用
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int sum = (nums[0][i] + nums[1][j]) % m;
                if (!map.containsKey(sum)) {
                    map.put(sum, new ArrayList<>());
                }
                int[] temparr = {i, j};
                map.get(sum).add(temparr);
            }
        }
        int[] sumArr = new int[map.keySet().size()];
        int index = 0;
        for (Integer i : map.keySet()) {
            sumArr[index++] = i;
        }
        Arrays.sort(sumArr);
        int count = 0;
        OUT:
        for (int i = sumArr.length - 1; i >= 0 ; i--) {
            int sum = sumArr[i];
            List<int[]> list = map.get(sum); // 从最大开始
            for (int[] ints : list) {
                if (!num1[ints[0]] && !num2[ints[1]]) { // 两个同时为flase 说明没有用
                    System.out.print(sum);
                    num1[ints[0]] = true;
                    num2[ints[1]] = true;
                    if (++count < n) System.out.println(" ");
                    else {
                        break OUT;
                    }
                }
            }
        }
    }

}
