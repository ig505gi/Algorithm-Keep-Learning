package August17th.zoom;

import java.util.Scanner;

/**
 * @author Gao Yuan
 * @date 2019-08-17 - 15:05
 */
public class Third {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String str = in.nextLine();
        int sum = 0;
        boolean isAdd = true;
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c == '-') {
                int count = 1;
                while (i+1< str.length() && str.charAt(i+1) == '-') {
                    count++;i++;
                }
                isAdd = count % 2 == 0 ? true : false;
            } else if (c >= '0' && c <= '9') {
                StringBuilder sb = new StringBuilder();
                sb.append(c);
                while (i+1< str.length() && str.charAt(i+1) >= '0' && str.charAt(i+1) <= '9') {
                    sb.append(str.charAt(i+1));
                    i++;
                }
                if (isAdd) sum += Integer.parseInt(sb.toString());
                else sum -= Integer.parseInt(sb.toString());
            }
        }
        System.out.println(sum);
    }

}
