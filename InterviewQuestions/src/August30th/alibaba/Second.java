package August30th.alibaba;
import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;
/**
 * @author Gao Yuan
 * @date 2019-08-30 - 20:26
 * 输入:
 * bgbbbggbg(站成一圈的同学，首尾相接，b代表男生g代表女生)
 * k(最大团队最多可选女生数量)
 * 输出:
 * 最幸福男生所在位置(序号从0开始，如果存在多个，取按序号排第一个)，最大男生团队男生人数
 * 输入范例:
 * bgbbbgbggbgbg
 * 3
 * 输出范例:
 * 6 6
 */
public class Second {
    static String getIndexAndLongest(String users) {
        return null;

    }

    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        String res;

        String _users;
        try {
            _users = in.nextLine();
        } catch (Exception e) {
            _users = null;
        }

        res = getIndexAndLongest(_users);
        System.out.println(res);
    }
}
