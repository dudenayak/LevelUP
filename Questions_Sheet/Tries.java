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
}
