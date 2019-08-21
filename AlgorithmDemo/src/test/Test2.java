package test;

import java.io.IOException;
import java.io.LineNumberReader;
import java.io.PrintWriter;
import java.io.StringReader;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author Gao Yuan
 * @date 2019-08-17 - 15:53
 */
public class Test2 {

    public static void main(String[] args) throws IOException {
        ThreadPoolExecutor executor = null;
        // 测试~
        int j = 0;
        System.out.println(~j);
        // 测试流
        StringReader sr = new StringReader("test");
        LineNumberReader reader = new LineNumberReader(sr);
        PrintWriter out = new PrintWriter(System.out);
        out.println(reader.readLine());
        int n = reader.getLineNumber();
        System.out.println(n);
        out.flush();
    }
}
