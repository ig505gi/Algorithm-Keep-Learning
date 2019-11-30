package September20th.citrix;

import java.io.*;
import java.util.*;
import java.util.stream.IntStream;

/**
 * @author Gao Yuan
 * @date 2019-09-20 - 18:04
 */
public class Forth {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        String[] friendsNodesEdges = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int friendsNodes = Integer.parseInt(friendsNodesEdges[0]);
        int friendsEdges = Integer.parseInt(friendsNodesEdges[1]);

        List<Integer> friendsFrom = new ArrayList<>();
        List<Integer> friendsTo = new ArrayList<>();
        List<Integer> friendsWeight = new ArrayList<>();

        IntStream.range(0, friendsEdges).forEach(i -> {
            try {
                String[] friendsFromToWeight = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

                friendsFrom.add(Integer.parseInt(friendsFromToWeight[0]));
                friendsTo.add(Integer.parseInt(friendsFromToWeight[1]));
                friendsWeight.add(Integer.parseInt(friendsFromToWeight[2]));
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        int result = maxShared(friendsNodes, friendsFrom, friendsTo, friendsWeight);

        System.out.println(result);

        bufferedReader.close();
    }
    public static int maxShared(int friendsNodes, List<Integer> friendsFrom, List<Integer> friendsTo, List<Integer> friendsWeight) {
        Set<Integer>[] interests = new Set[friendsNodes + 1];
        for (int i = 1; i < interests.length; i++) {
            interests[i] = new HashSet<>();
        }
        for (int i = 0; i < friendsFrom.size(); i++) {
            int interest = friendsWeight.get(i);
            interests[friendsFrom.get(i)].add(interest);
            interests[friendsTo.get(i)].add(interest);
        }
        int maxVal = 0, maxSameNum = 0;
        for (int i = 1; i < friendsNodes; i++) {
            for (int j = i + 1; j < friendsNodes + 1; j++) {
                int sameNum;
                if (interests[i].size() <= interests[j].size()) {
                    sameNum = getSameNum(interests[i], interests[j]);
                } else {
                    sameNum = getSameNum(interests[j], interests[i]);
                }
                if (sameNum > maxSameNum) {
                    maxSameNum = sameNum;
                    maxVal = i * j;
                } else if (sameNum == maxSameNum && i * j > maxVal) {
                    maxVal = i * j;
                }
            }
        }
        return maxVal;
    }

    private static int getSameNum(Set<Integer> setFewer, Set<Integer> setMore) {
        int sameNum = 0;
        for (int i : setFewer) {
            if (setMore.contains(i)) sameNum++;
        }
        return sameNum;
    }
}
