package test;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.io.*;

public class Test {

	public Test() {
		// TODO Auto-generated constructor stub
	}
	private static void changeArray(int[] arr) {
		int[] newArr = {1,1,1};
		// 这里 arr是一个局部变量，也可以改成bss，实际上是这个局部变量指向了局部变量newArr
		// 必须用arr[i]来获取这个位置的值，才能change
		arr = newArr;
	}
	private static void changeArray2(int[] arr) {
		arr[0] = 1;
		arr[1] = 1;
		arr[2] = 1;
	}
	public static void main(String[] args) {
		System.out.println();
		// 测试char
		int i = 65;
		char numToc = (char) i;
		System.out.println(numToc);
		// 验证数组
		int[] arr = new int[3];
		System.out.println(Arrays.toString(arr));
		changeArray(arr);
		// 数组仍然是000
		System.out.println(Arrays.toString(arr));
		changeArray2(arr);
		// 这样才能变成111
		// 
		System.out.println(Arrays.toString(arr));
		// 读取char
		InputStreamReader sr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(sr);
		
		// <<=
		int num = 15;
		num <<= 1;
		System.out.println(num);
		System.out.println(num>>>2);
		String s = null;
		Character c = 'a';
		double aa = 9.0;
		double bb = 123.1231;
		double cc = 123.12311;
		double dd = 123.12;
		// Double a = aa;
		// Double b = 9.1;
		long bitsa = 4;
		// (bitsa >>> 32)
		System.out.println("bitsa ^ 2 =" + (bitsa ^ 2));
		System.out.println(bitsa + ">>> 1:" + (bitsa >>> 1));
		System.out.printf("%-10f|%-10f|%n", aa, bb);
		
		System.out.printf("%-10f|%-10f|", cc, dd);
		String str = "aaa";
		System.out.println(str.equals("aaa"));
		System.out.println(-1 % 4);
		System.out.println(Integer.MAX_VALUE + ": "+1165911996);
		
		HashMap<Integer,Integer> hm = new HashMap<Integer, Integer>();
		ConcurrentHashMap<Integer, Integer> cchm = new ConcurrentHashMap<Integer, Integer>();
		TreeMap<Integer, Integer> tm = new TreeMap<Integer, Integer>();
		
		ArrayList<Integer> ab = new ArrayList<Integer>();
		LinkedList<Integer> ll = new LinkedList<Integer>();
		Vector<Integer> vector = new Vector<Integer>();
		
		HashSet<Integer> ccc = new HashSet<Integer>();
		LinkedHashSet<Integer> lhs = new LinkedHashSet<Integer>();
		TreeSet<Integer> ts = new TreeSet<Integer>();
		
		PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
		Lock lock = null;
		
		byte b = 111;
		System.out.println(b);
	}

}
