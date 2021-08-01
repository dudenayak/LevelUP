package Level2.Graphs;

public class DFSQuestion {

    // Q.695 LEETCODE

    public int dfs_area(int[][] grid, int i, int j, int[][] dir) {
        int n = grid.length, m = grid[0].length, length = 0;
        grid[i][j] = 0;
        for (int d = 0; d < dir.length; d++) {
            int r = i + dir[d][0];
            int c = j + dir[d][1];

            if (r >= 0 && c >= 0 && r < n && c < m && grid[r][c] == 1)
                length += dfs_area(grid, r, c, dir);
        }
        return length + 1;
    }

    public int maxAreaOfIsland(int[][] grid) {
        int[][] dir = { { -1, 0 }, { 1, 0 }, { 0, 1 }, { 0, -1 } };
        int n = grid.length, m = grid[0].length, maxArea = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 1) {
                    int area = dfs_area(grid, i, j, dir);
                    maxArea = Math.max(area, maxArea);
                }
            }
        }
        return maxArea;
    }

    // Q.200 LEETCODE
    void dfs_numIslands(char[][] grid, int i, int j, int[][] dir) {
        int n = grid.length, m = grid[0].length;
        grid[i][j] = '0';
        for (int d = 0; d < dir.length; d++) {
            int r = i + dir[d][0];
            int c = j + dir[d][1];

            if (r >= 0 && c >= 0 && r < n && c < m && grid[r][c] == '1')
                dfs_numIslands(grid, r, c, dir);
        }

    }

    public int numIslands(char[][] grid) {
        int[][] dir = { { -1, 0 }, { 1, 0 }, { 0, 1 }, { 0, -1 } };
        int n = grid.length, m = grid[0].length, components = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == '1') {
                    components++;
                    dfs_numIslands(grid, i, j, dir);
                }
            }
        }
        return components;
    }

    // Q.463 LEETCODE
    public int islandPerimeter(int[][] grid) {
        int[][] dir = { { -1, 0 }, { 1, 0 }, { 0, 1 }, { 0, -1 } };
        int n = grid.length, m = grid[0].length, onceCount = 0, nbrCount = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 1) {
                    onceCount++;
                    for (int d = 0; d < dir.length; d++) {
                        int r = i + dir[d][0];
                        int c = j + dir[d][1];

                        if (r >= 0 && c >= 0 && r < n && c < m && grid[r][c] == 1)
                            nbrCount++;
                    }
                }
            }
        }
        return 4 * onceCount - nbrCount;
    }

    // Q.130 LEETCODE
    void dfs_surrounded(char[][] grid, int i, int j, int[][] dir) {
        int n = grid.length, m = grid[0].length;
        grid[i][j] = '$';
        for (int d = 0; d < dir.length; d++) {
            int r = i + dir[d][0];
            int c = j + dir[d][1];

            if (r >= 0 && c >= 0 && r < n && c < m && grid[r][c] == 'O') {
                dfs_surrounded(grid, r, c, dir);
            }
        }
    }

    public void solve(char[][] grid) {
        int[][] dir = { { -1, 0 }, { 1, 0 }, { 0, 1 }, { 0, -1 } };

        int n = grid.length, m = grid[0].length;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (i == 0 || j == 0 || i == n - 1 || j == m - 1) {
                    if (grid[i][j] == 'O')
                        dfs_surrounded(grid, i, j, dir);
                }
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 'O')
                    grid[i][j] = 'X';
                else if (grid[i][j] == '$')
                    grid[i][j] = 'O';
            }
        }
    }
}
