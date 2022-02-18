package Level2.Recursion;

import java.util.*;

public class Class3 {

    // Rat in a Maze Problem - I
    public static int floodFill(int sr, int sc, int[][] vis, int[][] dir, String[] dirS, ArrayList<String> ans,
            String psf) {

        int n = vis.length, m = vis[0].length;

        if (sr == n - 1 && sc == m - 1) {
            ans.add(psf);
            return 1;
        }

        int count = 0;
        vis[sr][sc] = 0; // block

        for (int d = 0; d < dir.length; d++) {
            int r = sr + dir[d][0];
            int c = sc + dir[d][1];
            if (r >= 0 && c >= 0 && r < n && c < m && vis[r][c] == 1) {
                count += floodFill(r, c, vis, dir, dirS, ans, psf + dirS[d]);

            }
        }
        vis[sr][sc] = 1; // unblock
        return count;
    }

    public static ArrayList<String> findPath(int[][] m, int n) {
        // boolean[][] vis = new boolean[n][n];
        int[][] dir = { { 1, 0 }, { 0, -1 }, { 0, 1 }, { -1, 0 } };
        String[] dirS = { "D", "L", "R", "U" };

        ArrayList<String> ans = new ArrayList<String>();
        if (m[0][0] == 0 || m[n - 1][n - 1] == 0)
            return ans;

        int count = floodFill(0, 0, m, dir, dirS, ans, "");
        return ans;
    }

    public static class pair {
        String psf = "";
        int len = 0;

        pair(String psf, int len) {
            this.len = len;
            this.psf = psf;
        }
    }

    public static pair longestPath(int sr, int sc, boolean[][] vis, int[][] dir, String[] dirS) {
        int n = vis.length, m = vis[0].length;
        if (sr == n - 1 && sc == m - 1) {
            return new pair("", -1);
        }

        vis[sr][sc] = true; // blocked
        pair myAns = new pair("", 0);
        for (int d = 0; d < dir.length; d++) {
            int r = sr + dir[d][0];
            int c = sc + dir[d][1];

            if (r >= 0 && c >= 0 && r < n && c < m) {
                if (!vis[r][c]) {
                    pair recAns = longestPath(r, c, vis, dir, dirS);
                    if (recAns.len != -1 && recAns.len + 1 > myAns.len) {
                        myAns.len = recAns.len + 1;
                        myAns.psf = dirS[d] + recAns.psf;
                    }
                }
            }
        }
        vis[sr][sc] = false; // unblocked
        return myAns;
    }

    public static pair shortestPath(int sr, int sc, boolean[][] vis, int[][] dir, String[] dirS) {
        int n = vis.length, m = vis[0].length;
        if (sr == n - 1 && sc == m - 1) {
            return new pair("", 0);
        }

        vis[sr][sc] = true; // blocked
        pair myAns = new pair("", (int) 1e9);
        for (int d = 0; d < dir.length; d++) {
            int r = sr + dir[d][0];
            int c = sc + dir[d][1];

            if (r >= 0 && c >= 0 && r < n && c < m) {
                if (!vis[r][c]) {
                    pair recAns = shortestPath(r, c, vis, dir, dirS);
                    if (recAns.len != (int) 1e9 && recAns.len + 1 < myAns.len) {
                        myAns.len = recAns.len + 1;
                        myAns.psf = dirS[d] + recAns.psf;
                    }
                }
            }
        }
        vis[sr][sc] = false; // unblocked
        return myAns;
    }

    public static int equalSet(int[] arr, int idx, int sum1, int sum2, String set1, String set2) {
        if (idx == arr.length) {
            if (sum1 == sum2) {
                System.out.println(set1 + " = " + set2);
                return 1;
            }
            return 0;
        }
        int count = 0;
        count += equalSet(arr, idx + 1, sum1 + arr[idx], sum2, set1 + arr[idx] + " ", set2);
        count += equalSet(arr, idx + 1, sum1, sum2 + arr[idx], set1, set2 + +arr[idx] + " ");
        return count;
    }

    public static void equalSet() {
        int[] arr = { 10, 20, 30, 40, 50, 60, 70, 80 };
        System.out.println(equalSet(arr, 1, arr[0], 0, arr[0] + " ", ""));
    }

    public static int permutation(String str, String ans) {
        int count = 0;
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            String ros = str.substring(0, i) + str.substring(i + 1);
            count += permutation(ros, ans + ch);
        }
        return count;
    }
}
