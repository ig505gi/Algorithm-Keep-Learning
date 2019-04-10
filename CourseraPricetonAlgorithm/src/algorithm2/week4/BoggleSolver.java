package algorithm2.week4;

import java.util.ArrayList;
import java.util.HashSet;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

public class BoggleSolver {
	private final HashSet<String> dictionary = new HashSet<String>();
	private final Node dictTST;
	
	// 私有类，boggle的图的顶点，主要来求adj
	private class BoggleGraphDice {
    	int col;
    	int row;
    	
    	public BoggleGraphDice(int row, int col) {
    		this.col = col;
    		this.row = row;
    	}
    	
    	public ArrayList<BoggleGraphDice> adj() {
    		ArrayList<BoggleGraphDice> adj = new ArrayList<BoggleGraphDice>();
    		for (int i = row - 1; i <= row + 1; i++) {
    			for (int j = col - 1; j <= col + 1; j++) {
    				if (i != row || j != col) {
    					adj.add(new BoggleGraphDice(i, j));
    				}
    			}
    		}
    		return adj;
    	}
    }
    
	//私有类 TST的节点
    private class Node {
    	private final char c;
    	private Node left, mid, right;
    	
    	public Node(char c) {
    		this.c = c;
    	}   	
    }
    
	// Initializes the data structure using the given array of strings as the dictionary.
    // (You can assume each word in the dictionary contains only the uppercase letters A through Z.)
    public BoggleSolver(String[] dictionary) {
    	for (String word: dictionary) {
    		this.dictionary.add(word);
    	}
    	dictTST = constructDictTST(dictionary);
    }
    
    private Node constructDictTST(String[] dictionary) {
    	Node root = null;
    	for (String word: dictionary) {
    		root = put(root, word, 0);
    	}
    	return root;
    }
    
    private Node put(Node x, String key, int d) {
        char c = key.charAt(d);
        if (x == null) {
            x = new Node(c);
        }
        if      (c < x.c)               x.left  = put(x.left,  key, d);
        else if (c > x.c)               x.right = put(x.right, key, d);
        else if (d < key.length() - 1)  x.mid   = put(x.mid,   key, d+1);
        // 复制了TST的源代码，但是我的TST比较简单，没有val，只用于判断isPrefix
        // else                            x.val   = val;
        return x;
    }
    
    // 因为判断word是否存在直接使用了set的contains方法，因为hash，时间复杂度不高
    // 因此 实现isPrefix可以更简单，需要只有char没有value的TST就能实现
	private boolean isPrefix(String word) {
		Node current = dictTST;
		int d = 0;
		while (current != null) {
			char c = word.charAt(d);
			if (c < current.c) {
				current = current.left;
			} else if (c > current.c) {
				current = current.right;
			} else if (d < word.length() - 1) {
				current = current.mid;
				d++;
			} else if (d == word.length() - 1) {
				// 找到了，并且current不为空就跳出循环
				break;
			}
		}
		if (current != null) return true;
		// 没找到对应的情况是current为空
		// 1.有可能d没到末尾就没找到，返回的是某个left或者right为空
		// 2.有可能d到末尾了，但是TST没了，返回的是mid为空
		else return false;
	}

    // Returns the set of all valid words in the given Boggle board, as an Iterable.
    public Iterable<String> getAllValidWords(BoggleBoard board) {
    	if (board == null) throw new IllegalArgumentException();
    	HashSet<String> res = new HashSet<String>();
    	Stack<BoggleGraphDice> stack = new Stack<BoggleGraphDice>();		
		// 记录 目前stack中的词是什么
		StringBuilder sb = new StringBuilder();
		// 记录目前stack中visiting的dice，进栈变为true，出栈时变为false，防止重复
		boolean[][] visiting = new boolean[board.rows()][board.cols()];
		
    	for (int i = 0; i < board.rows(); i++) {
    		for (int j = 0; j < board.cols(); j++) {
    			// 把要遍历的第一个格子放进stack中，更新stack, word, visiting
    			stack.push(new BoggleGraphDice(i, j));
    			sb.append(board.getLetter(i, j));
    			visiting[i][j] = true;   			
    			// dfs
    			dfs(stack, sb, visiting, board, res);
    			// 到这里 stack应该为空，word为空， visiting全为false
    			// 然后开始下一个点的遍历
    		}
    	}
    	return res;
    }

    private void dfs(Stack<BoggleGraphDice> stack, StringBuilder sb, boolean[][] visiting, BoggleBoard board, HashSet<String> res) {
    	String word = modiWordWithQ(sb.toString());
    	if (isPrefix(word)) {
    		// 只有isPrefix,才有可能在字典中存在，
    		if (dictionary.contains(word) && word.length() >= 3) res.add(word);
    		// 如果有邻居没遍历，就继续dfs
    		for (BoggleGraphDice next: stack.peek().adj()) {
				// adj()返回了周围的八个dice，但是有可能在board外，需要排除
				// 也有可能还在栈中，排除
				if (next.col >= 0 && next.row >= 0 && next.col < board.cols() && next.row < board.rows() 
						&& !visiting[next.row][next.col]) {
					stack.push(next);
					sb.append(board.getLetter(next.row, next.col));
					visiting[next.row][next.col] = true;
					dfs(stack, sb, visiting, board, res);
				}
			}
		}
		// 进行完这个，需要pop
		BoggleGraphDice bgd = stack.pop();
		sb.deleteCharAt(sb.length() - 1);
		visiting[bgd.row][bgd.col] = false;
	}
    
    

	// Returns the score of the given word if it is in the dictionary, zero otherwise.
    // (You can assume the word contains only the uppercase letters A through Z.)
    public int scoreOf(String word) {
    	if (word == null) throw new IllegalArgumentException();
    	if (!dictionary.contains(word)) return 0; 
    	int score = 0;
    	int length = word.length();
    	if (length <= 2) {
    		score = 0;
    	} else if (length <= 4) {
    		score = 1;
    	} else if (length <= 5) {
    		score = 2;
    	} else if (length <= 6) {
    		score = 3;
    	} else if (length <= 7) {
    		score = 5;
    	} else {
    		score = 11;
    	}
    	return score;
    }
       
	private String modiWordWithQ(String word) {
		if (word == null) throw new IllegalArgumentException();
		if (!word.contains("Q")) return word;
		return word.replace("Q", "QU");
	}

	public static void main(String[] args) {
		In in = new In(args[0]);
	    String[] dictionary = in.readAllStrings();
	    BoggleSolver solver = new BoggleSolver(dictionary);
	    BoggleBoard board = new BoggleBoard(args[1]);
	    StdOut.println(board);
	    int score = 0;
	    for (String word : solver.getAllValidWords(board)) {
	        StdOut.println(word);
	        score += solver.scoreOf(word);
	    }
	    StdOut.println("Score = " + score);
	}

}
