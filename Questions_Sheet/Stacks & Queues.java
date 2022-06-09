package Questions_Sheet;

import java.util.ArrayDeque;
import java.util.Deque;

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
        int nextGreater[] = nextGreaterRight(nums1);
        HashMap<Integer, Integer> ans = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            ans.put(nums1[i], nextGreater[i]);
        }

        int newAns[] = new int[nums2.length];

        for (int i = 0; i < nums2.length; i++) {
            newAns[i] = ans.get(nums[i]);
        }
        return newAns;
    }

    // LEETCODE

    // LEETCODE

    // LEETCODE

    // LEETCODE

    // LEETCODE

    // LEETCODE

    // LEETCODE

    // LEETCODE

    // LEETCODE

    // LEETCODE
}
