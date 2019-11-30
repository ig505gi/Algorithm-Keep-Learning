package September15th.zhaoshangyinhang;

import java.util.Scanner;

/**
 * @author Gao Yuan
 * @date 2019-09-15 - 21:07
 */
public class First {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        int[] arr = new int[s.length()];
        int curFirstR = 0;
        int curLastR;
        int curFirstL;
        int curLastL;
        int point = 0;
        while (point < s.length() - 1) {
            if (s.charAt(point) == 'R' && s.charAt(point + 1) == 'L') {
                curLastR = point;
                curFirstL = point + 1;
                while (point < s.length() - 1 && s.charAt(++point) == 'L') {}
                curLastL = point - 1;
                if (point == s.length() - 1) curLastL = point;
                //
                int sum = curLastL - curFirstR + 1;
                if (sum % 2 == 1) {
                    if ((curLastR - curFirstR) % 2 == 0) {
                        arr[curLastR] = sum / 2 + 1;
                        arr[curFirstL] = arr[curLastR] - 1;
                    } else {
                        arr[curFirstL] = sum / 2 + 1;
                        arr[curLastR] = arr[curFirstL] - 1;
                    }
                } else {
                    arr[curLastR] = sum / 2;
                    arr[curFirstL] = arr[curLastR];
                }
                //
                curFirstR = point;
            } else {
                point++;
            }
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]);
            if (i != arr.length - 1) System.out.print(" ");
        }
    }
}
