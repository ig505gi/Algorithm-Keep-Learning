package algorithm2.week5.burrows;

import java.util.LinkedList;

import edu.princeton.cs.algs4.BinaryStdIn;
import edu.princeton.cs.algs4.BinaryStdOut;

/**
 * The main idea of move-to-front encoding is to maintain an ordered sequence 
 * of the characters in the alphabet, and repeatedly read a character from the 
 * input message, print out the position in which that character appears, and 
 * move that character to the front of the sequence. As a simple example, if 
 * the initial ordering over a 6-character alphabet is A B C D E F, and we want 
 * to encode the input CAAABCCCACCF, then we would update the move-to-front sequence as follows:

    move-to-front    in   out
    -------------    ---  ---
     A B C D E F      C    2 
     C A B D E F      A    1
     A C B D E F      A    0
     A C B D E F      A    0
     A C B D E F      B    2
     B A C D E F      C    2
     C B A D E F      C    0
     C B A D E F      C    0
     C B A D E F      A    2
     A C B D E F      C    1
     C A B D E F      C    0
     C A B D E F      F    5
     F C A B D E  

 * @author GaoYuan
 *
 */
public class MoveToFront {
	// apply move-to-front encoding, reading from standard input and writing to standard output
	public static void encode() {
		// 维持256个字符的table
		LinkedList<Character> table = new LinkedList<Character>();
		for (char c = 0; c < 256; c++) {
			table.addLast(c);
		}
		//读进来的一个字节代表一个char
		while (!BinaryStdIn.isEmpty()) {
			// 将该字节转化为ASCII码中的一个字符
			char c = (char) (BinaryStdIn.readByte() & 0xff);
			int index = table.indexOf(c);
			byte out = (byte) (index & 0xff);
			BinaryStdOut.write(out);
			table.remove(index);
			table.addFirst(c);
		}
		BinaryStdOut.close();
	}

	// apply move-to-front decoding, reading from standard input and writing to
	// standard output
	public static void decode() {
		// 维持256个字符的table
		LinkedList<Character> table = new LinkedList<Character>();
		for (char c = 0; c < 256; c++) {
			table.addLast(c);
		}
		// 读进来的一个字节代表一个index!!
		while (!BinaryStdIn.isEmpty()) {
			// 将该字节转化为int
			int index = BinaryStdIn.readByte() & 0xff;
			// 取出对应的char
			char c = table.get(index);
			// 输出该char对应的ASCII码值
			byte out = (byte) (c & 0xff);
			BinaryStdOut.write(out);
			table.remove(index);
			table.addFirst(c);
		}
		BinaryStdOut.close();
	}

	// if args[0] is '-', apply move-to-front encoding
	// if args[0] is '+', apply move-to-front decoding
	public static void main(String[] args) {
		if      (args[0].equals("-")) encode();
        else if (args[0].equals("+")) decode();
        else throw new IllegalArgumentException("Illegal command line argument");
	}
}
