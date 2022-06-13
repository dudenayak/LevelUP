package Questions_Sheet;

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
