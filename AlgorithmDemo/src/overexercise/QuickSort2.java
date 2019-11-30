package overexercise;

/**
 * @author Gao Yuan
 * @date 2019-08-28 - 18:28
 * 7分钟写完
 */
public class QuickSort2 {

    public static void sort(int[] a) {
        if (a == null || a.length == 1) return;
        sort(a, 0, a.length - 1);
    }

    private static void sort(int[] a, int l, int r) {
        if (l >= r) return;
        int mid = patition(a, l, r);
        sort(a, l, mid - 1);
        sort(a, mid + 1, r);
    }

    private static int patition(int[] a, int l, int r) {
        int i = l;
        int j = r + 1;
        while (true) {
            while ( a[++i] <= a[l]) {
                if (i == r) break;
            }
            while (a[--j] >= a[r]) {
                if (j == l) break;
            }
            if (i >= j) break;
            swap(a, i, j);
        }
        swap(a, l, j);
        return j;
    }

    private static void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
}
