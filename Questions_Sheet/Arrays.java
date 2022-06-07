package Questions_Sheet;

import java.util.ArrayList;
import java.util.HashSet;

public class Arrays {

    // LEETCODE 287. Find the Duplicate Number

    public int findDuplicate(int[] nums) {
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] == nums[i + 1]) {
                return nums[i];
            }
        }
        return -1;
    }

    // LEETCODE 75. Sort Colors

    public void sortColors(int[] nums) {
        Arrays.sort(nums);
    }

    public void sortColors(int[] arr) {
        int i = 0;
        int j = 0;
        int k = arr.length - 1;

        while (j <= k) {

            if (arr[j] == 1) {
                j++;
            } else if (arr[j] == 0) {
                swap(arr, j, i);
                i++;
                j++;
            } else {
                swap(arr, j, k);
                k--;
            }
        }
    }

    public void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    // LEETCODE 26. Remove Duplicates from Sorted Array

    public int removeDuplicates(int[] nums) {
        int i = 0;
        for (int j = 1; j < nums.length; j++) {
            if (nums[i] != nums[j]) {
                nums[i + 1] = nums[j];
                i++;
            }
        }
        return i + 1;
    }

    // LEETCODE 73. Set Matrix Zeroes

    public void setZeroes(int[][] matrix) {
        boolean[] row = new boolean[matrix.length];
        boolean[] col = new boolean[matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == 0) {
                    row[i] = true;
                    col[j] = true;
                }
            }
        }
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (col[j] == true || row[i] == true) {
                    matrix[i][j] = 0;
                }
            }
        }
    }

    // LEETCODE 283. Move Zeroes

    public void moveZeroes(int[] nums) {
        int i = 0;
        for (int j = 1; j < nums.length; j++) {
            if (nums[i] != 0) {
                i++;
            } else if (nums[j] != 0) {
                nums[i++] = nums[j];
                nums[j] = 0;
            }
        }
    }

    // LEETCODE 121. Best Time to Buy and Sell Stock

    public int maxProfit(int[] prices) {
        int min = Integer.MAX_VALUE;
        int profit = 0;

        for (int i = 0; i < prices.length; i++) {
            if (prices[i] < min) {
                min = prices[i];
            } else if (prices[i] - min > profit) {
                profit = prices[i] - min;
            }
        }
        return profit;
    }

    // GFG Chocolate Distribution Problem

    public static int findMinDiff(int arr[], int n, int m) {
        if (m == 0 || n == 0)
            return 0;

        Arrays.sort(arr);

        if (n < m)
            return -1;

        int mindiff = Integer.MAX_VALUE;

        for (int i = 0; i + m - 1 < n; i++) {
            int diff = arr[i + m - 1] - arr[i];
            if (diff < mindiff)
                mindiff = diff;
        }
        return mindiff;
    }

    // LEETCODE 1. Two Sum

    public int[] twoSum(int[] nums, int target) {
        int ans[] = new int[2];
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if ((nums[i] + nums[j]) == target) {
                    ans[0] = i;
                    ans[1] = j;
                }
            }
        }
        return ans;
    }

    // LEETCODE 122. Best Time to Buy and Sell Stock II

    public int maxProfit(int[] prices) {
        int profit = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > prices[i - 1]) {
                profit += prices[i] - prices[i - 1];
            }
        }
        return profit;
    }

    // LEETCODE 974. Subarray Sums Divisible by K

    public int subarraysDivByK(int[] nums, int k) {
        int[] count = new int[k];
        int sum = 0;
        for (int i : nums) {
            sum += ((i % k) + k) % k;
            count[sum % k]++;
        }

        int res = count[0];
        for (int j : count) {
            res += (j * (j - 1)) / 2;
        }
        return res;

    }

    // LEETCODE 442. Find All Duplicates in an Array

    public List<Integer> findDuplicates(int[] nums) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            int idx = Math.abs(nums[i]) - 1;
            if (nums[idx] < 0) {
                list.add(idx + 1);
            }
            nums[idx] = -nums[idx];
        }
        return list;
    }

    // LEETCODE 11. Container With Most Water

    public int maxArea(int[] height) {
        int max = Integer.MIN_VALUE;
        int i = 0;
        int j = height.length - 1;
        while (i < j) {
            int min = Math.min(height[i], height[j]);
            max = Math.max(max, min * (j - i));
            if (height[i] < height[j]) {
                i++;
            } else {
                j--;
            }
        }
        return max;
    }

    // LEETCODE 15. 3Sum

    public List<List<Integer>> threeSum(int[] nums) {
        HashSet<List<Integer>> set = new HashSet<>();
        Arrays.sort(nums);

        for (int i = 0; i < nums.length - 2; i++) {
            int low = i + 1;
            int high = nums.length - 1;
            while (low < high) {
                int sum = nums[i] + nums[low] + nums[high];
                if (sum == 0) {
                    set.add(List.of(nums[i], nums[low], nums[high]));
                }
                if (sum > 0)
                    high--;
                else
                    low++;
            }
        }
        return new ArrayList<>(set);
    }

    // LEETCODE 18. 4Sum
    // gives TLE

    public List<List<Integer>> fourSum(int[] nums, int target) {
        HashSet<List<Integer>> set = new HashSet<>();
        int low = 0, high = 0;
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 3; i++) {
            for (int j = i + 1; i < nums.length - 2; j++) {
                low = j + 1;
                high = nums.length - 1;
                while (low < high) {
                    int sum = nums[i] + nums[j] + nums[low] + nums[high];
                    if (sum == target) {
                        set.add(Arrays.asList(new Integer[] { nums[i], nums[j], nums[low], nums[high] }));
                        low++;
                        high--;
                    } else if (sum < target)
                        low++;
                    else
                        high--;
                }
            }
        }
        return new ArrayList<List<Integer>>(set);
    }

    // optimized solution

    class Solution {
        public List<List<Integer>> fourSum(int[] nums, int target) {
            int n = nums.length;
            Arrays.sort(nums);
            HashSet<ArrayList<Integer>> set = new HashSet<>();
            List<List<Integer>> res = new ArrayList<>();
            for (int i = 0; i < n - 3; i++) {
                for (int j = i + 1; j < n - 2; j++) {
                    int sum = nums[i] + nums[j];
                    int l = j + 1;
                    int r = n - 1;
                    while (l < r) {
                        if (sum + nums[l] + nums[r] == target) {
                            ArrayList<Integer> list = new ArrayList<>();
                            list.add(nums[i]);
                            list.add(nums[j]);
                            list.add(nums[l]);
                            list.add(nums[r]);
                            if (!set.contains(list)) {
                                res.add(list);
                                set.add(list);
                            }
                            l++;
                            r--;
                        } else if (sum + nums[l] + nums[r] < target) {
                            l++;
                        } else {
                            r--;
                        }
                    }
                }
            }
            return res;
        }
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

    // LEETCODE

    // LEETCODE
}
