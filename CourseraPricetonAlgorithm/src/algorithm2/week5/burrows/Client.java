package algorithm2.week5.burrows;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import edu.princeton.cs.algs4.BinaryStdIn;
import edu.princeton.cs.algs4.HexDump;


public class Client {
	
	public static void main(String[] args) throws FileNotFoundException {
//		String name = "couscous.txt";
//		String filename = "src/algorithm2/week5/burrows/";
//		filename += name;
//		InputStream f = new FileInputStream(filename);
//		System.setIn(f);
//
////		System.out.println("用HexDump测试开始：-------------------");
////		HexDump.main(args);
////		System.out.println("用HexDump测试结束：-------------------");
//		
//		
//		BurrowsWheeler.transform();
		
		// 00 00 00 03 41 52 44 21 52 43 41 41 41 41 42 42 测试inverse
		// byte[] buf = {0x00, 0x00, 0x00, 0x03, 0x41, 0x52, 0x44, 0x21, 0x52, 0x43, 0x41, 0x41, 0x41, 0x41, 0x42, 0x42};
//		byte[] buf = {0x41, 0x41, 0x41, 0x41};
//		byte[] buf = {0x00, 0x00, 0x00, 0x00, 0x2a, 0x2a, 0x2a, 0x2a, 0x2a, 0x2a, 0x2a, 0x2a, 0x2a, 0x2a, 0x2a, 0x2a};
//		InputStream in = new ByteArrayInputStream(buf);
//		System.setIn(in);
//		BurrowsWheeler.inverseTransform();
//		//MoveToFront.encode();
		
		byte[] buf = {0x41, 0x41, 0x41, 0x41};
		InputStream in = new ByteArrayInputStream(buf);
		System.setIn(in);
		int first = (BinaryStdIn.readByte() & 0xff) << 24;
		first += (BinaryStdIn.readByte() & 0xff) << 16;
		first += (BinaryStdIn.readByte() & 0xff) << 8;
		first += BinaryStdIn.readByte() & 0xff;
		System.out.println(first);
		System.out.println(0x41414141);
//		byte b  = -1;
//		int c = b & 0xffffffff;
//		System.out.println();
//		int[] a = {1,2};
// 		int b = a[1]++;
// 		int c = a[1];
// 		System.out.println();
//		System.out.println("用Huffman测试开始：-------------------");
//		// 将 文件输入流设置为System.in
//		InputStream f2 = new FileInputStream(filename);
//		System.setIn(f2);
////		// 创建输出流，并初始化printStream
////		ByteArrayOutputStream out = new ByteArrayOutputStream();
////		PrintStream ps = new PrintStream(out);
////		// 设置ps为System.out
////		System.setOut(ps);
//		
//		Huffman.compress();
//		// 创建输入流
////		InputStream f3 = new ByteArrayInputStream(out.toByteArray());
////		System.setIn(f3);
//		Huffman.expand();
//		System.out.println("用Huffman测试结束：-------------------");
		
		
	}
}