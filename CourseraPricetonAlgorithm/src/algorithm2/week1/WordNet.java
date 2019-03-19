package algorithm2.week1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class WordNet {
	private final HashMap<String, HashSet<Integer>> wordMap;
	private final Digraph wordNet;
	private final SAP sap;
	private final String[] synsets;

	// constructor takes the name of the two input files
	// The constructor should take time linearithmic (or better) in the input size.
	public WordNet(String synsets, String hypernyms) {
		if (synsets == null || hypernyms == null)
			throw new IllegalArgumentException();
		// 创建map，遍历sysets
		wordMap = new HashMap<String, HashSet<Integer>>();
		ArrayList<String> synList = new ArrayList<String>();
		int V = 0;
		In in = new In(synsets);
		while (in.hasNextLine()) {
			// 处理一行
			String[] fields = in.readLine().split(",");
			// 处理field1，保存行号，最后一个行号也就是顶点数
			V = Integer.parseInt(fields[0]);
			// 处理field2，将synset中的noun保存到map中，并对应行号
			synList.add(fields[1]);
			String[] synset = fields[1].split(" ");
			for (String noun : synset) {
				if (!wordMap.containsKey(noun)) {
					wordMap.put(noun, new HashSet<Integer>());
				}
				wordMap.get(noun).add(V);
			}
		}
		this.synsets = synList.toArray(new String[0]);

		// 创建Digraph
		wordNet = new Digraph(V + 1);
		in = new In(hypernyms);
		while (in.hasNextLine()) {
			String[] vertices = in.readLine().split(",");
			if (vertices.length > 1) { // 有两个以上顶点才有边 边都是v[0]->v[1],v[0]->v[2]...
				int v = Integer.parseInt(vertices[0]);
				for (int i = 1; i < vertices.length; i++) {
					int w = Integer.parseInt(vertices[i]);
					wordNet.addEdge(v, w);
				}
			}
		}
		// 判断是否是DAG，是否有环
		// if (!isDAG(wordNet)) throw new IllegalArgumentException();
		// 判断是否 rooted，有且只有一个点的outdegree == 0
		int countZero = 0;
		for (int v = 0; v < wordNet.V(); v++) {
			if (wordNet.outdegree(v) == 0)
				countZero++;
		}
		if (countZero != 1)
			throw new IllegalArgumentException("NOT a rooted DAG!");

		// 初始化sap
		sap = new SAP(wordNet);
	}

	// returns all WordNet nouns
	public Iterable<String> nouns() {
		return wordMap.keySet();
	}

	// is the word a WordNet noun?
	// The method isNoun() should run in time logarithmic (or better) in the number
	// of nouns.
	public boolean isNoun(String word) {
		if (word == null)
			throw new IllegalArgumentException();
		return wordMap.containsKey(word);
	}

	// distance between nounA and nounB (defined below)
	// should run in time linear in the size of the WordNet digraph.
	public int distance(String nounA, String nounB) {
		if (!isNoun(nounA) || !isNoun(nounB))
			throw new IllegalArgumentException();
		HashSet<Integer> synsetsOfNounA = wordMap.get(nounA);
		HashSet<Integer> synsetsOfNounB = wordMap.get(nounB);
		return sap.length(synsetsOfNounA, synsetsOfNounB);
	}

	// a synset (second field of synsets.txt) that is the common ancestor of nounA
	// and nounB in a shortest ancestral path (defined below)
	// should run in time linear in the size of the WordNet digraph.
	public String sap(String nounA, String nounB) {
		if (!isNoun(nounA) || !isNoun(nounB))
			throw new IllegalArgumentException();
		HashSet<Integer> synsetsOfNounA = wordMap.get(nounA);
		HashSet<Integer> synsetsOfNounB = wordMap.get(nounB);
		int ancestorIndx = sap.ancestor(synsetsOfNounA, synsetsOfNounB);
		return synsets[ancestorIndx];
//		StringBuilder synset = new StringBuilder();
//		for (String noun: wordMap.keySet()) {
//			if (wordMap.get(noun).contains(ancestorIndx)) {
//				synset.append(noun + " ");
//			}
//		}
//		return synset.substring(0, synset.length()-1);
	}

	// do unit testing of this class
	public static void main(String[] args) {
		WordNet wordnet = new WordNet(args[0], args[1]);
		StdOut.println(wordnet.isNoun("1760s"));
		StdOut.println(wordnet.sap("table", "apple"));
	}

}
