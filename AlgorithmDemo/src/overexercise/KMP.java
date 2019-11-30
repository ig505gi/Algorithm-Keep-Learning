package overexercise;

/**
 * @author Gao Yuan
 * @date 2019-08-08 - 10:08
 */
public class KMP {

    public static int find(String str, String patten) {
        if (str == null || str.length() == 0) throw new IllegalArgumentException();
        if (patten == null || patten.length() == 0) throw new IllegalArgumentException();
        int[] next = new int[patten.length()];
        next[0] = -1;
        int j = 0, k = -1;
        while (j < next.length - 1) {
            if (k == -1 || patten.charAt(j) == patten.charAt(k)) {
                j++;
                k++;
                if (patten.charAt(j) != patten.charAt(k)) {
                    next[j] = k;
                } else {
                    next[j] = next[k];
                }
            } else {
                k = next[k];
            }
        }
//        if (next.length > 1) next[1] = 0;
//        for (int i = 2; i < next.length; i++) {
//            int idx = i - 1;
//            int preIdx = next[i - 1];
//            while (preIdx >= 0) {
//                if (str.charAt(preIdx) == str.charAt(idx)) {
//                    next[i] = next[idx] + 1;
//                    break;
//                } else {
//                    idx = preIdx;
//                    preIdx = next[preIdx];
//                }
//            }
//            if (preIdx < 0) next[i] = 0;
//        }

        int strIdx = 0, patIdx = 0;
        while (strIdx < str.length()) {
            if (str.charAt(strIdx) == patten.charAt(patIdx)) {
                if (patIdx == patten.length() - 1) {
                    return strIdx - patIdx; // 成功
                }
                patIdx++;
                strIdx++;
            } else {
                patIdx = next[patIdx];
                if (patIdx < 0) { patIdx = 0; strIdx++; }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        System.out.println(find("aklhuelae", "hue"));
    }

}
