package algorithm2.week5.burrows;

import java.util.Arrays;

public class CircularSuffixArray {
	
	private int[] index;
	
	public CircularSuffixArray(String s) // circular suffix array of s
	{
		if (s == null) throw new IllegalArgumentException();
		if (s.length() == 0) return;
		Ref[] ref = new Ref[s.length()];
		for (int i = 0; i < ref.length; i++) ref[i] = new Ref(i);
		// 利用自带的sort函数排序，但是比较的是String的字符从初始位置开始的suffix
		Arrays.sort(ref, (ref1, ref2) -> {
			int i = ref1.index;
			int j = ref2.index;
			// 这里的重大bug如果字符串中有连续相同的字符，会导致一直比较下去，无限循环
			int count = 0;
			while(count++ <s.length() && s.charAt(i) == s.charAt(j)) {
				i = i == s.length() - 1 ? 0 : i + 1;
				j = j == s.length() - 1 ? 0 : j + 1;
			}
			return s.charAt(i) - s.charAt(j);
		});
		index = new int[ref.length];
		for (int i = 0; i < index.length; i++) index[i] = ref[i].index;
	}
	
	private class Ref {
		int index;
		Ref(int index) { this.index = index; }
	}

	public int length() // length of s
	{
		if (index == null) return 0;
		return index.length;
	}

	public int index(int i) // returns index of ith sorted suffix
	{
		if (index == null || i < 0 || i >= index.length) throw new IllegalArgumentException(); 
		return index[i];
	}

	public static void main(String[] args) // unit testing (required)
	{
		CircularSuffixArray csa = new CircularSuffixArray("ABRACADABRA!");
		for (int i = 0; i < csa.length(); i++) {
			System.out.print(csa.index(i) + " ");
		}
	}

}
