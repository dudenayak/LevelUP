package Level2.Recursion;

import java.util.*;

public class Class2 {

    // top to bottom approach
    public static ArrayList<String> mazePath_HVD(int sr, int sc, int er, int ec) {

        if (sr == er && er == ec) {
            ArrayList<String> base = new ArrayList<>();
            base.add("");
            return base;
        }

        ArrayList<String> ans = new ArrayList<>();
        if (sr + 1 <= er) {
            ArrayList<String> Vertical = mazePath_HVD(sr + 1, sc, er, ec);
            for (String s : Vertical) {
                ans.add("V" + s);
            }
        }

        if (sr + 1 <= er && sc + 1 <= ec) {
            ArrayList<String> Diagonal = mazePath_HVD(sr + 1, sc + 1, er, ec);
            for (String s : Diagonal) {
                ans.add("D" + s);
            }
        }

        if (sc + 1 <= ec) {
            ArrayList<String> Horizontal = mazePath_HVD(sr, sc + 1, er, ec);
            for (String s : Horizontal) {
                ans.add("H" + s);
            }
        }

        return ans;
    }

    // bottom to top approach
    public static int mazePath_HVD(int sr, int sc, int er, int ec, ArrayList<String> ans, String psf) {

        if (sr == ec && sc == ec) {
            ans.add(psf);
            return 1;
        }
        int count = 0;
        if (sr + 1 <= er)
            count += mazePath_HVD(sr + 1, sc, er, ec, ans, psf + "V");
        if (sr + 1 <= er && sc + 1 <= ec)
            count += mazePath_HVD(sr + 1, sc + 1, er, ec, ans, psf + "D");
        if (sc + 1 <= ec)
            count += mazePath_HVD(sr, sc + 1, er, ec, ans, psf + "H");

        return count;
    }

    public static ArrayList<String> mazePath_HVD_multi(int sr, int sc, int er, int ec) {
        if (sr == er && er == ec) {
            ArrayList<String> base = new ArrayList<>();
            base.add("");
            return base;
        }

        ArrayList<String> ans = new ArrayList<>();
        for (int jump = 1; sr + jump <= er; jump++) {
            ArrayList<String> Vertical = mazePath_HVD_multi(sr + jump, sc, er, ec);
            for (String s : Vertical) {
                ans.add("V" + jump + s);
            }
        }

        for (int jump = 1; sr + jump <= er && sc + jump <= ec; jump++) {
            ArrayList<String> Diagonal = mazePath_HVD_multi(sr + jump, sc + jump, er, ec);
            for (String s : Diagonal) {
                ans.add("D" + jump + s);
            }
        }

        for (int jump = 1; sc + jump <= ec; jump++) {
            ArrayList<String> Horizontal = mazePath_HVD_multi(sr, sc + jump, er, ec);
            for (String s : Horizontal) {
                ans.add("H" + jump + s);
            }
        }

        return ans;
    }

    public static int mazePath_HVD_multi(int sr, int sc, int er, int ec, ArrayList<String> ans, String psf) {
        if (sr == ec && sc == ec) {
            ans.add(psf);
            return 1;
        }
        int count = 0;
        for (int jump = 1; sr + jump <= er; jump++)
            count += mazePath_HVD_multi(sr + 1, sc, er, ec, ans, psf + "V" + jump);
        for (int jump = 1; sr + jump <= er && sc + jump <= ec; jump++)
            count += mazePath_HVD_multi(sr + 1, sc + 1, er, ec, ans, psf + "D" + jump);
        for (int jump = 1; sc + jump <= ec; jump++)
            count += mazePath_HVD_multi(sr, sc + 1, er, ec, ans, psf + "H" + jump);

        return count;
    }

    public static int mazePath_HVD_2(int sr, int sc, int er, int ec, int[][] dir, String[] dirS, ArrayList<String> ans,
            String psf) {

        if (sr == er && sc == ec) {
            ans.add(psf);
            return 1;
        }

        int count = 0;
        for (int d = 0; d < dir.length; d++) {
            int x = sr + dir[d][0];
            int y = sc + dir[d][1];

            if (x >= 0 && y >= 0 && x <= er && y <= ec)
                count += mazePath_HVD_2(x, y, er, ec, dir, dirS, ans, psf + dirS[d]);
        }
        return count;
    }

    public static int floodFill(int sr, int sc, boolean[][] vis, int[][] dir, String[] dirS, ArrayList<String> ans,
            String psf) {

        int n = vis.length, m = vis[0].length;

        if (sr == n - 1 && sc == m - 1) {
            ans.add(psf);
            return 1;
        }

        int count = 0;
        vis[sr][sc] = true;

        for (int d = 0; d < dir.length; d++) {
            int r = sr + dir[d][0];
            int c = sc + dir[d][1];
            if (r >= 0 && c >= 0 && r < n && c < m && !vis[r][c])
                count += floodFill(r, c, vis, dir, dirS, ans, psf + dirS[d]);
        }
        vis[sr][sc] = false;
        return count;
    }

    public static int floodFill_multi(int sr, int sc, boolean[][] vis, int[][] dir, String[] dirS,
            ArrayList<String> ans,
            String psf) {

        int n = vis.length, m = vis[0].length;

        if (sr == n - 1 && sc == m - 1) {
            ans.add(psf);
            return 1;
        }

        int count = 0;
        vis[sr][sc] = true;

        for (int d = 0; d < dir.length; d++)
            for (int rad = 1; rad <= Math.max(n, m); rad++) {
                int r = sr + dir[d][0];
                int c = sc + dir[d][1];
                if (r >= 0 && c >= 0 && r < n && c < m) {
                    if (!vis[r][c])
                        count += floodFill_multi(r, c, vis, dir, dirS, ans, psf + dirS[d] + rad);
                } else
                    break;
            }
        vis[sr][sc] = false;
        return count;
    }

}
