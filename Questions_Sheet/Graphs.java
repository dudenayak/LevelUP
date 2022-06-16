package Questions_Sheet;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

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

    // GFG Steps by Knight

    boolean isValid(int x, int y, int N) {
        return (x >= 0 && x < N && y >= 0 && y < N);
    }

    public int minStepToReachTarget(int KnightPos[], int TargetPos[], int N) {
        KnightPos[0]--;
        KnightPos[1]--;
        TargetPos[0]--;
        TargetPos[1]--;

        int dx[] = { -2, -1, 1, 2, -2, -1, 1, 2 };
        int dy[] = { -1, -2, -2, -1, 1, 2, 2, 1 };
        boolean vis[][] = new boolean[N][N];

        Queue<ArrayList<Integer>> q = new LinkedList<>();
        ArrayList<Integer> temp = new ArrayList<>();
        temp.add(KnightPos[0]);
        temp.add(KnightPos[1]);
        temp.add(0);

        q.add(temp);
        vis[KnightPos[0]][KnightPos[1]] = true;

        while (!q.isEmpty()) {
            ArrayList<Integer> temp2 = q.poll();
            int x = temp2.get(0);
            int y = temp2.get(1);
            int steps = temp2.get(2);

            if (x == TargetPos[0] && y == TargetPos[1])
                return steps;

            for (int i = 0; i < 8; i++) {
                int x1 = x + dx[i];
                int y1 = y + dy[i];
                if (isValid(x1, y1, N) && !vis[x1][y1]) {
                    ArrayList<Integer> temp1 = new ArrayList<>();
                    temp1.add(x1);
                    temp1.add(y1);
                    temp1.add(steps + 1);
                    q.add(temp1);
                    vis[x1][y1] = true;
                }
            }
        }
        return -1;
    }

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

    // LEETCODE 934. Shortest Bridge

    Queue<int[]> q = new LinkedList<>();
    private int[][] dirs = new int[][] { { 0, -1 }, { 0, 1 }, { -1, 0 }, { 1, 0 } };

    public int shortestBridge(int[][] grid) {
        boolean flag = false;

        for (int i = 0; i < grid.length && !flag; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    dfs(i, j, grid);
                    flag = true;
                    break;
                }
            }
        }

        while (!q.isEmpty()) {
            int[] cell = q.poll();
            int i = cell[0], j = cell[1], dist = cell[2];
            for (int[] dir : dirs) {
                int x = i + dir[0], y = j + dir[1];
                if (x >= 0 && x < grid.length && y >= 0 && y < grid[0].length) {
                    if (grid[x][y] == 1)
                        return dist;
                    if (grid[x][y] == 0) {
                        q.add(new int[] { x, y, dist + 1 });
                        grid[x][y] = 2;
                    }
                }
            }
        }
        return -1;
    }

    private void dfs(int i, int j, int[][] grid) {
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] != 1)
            return;
        grid[i][j] = 2;
        q.add(new int[] { i, j, 0 });
        dfs(i, j - 1, grid);
        dfs(i, j + 1, grid);
        dfs(i - 1, j, grid);
        dfs(i + 1, j, grid);

    }

    // LEETCODE 1319. Number of Operations to Make Network Connected

    public int wires(int node, int parent[]) {
        if (node == parent[node]) {
            return node;
        }
        return parent[node] = wires(parent[node], parent);
    }

    public int makeConnected(int n, int[][] connections) {
        int parent[] = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }
        int leftWire = 0;
        int component = 0;
        int m = connections.length;
        for (int i = 0; i < m; i++) {
            int p1 = wires(connections[i][0], parent);
            int p2 = wires(connections[i][1], parent);
            if (p1 != p2) {
                parent[p1] = p2;
            } else {
                leftWire++;
            }
        }
        for (int i = 0; i < n; i++) {
            if (parent[i] == i) {
                component++;
            }
        }
        return (component - 1) <= leftWire ? component - 1 : -1;
    }

    // LEETCODE 802. Find Eventual Safe States

    public List<Integer> eventualSafeNodes(int[][] graph) {
        List<Integer> ans = new ArrayList<>();
        Boolean[] node = new Boolean[graph.length];
        for (int i = 0; i < graph.length; i++) {
            if (dfs(i, graph, node)) {
                ans.add(i);
            }
        }
        return ans;
    }

    private boolean dfs(int i, int[][] graph, Boolean[] node) {
        if (node[i] != null)
            return node[i];
        node[i] = false;
        for (int next : graph[i]) {
            if (!dfs(next, graph, node))
                return false;
        }
        return node[i] = true;
    }

    // GFG

    // LEETCODE 1376. Time Needed to Inform All Employees

    public int numOfMinutes(int n, int headID, int[] manager, int[] informTime) {

        int[] calculatedTime = new int[n];
        Arrays.fill(calculatedTime, -1);
        int result = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            if (informTime[i] == 0) {
                result = Math.max(result, calc(i, manager, informTime, calculatedTime));
            }
        }
        return result;
    }

    private int calc(int node, int[] manager, int[] informTime, int[] calculatedTime) {
        if (manager[node] == -1) {
            calculatedTime[node] = informTime[node];
        } else if (calculatedTime[node] == -1) {
            calculatedTime[node] = informTime[node] + calc(manager[node], manager, informTime, calculatedTime);
        }
        return calculatedTime[node];
    }

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
