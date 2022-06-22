package Questions_Sheet;

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
}
