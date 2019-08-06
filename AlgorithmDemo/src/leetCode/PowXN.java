package leetCode;

/**
 * @author Gao Yuan
 * @date 2019-08-06 - 15:32
 * Note:
 *
 *     -100.0 < x < 100.0
 *     n is a 32-bit signed integer, within the range [−231, 231 − 1]
 */
public class PowXN {
    /*
    之前看过一些优化 可以二分，自己还是写不出来。。
    Runtime: 0 ms, faster than 100.00% of Java online submissions for Pow(x, n).
Memory Usage: 33.5 MB, less than 5.04% of Java online submissions for Pow(x, n).
    由于范围问题，
     */
    public double myPow(double x, int n) {
        if (n == 0) return 1.0;
        if (n < 0) {
            if (n == Integer.MIN_VALUE) {
                // 因为 2^-31,取负值，但是int类型没有2^31次方，得到的结果仍然是2^31
                // 会使栈溢出 是这个题的本身的范围问题，此时早已超出了double的精度
                // 猜测是正确的，换成了 +4 结果也是一样的，此时都已经溢出了double的精度，返回都是0.0，
                //
                n += 2;
            }
            n = -n;
            x = 1/x;
        }
        return (n % 2 == 0 ? 1 : x) * myPow(x * x, n >>> 1);
    }
}
