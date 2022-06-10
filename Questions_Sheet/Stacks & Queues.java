package Questions_Sheet;

import java.util.ArrayDeque;
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

    // LEETCODE

    // LEETCODE

    // LEETCODE

    // LEETCODE

    // LEETCODE

    // LEETCODE

    // LEETCODE

    // LEETCODE
}
