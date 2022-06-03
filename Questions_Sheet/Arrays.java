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

    }

}
