package swordToOffer;

public class IsNumeric {
	int p = 0;
    public boolean isNumeric(char[] str) {
        if (str.length == 0) return false;
        
        boolean numeric = scanInteger(str);
        
        if (p < str.length && str[p] == '.') {
            p++;
            numeric = scanUnsignedInteger(str) || numeric;
        }
        
        if (p < str.length && (str[p] == 'e' || str[p] == 'E')) {
            p++;
            numeric = scanInteger(str) && numeric;
        }
        return numeric && p == str.length;
    }
    
    private boolean scanInteger(char[] str) {
        // 可以从-+开始的整数
        if (p < str.length && (str[p] == '-' || str[p] == '+')) p++;
        return scanUnsignedInteger(str);
    }
    
    private boolean scanUnsignedInteger(char[] str) {
        int begin = p;
        while (p < str.length && str[p] >= '0' && str[p] <= '9') {
            p++;
        }
        return p > begin;
    }
    
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
