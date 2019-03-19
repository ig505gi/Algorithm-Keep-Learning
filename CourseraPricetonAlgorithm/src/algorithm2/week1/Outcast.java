package algorithm2.week1;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

/**
 * Outcast detection. Given a list of WordNet nouns x1, x2, ..., xn, which noun
 * is the least related to the others? To identify an outcast, compute the sum
 * of the distances between each noun and every other one: di = distance(xi, x1)
 * + distance(xi, x2) + ... + distance(xi, xn) and return a noun xt for which dt
 * is maximum.
 * 
 * @author GaoYuan
 *
 */
public final class Outcast {
	private final WordNet wordnet;

	public Outcast(WordNet wordnet) // constructor takes a WordNet object
	{
		this.wordnet = wordnet;
	}

	public String outcast(String[] nouns) // given an array of WordNet nouns, return an outcast
	{
		if (nouns == null)
			throw new IllegalArgumentException();
		int[] distances = new int[nouns.length];
		for (int i = 0; i < nouns.length; i++) {
			for (int j = 0; j < nouns.length; j++) {
				if (j != i) {
					distances[i] += wordnet.distance(nouns[i], nouns[j]);
				}
			}
		}

		int maxDistance = distances[0];
		int maxIndx = 0;
		for (int i = 1; i < distances.length; i++) {
			if (distances[i] > maxDistance) {
				maxDistance = distances[i];
				maxIndx = i;
			}
		}
		return nouns[maxIndx];
	}

	public static void main(String[] args) // see test client below
	{
		WordNet wordnet = new WordNet(args[0], args[1]);
		Outcast outcast = new Outcast(wordnet);
		for (int t = 2; t < args.length; t++) {
			In in = new In(args[t]);
			String[] nouns = in.readAllStrings();
			StdOut.println(args[t] + ": " + outcast.outcast(nouns));
		}
	}
}
