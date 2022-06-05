package Questions_Sheet;

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

    // LEETCODE

    // LEETCODE

    // LEETCODE

    // LEETCODE

    // LEETCODE

    // LEETCODE
}
