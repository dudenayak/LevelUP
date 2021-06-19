// QUESTION 52. N-Queens II
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
}