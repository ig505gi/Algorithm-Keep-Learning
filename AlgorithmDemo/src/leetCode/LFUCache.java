package leetCode;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;

/**
 * @author Gao Yuan
 * @date 2019-08-20 - 09:06
 */
public class LFUCache {
    private HashMap<Integer, Node> cacheMap;
    private HashMap<Integer, LinkedHashSet<Integer>> freqs;
    private int minFreq = 1;
    private int capacity;

    class Node {
        int val;
        int freq;

        public Node(int val, int freq) {
            this.val = val;
            this.freq = freq;
        }
    }

    public LFUCache(int capacity) {
        this.capacity = capacity;
    }

    public int get(int key) {
        if (cacheMap == null || cacheMap.size()== 0
                || !cacheMap.containsKey(key)) return -1;
        update(key);
        return cacheMap.get(key).val;
    }

    public void put(int key, int value) {
        if (cacheMap == null) {
            cacheMap = new HashMap<>();
            freqs = new HashMap<>();
        }
        if (capacity == 0) return;
        if (cacheMap.containsKey(key)) {
            Node node = cacheMap.get(key);
            update(key);
            node.val = value;
        } else {
            if (cacheMap.size() >= capacity) { // 没有重复，只是超容
                LinkedHashSet<Integer> minFreqSet = freqs.get(minFreq);
                int deleteKey = minFreqSet.iterator().next(); // 这个方法好像是可以取到插入的最后一个
                // 处理freqs
                minFreqSet.remove(deleteKey);
                if (minFreqSet.size() == 0) {
                    freqs.remove(minFreq);
                }
                // 处理map
                cacheMap.remove(deleteKey);
            }
            Node node = new Node(value, 1);
            cacheMap.put(key, node);
            if (!freqs.containsKey(1)) {
                freqs.put(1, new LinkedHashSet<>());
            }
            freqs.get(1).add(key);
            minFreq = 1; // 更新最小值为1
        }
    }
    /*
    更新key的freq
     */
    private void update(int key) {
        Node node = cacheMap.get(key);
        // 删除原有的freq
        LinkedHashSet<Integer> freqSet = freqs.get(node.freq);
        freqSet.remove(key);
        if (freqSet.size() == 0) {
            freqs.remove(node.freq);
            if (node.freq == minFreq) { minFreq++; }
        }
        // 新增一个freq
        node.freq++;
        if (!freqs.containsKey(node.freq)) {
            freqs.put(node.freq, new LinkedHashSet<>());
        }
        freqs.get(node.freq).add(key);
    }

    public static void main(String[] args) {
        LFUCache cache = new LFUCache(2);
        cache.put(1,2);
        cache.put(3,4); // 3,1
        System.out.println(cache.get(3)); // 4
        cache.put(1,3); // 1, 3
        cache.put(5, 6); // 1，5
        System.out.println(cache.get(3)); // -1
        System.out.println(cache.get(1)); // 3
        cache.put(7,8);
        System.out.println(cache.get(5)); // -1
    }
}

/**
 * Your LFUCache object will be instantiated and called as such:
 * LFUCache obj = new LFUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
