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
}
