package August17th.zoom;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

/**
 * @author Gao Yuan
 * @date 2019-08-17 - 13:56
 */
public class Second {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        String nn = in.nextLine();
        String line = in.nextLine();
        String[] ints = line.split(",");

        HashMap<Integer, List<Integer>> map = new HashMap<>();
        for (String str : ints) {
            int num = Integer.parseInt(str);
            int intTemp = num ^ N;
            int count = 0;
            int mask = 1;
            while (mask <= intTemp) {
                   if ((intTemp & mask) == mask) count++;
                mask = mask << 1;
            }
           if (!map.containsKey(count)) {
               map.put(count, new ArrayList<>());
           }
           map.get(count).add(num);
        }
//        for (Integer count : map.keySet()) {
//            List temp = map.get(count);
//
//        }
        System.out.println(map.toString());
    }
}
