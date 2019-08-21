package overexercise;

/**
 * @author Gao Yuan
 * @date 2019-08-08 - 14:52
 */
public class Manacher {
    /*
    马拉车算法manacher
    求一个字符串的最长回文子串
    1.预处理数组，把数组变成奇数个数
    2.回文半径概念，以位置往两边扩的回文子串长度 len/2就是半径，r数组
    3.回文右边界，不管前面更新的回文是哪个，目前子回文串能达到的最大右侧index，bound
    4.回文右边界的中心，取得回文右边界的中心，和上面同步更新（相同只记录最早），pivot
    步骤：
    三种情况：
    1.要扩的i在右边界外，暴力扩，有可能更新bound和pivot
    2. 在边界内，找对称点i’，
    2.1   i'的左边界没超过L, 直接等于对称点的回文半径，不用更新bound和pivot
    2.2   i'的左边界超过了L, 那么i的右边界只能到R，不用更新bound和pivot
    2.3   i’的左边界刚好到L，直接从R+1的位置以i为中心，扩展，有可能更新bound和pivot

     */
    public static char[] longestPalindromeSubstring(char[] str) {
        if (str == null || str.length == 0) throw new IllegalArgumentException();
        if (str.length == 1) return str;
        // 预处理和初始化数组
        char[] strMod = modifyStr(str);
        int[] r = new int[strMod.length];
        int bound = 0, pivot = 0, maxR = 0, maxIdx = 0; // 再遍历过程中保存最大的半径
        for (int i = 1; i < strMod.length; i++) {
            if (i > bound) { // 暴力扩
                int right = i + 1, left = i - 1, curR = 0;
                while (left >= 0 && right < strMod.length && strMod[left] == strMod[right]) {
                    curR++; left--; right++;
                }
                r[i] = curR;
                if (right - 1 > bound) { bound = right - 1; pivot = i; }
                if (curR > maxR) { maxR = curR; maxIdx = i; }
            } else {
                int iSym = pivot * 2 - i, leftBount = pivot * 2 - bound, iSymLeft = iSym - r[iSym];
                if (iSymLeft > leftBount) { // 2.1
                    r[i] = r[iSym];
                } else if (iSymLeft < leftBount) {// 2.2
                    r[i] = bound - i;
                } else { // 2.3
                    int right = bound + 1, left = i - r[iSym] - 1, curR = r[iSym];
                    while (left >= 0 && right < strMod.length && strMod[left] == strMod[right]) {
                        curR++; left--; right++;
                    }
                    r[i] = curR;
                    if (right - 1 > bound) { bound = right - 1; pivot = i; }
                    if (curR > maxR) { maxR = curR; maxIdx = i; }
                }
            }
        }
        // 找到了最大回文的 maxR 和 maxIdx 但是是在strMod中的位置，还原到str，找到
        int start = maxIdx - maxR, end = maxIdx + maxR; // 一定是偶数
        char[] res = new char[maxR];
        for (int i = 0, idxMod = start + 1; i < res.length; i++, idxMod += 2) {
            res[i] = strMod[idxMod];
        }
        return res;
    }

    private static char[] modifyStr(char[] str) {
        char[] strMod = new char[str.length * 2 + 1];
        for (int i = 0; i < strMod.length; i++) {
            if ((i & 1) == 0) strMod[i] = '#';
            else strMod[i] = str[i / 2];
        }
        return strMod;
    }

    public static void main(String[] args) {
        String str = "abcbbcbacccabcb";
        System.out.println(String.valueOf(Manacher.longestPalindromeSubstring(str.toCharArray())));
    }
}
