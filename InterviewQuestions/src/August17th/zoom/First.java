package August17th.zoom;

import java.util.Scanner;

/**
 * @author Gao Yuan
 * @date 2019-08-17 - 13:55
 */
public class First {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String str = in.nextLine();
        int n = Integer.parseInt(in.nextLine());
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < n; i++) {
            sb.append("0");
        }
        String[] strs = str.split(sb.toString());
        for (int i = 0; i < strs.length; i++) {
            System.out.print(strs[i]);
        }
    }
    public static void main2(String[] args) {
        Scanner in = new Scanner(System.in);
        String str = in.nextLine();
        int n = Integer.parseInt(in.nextLine());
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < str.length(); i++) {

        }
    }


}
