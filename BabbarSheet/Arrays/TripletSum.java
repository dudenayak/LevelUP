package BabbarSheet.Arrays;

import java.util.*;

public class TripletSum {

    // 1
    public static List<List<Integer>> threeSum(int[] nums) {

        List<List<Integer>> res = new ArrayList<>();

        Arrays.sort(nums);

        int len = nums.length;

        if (len < 3 || nums[0] > 0 || nums[len - 1] < 0) {
            return res;
        }

        for (int i = 0; i < len; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }

            int left = i + 1;
            int right = len - 1;

            while (left < right) {
                int sum = nums[left] + nums[right] + nums[i];

                if (sum == 0) {
                    res.add(Arrays.asList(nums[left], nums[i], nums[right]));

                    while (left < right && nums[left] == nums[left + 1]) {
                        left++;
                    }
                    while (left < right && nums[right] == nums[right - 1]) {
                        right--;
                    }

                    left++;
                    right--;

                } else if (sum > 0) {
                    right--;
                } else {
                    left++;
                }
            }
        }
        return res;
    }

    // 2
    public int countGoodTriplets(int[] arr, int a, int b, int c) {
        int i, j, k, count = 0;
        for (i = 0; i < (arr.length) - 2; i++)
            for (j = i + 1; j < (arr.length) - 1; j++)
                if (Math.abs(arr[i] - arr[j]) <= a)
                    for (k = j + 1; k < arr.length; k++) {
                        if (Math.abs(arr[j] - arr[k]) <= b)
                            if (Math.abs(arr[i] - arr[k]) <= c)
                                count++;
                    }
        return count;
    }
}
