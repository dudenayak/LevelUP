package Recursion;
// nqueens(combination)

// import java.io;
// import java.util;

public class Queens {

    public static void main(String[] args) throws Exception {
        int tnq = 4;
        boolean vis[][] = new boolean[4][4];
        vis[0][0] = false;

        // there is no need to initialize boolean with false becuase java by default
        // initilaizes new boolean with a false value(this step is optional)
        System.out.println(nqueen_comb(vis, tnq, 0, ""));
        System.out.println(nqueen_perm(vis, tnq, 0, ""));
        System.out.println(Queen_comb_sub(vis, tnq, 0, ""));
        System.out.println(perm_comb_sub(vis, tnq, 0, ""));

        // for perm_queen_optimized1 & comb_queen_optimized1---------
        // Scanner scn = new Scanner(System.in);
        int n = 4;
        int m = 4;
        // int tnq = 4;
        boolean[] row = new boolean[n];
        boolean[] col = new boolean[m];
        boolean[] diag = new boolean[n + m - 1];
        boolean[] adiag = new boolean[n + m - 1];
        System.out.println(comb_queen_optimized1(n, m, tnq, 0, row, col, diag, adiag, ""));
        System.out.println(perm_queen_optimized1(n, m, tnq, 0, row, col, diag, adiag, ""));
    }

    // ----------------------------IS SAFE TO PLACE---------------------------------

    public static boolean isSafeToPlaceQueen(boolean[][] vis, int row, int col) {
        // int[][] dir ={{0, -1}, {-1, -1}, {-1, 0}, {-1, 1}};
        // combination...mei neeche koi queen hi nahi hogi toh uski neeche vali calls
        // check karne ki need nahi ..but for perm queen baadmei bhi placed ho sakti hai
        // already toh uski baad vali 4 calls bhi check krni padegi
        int[][] dir = { { 0, -1 }, { -1, -1 }, { -1, 0 }, { -1, 1 }, { 0, 1 }, { 1, 1 }, { 1, 0 }, { 1, -1 } };
        int n = vis.length;
        int m = vis[0].length;
        for (int d = 0; d < dir.length; d++) {
            for (int rad = 1; rad < vis.length; rad++) {
                int r = row + rad * dir[d][0];
                int c = col + rad * dir[d][1];
                if (r >= 0 && c >= 0 && r < n && c < m) {
                    if (vis[r][c])
                        return false;
                } else
                    break;

            }
        }
        return true;
    }

    // ----------------------COMBINATION QUEEN----------------------------

    public static int nqueen_comb(boolean[][] vis, int tnq, int idx, String asf) {
        if (tnq == 0) {
            System.out.println(asf);
            return 1;
        }

        int count = 0;
        int n = vis.length;
        int m = vis[0].length;
        for (int i = idx; i < n * m; i++) {
            int r = i / m;
            int c = i % m;
            if (isSafeToPlaceQueen(vis, r, c)) {
                vis[r][c] = true;
                count += nqueen_comb(vis, tnq - 1, i + 1,
                        asf + "(" + Integer.toString(r) + "," + Integer.toString(c) + ")");
                vis[r][c] = false;
            }
        }

        return count;

    }

    // --------------------PERMUTATION QUEEN---------------------------
    public static int nqueen_perm(boolean[][] vis, int tnq, int idx, String asf) {
        if (tnq == 0) {
            System.out.println(asf);
            return 1;
        }

        int count = 0;
        int n = vis.length;
        int m = vis[0].length;
        for (int i = idx; i < n * m; i++) {
            int r = i / m;
            int c = i % m;
            if (isSafeToPlaceQueen(vis, r, c) && !vis[r][c]) {
                vis[r][c] = true;
                count += nqueen_perm(vis, tnq - 1, 0,
                        asf + "(" + Integer.toString(r) + "," + Integer.toString(c) + ")");
                vis[r][c] = false;
            }
        }

        return count;

    }

    // ------------------COMBINATION QUEENS SUBSEQUENCE-------------------------

    public static int Queen_comb_sub(boolean[][] vis, int tnq, int idx, String asf) {
        int n = vis.length;
        int m = vis[0].length;

        if (tnq == 0 || idx == n * m) {
            if (tnq == 0) {
                System.out.println(asf);
            }
            return tnq == 0 ? 1 : 0;
        }
        // OR
        // if(tnq==0 || idx==n*m){
        // if(tnq==0){
        // System.out.println(asf);
        // return 1;
        // }
        // return 0;
        // }

        int count = 0;
        int r = idx / m;
        int c = idx % m;
        if (isSafeToPlaceQueen(vis, r, c)) {
            vis[r][c] = true;
            count += Queen_comb_sub(vis, tnq - 1, idx + 1,
                    asf + "(" + Integer.toString(r) + "," + Integer.toString(c) + ")");
            vis[r][c] = false;
        }
        count += Queen_comb_sub(vis, tnq, idx + 1, asf);

        return count;
    }

    // ---------------------- PERMUTATION SUBSEQUNCE QUEENS-----------------------

    public static int perm_comb_sub(boolean[][] vis, int tnq, int idx, String asf) {
        int n = vis.length;
        int m = vis[0].length;

        if (tnq == 0 || idx == n * m) {
            if (tnq == 0) {
                System.out.println(asf);
            }
            return tnq == 0 ? 1 : 0;
        }

        int count = 0;
        int r = idx / m;
        int c = idx % m;
        if (isSafeToPlaceQueen(vis, r, c) && !vis[r][c]) {
            vis[r][c] = true;
            count += perm_comb_sub(vis, tnq - 1, 0, asf + "(" + Integer.toString(r) + "," + Integer.toString(c) + ")");
            vis[r][c] = false;
        }
        count += perm_comb_sub(vis, tnq, idx + 1, asf);

        return count;
    }

    // create static varibales....we can also pass them in arguments..it's upto us
    // boolean[] row;
    // boolean[] col;
    // boolean[] diag;
    // boolean[] adiag;

    // --------------------------OPTIMIZED 01 COMBINATION---------------------------
    public static int comb_queen_optimized1(int n, int m, int tnq, int r, boolean[] row, boolean[] col, boolean[] diag,
            boolean[] adiag, String asf) {
        if (tnq == 0) {
            System.out.println(asf);
            return 1;
        }

        int count = 0;
        for (int c = 0; c < m; c++) {
            if (!row[r] && !col[c] && !diag[r + c] && !adiag[r - c + m - 1]) {
                row[r] = col[c] = diag[r + c] = adiag[r - c + m - 1] = true;
                count += comb_queen_optimized1(n, m, tnq - 1, r + 1, row, col, diag, adiag,
                        asf + "(" + Integer.toString(r) + " " + Integer.toString(c) + ")");
                row[r] = col[c] = diag[r + c] = adiag[r - c + m - 1] = false;
            }

        }
        return count;

    }

    // -----------------------OPTIMIZED 01 PERMUTATION------------------------
    public static int perm_queen_optimized1(int n, int m, int tnq, int r, boolean[] row, boolean[] col, boolean[] diag,
            boolean[] adiag, String asf) {
        if (tnq == 0 || r == n) {
            if (tnq == 0) {
                System.out.println(asf);
                return 1;
            }
            return 0;
        }

        int count = 0;
        for (int c = 0; c < m; c++) {
            if (!row[r] && !col[c] && !diag[r + c] && !adiag[r - c + m - 1]) {
                row[r] = col[c] = diag[r + c] = adiag[r - c + m - 1] = true;
                count += perm_queen_optimized1(n, m, tnq - 1, 0, row, col, diag, adiag,
                        asf + "(" + Integer.toString(r) + " " + Integer.toString(c) + ")");
                row[r] = col[c] = diag[r + c] = adiag[r - c + m - 1] = false;
            }
        }
        count += perm_queen_optimized1(n, m, tnq, r + 1, row, col, diag, adiag, asf);
        return count;

    }
}