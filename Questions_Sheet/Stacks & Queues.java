package Questions_Sheet;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Stack;

import Foundation.HashMap;

public class Stacks&Queues
{
    
    // LEETCODE 232. Implement Queue using Stacks
    class MyQueue {

        Stack<Integer> s1 = null;
        Stack<Integer> s2 = null;
        
        public MyQueue() {
            s1 = new Stack<>();
            s2 = new Stack<>();
        }
        
        public void push(int x) {
            while(!s2.isEmpty()){
                s1.push(s2.pop());
            }
            s1.push(x);
        }
        
        public int pop() {
            while(!s1.isEmpty()){
                s2.push(s1.pop());
            }
            
            return s2.pop();
        }
        
        public int peek() {
            if(!s2.isEmpty()){
                return s2.peek();
            }
            
            while(!s1.isEmpty()){
               s2.push(s1.pop()); 
            }
            
            return s2.peek();
        }
        
        public boolean empty() {
           return s1.isEmpty() && s2.isEmpty(); 
        }
    }

    // LEETCODE 844. Backspace String Compare

    public boolean backspaceCompare(String s, String t) {
        return build(s).equals(build(t));
    }

    public String build(String s) {
        Stack<Character> ans = new Stack();
        for (char c : s.toCharArray()) {
            if (c != '#')
                ans.push(c);
            else if (!ans.empty())
                ans.pop();
        }
        return String.valueOf(ans);
    }

    // LEETCODE 225. Implement Stack using Queues

    class MyStack {
        Deque<Integer> q1;

        public MyStack() {
            q1 = new ArrayDeque<>();
        }

        public void push(int x) {
            int size = q1.size();
            q1.addLast(x);
            while (size-- > 0)
                q1.addLast(q1.removeFirst());
        }

        public int pop() {
            return q1.removeFirst();
        }

        public int top() {
            return q1.peekFirst();
        }

        public boolean empty() {
            return q1.isEmpty();
        }
    }

    // LEETCODE 496. Next Greater Element I

    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        int res[] = new int[nums1.length];
        for (int i = 0; i < nums1.length; i++) {
            int num = nums1[i];
            res[i] = check(num, nums2);
        }
        return res;
    }

    public static int check(int num, int nums2[]) {
        int index = 0;
        for (int i = 0; i < nums2.length; i++)
            if (num == nums2[i])
                index = i;
        for (int i = index + 1; i < nums2.length; i++)
            if (nums2[i] > num)
                return nums2[i];
        return -1;
    }

    // LEETCODE 1130. Minimum Cost Tree From Leaf Values

    public int mctFromLeafValues(int[] arr) {
        if (arr == null && arr.length < 2)
            return 0;
        int ans = 0;
        Stack<Integer> s = new Stack<>();

        s.push(arr[0]);

        for (int i = 1; i < arr.length; i++) {
            while (s.size() != 0 && s.peek() <= arr[i]) {
                int sv = s.pop();
                int prev = (s.size() == 0) ? Integer.MAX_VALUE : s.peek();

                ans += sv * Math.min(arr[i], prev);
            }
            s.push(arr[i]);
        }

        while (s.size() > 1) {
            ans += s.pop() * s.peek();
        }
        return ans;
    }

    // LEETCODE 739. Daily Temperatures

    public int[] dailyTemperatures(int[] temperatures) {
        int len = temperatures.length;
        int[] ans = new int[len];
        Stack<Integer> st = new Stack<>();

        for (int i = 0; i < len; i++) {
            while (!st.empty() && temperatures[st.peek()] < temperatures[i]) {
                ans[st.peek()] = i - st.peek();
                st.pop();
            }
            st.push(i);
        }
        return ans;
    }

    // GFG Distance of nearest cell having 1

    public int[][] nearest(int[][] mat) {
        int r = mat.length;
        int c = mat[0].length;
        int max = 100001;
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (mat[i][j] != 1) {
                    int top = (i - 1 >= 0) ? mat[i - 1][j] : max;
                    int left = (j - 1 >= 0) ? mat[i][j - 1] : max;
                    mat[i][j] = Math.min(top, left) + 1;
                }
            }
        }
        for (int i = r - 1; i >= 0; i--) {
            for (int j = c - 1; j >= 0; j--) {
                if (mat[i][j] != 1) {
                    int bottom = (i + 1 < r) ? mat[i + 1][j] : max;
                    int right = (j + 1 < c) ? mat[i][j + 1] : max;
                    mat[i][j] = Math.min(mat[i][j], 1 + Math.min(bottom, right));
                }
            }
        }
        for (int i = 0; i < r; i++)
            for (int j = 0; j < c; j++)
                mat[i][j]--;
        return mat;
    }

    // LEETCODE 901. Online Stock Span

    class StockSpanner {

        Stack<Integer> stack;
        ArrayList<Integer> list;

        int index = 0;

        public StockSpanner() {
            list = new ArrayList<>();
            stack = new Stack<>();
        }

        public int next(int price) {
            if (stack.isEmpty()) {
                list.add(price);
                stack.add(index++);
                return 1;
            } else {
                list.add(price);
                while (!stack.isEmpty() && list.get(index) >= list.get(stack.peek()))
                    stack.pop();
                int ans = 0;
                if (!stack.isEmpty())
                    ans = index - stack.peek();
                else
                    ans = index + 1;
                stack.add(index++);
                return ans;
            }
        }
    }

    // more optimized solution

    class StockSpanner {
        int day = 0;
        LinkedList<int[]> st = new LinkedList<>();

        public StockSpanner() {
            st.addFirst(new int[] { -1, -1 });
        }

        public int next(int price) {
            while (st.getFirst()[0] != -1 && st.getFirst()[1] <= price)
                st.removeFirst();
            int span = day - st.getFirst()[0];
            st.addFirst(new int[] { day++, price });
            return span;
        }
    }

    // GFG Rotten Oranges

    public int orangesRotting(int[][] grid) {
        if (grid == null || grid.length == 0)
            return 0;
        int countTotal = 0;
        Queue<int[]> q = new LinkedList<int[]>();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] != 0) {
                    count_total++;
                }
                if (grid[i][j] == 2) {
                    q.offer(new int[] { i, j });
                }
            }
        }

        if (countTotal == 0)
            return 0;

        int count = 0, count_min = 0;
        while (!q.isEmpty()) {
            int size = q.size();
            count += size;
            for (int t = 0; t < size; t++) {
                int[] temp = q.poll();
                int i = temp[0];
                int j = temp[1];

                if (i - 1 >= 0 && grid[i - 1][j] != 0 && grid[i - 1][j] != 2) {
                    q.offer(new int[] { i - 1, j });
                    grid[i - 1][j] = 2;
                }
                if (j - 1 >= 0 && grid[i][j - 1] != 0 && grid[i][j - 1] != 2) {
                    q.offer(new int[] { i, j - 1 });
                    grid[i][j - 1] = 2;
                }
                if (j + 1 < grid[0].length && grid[i][j + 1] != 0 && grid[i][j + 1] != 2) {
                    q.offer(new int[] { i, j + 1 });
                    grid[i][j + 1] = 2;
                }
                if (i + 1 < grid.length && grid[i + 1][j] != 0 && grid[i + 1][j] != 2) {
                    q.offer(new int[] { i + 1, j });
                    grid[i + 1][j] = 2;
                }

            }
            if (q.size() != 0) {
                count_min++;
            }
        }

        return count == countTotal ? count_min : -1;
    }

    // LEETCODE 907. Sum of Subarray Minimums

    public int sumSubarrayMins(int[] arr) {
        Deque<Integer> stack = new ArrayDeque<>();
        int[] dp = new int[arr.length];
        int res = 0;
        for (int i = 0; i < arr.length; i++) {
            while (!stack.isEmpty() && arr[stack.getLast()] > arr[i])
                stack.removeLast();
            int j = stack.isEmpty() ? -1 : stack.getLast();
            int lastElementSum = j < 0 ? 0 : dp[j];
            dp[i] = lastElementSum + (i - j) * arr[i];
            res = (res + dp[i]) % 1_000_000_007;
            stack.addLast(i);
        }
        return res;
    }

    // LEETCODE

    // LEETCODE

    // LEETCODE

    // LEETCODE
}
