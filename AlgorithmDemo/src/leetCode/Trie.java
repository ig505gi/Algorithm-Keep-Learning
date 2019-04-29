package leetCode;
/**
 * Runtime: 78 ms, faster than 76.27% of Java online submissions for Implement Trie (Prefix Tree).
Memory Usage: 49.1 MB, less than 98.89% of Java online submissions for Implement Trie (Prefix Tree).

很不容易。。bug了三次。。一些细节没处理好
这种方式空间占用少，如果用数组的会快点，但是空间消耗非常大
 * @author GaoYuan
 *
 */
public class Trie {
	private Node root;
	
    /** Initialize your data structure here. */
    public Trie() {
        
    }
    
    /** Inserts a word into the trie. */
    public void insert(String word) {
        if (word != null && word.length() > 0)
        	root = insert(root, word, 0);
    }
    
    private Node insert(Node root, String word, int i) {
    	char c = word.charAt(i);
    	if (root == null)
			root = new Node(c, false);
		if (i == word.length() - 1 && root.c == c) {
			root.isTail = c == root.c;
		} else {
			if (c > root.c)
				root.right = insert(root.right, word, i);
			else if (c < root.c)
				root.left = insert(root.left, word, i);
			else
				root.child = insert(root.child, word, i + 1);
		}
		return root;
	}

	/** Returns if the word is in the trie. */
    public boolean search(String word) {
        if (word == null || word.length() == 0) return false;
    	return search(root, word, 0);
    }
    
    private boolean search(Node root, String word, int i) {
    	if (root == null || i == word.length()) return false;
    	char c = word.charAt(i);
    	if (i == word.length() - 1 && root.isTail && c == root.c) {
    		return true;
    	}
    	if (c > root.c)
    		return search(root.right, word, i);
    	else if (c < root.c)
    		return search(root.left, word, i);
    	else
    		return search(root.child, word, i + 1);
	}

	/** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
    	if (prefix == null || prefix.length() == 0) return false;
    	return startsWith(root, prefix, 0);
    }

	private boolean startsWith(Node root, String prefix, int i) {
		if (root == null || i == prefix.length()) return false;
    	char c = prefix.charAt(i);
    	if (i == prefix.length() - 1 && c == root.c) {
    		return true;
    	}
    	if (c > root.c)
    		return startsWith(root.right, prefix, i);
    	else if (c < root.c)
    		return startsWith(root.left, prefix, i);
    	else
    		return startsWith(root.child, prefix, i + 1);
	}
	
	public static void main(String[] args) {
		Trie dic = new Trie();
		dic.insert("apple");
		System.out.println(dic.search("app"));
		System.out.println(dic.search("apple"));
		System.out.println(dic.startsWith("app"));
		dic.insert("bee");
		System.out.println(dic.search("appee"));
		dic.insert("app");
		System.out.println(dic.startsWith("b"));
	}
}

class Node {
	char c;
	boolean isTail;
	Node left, right, child;
	
	public Node(char c, boolean isTail) {
		this.c = c;
		this.isTail = isTail;
	}
}
/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */
