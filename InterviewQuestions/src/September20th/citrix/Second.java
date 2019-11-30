package September20th.citrix;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author Gao Yuan
 * @date 2019-09-20 - 21:13
 */
public class Second {

    public static int getMinimumMoves(List<Integer> arr) {
        // Write your code here
        int[][] numsAndIdx = new int[arr.size()][2];
        for (int i = 0; i < arr.size(); i++) {
            numsAndIdx[i][0] = arr.get(i);
            numsAndIdx[i][1] = i;
        }
        Arrays.sort(numsAndIdx, (a, b) -> {
            if (a[0] != b[0]) return a[0] - b[0];
            else return a[1] - b[1];
        });
        int i = 1;
        for (; i < numsAndIdx.length; i++) {
            if (numsAndIdx[i][1] < numsAndIdx[i - 1][1]) {
                break;
            }
        }
        return arr.size() - i;
    }
}
