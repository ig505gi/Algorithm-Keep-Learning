package August29th.shunfeng;

import java.util.*;

/**
 * @author Gao Yuan
 * @date 2019-08-29 - 19:00
 */
public class First {

    public static void main(String[] args) {
        // 输入
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        int k = in.nextInt();
        HashMap<Integer, List<Integer>> lanToPer = new HashMap<>();
        HashMap<Integer, List<Integer>> perToLan = new HashMap<>();
        for (int i = 0; i < k; i++) {
            int per = in.nextInt();
            int lan = in.nextInt();
            if (!perToLan.containsKey(per)) perToLan.put(per, new ArrayList<>());
            perToLan.get(per).add(lan);
            if (!lanToPer.containsKey(lan)) lanToPer.put(lan, new ArrayList<>());
            lanToPer.get(lan).add(per);
        }
        int res = 0;
        for (int i = 1; i < n + 1; i++) {
            if (!perToLan.containsKey(i)) { // 这个人一个语言都不会，就是一个组
                res++;
            }
        }
        boolean[] visLan = new boolean[m + 1];
        for (int i = 1; i < m + 1; i++) {// 对每一种语言进行遍历，找到所有的可以交流的人，放在一个group中
            int lan = i;
            if (!lanToPer.containsKey(lan)) continue;
            if (visLan[lan]) continue;
            HashSet<Integer> groupLan = new HashSet<>(); // 目前这个group会的语言
            Stack<Integer> stack = new Stack<>(); // 用BFS遍历
            stack.push(lan);
            HashSet<Integer> curGroup = new HashSet<>();
            while (!stack.isEmpty()) {
                lan = stack.pop();
                groupLan.add(lan);
                visLan[lan] = true;
                for (int per : lanToPer.get(lan)) {
                    if (!curGroup.contains(per)) {
                        curGroup.add(per); // 现在所有的这个语言的人
                        for (int lan2 : perToLan.get(per)) {
                            if (!groupLan.contains(lan2)) { // 还没遍历这个语言
                                groupLan.add(lan2);
                                stack.push(lan2);
                            }
                        }
                    }
                }
            }
            res++; // 这样遍历出了一个group
        }
        System.out.println(res - 1); // 一共有n个组 只需要 n - 1个机器
    }
}
