package Questions_Sheet;

import java.util.*;

public class Tries {

    // LEETCODE 211. Design Add and Search Words Data Structure

    class WordDictionary {

        TreeNode root;

        public WordDictionary() {
            root = new TreeNode();
        }

        public void addWord(String word) {
            TreeNode node = root;
            for (int i = 0; i < word.length(); i++) {
                int position = word.charAt(i) - 'a';
                if (node.nodes[position] == null)
                    node.nodes[position] = new TreeNode();
                node = node.nodes[position];
            }
            node.isWord = true;
        }

        public boolean search(String word) {
            TreeNode node = root;
            return dfs(0, word, node);
        }

        public boolean dfs(int index, String word, TreeNode node) {
            if (node == null)
                return false;
            if (index == word.length())
                return node.isWord;
            boolean ans = false;
            if (word.charAt(index) == '.') {
                for (int i = 0; i < 26; i++) {
                    ans = ans || dfs(index + 1, word, node.nodes[i]);
                }
            } else {
                ans = dfs(index + 1, word, node.nodes[word.charAt(index) - 'a']);
            }
            return ans;
        }
    }

    class TreeNode {

        boolean isWord;
        TreeNode[] nodes;

        TreeNode() {
            nodes = new TreeNode[26];
        }
    }

    // GFG Print Anagrams Together

    public List<List<String>> Anagrams(String[] string_list) {
        // Code here
        List<List<String>> res = new ArrayList<>();
        HashMap<String, ArrayList<String>> map = new HashMap<>();
        for (String str : string_list) {
            char[] ch = str.toCharArray();
            Arrays.sort(ch);
            String s = new String(ch);
            if (map.containsKey(s)) {
                ArrayList<String> list = map.get(S);
                list.add(Str);
                map.put(s, list);
            } else {

                ArrayList<String> list = new ArrayList<>();
                list.add(str);
                map.put(s, list);
            }
        }
        for (String key : map.keySet()) {
            res.add(map.get(key));
        }
        return res;
    }

    // LEETCODE 336. Palindrome Pairs

    public List<List<Integer>> palindromePairs(String[] words) {
        Map<String, Integer> index = new HashMap<>();
        Map<String, Integer> revIndex = new HashMap<>();
        String[] revWords = new String[words.length];
        for (int i = 0; i < words.length; ++i) {
            String s = words[i];
            String r = new StringBuilder(s).reverse().toString();
            index.put(s, i);
            revIndex.put(r, i);
            revWords[i] = r;
        }
        List<List<Integer>> result = new ArrayList<>();
        result.addAll(findPairs(words, revWords, revIndex, false));
        result.addAll(findPairs(revWords, words, index, true));
        return result;
    }

    private static List<List<Integer>> findPairs(String[] words, String[] revWords, Map<String, Integer> revIndex,
            boolean reverse) {
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < words.length; ++i) {
            String s = words[i];
            for (int k = reverse ? 1 : 0; k <= s.length(); ++k) {
                Integer j = revIndex.get(s.substring(k));
                if (j != null && j != i) {
                    if (s.regionMatches(0, revWords[i], s.length() - k, k)) {
                        result.add(reverse ? Arrays.asList(i, j) : Arrays.asList(j, i));
                    }
                }
            }
        }
        return result;
    }

    // GFG Phone directory

    class Node {
        Node links[];
        boolean flag;
        ArrayList<Integer> index;

        public Node() {
            links = new Node[26];
            flag = false;
            index = new ArrayList<>();
        }

        void putIndex(int i) {
            index.add(i);
        }

        ArrayList<Integer> getIndexes() {
            return index;
        }

        boolean contains(char c) {
            return links[c - 'a'] != null;
        }

        void put(char c, Node node) {
            links[c - 'a'] = node;
        }

        Node get(char c) {
            return links[c - 'a'];
        }

        void setEnd() {
            flag = true;
        }

        boolean getEnd() {
            return flag;
        }
    }

    class Pair {
        boolean isMatch;
        ArrayList<Integer> idxs;

        public Pair(boolean isMatch, ArrayList<Integer> idxs) {
            this.isMatch = isMatch;
            this.idxs = idxs;
        }
    }

    class Solution {
        static ArrayList<ArrayList<String>> displayContacts(int n,
                String contact[], String str) {
            Node root = new Node();
            int i = 0;
            for (String s : contact) {
                insert(s, root, i++);
            }
            int len = str.length();
            ArrayList<ArrayList<String>> list = new ArrayList<>();
            for (i = 1; i <= len; i++) {
                Set<String> ll = new TreeSet<>();
                Pair p = search(str.substring(0, i), root);
                if (p.isMatch) {
                    for (int idx : p.idxs) {
                        ll.add(contact[idx]);
                    }
                }
                if (ll.isEmpty())
                    ll.add("0");
                list.add(new ArrayList<>(ll));
            }
            return list;
        }

        static void insert(String s, Node root, int i) {
            Node node = root;
            for (char c : s.toCharArray()) {
                if (!node.contains(c))
                    node.put(c, new Node());
                node = node.get(c);
                node.putIndex(i);
            }
            node.setEnd();
        }

        static Pair search(String s, Node root) {
            Node node = root;
            for (char c : s.toCharArray()) {
                if (!node.contains(c))
                    return new Pair(false, new ArrayList<Integer>());
                node = node.get(c);
            }
            return new Pair(true, node.getIndexes());
        }
    }

}
