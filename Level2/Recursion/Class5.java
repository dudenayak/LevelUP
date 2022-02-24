package Level2.Recursion;

import java.util.*;

public class Class5 {
    class Solution {
        boolean[] row;
        boolean[] col;
        boolean[] diag;
        boolean[] aDiag;

        public int nqueen_03_combi(int n, int floor) {
            if (floor == 0) {
                return 1;
            }

            int count = 0;
            for (int room = 0; room < n; room++) {
                int r = floor, c = room;
                if (!row[r] && !col[c] && !diag[r + c] && !aDiag[r - c + n - 1]) {
                    row[r] = col[c] = diag[r + c] = aDiag[r - c + n - 1] = true;
                    count += nqueen_03_combi(n, floor + 1);
                    row[r] = col[c] = diag[r + c] = aDiag[r - c + n - 1] = false;
                }
            }

            return count;
        }

        public int totalNQueens(int n) {
            row = new boolean[n];
            col = new boolean[n];
            diag = new boolean[2 * n - 1];
            aDiag = new boolean[2 * n - 1];
            return nqueen_03_combi(n, 0);
        }
    }

    // combination sum ii
    private void findCombinations(int index, int[] array, int target, Set<List<Integer>> ans, List<Integer> ds) {

        if (index == array.length) {
            if (target == 0) {
                ans.add(new ArrayList<>(ds));

            }
            return;
        }

        if (array[index] <= target) {
            ds.add(array[index]);

            findCombinations(index + 1, array, target - array[index], ans, ds);
            ds.remove(ds.size() - 1);
        }
        findCombinations(index + 1, array, target, ans, ds);
    }

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {

        Set<List<Integer>> ans = new HashSet<>();
        Arrays.sort(candidates);
        findCombinations(0, candidates, target, ans, new ArrayList<>());

        List<List<Integer>> op = new ArrayList<>();
        for (List a : ans) {
            op.add(a);
        }
        return op;
    }

    // coin change
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

    // n queens
    public List<List<String>> solveNQueens(int n) {

        boolean[][] board = new boolean[n][n];

        List<List<String>> list = new ArrayList<>(queen("", board, 0));
        return list;

    }

    public static List<List<String>> queen(String p, boolean[][] board, int row) {
        if (row == board.length) {
            List<List<String>> list = new ArrayList<>();
            list.add(addQ(board));
            return list;
        }

        List<List<String>> list = new ArrayList<>();

        for (int col = 0; col < board.length; col++) {
            if (isSafe(board, row, col)) {
                board[row][col] = true;
                list.addAll(queen(p, board, row + 1));
                board[row][col] = false;
            }
        }
        return list;
    }

    private static List<String> addQ(boolean[][] board) {
        List<String> list = new ArrayList<>();

        for (boolean[] arr : board) {
            StringBuilder s = new StringBuilder();
            for (boolean element : arr) {
                if (element)
                    s = s.append('Q');
                else
                    s = s.append('.');
            }
            list.add(s.toString());
        }
        return list;
    }

    private static boolean isSafe(boolean[][] board, int row, int col) {
        // vertical checkUP
        for (int i = 0; i < row; i++) {
            if (board[i][col])
                return false;
        }
        // check Diagonal left
        int maxLeft = Math.min(row, col);
        for (int i = 0; i <= maxLeft; i++) {
            if (board[row - i][col - i])
                return false;
        }
        // check Diagonal Right
        int maxRight = Math.min(row, board.length - 1 - col);
        for (int i = 0; i <= maxRight; i++)

        {
            if (board[row - i][col + i])
                return false;
        }
        return true;
    }
}
