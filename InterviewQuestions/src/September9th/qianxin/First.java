package September9th.qianxin;

import java.util.*;

/**
 * @author Gao Yuan
 * @date 2019-09-09 - 19:56
 */
public class First {
    /*
    3 1 5 21 10
    0 3 3 1 5
    5
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String pidLine = sc.nextLine();
        String[] pidArr = pidLine.split(" ");

        String ppidLine = sc.nextLine();
        String[] ppidArr = ppidLine.split(" ");
        int[] pids = new int[pidArr.length];
        int[] ppids = new int[ppidArr.length];
        for (int i = 0; i < pidArr.length; i++) {
            pids[i] = Integer.parseInt(pidArr[i]);
            ppids[i] = Integer.parseInt(ppidArr[i]);
        }
        int tar = Integer.parseInt(sc.nextLine());
        HashMap<Integer, List<Integer>> p2cList = new HashMap<>();
        boolean flag = false;
        for (int pid : pids) {
            if (pid == tar) {
                flag = true; break;
            }
        }
        if (!flag) {
            System.out.println(0);
            return;
        }
        for (int i = 0; i < ppids.length; i++){
            int ppid = ppids[i];
            if (ppid == 0) continue;
            if (!p2cList.containsKey(ppid)) {
                p2cList.put(ppid, new ArrayList<>());
            }
            p2cList.get(ppid).add(pids[i]);
        }

        Queue<Integer> q = new LinkedList<>();
        q.offer(tar);
        int res = 0;
        //Set<Integer> res = new HashSet<>();
        while (!q.isEmpty()) {
            int cur = q.poll();
            res++;
            if (p2cList.containsKey(cur)) {
                List<Integer> children = p2cList.get(cur);
                for (int child : children) {
                    q.offer(child);
                }
            }
        }
        System.out.println(res);
    }
}
