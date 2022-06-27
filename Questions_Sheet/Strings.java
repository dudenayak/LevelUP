package Questions_Sheet;

import java.util.ArrayList;

public class Strings {

    // LEETCODE 20. Valid Parentheses

    public boolean isValid(String str) {
        LinkedList<Character> st = new LinkedList<>();
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if (ch == '(' || ch == '[' || ch == '{')
                st.addFirst(ch);
            else {
                if (st.size() == 0)
                    return false;
                else if (st.getFirst() == '(' && ch != ')')
                    return false;
                else if (st.getFirst() == '[' && ch != ']')
                    return false;
                else if (st.getFirst() == '{' && ch != '}')
                    return false;
                else
                    st.removeFirst();
            }
        }

        return st.size() == 0;
    }

    // LEETCODE 28. Implement strStr()

    public int strStr(String haystack, String needle) {
        if (haystack.contains(needle))
            return haystack.indexOf(needle);
        else
            return -1;
    }

    // LEETCODE 14. Longest Common Prefix

    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0)
            return "";

        for (int i = 0; i < strs[0].length(); i++) {
            char c = strs[0].charAt(i);
            for (int j = 1; j < strs.length; j++) {
                if (i == strs[j].length() || strs[j].charAt(i) != c)
                    return strs[0].substring(0, i);
            }
        }
        return strs[0];
    }

    // LEETCODE 680. Valid Palindrome II

    private boolean checkPalindrome(String s, int i, int j) {
        while (i < j) {
            if (s.charAt(i) != s.charAt(j)) {
                return false;
            }
            i++;
            j--;
        }

        return true;
    }

    public boolean validPalindrome(String s) {
        int i = 0;
        int j = s.length() - 1;

        while (i < j) {
            if (s.charAt(i) != s.charAt(j)) {
                return (checkPalindrome(s, i, j - 1) || checkPalindrome(s, i + 1, j));
            }

            i++;
            j--;
        }

        return true;
    }

    // LEETCODE 12. Integer to Roman

    public String intToRoman(int num) {
        int[] digits = { 1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1 };
        String[] values = { "M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I" };

        String result = "";
        int i = 0, lengthOfDigits = 13;

        while (i < lengthOfDigits) {
            while (num >= digits[i]) {
                num = num - digits[i];
                result += values[i];
                if (num == 0)
                    break;
            }
            if (num == 0)
                break;
            i++;
        }

        return result;
    }

    // LEETCODE 22. Generate Parentheses

    public List<String> generateParenthesis(int n) {
        List<String> ans = new ArrayList<>();
        if (n == 0) {
            ans.add("");
        } else {
            for (int i = 0; i < n; i++)
                for (String left : generateParenthesis(i))
                    for (String right : generateParenthesis(n - 1 - i))
                        ans.add("(" + left + ")" + right);
        }
        return ans;
    }

    // LEETCODE 71. Simplify Path

    public String simplifyPath(String path) {

        Stack<String> s = new Stack<>();
        StringBuilder res = new StringBuilder();
        String[] p = path.split("/");

        for (int i = 0; i < p.length; i++) {
            if (!s.isEmpty() && p[i].equals(".."))
                s.pop();
            else if (!p[i].equals("") && !p[i].equals(".") && !p[i].equals(".."))
                s.push(p[i]);
        }

        if (s.isEmpty())
            return "/";
        while (!s.isEmpty()) {
            res.insert(0, s.pop()).insert(0, "/");
        }

        return res.toString();

    }

    // GFG Smallest window in a string containing all the characters of another
    // string

    public static String smallestWindow(String s, String p) {
        if (s.length() == 0 || p.length() == 0 || s.length() < p.length())
            return "-1";
        HashMap<Character, Integer> h = new HashMap<>();
        for (int i = 0; i < p.length(); i++) {
            if (!h.containsKey(p.charAt(i)))
                h.put(p.charAt(i), 0);
            h.put(p.charAt(i), h.get(p.charAt(i)) + 1);
        }
        String res = "";
        int n = h.size();
        int start = 0, end = 0;
        int min_length = Integer.MAX_VALUE;
        int count = 0;
        char ch[] = s.toCharArray();
        int ind = 0;
        HashMap<Character, Integer> hm = new HashMap<>();
        for (int i = 0; i < ch.length; i++) {
            if (h.containsKey(ch[i])) {
                if (!hm.containsKey(ch[i]))
                    hm.put(ch[i], 0);
                hm.put(ch[i], hm.get(ch[i]) + 1);
                if (h.get(ch[i]) == hm.get(ch[i])) {
                    count++;
                }
            }
            if (count == n) {
                while ((!hm.containsKey(ch[ind])) || hm.get(ch[ind]) > h.get((ch[ind]))) {
                    if ((!hm.containsKey(ch[ind]))) {
                        ind++;
                    } else if (hm.get(ch[ind]) > h.get((ch[ind]))) {
                        hm.put(ch[ind], hm.get(ch[ind]) - 1);
                        ind++;
                    }
                }
                int min = i - ind + 1;
                if (min_length > min) {
                    start = ind;
                    end = i + 1;
                    min_length = min;
                }

            }
        }
        if (min_length == Integer.MAX_VALUE) {
            return "-1";
        }
        return s.substring(start, min_length + start);

    }
}
