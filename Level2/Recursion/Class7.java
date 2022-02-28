package Level2.Recursion;

import java.util.*;

public class Class7 {

    // 37. Sudoku Solver
    public static boolean isSafeToPlaceNumber(char[][] board, int r, int c, int num) {
        // row
        for (int i = 0; i < 9; i++) {
            if (board[r][i] - '0' == num)
                return false;
        }

        // col
        for (int i = 0; i < 9; i++) {
            if (board[i][c] - '0' == num)
                return false;
        }

        // mat
        r = (r / 3) * 3;
        c = (c / 3) * 3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[r + i][c + j] - '0' == num)
                    return false;
            }
        }
        return true;
    }

    public boolean solveSudoku(char[][] board, ArrayList<Integer> emptyIndex, int idx) {

        if (idx == emptyIndex.size()) {
            return true;
        }

        int cell = emptyIndex.get(idx);
        int r = cell / 9;
        int c = cell % 9;

        for (int num = 1; num <= 9; num++) {
            if (isSafeToPlaceNumber(board, r, c, num)) {
                board[r][c] = (char) (num + '0');
                if (solveSudoku(board, emptyIndex, idx + 1))
                    return true;
                board[r][c] = '.';
            }
        }
        return false;
    }

    public void solveSudoku(char[][] board) {

        ArrayList<Integer> emptyIndex = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] == '.')
                    emptyIndex.add(i * 9 + j); // (r,c) = idx // idx = r * m + c
            }
        }
        solveSudoku(board, emptyIndex, 0);
    }

    // 36.Valid Sudoku
    public boolean isValidSudoku(char[][] board) {
        int[] row = new int[9];
        int[] col = new int[9];
        int[][] mat = new int[3][3];

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] != '.') {
                    int mask = 1 << (board[i][j] - '0');
                    if ((row[i] & mask) == 0 && (col[j] & mask) == 0 && (mat[i / 3][j / 3] & mask) == 0) {
                        row[i] ^= mask;
                        col[j] ^= mask;
                        mat[i / 3][j / 3] ^= mask;
                    } else
                        return false;

                }
            }
        }

        return true;
    }

    public static int wordBreak(String str, String ans, HashSet<String> dict, int longestWordLen) {
        if (str.length() == 0) {
            System.out.println(ans);
            return 1;
        }

        int count = 0;
        for (int i = 0; i < str.length(); i++) {
            String nstr = str.substring(0, i + 1);
            if (nstr.length() > longestWordLen)
                break;
            if (dict.contains(nstr)) {
                count += wordBreak(str.substring(i + 1), ans + nstr + " ", dict, longestWordLen);
            }
        }

        return count;
    }

    public static int friendsPairing(int n, String ans, boolean[] used) {
        int idx = 0; // first Unsed Friend
        while (idx <= n) {
            if (!used[idx])
                break;
            idx++;
        }

        if (idx > n) {
            System.out.println(ans);
            return 1;
        }

        int count = 0;
        used[idx] = true;
        count += friendsPairing(n, ans + "(" + idx + ") ", used); // single

        for (int i = idx + 1; i <= n; i++) {
            if (!used[i]) {
                String friend = "(" + idx + "" + i + ") ";
                used[i] = true;
                count += friendsPairing(n, ans + friend, used); // pairing
                used[i] = false;
            }
        }

        used[idx] = false;

        return count;

    }

    // 179. Largest Number
    class Solution {
        public String largestNumber(int[] nums) {
            String[] arr = new String[nums.length];
            int idx = 0;
            for (int ele : nums)
                arr[idx++] = ele + "";
            Arrays.sort(arr, (a, b) -> {
                String s1 = a + "" + b;
                String s2 = b + "" + a;
                return s2.compareTo(s1);
            });

            StringBuilder sb = new StringBuilder();
            for (String ele : arr) {
                sb.append(ele);
            }
            if (sb.charAt(0) == '0')
                return "0";
            return sb.toString();

        }
    }

}
