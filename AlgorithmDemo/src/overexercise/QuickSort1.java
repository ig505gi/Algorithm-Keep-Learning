package overexercise;

/**
 * @author Gao Yuan
 * @date 2019-08-23 - 12:16
 * 第一次写，凭印象写了大概10分钟，然后看原来的代码修改了10分钟。
 */
public class QuickSort1 {

    public void sort(int[] arr) {
        if (arr == null) return;
        sort(arr, 0, arr.length - 1);
    }

    private void sort(int[] arr, int l, int r) {
        if ( l >= r) return;
        int mid = patition(arr, l, r);
        sort(arr, l, mid - 1);
        sort(arr, mid + 1, r);
    }

    private int patition(int[] arr, int l, int r) {
        int i = l;
        int j = r + 1;
        while (true) {
            while (arr[++i] < arr[l]) {
                if (i == r) break;
            }
            while (arr[--j] > arr[l]) {
                if (j == l) break;
            }
            if (i >= j) break;
            swap(arr, i, j);
        }
        swap(arr, l, j);
        return j;
    }

    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {

    }
}
