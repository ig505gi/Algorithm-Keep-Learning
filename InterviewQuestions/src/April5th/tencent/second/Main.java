package April5th.tencent.second;


import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String a;
		int n = Integer.parseInt(in.nextLine());
        a = in.nextLine();
        // Stack<Integer> stack = new Stack<Integer>();
        int res = 0;
        for (int i = 0; i < n; i++) {
        	if (a.charAt(i) == '0') {
        		res++;
        	}
        	if (a.charAt(i) == '1') {
        		res--;
        	}
        }
        System.out.print(Math.abs(res));
    }
}
