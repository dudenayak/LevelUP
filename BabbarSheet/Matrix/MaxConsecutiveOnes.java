package BabbarSheet.Matrix;

public class MaxConsecutiveOnes {

    // 1
    public int findMaxConsecutiveOnes(int[] nums) {
        int maxOnes = 0;
        int onesCount = 0;

        for (int i : nums) {
            if (i == 1) {
                onesCount++;
                maxOnes = Math.max(maxOnes, onesCount);

            } else {
                onesCount = 0;
            }

        }

        return maxOnes;

    }

    // 2
    int rowWithMax1s(int arr[][], int n, int m) {
        int col = m - 1;
        int row = -1;

        for (int i = 0; i < n; i++) {
            for (int j = col; j >= 0; j--) {
                if (arr[i][j] == 1) {
                    row = i;
                    col--;
                } else {
                    break;
                }
            }
        }
        return row;
    }

    // 3
    public int longestOnes(int[] nums, int k) {
        int i = 0, j = 0, max = 0, z = 0, n = nums.length;
        while (j < n) {
            if (z <= k) {
                max = Math.max(max, j - i);
                if (nums[j++] == 0)
                    z++;
            } else {
                if (nums[i++] == 0)
                    z--;
            }
        }
        if (z <= k)
            max = Math.max(max, j - i);
        return max;
    }
}
