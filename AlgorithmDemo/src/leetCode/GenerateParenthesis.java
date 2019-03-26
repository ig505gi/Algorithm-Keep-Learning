package leetCode;

import java.util.ArrayList;
import java.util.List;

public class GenerateParenthesis {
	/*
    初步审题，应该也是关于回溯 backtrack，遇到括号的问题，需要stack
    只用记录括号数目即可，左括号不能多于n/2，右括号不能超过左括号数
    
    自己一开始弄巧成拙，还用stringbuffer创建对象啥的。。速度很慢
    */
	public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<String>();
        backtrack(res, "", 0, 0, n);
        return res;
    }
    
    private void backtrack(List<String> res, String str, int left, int right, int n) {
        if (str.length() == n * 2) {
            res.add(str);
        } else {
            if (left < n) {
                backtrack(res, str+"(", left + 1, right, n);
            }
            if (right < left) {
                backtrack(res, str+")", left, right + 1, n);
            }
        }
    }
    
    public static void main(String[] args) {
    	GenerateParenthesis gp = new GenerateParenthesis();
    	List<String> res = gp.generateParenthesis(3);
    	for (String s: res) {
    		System.out.println(s);
    	}
    	
    }
}
