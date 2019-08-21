package test;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.io.*;

public class Test {

	public Exception te() {
		Throwable aa = new Exception();
		try {
			int a = 1;
		} catch (IllegalArgumentException e2) {

		}
		return (Exception) aa;

	}


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
		Integer staticInt = 127;
		Integer staticInt2 = 127;
		Integer dynamicInt = 128;
		Integer dynamicInt2 = 128;
		System.out.println(staticInt == staticInt2);
		System.out.println(dynamicInt == dynamicInt2);
		// 演示array转list
		Integer[] aaa = new Integer[3];
		List<Integer> list1 = new ArrayList<>();
		Collections.addAll(list1, aaa);

		// 测试最大最小值
		int min = Integer.MIN_VALUE;
		int count = 32;
		System.out.println(0 - min);
		while (count-- > 0) {
			min /= 2;
			System.out.println(min);
		}
		// 测试 Calendar
		Calendar now = Calendar.getInstance();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		now.set(Calendar.HOUR_OF_DAY, 0);
		now.set(Calendar.MINUTE, 0);
		now.set(Calendar.SECOND, 0);
		System.out.println(format.format(now.getTime()));
		int year = now.get(Calendar.YEAR);
		System.out.println(year);
		System.out.println("-----------------");
		// StringBuffer
		StringBuffer sb = new StringBuffer("adad adad");
		sb.substring(0, sb.length());
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
		Integer test628 = Integer.valueOf(123);
		String s1 = new String("aaa");
		String s2 = new String("aaa");
		System.out.println(s1 == s2);           // false
		String s3 = s1.intern();
		String s4 = s1.intern();
		System.out.println(s3 == s4); // true
	}

}
