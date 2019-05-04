package algorithm2.week5.burrows;

import java.util.ArrayList;
import java.util.Arrays;
import edu.princeton.cs.algs4.BinaryStdIn;
import edu.princeton.cs.algs4.BinaryStdOut;

public class BurrowsWheeler {
	// apply Burrows-Wheeler transform, reading from standard input and writing to
	// standard output
	public static void transform() {
		// 从标准输入中读取String
		String s = BinaryStdIn.readString();
		BinaryStdIn.close();
		// 用该String创建CircularSuffixArray
		CircularSuffixArray csa = new CircularSuffixArray(s);
		char[] original = s.toCharArray();
		// 将String排序得到第一列
		Arrays.sort(original);
		// 根据index和第一列，求出first和t[]
		int first = 0;
		char[] t = new char[s.length()];
		for (int i = 0; i < csa.length(); i++) {
			if (csa.index(i) == 0) {
				t[i] = s.charAt(s.length() - 1);
				first = i;
			} else {
				t[i] = s.charAt(csa.index(i) - 1);
			}
		}
		// 输出first和t[]
		BinaryStdOut.write(first);
		for (int i = 0; i < t.length; i++) {
			BinaryStdOut.write( (byte) t[i] );
		}
		BinaryStdOut.close();
	}

	// apply Burrows-Wheeler inverse transform, reading from standard input and
	// writing to standard output
	public static void inverseTransform() {
		// 先将前4byte变成first
		int first = (BinaryStdIn.readByte() & 0xff) << 24;
		first += (BinaryStdIn.readByte() & 0xff) << 16;
		first += (BinaryStdIn.readByte() & 0xff) << 8;
		first += BinaryStdIn.readByte() & 0xff;
		// 读取后面的t[]
		ArrayList<Character> t = new ArrayList<Character>();
		while (!BinaryStdIn.isEmpty()) {
			t.add((char) (BinaryStdIn.readByte() & 0xff));
		}
		BinaryStdIn.close();
		// 排序得到第一列
		Character[] firCol = new Character[t.size()]; 
		t.toArray(firCol);
		Arrays.sort(firCol);
		// 构造next[]
		// 开始想着有二分查找，之后又想到一个方法，维持一个256大小的数组，
		// 保存的是每个char在firCol中的第一次出现的index
		int[] firstOfCharIndx = new int[256];
		Arrays.fill(firstOfCharIndx, -1);
		for (int i = 0; i < firCol.length; i++) {
			char c = firCol[i];
			// 如果小于0说明是第一次出现，直接更新
			if (firstOfCharIndx[c] < 0) firstOfCharIndx[c] = i;
		}
		int[] next = new int[t.size()];
		for (int i = 0; i < t.size(); i++) {
			char c = t.get(i);
			// 获取第一次出现的index之后，需要+1，为第二次出现准备
			int index = firstOfCharIndx[c]++;
			next[index] = i;
		}
		// 利用next，first，firCol还原
		for (int i = 0; i < firCol.length; i++) {
			BinaryStdOut.write(firCol[first]);
			first = next[first];
		}
		BinaryStdOut.close();
	}

	// if args[0] is '-', apply Burrows-Wheeler transform
	// if args[0] is '+', apply Burrows-Wheeler inverse transform
	public static void main(String[] args) {
		if      (args[0].equals("-")) transform();
        else if (args[0].equals("+")) inverseTransform();
        else throw new IllegalArgumentException("Illegal command line argument");
	}
}
