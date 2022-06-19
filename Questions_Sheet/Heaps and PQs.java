package Questions_Sheet;

import java.util.Map;

import Foundation.HashMap;
import Foundation.HashMap.PriorityQueue;

public class HeapsAndPQs {

    // LEETCODE 347. Top K Frequent Elements

    public int[] topKFrequent(int[] nums, int k) {
        int[] ans = new int[k];
        Map<Integer, Integer> map = new HashMap<>();

        for (int i : nums) {
            map.put(i, map.getOrDefault(i, 0) + 1);
        }

        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> map.get(a) - map.get(b));
        for (int j : map.keySet()) {
            pq.add(j);
            if (pq.size() > k) {
                pq.poll();
            }
        }
        int idx = 0;
        while (pq.size() != 0) {
            int num = pq.poll();
            ans[idx++] = num;
        }
        return ans;
    }

    // LEETCODE 215. Kth Largest Element in an Array

    public int findKthLargest(int[] nums, int k) {
        Arrays.sort(nume);
        int n = nums.length;
        return nums[n - k];
    }

    // more optimized solution

    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i = 0; i < nums.length; i++) {
            if (i < k) {
                pq.add(nums[i]);
            } else {
                if (nums[i] > pq.peek()) {
                    pq.remove();
                    pq.add(nums[i]);
                }
            }
        }
        int res = pq.peek();
        return res;
    }

    // LEETCODE 264. Ugly Number II

    public int nthUglyNumber(int n) {
        int p[] = new int[n + 1];
        p[1] = 1;
        int a = 1, b = 1, c = 1;
        for (int i = 2; i <= n; i++) {
            p[i] = Math.min(2 * p[a], Math.min(3 * p[b], 5 * p[c]));
            if (p[i] == 2 * p[a])
                a++;
            if (p[i] == 3 * p[b])
                b++;
            if (p[i] == 5 * p[c])
                c++;
        }
        return p[n];
    }
}
