package April10th.huawei;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
public class First {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = Integer.parseInt(in.next());
        ArrayList<String> list = new ArrayList<String>();
        for (int i = 0; i < n; i++) {
            StringBuilder str = new StringBuilder(in.next());
			while (str.length() > 8) {
				String strleft = str.substring(0, 8);
				str = str.delete(0, 8);
				list.add(strleft);
			}
			for (int j = str.length(); j < 8; j++) {
				str.append('0');
			}
			list.add(str.toString());
		}
		in.close();
		Collections.sort(list);
		for (int i = 0; i < list.size(); i++) {
			System.out.print(list.get(i));
			if (i != list.size() - 1) {
				System.out.print(" ");
			}
		}
    }
}
