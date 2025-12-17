package leetcode150.F01ArrayString;

public class P20LongestCommonPrefix {
    class TrieNode {
        TrieNode[] children;
        int count;
        boolean isTerminal;

        public TrieNode() {
            this.children = new TrieNode[26];
            this.count = 0;
        }
    }

    TrieNode node = null;

    public P20LongestCommonPrefix() {
        this.node = new TrieNode();
    }

    public void addTrie(String s) {
        TrieNode temp = this.node;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            int idx = c - 'a';
            if (temp.children[idx] == null) {
                temp.children[c - 'a'] = new TrieNode();
                temp.count++;
            }
            temp = temp.children[c - 'a'];
        }
        temp.isTerminal = true;
    }

    public String iterate(TrieNode root, String s) {
        TrieNode curr = root;
        int i = 0;
        while (curr.count == 1 && !curr.isTerminal) {
            int idx = s.charAt(i) - 'a';
            i++;
            curr = curr.children[idx];
        }
        return s.substring(0, i);
    }

    public String longestCommonPrefix(String[] strs) {
        for (String s : strs) {
            addTrie(s);
        }
        // validate trie value
        return iterate(node, strs[0]);
    }
    public static void main(String[] args) {
        P20LongestCommonPrefix obj = new P20LongestCommonPrefix();
        String res = obj.longestCommonPrefix(new String[]{ "geeksforgeeks","geeks","geek","geezer"});
        System.out.println(res);
    }
}
