package Questions_Sheet;

import Foundation.HashMap;

public class DynamicProgramming {

    // LEETCODE 70. Climbing Stairs
    public int climbStairs(int n) {
        int a = 1, b = 1;
        for (int i = 2; i <= n; i++) {
            int sum = a + b;
            a = b;
            b = sum;
        }

        return b;
    }

    // GFG Maximize The Cut Segments

    public int maximizeCuts(int n, int x, int y, int z) {

        int dp[] = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            dp[i] = Integer.MIN_VALUE;
        }
        dp[0] = 0;
        int[] arr = new int[3];
        arr[0] = x;
        arr[1] = y;
        arr[2] = z;

        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < 3; j++) {
                if (arr[j] <= i) {
                    int sub_res = dp[i - arr[j]];
                    if (sub_res != Integer.MIN_VALUE && sub_res + 1 > dp[i]) {
                        dp[i] = sub_res + 1;
                    }
                }
            }
        }
        return dp[n] == Integer.MIN_VALUE ? 0 : dp[n];

    }

    // LEETCODE 152. Maximum Product Subarray

    public int maxProduct(int[] nums) {
        int max = nums[0], min = nums[0], ans = nums[0];
        int n = nums.length;
        for (int i = 1; i < n; i++) {
            if (nums[i] < 0) {
                int temp = max;
                max = min;
                min = temp;
            }
            max = Math.max(nums[i], max * nums[i]);
            min = Math.min(nums[i], min * nums[i]);
            ans = Math.max(ans, max);
        }
        return ans;
    }

    // LEETCODE 474. Ones and Zeroes

    int[][] dp;

    public int findMaxForm(String[] strs, int m, int n) {
        dp = new int[m + 1][n + 1];
        for (String s : strs) {

            int[] count = count(s);
            for (int zeroes = m; zeroes >= count[0]; zeroes--) {
                for (int ones = n; ones >= count[1]; ones--) {
                    dp[zeroes][ones] = Math.max(dp[zeroes - count[0]][ones - count[1]] + 1, dp[zeroes][ones]);
                }
            }
        }
        return dp[m][n];
    }

    private static int[] count(String str) {
        int[] count = new int[2];
        for (char c : str.toCharArray()) {
            count[c - '0']++;
        }
        return count;
    }

    // LEETCODE 338. Counting Bits

    public int[] countBits(int n) {
        int[] ans = new int[n + 1];
        ans[0] = 0;
        for (int i = 1; i <= n; i++) {
            ans[i] = (i & 1) + ans[i / 2];
        }
        return ans;
    }

    // LEETCODE 935. Knight Dialer

    final int LIMIT = (int) Math.pow(10, 9) + 7;

    final int[][] moves = new int[][] {
            { 4, 6 }, // 0
            { 6, 8 }, // 1
            { 7, 9 }, // 2
            { 4, 8 }, // 3
            { 3, 9, 0 }, // 4
            {}, // 5
            { 1, 7, 0 }, // 6
            { 2, 6 }, // 7
            { 1, 3 }, // 8
            { 2, 4 }, // 9
    };

    public int knightDialer(int n) {
        long[] prev = new long[10];
        for (int i = 0; i < 10; i++) {
            prev[i] = 1;
        }

        for (int i = 1; i < n; i++) {
            long[] curr = new long[10];
            for (int v = 0; v < 10; v++) {
                for (int u : moves[v]) {
                    curr[u] = (curr[u] + prev[v]) % LIMIT;
                }
            }
            prev = curr;
        }

        long total = 0;
        for (int i = 0; i < 10; i++) {
            total = (total + prev[i]) % LIMIT;
        }
        return (int) (total % LIMIT);
    }

    // LEETCODE 62. Unique Path

    public static int mazePath(int SR, int SC, int ER, int EC, int[][] dp, int[][] dir) {
        for (int sr = ER; sr >= SR; sr--) {
            for (int sc = EC; sc >= SC; sc--) {
                if (ER == sr && EC == sc) {
                    dp[sr][sc] = 1;
                    continue;
                }

                int count = 0;
                for (int[] d : dir) {
                    int r = sr + d[0], c = sc + d[1];
                    if (r >= 0 && c >= 0 && r < dp.length && c < dp[0].length) {
                        count += dp[r][c];// mazePath_memo(r, c, er, ec, dp, dir);
                    }
                }

                dp[sr][sc] = count;
            }
        }

        return dp[SR][SC];
    }

    public int uniquePaths(int m, int n) {
        int[][] dp = new int[n][m];
        int[][] dir = { { 1, 0 }, { 0, 1 } };
        return mazePath(0, 0, n - 1, m - 1, dp, dir);

    }

    // LEETCODE 64. Minimum Path Sum

    public int minPathSum(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        int[][] dp = new int[n][m];
        for (int i = dp.length - 1; i >= 0; i--) {
            for (int j = dp[0].length - 1; j >= 0; j--) {

                if (i == dp.length - 1 && j == dp[0].length - 1) {
                    dp[i][j] = grid[i][j];
                } else if (i == dp.length - 1) {
                    dp[i][j] = dp[i][j + 1] + grid[i][j];
                } else if (j == dp[0].length - 1) {
                    dp[i][j] = dp[i + 1][j] + grid[i][j];
                } else {
                    dp[i][j] = Math.min(dp[i + 1][j], dp[i][j + 1]) + grid[i][j];
                }
            }
        }
        return dp[0][0];
    }

    // LEETCODE 322. Coin Change

    public int coinChange(int[] coins, int val) {
        int[] dp = new int[val + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;

        for (int i = 1; i < dp.length; i++) {
            for (int j = 0; j < coins.length; j++) {
                if (coins[j] <= i) {
                    int subres = dp[i - coins[j]];

                    if (subres != Integer.MAX_VALUE) {
                        dp[i] = Math.min(subres + 1, dp[i]);
                    }
                }
            }
        }

        if (dp[val] == Integer.MAX_VALUE) {
            return -1;
        }
        return dp[val];
    }

    // LEETCODE 91. Decode Ways

    public static int numDecodings(String s, int IDX, int[] dp) {
        for (int idx = s.length(); idx >= 0; idx--) {
            if (idx == s.length()) {
                dp[idx] = 1;
                continue;
            }

            char ch = s.charAt(idx);
            if (ch == '0') {
                dp[idx] = 0;
                continue;
            }
            int count = dp[idx + 1];

            if (idx < s.length() - 1) {
                char ch1 = s.charAt(idx + 1);
                int num = (ch - '0') * 10 + (ch1 - '0');
                if (num <= 26)
                    count += dp[idx + 2];
            }
            dp[idx] = count;
        }
        return dp[IDX];
    }

    public int numDecodings(String s) {
        int n = s.length();
        int[] dp = new int[n + 1];
        Arrays.fill(dp, -1);
        return numDecodings(s, 0, dp);
    }

    // LEETCODE 718. Maximum Length of Repeated Subarray

    public int findLength(int[] nums1, int[] nums2) {
        int ans = 0;
        int n = nums1.length, m = nums2.length;
        int[][] dp = new int[n + 1][m + 1];

        for (int i = n - 1; i >= 0; i--) {
            for (int j = m - 1; j >= 0; j--) {
                if (nums1[i] == nums2[j]) {
                    dp[i][j] = dp[i + 1][j + 1] + 1;
                    if (ans < dp[i][j])
                        ans = dp[i][j];
                }
            }
        }
        return ans;
    }

    // LEETCODE 300. Longest Increasing Subsequence

    public int lengthOfLIS(int[] nums) {
        int[] dp = new int[nums.length];
        Arrays.fill(dp, 1);
        int ans = dp[0];
        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[j] + 1, dp[i]);
                }
            }
            ans = Math.max(ans, dp[i]);
        }
        return ans;
    }

    // GFG Longest Common Substring

    int longestCommonSubstr(String S1, String S2, int n, int m) {
        return LCS(S1.toCharArray(), S2.toCharArray(), n, m);
    }

    int LCS(char[] s1, char[] s2, int n, int m) {
        int[][] matrix = new int[n + 1][m + 1];

        int lcs = 0;
        int row = 0, col = 0;
        int curr = 0;
        for (int i = 0; i < n; i++) {
            row = i + 1;
            for (int j = 0; j < m; j++) {
                col = j + 1;

                if (s1[i] == s2[j]) {
                    curr = matrix[row - 1][col - 1] + 1;
                    matrix[row][col] = curr;
                    lcs = Math.max(lcs, curr);
                }
            }
        }
        return lcs;
    }

    // LEETCODE 1277. Count Square Submatrices with All Ones

    public int countSquares(int[][] matrix) {
        int row = matrix.length;
        int col = matrix[0].length;
        int count = 0;

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (i > 0 && j > 0) {
                    if (matrix[i][j] == 1 && matrix[i - 1][j] > 0 && matrix[i][j - 1] > 0 && matrix[i - 1][j - 1] > 0) {
                        int temp = Math.min(matrix[i - 1][j], matrix[i][j - 1]);
                        matrix[i][j] += Math.min(temp, matrix[i - 1][j - 1]);
                    }
                }
                count += matrix[i][j];
            }
        }
        return count;
    }

    // LEETCODE 221. Maximal Square

    public int maximalSquare(char[][] matrix) {
        int row = matrix.length, col = row > 0 ? matrix[0].length : 0;
        int[][] dp = new int[row + 1][col + 1];
        int max = 0;
        for (int i = 1; i <= row; i++) {
            for (int j = 1; j <= col; j++) {
                if (matrix[i - 1][j - 1] == '1') {
                    dp[i][j] = Math.min(Math.min(dp[i][j - 1], dp[i - 1][j]), dp[i - 1][j - 1]) + 1;
                    max = Math.max(max, dp[i][j]);
                }
            }
        }
        return max * max;
    }

    // GFG Mobile numeric keypad

    public long getCount(int N) {
        int paths[][] = { { 8, 0 }, { 1, 2, 4 }, { 1, 2, 3, 5 }, { 2, 6, 3 }, { 1, 4, 5, 7 }, { 2, 4, 5, 6, 8 },
                { 3, 5, 9, 6 }, { 4, 7, 8 }, { 7, 5, 8, 0, 9 }, { 8, 6, 9 } };
        long dp[][] = new long[N + 1][10];
        for (int i = 1; i <= N; i++) {
            for (int j = 0; j <= 9; j++) {
                if (i == 1)
                    dp[i][j] = 1;
                else {
                    for (int path : paths[j])
                        dp[i][j] += dp[i - 1][path];
                }
            }
        }

        long ans = 0;
        for (int j = 0; j < 10; j++)
            ans += dp[N][j];

        return ans;
    }

    // LEETCODE 740. Delete and Earn

    public int deleteAndEarn(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int max = 0;

        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + num);
            max = Math.max(max, num);
        }

        int[] maxPoints = new int[max + 1];
        maxPoints[1] = map.getOrDefault(1, 0);

        for (int num = 2; num < maxPoints.length; num++) {

            int gain = map.getOrDefault(num, 0);
            maxPoints[num] = Math.max(maxPoints[num - 1], maxPoints[num - 2] + gain);
        }

        return maxPoints[max];
    }

    // LEETCODE 304. Range Sum Query 2D - Immutable

    private int[][] dp;

    public NumMatrix(int[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0) return;
        dp = new int[matrix.length + 1][matrix[0].length + 1];
        for (int r = 0; r < matrix.length; r++) {
            for (int c = 0; c < matrix[0].length; c++) {
                dp[r + 1][c + 1] = dp[r + 1][c] + dp[r][c + 1] + matrix[r][c] - dp[r][c];
            }
        }
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        return dp[row2 + 1][col2 + 1] - dp[row1][col2 + 1] - dp[row2 + 1][col1] + dp[row1][col1];
    }

}
