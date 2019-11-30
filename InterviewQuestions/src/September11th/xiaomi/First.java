package September11th.xiaomi;

import java.util.*;

/**
 * @author Gao Yuan
 * @date 2019-09-11 - 19:33
 */
public class First {
    /*请完成下面这个函数，实现题目要求的功能
当然，你也可以不按照下面这个模板来作答，完全按照自己的想法来 ^-^
******************************开始写代码******************************/
    /*
    第二道先算出最长不严格上升子序列长度ans1，和最长不严格下降子序列长度ans2，然后看哪个大，答案就是 n-大的长度
     */
    static int solution(int[] arr) {

        // 排序，并查集，一个集合中有n个那么需要移动n-1次
        int[] sortedArr = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            sortedArr[i] = arr[i];
        }
        Arrays.sort(sortedArr);
        Map<Integer, Set<Integer>> int2NewInts = new HashMap<>();
        Map<Integer, Set<Integer>> int2OldInts = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            if (!int2NewInts.containsKey(arr[i])) {
                int2NewInts.put(arr[i], new HashSet<>());
            }
            int2NewInts.get(arr[i]).add(sortedArr[i]);
            if (!int2OldInts.containsKey(sortedArr[i])) {
                int2OldInts.put(arr[i], new HashSet<>());
            }
            int2OldInts.get(sortedArr[i]).add(arr[i]);
        }
        int res = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != sortedArr[i]) {
                if (int2OldInts.get(arr[i]).contains(sortedArr[i])) { //
                    res += 1;
                    int2OldInts.get(sortedArr).remove(arr[i]);
                } else {
                    res += 2; }
            }
        }
        return res;

    }
    /******************************结束写代码******************************/


    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        int res;

        int _arr_size = 0;
        _arr_size = Integer.parseInt(in.nextLine().trim());
        int[] _arr = new int[_arr_size];
        int _arr_item;
        for(int _arr_i = 0; _arr_i < _arr_size; _arr_i++) {
            _arr_item = Integer.parseInt(in.nextLine().trim());
            _arr[_arr_i] = _arr_item;
        }

        res = solution(_arr);
        System.out.println(String.valueOf(res));

    }
}
