package Questions_Sheet;

import java.util.ArrayList;
import java.util.Iterator;

public class Graphs {

    // GFG BFS of graph

    public ArrayList<Integer> bfsOfGraph(int V, ArrayList<ArrayList<Integer>> adj) {
        ArrayList<Integer> result = new ArrayList<>();
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[V];

        Arrays.fill(visited, false);
        queue.add(0);
        visited[0] = true;

        while (!queue.isEmpty()) {
            int f = queue.poll();
            result.add(f);
            for (var nbr : adj.get(f)) {
                if (!visited[nbr]) {
                    queue.add(nbr);
                    visited[nbr] = true;
                }
            }
        }
        return result;
    }

    // LEETCODE 200. Number of Islands

    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0)
            return 0;

        int numIslands = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == '1') {
                    numIslands += dfs(grid, i, j);
                }
            }
        }
        return numIslands;
    }

    public int dfs(char[][] grid, int i, int j) {
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[i].length || grid[i][j] == '0') {
            return 0;
        }
        grid[i][j] = '0';
        dfs(grid, i + 1, j);
        dfs(grid, i - 1, j);
        dfs(grid, i, j + 1);
        dfs(grid, i, j - 1);
        return 1;
    }

    // LEETCODE 733. Flood Fill

    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        if (image[sr][sc] == newColor)
            return image;

        fill(image, sr, sc, image[sr][sc], newColor);
        return image;
    }

    public void fill(int[][] image, int i, int j, int color, int newColor) {
        if (i < 0 || i >= image.length || j < 0 || j >= image[i].length || image[i][j] != color)
            return;
        image[i][j] = newColor;
        fill(image, i + 1, j, color, newColor);
        fill(image, i - 1, j, color, newColor);
        fill(image, i, j + 1, color, newColor);
        fill(image, i, j - 1, color, newColor);
    }

    // GFG Rat in a Maze Problem - I

    class Solution {

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
            vis[sr][sc] = 1;// unblock
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
    }

    // GFG Detect cycle in an undirected graph

    Boolean isCyclicUtil(int v, Boolean visited[], int parent,
            ArrayList<ArrayList<Integer>> adj) {

        visited[v] = true;
        Integer i;

        Iterator<Integer> it = adj.get(v).iterator();
        while (it.hasNext()) {
            i = it.next();

            if (!visited[i]) {
                if (isCyclicUtil(i, visited, v, adj))
                    return true;
            }

            else if (i != parent)
                return true;
        }
        return false;
    }

    public boolean isCycle(int V, ArrayList<ArrayList<Integer>> adj) {

        Boolean visited[] = new Boolean[V];
        for (int i = 0; i < V; i++)
            visited[i] = false;

        for (int u = 0; u < V; u++) {
            if (!visited[u])
                if (isCyclicUtil(u, visited, -1, adj))
                    return true;
        }

        return false;
    }

    // GFG

    // GFG

    // LEETCODE 394. Decode String

    private int index = 0;

    public String decodeString(String s) {
        String result = "";
        int count = 0;
        while (index < s.length()) {
            char current = s.charAt(index);
            if (Character.isLetter(current)) {
                result += String.valueOf(current);
            } else if (Character.isDigit(current)) {
                count = count * 10 + (current - '0');
            } else if (current == '[') {
                index++;
                String temp = decodeString(s);
                while (count > 0) {
                    result += temp;
                    count--;
                }
            } else {
                break;
            }
            index++;
        }
        return result;
    }

    // LEETCODE

    // LEETCODE

    // LEETCODE

    // GFG

    // LEETCODE

    // GFG

    // LEETCODE

    // LEETCODE

    // LEETCODE

    // GFG

    // GFG

    // GFG

    // LEETCODE

    // LEETCODE

    // GFG

    // LEETCODE

    // GFG

    // GFG

}
