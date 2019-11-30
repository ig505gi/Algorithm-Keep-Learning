package September22th.vivo.Second;

/**
 * @author Gao Yuan
 * @date 2019-09-22 - 16:03
 */
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Welcome to vivo !
 */

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String inputStr = br.readLine();
        int input[] = parseInts(inputStr.split(" "));
        String output = solution(input);
        System.out.println(output);
    }

    private static int[] parseInts(String[] strArr) {
        if (strArr == null || strArr.length == 0) {
            return new int[0];
        }
        int[] intArr = new int[strArr.length];
        for (int i = 0; i < intArr.length; i++) {
            intArr[i] = Integer.parseInt(strArr[i]);
        }
        return intArr;
    }

    private static String solution(int[] input) {

        // TODO Write your code here
        int n = input[0];
        int m = input[1];
        List<Integer> line = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            line.add(i);
        }
        StringBuilder sb = new StringBuilder();
        int k = 0;
        while (line.size() > 0) {
            k = (k + m) % line.size();
            k = k % (line.size()) - 1;
            if (k < 0) {
                sb.append(line.get(line.size() - 1));
                sb.append(" ");
                line.remove(line.size() - 1);
                k = 0;
            } else {
                sb.append(line.get(k));
                sb.append(" ");
                line.remove(k);
            }
        }
        sb.deleteCharAt(sb.length() - 1); // 删除最后的空格
        return sb.toString();
    }

}