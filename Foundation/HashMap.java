package Foundation;

import java.io.*;
import java.util.*;

public class HashMap {
    public static void main(String[] args) throws Exception {
        Scanner scn = new Scanner(System.in);
        String str = scn.nextLine();

        // Highest Frequency Character
        HashMap<Character, Integer> hm = new HashMap<>();
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if (hm.containsKey(ch)) {
                int of = hm.get(ch);
                int nf = of + 1;
                hm.put(ch, nf);
            } else {
                hm.put(ch, 1);
            }
        }

        char mfc = str.charAt(0);
        for (Character key : hm.keySet()) {
            if (hm.get(key) > hm.get(mfc)) {
                mfc = key;
            }
        }

        System.out.println(mfc);

        // Get Common Elements - 1
        int n1 = scn.nextInt();
        int[] a1 = new int[n1];
        for (int i = 0; i < a1.length; i++) {
            a1[i] = scn.nextInt();
        }

        int n2 = scn.nextInt();
        int[] a2 = new int[n2];
        for (int i = 0; i < a2.length; i++) {
            a2[i] = scn.nextInt();
        }
        HashMap<Integer, Integer> fmap = new HashMap<>();
        for (int val : a1) {
            if (fmap.containsKey(val)) {
                int of = fmap.get(val);
                int nf = of + 1;
                fmap.put(val, nf);
            } else {
                fmap.put(val, 1);
            }
        }
        for (int val : a2) {
            if (fmap.containsKey(val)) {
                System.out.println(val);
                fmap.remove(val);
            }
        }

        // Get Common Elements - 2
        // same code as above just change second for loop
        for (int val : a2) {
            if (fmap.containsKey(val) && fmap.get(val) > 0) {
                System.out.println(val);
                int of = fmap.get(val);
                int nf = of - 1;
                fmap.put(val, nf);
            }
        }

        // Longest Consecutive Sequence Of Elements
        HashMap<Integer, Boolean> map = new HashMap<>();

        for (int val : a) {
            map.put(val, true);
        }

        for (int val : a) {
            if (map.containsKey(val - 1)) {
                map.put(val, false);
            }
        }

        int msp = 0;
        int ml = 0;
        for (int val : a) {
            if (map.get(val) == true) {
                int tl = 1;
                int tsp = val;

                while (map.containsKey(tsp + tl)) {
                    tl++;
                }
                if (tl > ml) {
                    msp = tsp;
                    ml = tl;
                }
            }
        }
        for (int i = 0; i < ml; i++) {
            System.out.println(msp + i);
        }
    }

    // K Largest Elements
    public static void main1(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        int k = Integer.parseInt(br.readLine());
        // write your code here
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i = 0; i < arr.length; i++) {
            if (i < k) {
                pq.add(arr[i]);
            } else {
                if (arr[i] > pq.peek()) {
                    pq.remove();
                    pq.add(arr[i]);
                }
            }
        }
        while (pq.size() > 0) {
            System.out.println(pq.remove());
        }

        // Sort K-sorted Array
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i = 0; i <= k; i++) {
            pq.add(arr[i]);
        }

        for (int i = k + 1; i < arr.length; i++) {
            System.out.println(pq.remove());
            pq.add(arr[i]);
        }
        while (pq.size() > 0) {
            System.out.println(pq.remove());
        }

        // Median Priority Queue
        PriorityQueue<Integer> left;
    PriorityQueue<Integer> right;

    public MedianPriorityQueue() {
      left = new PriorityQueue<>(Collections.reverseOrder());
      right = new PriorityQueue<>();
    }

    public void add(int val) {
        // write your code here
        if (right.size() > 0 && val > right.peek()) {
            right.add(val);
        } else {
            left.add(val);
        }

        if (left.size() - right.size() == 2) {
            right.add(left.remove());
        } else if (right.size() - left.size() == 2) {
            left.add(right.remove());
        }
    }

    public int remove() {
        // write your code here
        if (this.size() == 0) {
            System.out.println("Underflow");
            return -1;
        } else if (left.size() >= right.size()) {
            return left.remove();
        } else {
            return right.remove();
        }

    }

    public int peek() {
        // write your code here
        if (this.size() == 0) {
            System.out.println("Underflow");
            return -1;
        } else if (left.size() >= right.size()) {
            return left.peek();
        } else {
            return right.peek();
        }
    }

    public int size() {
        // write your code here
        return left.size() + right.size();
    }

}
