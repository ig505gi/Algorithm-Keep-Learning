package test;


public class Test {

	public Test() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		double aa = 9.0;
		double bb = 123.1231;
		double cc = 123.12311;
		double dd = 123.12;
		// Double a = aa;
		// Double b = 9.1;
		long bitsa = 2;
		// (bitsa >>> 32)
		System.out.println(bitsa ^ 2);
		System.out.println((bitsa >>> 32));
		System.out.printf("%-10f|%-10f|%n", aa, bb);
		
		System.out.printf("%-10f|%-10f|", cc, dd);
		String str = "aaa";
		System.out.print(str.equals("aaa"));
	}

}
