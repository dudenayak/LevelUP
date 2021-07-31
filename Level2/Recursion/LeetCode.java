package Level2.Recursion;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

// Q52. N-Queens II
// n*n matrix && tnq = n && store answer

class Solution {
    public int totalNQueens(int n) {

        boolean[] row = new boolean[n];
        boolean[] col = new boolean[n];
        boolean[] diag = new boolean[n + n - 1];
        boolean[] adiag = new boolean[n + n - 1];
        int ans = totalNQueens(n, row, col, diag, adiag, 0);
        return ans;
    }

    public static int totalNQueens(int n, boolean[] row, boolean[] col, boolean[] diag, boolean[] adiag, int r) {

        if (r == n) {
            return 1;
        }
        int count = 0;
        for (int c = 0; c < n; c++) {
            if (!row[r] && !col[c] && !diag[r + c] && !adiag[r - c + n - 1]) {
                row[r] = col[c] = diag[r + c] = adiag[r - c + n - 1] = true;
                count += totalNQueens(n, row, col, diag, adiag, r + 1);
                row[r] = col[c] = diag[r + c] = adiag[r - c + n - 1] = false;

            }

        }
        return count;

    }

    // Q39. COMBINATION SUM

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> asf = new ArrayList<>();
        combinationSum(candidates, 0, target, ans, asf);
        return ans;
    }

    public void combinationSum(int[] arr, int idx, int target, List<List<Integer>> ans, List<Integer> asf) {
        if (target == 0 || idx == arr.length) {
            if (target == 0) {
                List<Integer> base = new ArrayList<>(asf);
                ans.add(base);
            }
            return;
        }

        if (target - arr[idx] >= 0) {
            asf.add(arr[idx]);
            combinationSum(arr, idx, target - arr[idx], ans, asf);
            asf.remove(asf.size() - 1);
        }
        combinationSum(arr, idx + 1, target, ans, asf);

    }

    // Q40. COMBINATION SUM 2

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> asf = new ArrayList<>();
        Arrays.sort(candidates); // so that same answer wont repeat
        combinationSum2(candidates, 0, target, ans, asf);
        return ans;
    }

    public void combinationSum2(int[] arr, int idx, int target, List<List<Integer>> ans, List<Integer> asf) {
        if (target == 0) {

            List<Integer> base = new ArrayList<>(asf);
            ans.add(base);
            return;
        }

        int prev = -1; // to check if previous element is -1 or not
        for (int i = idx; i < arr.length; i++) { // (repetition check)
            if (prev != arr[i] && target - arr[i] >= 0) {
                asf.add(arr[i]);
                combinationSum2(arr, i + 1, target - arr[i], ans, asf); // (i+1 because we're using candidate only once)
                asf.remove(asf.size() - 1);
                prev = arr[i];
            }
        }

    }

    // Q322. COIN CHANGE(TLE)

    public int coinChange(int[] coins, int amount) {
        if (amount == 0)
            return 0;
        int minAns = (int) 1e9;
        for (int ele : coins) {
            if (amount - ele >= 0) {
                int recAns = coinChange(coins, amount - ele);
                if (recAns != (int) 1e9 && recAns + 1 < minAns) {
                    minAns = recAns + 1;
                }
            }
        }
        return minAns;

    }

    public int coinChange_(int[] coins, int amount) {
        int ans = coinChange(coins, amount);
        return ans != (int) 1e9 ? ans : -1;
    }

    // Q191. NUMBER OF 1 BITS

    // you need to treat n as an unsigned value
    public int hammingWeight(int n) {
        int count = 0;
        while (n != 0) {
            if ((n & 1) != 0)
                count++; // n &1 is telling if previous number is 1 or 0
            n >>>= 1;
        }
        return count;

    }

    // Q338. COUNTING BITS

    public int[] countBits(int n) {

        int[] ans = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            ans[i] = ans[i & (i - 1)] + 1;
        }
        return ans;
    }

    // Q231. POWER OF TWO

    public boolean isPowerOfTwo(int n) {
        return n > 0 && (n & (n - 1)) == 0;

    }

    // Q342. POWER OF FOUR

    public boolean isPowerOfFour(int n) {
        if (n <= 0 || (n & (n - 1)) != 0)
            return false;

        int count = 0;
        while (n != 0) {
            if ((n & 1) == 0)
                count++;
            n >>>= 1;
        }
        return (count & 1) == 0;

    }

    // Q136. SINGLE NUMBER

    public int singleNumber(int[] nums) {
        int ans = 0;
        for (int ele : nums)
            ans ^= ele; // ^ = XOR
        return ans;

    }

    // // Q37. SUDOKU

    // public static boolean isSafeToPlaceNumber(char[][] board, int r, int c, int
    // num) {
    // // row
    // for (int i = 0; i < 9; i++) {
    // if (board[r][i] - '0' == num)
    // return false;
    // }

    // // col
    // for (int i = 0; i < 9; i++) {
    // if (board[i][c] - '0' == num)
    // return false;

    // // mat
    // r = (r / 3) * 3;
    // c = (c / 3) * 3;
    // for (int i = 0; i < 9; i++) {
    // for (int j = 0; j < 9; j++) {
    // if (board[r + i][c + j] - '0' == num)
    // return false;
    // }
    // }
    // return true;
    // }

    // }

}
