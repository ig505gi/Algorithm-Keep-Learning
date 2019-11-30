package September8th.mihoyo;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * @author Gao Yuan
 * @date 2019-09-07 - 18:12
 */
public class Second {

    public static void main(String[] args) {
        // 处理输入
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        in.nextLine();
        char[][] board = new char[n][m];
        for (int row = 0; row < n; row++) {
            String line = in.nextLine();
            for (int col = 0; col < m; col++) {
                board[row][col] = line.charAt(col);
            }
        }
        String line = in.nextLine();
        String[] nums = line.split(" ");
        int[] p1 = new int[]{Integer.parseInt(nums[0]), Integer.parseInt(nums[1])};
        int[] p2 = new int[]{Integer.parseInt(nums[2]), Integer.parseInt(nums[3])};
        // 算法
        // 交换
        char temp = board[p1[0]][p2[1]];
        board[p1[0]][p2[1]] = board[p2[0]][p2[1]];
        board[p2[0]][p2[1]] = temp;
        Set<Integer> chechRows = new HashSet<>();// 每次循环需要检查的行 不用所有行都检查
        Set<Integer> chechCols = new HashSet<>();
        chechRows.add(p1[0]);
        chechRows.add(p2[0]);
        chechCols.add(p1[1]);
        chechCols.add(p2[1]);
        int res = 0;
        while (true) {
            int num = minus(board, chechRows, chechCols);
            if (num == 0) break;
            res += num;
        }
        System.out.println(res);
    }
    /*
    写不完了！备注有思路，希望能被看到！
     */
    private static int minus(char[][] board, Set<Integer> chechRows, Set<Integer> chechCols) {
        int n = board.length;
        int m = board[0].length;
        int num = 0;
        // 检查消除，执行消除

        Set<Integer> rows = new HashSet<>(); // 受影响的行
        Set<Integer> cols = new HashSet<>();

        for (int row : chechRows) {
            for (int col = 0; col < m; col++) {
                // 检查一行有哪些该消除的
                int numOfRowSame = 1;
                int right = col;
                for (; right < m; right++) {
                    if (board[row][col] == board[row][right]) numOfRowSame++;
                    else break;
                }
                int left = col;
                for (; left > 0; left--) {
                    if (board[row][col] == board[row][left]) numOfRowSame++;
                    else break;
                }
                if (numOfRowSame >=3 ) {
                    // 将该记录信息保存下来 说明该消除的
                }
                // 检查列要被消除的
                // 并记录
            }
        }
        for (int col : chechCols) {
            // 和上面一样，这部分可以提取出来写成一个方法
        }
        // 遍历所有要消除的位置的记录，求出num
        // 并更新受影响的行和列
        return num;
    }
}
