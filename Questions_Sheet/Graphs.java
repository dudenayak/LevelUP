package Questions_Sheet;

public class Graphs {

    // GFG

    // GFG

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

    // LEETCODE

    // GFG

    // GFG

    // GFG

    // GFG

    // LEETCODE

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
