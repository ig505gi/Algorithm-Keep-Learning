package leetCode;

/**
 * @author Gao Yuan
 * @date 2019-08-21 - 17:09
 */
public class ValidPalindrome {
    /*
    Runtime: 4 ms, faster than 81.93% of Java online submissions for Valid Palindrome.
Memory Usage: 37.8 MB, less than 93.75% of Java online submissions for Valid Palindrome.
     */
    public boolean isPalindrome(String s) {
        s = s.toLowerCase();
        for (int i = 0, j = s.length() - 1; i < j; i++, j--) {
            char lc = s.charAt(i);
            char rc = s.charAt(j);
            while (i<j && !isAlphanumeric(lc)) { lc = s.charAt(++i); }
            while (i<j && !isAlphanumeric(rc)) { rc = s.charAt(--j); }
            if (lc != rc) return false;
        }
        return true;
    }

    private boolean isAlphanumeric(char c) {
        return (c >= '0' && c <= '9') || (c >= 'a' && c <= 'z');
    }

    public static void main(String[] args) {
        ValidPalindrome solution = new ValidPalindrome();
        System.out.println(solution.isPalindrome("A man, a plan, a canal: Panama"));
    }
}
