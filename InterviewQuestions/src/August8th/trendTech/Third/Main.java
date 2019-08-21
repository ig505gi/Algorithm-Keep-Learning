package August8th.trendTech.Third;

import java.util.*;

/**
 * @author Gao Yuan
 * @date 2019-08-08 - 19:16
 */
public class Main {
    private static int calEncodeLen(String mystr)
    {
        int len = 0;
        //Please write your code here
        HashMap<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < mystr.length(); i++) {
            char c = mystr.charAt(i);
            if (!map.containsKey(c)) {
                map.put(c, 1);
            } else {
                map.replace(c, map.get(c) + 1);
            }
        }
        HashMap<Character, Integer> lensMap = new HashMap<>();

        PriorityQueue<Freq> pq = new PriorityQueue<>();
        for (Character c: map.keySet()) {
            List<Character> list = new ArrayList<>();
            list.add(c);
            pq.add(new Freq(list, map.get(c)));
            lensMap.put(c, 0);
        }

        while (!pq.isEmpty()) {
            List<Character> curMinList = new ArrayList<>();
            int newFreq = 0;
            for (int i = 0; i < 3; i++) {
                if (pq.isEmpty()) break;
                Freq freq = pq.poll();
                newFreq += freq.freq;
                for (Character c : freq.charList) {
                    curMinList.add(c);
                    lensMap.replace(c, lensMap.get(c) + 1); // 全部长度增加1
                }
            }
            if (pq.isEmpty()) break;
            pq.add(new Freq(curMinList, newFreq));
        }
        for (Character c : lensMap.keySet()) {
            len += lensMap.get(c) * map.get(c);
        }

        return len;
    }

    public static void main(String[] args) {
        String mystr = "adsfaerwkeljfasodfjoqweirfaskdh";

        int len = calEncodeLen(mystr);
        System.out.println(len);
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        pq.add(1);
        pq.add(2);
        System.out.println(pq);
    }
}
class Freq implements Comparable<Freq>{
    List<Character> charList;
    int freq;

    public Freq(List<Character> charList, int freq) {
        this.charList = charList;
        this.freq = freq;
    }

    @Override
    public int compareTo(Freq o) {
        return this.freq - o.freq;
    }
}
