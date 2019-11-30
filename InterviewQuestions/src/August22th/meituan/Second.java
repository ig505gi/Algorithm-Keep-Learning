package August22th.meituan;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author Gao Yuan
 * @date 2019-08-22 - 14:55
 */
public class Second {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String line = in.nextLine();
        String[] strs = line.split(" ");
        List<String> dic = new ArrayList<>();
        String pre = strs[0];
        int len = strs.length;
        for (int i = 1; i < len; i++) {
            String str = strs[i];
            if (str.compareTo(pre) != 0) {
                int n = Math.min(str.length(), pre.length());
                for (int j = 0; j < n; j++) {
                    if (str.charAt(j) != pre.charAt(j)) {
                        dic.add(""+ pre.charAt(j) + str.charAt(j));
                        break;
                    }
                }
            }
            pre = str;
        }

        int dicLen = dic.size();
        if (dicLen > 1) {
            boolean isGetNewRule = false;
            for (int i = 0; i < dicLen - 1; i++) {
                isGetNewRule = false;
                for (int j = i + 1; j < dicLen; j++) {
                    String str1 = dic.get(i);
                    String str2 = dic.get(j);
                    if (str1.charAt(0) == str2.charAt(str2.length() - 1)) {
                        if (str2.charAt(0) == str1.charAt(str1.length() - 1)) {
                            System.out.println("invalid");
                            return;
                        }
                        dic.set(j, str2 + str1.substring(1, str1.length()));
                        isGetNewRule = true;
                    } else if (str2.charAt(0) == str1.charAt(str1.length() - 1)) {
                        dic.set(j, str1 + str2.substring(1, str1.length()));
                        isGetNewRule = true;
                    }
                }
                if (isGetNewRule) {
                    dic.set(i, null);
                }
            }
        }
        for (int i = 0; i < dic.size(); i++) {
            if (dic.get(i) != null) {
                if (i != dic.size() - 1) {
                    System.out.println("invalid");
                    break;
                } else {
                    System.out.print(dic.get(i));
                }
            }
        }
    }

    public static void main2(String[] args) {
        String[] sortedArray = { "guest", "guestsc", "guard", "guy", "gym", "goat", "goal", "east" };
        Second test = new Second();
        // test.getSortRule(sortedArray);
    }
}

