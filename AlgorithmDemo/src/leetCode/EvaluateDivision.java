package leetCode;

import javax.jws.soap.SOAPBinding;
import java.util.*;

/**
 * @author Gao Yuan
 * @date 2019-07-25 - 09:13
 * Example:
 * Given a / b = 2.0, b / c = 3.0.
 * queries are: a / c = ?, b / a = ?, a / e = ?, a / a = ?, x / x = ? .
 * return [6.0, 0.5, -1.0, 1.0, -1.0 ].
 *
 * equations = [ ["a", "b"], ["b", "c"] ],
 * values = [2.0, 3.0],
 * queries = [ ["a", "c"], ["b", "a"], ["a", "e"], ["a", "a"], ["x", "x"] ].
 */
public class EvaluateDivision {
    /*
    Runtime: 1 ms, faster than 89.73% of Java online submissions for Evaluate Division.
Memory Usage: 35.2 MB, less than 91.06% of Java online submissions for Evaluate Division.
其实思路都差不多，但是编程实现上有点不熟练。。
     */
    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        HashMap<String, List<edge>> equ = new HashMap<>();
        for (int i = 0; i < values.length; i++) {
            String from = equations.get(i).get(0);
            String to = equations.get(i).get(1);
            equ.putIfAbsent(from, new ArrayList<>());
            equ.putIfAbsent(to, new ArrayList<>());
            equ.get(from).add(new edge(to, values[i]));
            equ.get(to).add(new edge(from, 1 / values[i]));
        }
        double[] res = new double[queries.size()];
        for (int i = 0; i < res.length; i++) {
            res[i] = dfs(equ, queries.get(i).get(0), queries.get(i).get(1), 1.0, new HashSet<>());
        }

        return res;
    }

    private double dfs(HashMap<String, List<edge>> equ, String from, String to, double curWei, Set<String> path) {
        if (!equ.containsKey(from) || !path.add(from)) return -1.0;
        if (from.equals(to)) return curWei;
        for (edge edge : equ.get(from)) {
            double res = dfs(equ, edge.to, to, curWei * edge.weight, path);
            if (res != -1) return res;
        }
        // 走到这里，没到找
        return -1.0;
    }

    class edge {
        String to;
        double weight;

        public edge(String to, double weight) {
            this.to = to;
            this.weight = weight;
        }
    }

    public static void main(String[] args) {
        List<String> l1 = new ArrayList<String>(Arrays.asList("a", "b"));
        List<String> l2 = new ArrayList<String>(Arrays.asList("b", "c"));
        List<List<String>> equ = new ArrayList<>();
        equ.add(l1);
        equ.add(l2);
        double[] vals = {2.0, 3.0};
        List<List<String>> queries = new ArrayList<>();
        queries.add(new ArrayList<String>(Arrays.asList("a", "c")));
        queries.add(new ArrayList<String>(Arrays.asList("b", "a")));
        queries.add(new ArrayList<String>(Arrays.asList("a", "e")));
        queries.add(new ArrayList<String>(Arrays.asList("a", "a")));
        queries.add(new ArrayList<String>(Arrays.asList("x", "x")));
        EvaluateDivision solution = new EvaluateDivision();
        System.out.println(solution.calcEquation(equ, vals, queries));
    }
}
