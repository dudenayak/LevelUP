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

}

// Q39. COMBINATION SUM

class Solution {

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

}

// Q40. COMBINATION SUM 2

class Solution {

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

 }

    // Q37. SUDOKU

public static boolean isSafeToPlaceNumber(char[][] board, int r,int c, int num ) {
    //row
    for(int i=0;i<9;i++){
        if(board[r][i] - '0' == num)
        return false;
    }

    //col
    for(int i=0;i<9;i++){
        if(board[i][c] - '0' == num)
        return false;

    //mat
    r = (r/3) * 3;
    c = (c/3) * 3;
    for(int i=0;i<9;i++){
        for(int j=0;j<9;j++){
            if(board[r+i][c+j] - '0' == num)
            return false;
        }
    }
    return true;
}

}
