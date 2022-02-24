// https://drive.google.com/drive/folders/1AfWvx6tOGN6WvZ_mzeUyvrWHU6kRUPc1
// ENV
package Level2.Recursion;

import java.util.*;

public class Class4 {

    // ----------------------------------PERMUTAION AND
    // COMBINATION--------------------------------
    public static int permutationInfiCoins(int[] arr, int tar, String ans) {
        if (tar == 0) {
            System.out.println(ans);
            return 1;
        }

        int count = 0;
        for (int ele : arr) {
            if (tar - ele >= 0)
                count += permutationInfiCoins(arr, tar - ele, ans + ele);
        }
        return count;
    }

    public static int combinationInfiCoins(int[] arr, int tar, int idx, String ans) {
        if (tar == 0) {
            System.out.println(ans);
            return 1;
        }
        int count = 0;
        for (int i = idx; i < arr.length; i++) {
            if (tar - arr[i] >= 0)
                count += combinationInfiCoins(arr, tar - arr[i], i, ans + arr[i]);
        }
        return count;
    }

    public static int permutationSingleCoins(int[] arr, int tar, String ans) {
        if (tar == 0) {
            System.out.println(ans);
            return 1;
        }
        int count = 0;
        for (int i = 0; i < arr.length; i++) {
            int val = arr[i];
            if (arr[i] > 0 && tar - arr[i] >= 0) {
                arr[i] = -val;
                count += permutationSingleCoins(arr, tar - val, ans + val);
                arr[i] = val;
            }
        }
        return count;
    }

    public static int combinationSingleCoins(int[] arr, int tar, int idx, String ans) {
        if (tar == 0) {
            System.out.println(ans);
            return 1;
        }
        int count = 0;
        for (int i = idx; i < arr.length; i++) {
            if (tar - arr[i] >= 0)
                count += combinationSingleCoins(arr, tar - arr[i], i + 1, ans + arr[i]);
        }
        return count;
    }

    public static int combinationSingleCoins_sub(int[] arr, int tar, int idx, String ans) {
        if (tar == 0 || idx == arr.length) {
            if (tar == 0) {
                System.out.println(ans);
                return 1;
            }
            return 0;
        }

        int count = 0;
        if (tar - arr[idx] >= 0) {
            count += combinationSingleCoins_sub(arr, tar - arr[idx], idx, ans + arr[idx]);
            count += combinationSingleCoins_sub(arr, tar, idx + 1, ans);
        }
        return count;
    }

    public static int combinationInfiCoins_sub(int[] arr, int tar, int idx, String ans) {
        if (tar == 0 || idx == arr.length) {
            if (tar == 0) {
                System.out.println(ans);
                return 1;
            }
            return 0;
        }
        int count = 0;

        if (tar - arr[idx] >= 0) {
            count += combinationInfiCoins_sub(arr, tar - arr[idx], idx, ans + arr[idx]);
            count += combinationInfiCoins_sub(arr, tar, idx + 1, ans);
        }
        return count;
    }

    public static int permutationInfiCoins_sub(int[] arr, int tar, int idx, String ans) {
        if (tar == 0 || idx == arr.length) {
            if (tar == 0) {
                System.out.println(ans);
                return 1;
            }
            return 0;
        }
        int count = 0;

        if (tar - arr[idx] >= 0) {
            count += permutationInfiCoins_sub(arr, tar - arr[idx], 0, ans + arr[idx]);
            count += permutationInfiCoins_sub(arr, tar, idx + 1, ans);
        }
        return count;
    }

    public static int permutationSingleCoins_sub(int[] arr, int tar, int idx, String ans) {
        if (tar == 0 || idx == arr.length) {
            if (tar == 0) {
                System.out.println(ans);
                return 1;
            }
            return 0;
        }
        int count = 0;

        if (arr[idx] > 0 && tar - arr[idx] >= 0) {
            int val = arr[idx];
            arr[idx] = -val;
            count += permutationSingleCoins_sub(arr, tar - val, 0, ans + val);
            arr[idx] = val;
        }
        count += permutationSingleCoins_sub(arr, tar, idx + 1, ans);
        return count;
    }

    public static void combinationPermutation() {
        int[] arr = { 2, 3, 5, 7 };
        int tar = 10;
        String ans = "";

        System.out.println(permutationInfiCoins(arr, tar, ans));
    }

    // -----------------------------QUEENS------------------------------
    // tnb : total no of boxes, tnq : total no queens, bno : box number, qmo : queen
    // number

    public static int queenCombination(int tnb, int tnq, int bno, int qno, String ans) {
        if (qno == tnq) {
            System.out.println(ans);
            return 1;
        }

        int count = 0;
        for (int i = bno; i < tnb; i++) {
            count += queenCombination(tnb, tnq, i + 1, qno + 1, ans + "b" + i + "q" + qno + " ");
        }

        // count += queenCombination(tnb, tnq, bno + 1, qno, ans);

        return count;
    }

    public static int queenPermutation(Boolean[] boxes, int tnq, int bno, int qno, String ans) {
        if (qno == tnq) {
            System.out.println(ans);
            return 1;
        }

        int count = 0;
        for (int i = bno; i < boxes.length; i++) {
            if (!boxes[i]) {
                boxes[i] = true;
                count += queenPermutation(boxes, tnq, 0, qno + 1, ans + "b" + i + "q" + qno + " ");
                boxes[i] = false;
            }
        }

        return count;
    }

    public static int queenCombination2D(Boolean[][] board, int tnq, int bno, String ans) {
        if (tnq == 0) {
            System.out.println(ans);
            return 1;
        }

        int count = 0, n = board.length, m = board[0].length;
        for (int i = bno; i < n * m; i++) {
            int r = i / m;
            int c = i % m;
            count += queenCombination2D(board, tnq - 1, i + 1, ans + "(" + r + "," + c + ") ");
        }

        return count;
    }

    public static int queenPermutation2D(Boolean[][] board, int tnq, int bno, String ans) {
        if (tnq == 0) {
            System.out.println(ans);
            return 1;
        }

        int count = 0, n = board.length, m = board[0].length;
        for (int i = bno; i < n * m; i++) {
            int r = i / m;
            int c = i % m;
            if (!board[r][c]) {
                board[r][c] = true;
                count += queenPermutation2D(board, tnq - 1, 0, ans + "(" + r + "," + c + ") ");
                board[r][c] = false;
            }
        }

        return count;
    }

    public static boolean isSafeToPlaceQueen_C(Boolean[][] board, int row, int col) {

        int[][] dir = { { 0, -1 }, { -1, -1 }, { -1, 0 }, { -1, 1 } };

        int n = board.length, m = board[0].length;
        for (int d = 0; d < n; d++) {
            for (int rad = 1; rad < board.length; rad++) {
                int r = row + rad * dir[d][0];
                int c = col + rad * dir[d][1];

                if (r >= 0 && c >= 0 && r < n && c < m) {
                    if (board[r][c])
                        return false;
                } else
                    break;
            }
        }
        return true;
    }

    public static int nqueen_01_combination(Boolean[][] board, int tnq, int idx, String ans) {
        if (tnq == 0) {
            System.out.println(ans);
            return 1;
        }

        int count = 0, n = board.length, m = board[0].length;
        for (int i = idx; i < n * m; i++) {
            int r = i / m;
            int c = i % m;
            if (isSafeToPlaceQueen_C(board, r, c)) {
                board[r][c] = true;
                count += nqueen_01_combination(board, tnq - 1, i + 1, ans + "(" + r + "," + c + ") ");
                board[r][c] = false;
            }
        }

        return count;

    }

    public static int nqueen_01_combination_sub(Boolean[][] board, int tnq, int idx, String ans) {

        int count = 0, n = board.length, m = board[0].length;
        if (tnq == 0 || idx == n * m) {
            if (tnq == 0) {
                System.out.println(ans);
            }
            return tnq == 0 ? 1 : 0;
        }
        int r = idx / m;
        int c = idx % m;
        if (isSafeToPlaceQueen_C(board, r, c)) {
            board[r][c] = true;
            count += nqueen_01_combination_sub(board, tnq - 1, idx + 1, ans + "(" + r + "," + c + ") ");
            board[r][c] = false;
        }
        count += nqueen_01_combination_sub(board, tnq, idx + 1, ans);

        return count;

    }

    static boolean[] row;
    static boolean[] col;
    static boolean[] diag;
    static boolean[] aDiag;

    int calls = 0;

    public static int nqueen_02_combi(int n, int m, int tnq, int idx, String ans) {
        if (tnq == 0) {
            System.out.println(ans);
            return 1;
        }

        int count = 0;
        // calls++;
        for (int i = idx; i < n * m; i++) {
            int r = i / m;
            int c = i % m;
            if (!row[r] && !col[c] && !diag[r + c] && !aDiag[r - c + m - 1]) {
                row[r] = col[c] = diag[r + c] = aDiag[r - c + m - 1] = true;
                count += nqueen_02_combi(n, m, tnq - 1, i + 1, ans + "(" + r + "," + c + ") ");
                row[r] = col[c] = diag[r + c] = aDiag[r - c + m - 1] = false;
            }
        }

        return count;
    }

    public static int nqueen_03_combi(int n, int m, int tnq, int floor, String ans) {
        if (tnq == 0) {
            System.out.println(ans);
            return 1;
        }

        int count = 0;
        // calls++;
        for (int room = 0; room < m; room++) {
            int r = floor, c = room;
            if (!row[r] && !col[c] && !diag[r + c] && !aDiag[r - c + m - 1]) {
                row[r] = col[c] = diag[r + c] = aDiag[r - c + m - 1] = true;
                count += nqueen_03_combi(n, m, tnq - 1, floor + 1, ans + "(" + r + "," + c + ") ");
                row[r] = col[c] = diag[r + c] = aDiag[r - c + m - 1] = false;
            }
        }

        return count;
    }

    static int cols = 0;
    static int diags = 0;
    static int aDiags = 0;

    public static int nqueen_04_combi(int n, int floor) {
        if (floor == n) {
            return 1;
        }

        int count = 0, m = n;
        for (int room = 0; room < n; room++) {
            int r = floor, c = room;
            if ((cols & (1 << c)) == 0 && (diags & (1 << (r + c))) == 0 && (aDiags & (1 << (r - c + m - 1))) == 0) {
                cols ^= (1 << c);
                diags ^= (1 << (r + c));
                aDiags ^= (1 << (r - c + m - 1));

                count += nqueen_04_combi(n, floor + 1);

                cols ^= (1 << c);
                diags ^= (1 << (r + c));
                aDiags ^= (1 << (r - c + m - 1));
            }
        }

        return count;
    }

    public static boolean isSafeToPlaceQueen_P(Boolean[][] board, int row, int col) {

        int[][] dir = { { 0, -1 }, { -1, -1 }, { -1, 0 }, { -1, 1 }, { 0, 1 }, { 1, 1 }, { 1, 0 }, { 1, -1 } };
        int n = board.length, m = board[0].length;
        for (int d = 0; d < dir.length; d++) {
            for (int rad = 1; rad < board.length; rad++) {
                int r = row + rad * dir[d][0];
                int c = col + rad * dir[d][1];

                if (r >= 0 && c >= 0 && r < n && c < m) {
                    if (board[r][c])
                        return false;
                } else
                    break;
            }
        }
        return true;
    }

    public static int nqueen_01_permutation(Boolean[][] board, int tnq, int idx, String ans) {
        if (tnq == 0) {
            System.out.println(ans);
            return 1;
        }

        int count = 0, n = board.length, m = board[0].length;
        for (int i = idx; i < n * m; i++) {
            int r = i / m;
            int c = i % m;
            if (isSafeToPlaceQueen_P(board, r, c)) {
                board[r][c] = true;
                count += nqueen_01_permutation(board, tnq - 1, 0, ans + "(" + r + "," + c + ") ");
                board[r][c] = false;
            }
        }

        return count;

    }

}
