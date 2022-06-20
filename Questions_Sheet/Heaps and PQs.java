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

    // LEETCODE 1642. Furthest Building You Can Reach

    public int furthestBuilding(int[] heights, int bricks, int ladders) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        int n = heights.length, jumpHeight = 0, i = 0;
        for (i = 0; i < n - 1; i++) {
            jumpHeight = heights[i + 1] - heights[i];
            if (jumpHeight <= 0)
                continue;
            pq.add(jumpHeight);
            if (pq.size() > ladders)
                bricks -= pq.remove();
            if (bricks < 0)
                return i;
        }
        return i;
    }

    // LEETCODE 378. Kth Smallest Element in a Sorted Matrix

    public int kthSmallest(int[][] matrix, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        int n = matrix.length;

        for (int i = 0; i < n; i++) {
            int m = matrix[i].length;
            for (int j = 0; j < m; j++) {
                pq.add(matrix[i][j]);
            }
        }
        while (k-- > 1) {
            pq.remove();
        }
        return pq.remove();
    }

    // LEETCODE 767. Reorganize String

    class Pair {
        char c;
        int freq;

        Pair(char c, int freq) {
            this.c = c;
            this.freq = freq;
        }
    }

    public String reorganizeString(String s) {
        int n = s.length();
        if (n == 1)
            return s;

        HashMap<Character, Integer> map = new HashMap<>();
        PriorityQueue<Pair> heap = new PriorityQueue<>((a, b) -> b.freq - a.freq);
        for (int i = 0; i < n; i++)
            map.put(s.charAt(i), map.getOrDefault(s.charAt(i), 0) + 1);
        for (char c : map.keySet())
            heap.add(new Pair(c, map.get(c)));

        int max = heap.peek().freq;
        if (max > (n + 1) / 2)
            return "";

        char[] ans = new char[n];
        int idx = 0;

        while (heap.size() > 0) {
            Pair p = heap.remove();
            int f = p.freq;

            while (f > 0) {
                if (idx >= n)
                    idx = 1;

                ans[idx] = p.c;
                idx += 2;
                f--;
            }
        }
        return String.valueOf(ans);
    }

    // LEETCODE 1673. Find the Most Competitive Subsequence

    public int[] mostCompetitive(int[] nums, int k) {
        int[] stack = new int[k];
        for (int i = 0, j = 0; i < nums.length; i++) {
            while (j > 0 && stack[j - 1] > nums[i] && j + nums.length - i > k) {
                j--;
            }
            if (j < k) {
                stack[j++] = nums[i];
            }
        }
        return stack;
    }

    // GFG Smallest Positive missing number

    static int missingNumber(int arr[], int size) {

        for (int i = 0; i < size; i++) {
            while (arr[i] >= 1 && arr[i] <= size && arr[i] != arr[arr[i] - 1]) {
                int temp = arr[arr[i] - 1];
                arr[arr[i] - 1] = arr[i];
                arr[i] = temp;
            }
        }
        for (int i = 0; i < size; i++) {
            if (arr[i] != i + 1)
                return i + 1;
        }
        return size + 1;

    }

    // GFG Largest subarray with 0 sum

    int maxLen(int arr[], int n) {
        int max_len = 0, sum = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            sum += arr[i];
            if (sum == 0) {
                max_len = Math.max(max_len, i + 1);
            } else {
                if (map.containsKey(sum)) {
                    max_len = Math.max(max_len, (i - map.get(sum)));
                } else {
                    map.put(sum, i);
                }
            }
        }
        return max_len;
    }

    // LEETCODE 973. K Closest Points to Origin

    public int[][] kClosest(int[][] points, int k) {
        Arrays.sort(points, (a, b) -> squaredDistance(a) - squaredDistance(b));
        return Arrays.copyOf(points, k);
    }

    private int squaredDistance(int[] point) {
        return point[0] * point[0] + point[1] * point[1];
    }
}
