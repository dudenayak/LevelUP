package Questions_Sheet;

import java.util.*;

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

    // GFG Strongly Connected Components (Kosaraju's Algo)

    class Solution {
        public int kosaraju(int V, ArrayList<ArrayList<Integer>> adj) {

            Stack<Integer> st = new Stack<>();
            boolean[] visited = new boolean[V];

            Arrays.fill(visited, false);

            for (int i = 0; i < V; i++) {
                if (!visited[i])
                    dfs(i, visited, st, adj);
            }
            ArrayList<ArrayList<Integer>> transposeAdj = new ArrayList<>();
            transposeGraph(V, adj, transposeAdj);

            int componentCount = 0;
            Arrays.fill(visited, false);
            while (!st.isEmpty()) {
                int u = st.peek();
                st.pop();
                if (!visited[u]) {
                    componentCount++;
                    dfs(u, visited, transposeAdj);
                }
            }
            return componentCount;
        }

        static void dfs(int u, boolean[] visited, Stack<Integer> st, ArrayList<ArrayList<Integer>> adj) {
            visited[u] = true;
            for (int v : adj.get(u)) {
                if (visited[v] == false)
                    dfs(v, visited, st, adj);
            }
            st.push(u);
        }

        static void transposeGraph(int V, ArrayList<ArrayList<Integer>> adj,
                ArrayList<ArrayList<Integer>> transposeAdj) {
            for (int i = 0; i < V; i++) {
                transposeAdj.add(new ArrayList<Integer>());
            }

            for (int i = 0; i < V; i++) {
                for (int v : adj.get(i)) {
                    transposeAdj.get(v).add(i);
                }
            }
        }

        // overloading concept
        static void dfs(int u, boolean[] visited, ArrayList<ArrayList<Integer>> adj) {
            visited[u] = true;
            for (int v : adj.get(u)) {
                if (visited[v] == false) {
                    dfs(v, visited, adj);
                }
            }
        }
    }

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

    // LEETCODE 947. Most Stones Removed with Same Row or Column

    Map<Integer, Integer> parent = new HashMap<>();
    Map<Integer, Integer> rank = new HashMap<>();
    int count = 0;

    private int findParent(int x) {
        if (parent.getOrDefault(x, 0) != x)
            parent.put(x, findParent(parent.getOrDefault(x, 0)));
        return parent.get(x);
    }

    private void union(int r, int c) {
        int xSet = findParent(r), ySet = findParent(c);

        if (xSet == ySet)
            return;

        count--;

        if (rank.getOrDefault(xSet, 0) < rank.getOrDefault(ySet, 0))
            parent.put(xSet, ySet);

        else if (rank.getOrDefault(xSet, 0) > rank.getOrDefault(ySet, 0))
            parent.put(ySet, xSet);

        else {
            parent.put(ySet, xSet);
            rank.put(xSet, rank.getOrDefault(ySet, 0) + 1);
        }
    }

    public int removeStones(int[][] stones) {
        for (int[] c : stones) {
            int row = -(c[0] + 1);
            int col = (c[1] + 1);

            parent.put(row, row);
            parent.put(col, col);
        }
        count = parent.size();

        for (int[] c : stones) {
            int row = -(c[0] + 1);
            int col = (c[1] + 1);

            union(row, col);
        }
        return stones.length - count;
    }

    // LEETCODE 1162. As Far from Land as Possible

    int dx[] = { 1, 0, -1, 0 };
    int dy[] = { 0, 1, 0, -1 };

    public int maxDistance(int[][] grid) {
        int size = grid.length + grid[0].length;
        for (int i = 1; i <= size; i++) {
            boolean f = false;
            for (int j = 0; j < grid.length; ++j) {
                for (int k = 0; k < grid[0].length; ++k) {
                    for (int l = 0; l < 4; ++l) {
                        int x = j + dx[l];
                        int y = k + dy[l];
                        if (grid[j][k] == i) {
                            if (valid(x, y, grid)) {
                                f = true;
                                grid[x][y] = i + 1;
                            }
                        }

                    }
                }
            }
            if (!f)
                return i == 1 ? -1 : i - 1;
        }
        return 0;
    }

    public boolean valid(int i, int j, int[][] grid) {
        return i >= 0 && j >= 0 && i < grid.length && j < grid[0].length && grid[i][j] == 0;
    }

    // LEETCODE 1334. Find the City With the Smallest Number of Neighbors at a
    // Threshold Distance

    public int findTheCity(int n, int[][] edges, int distanceThreshold) {

        int[][] arr = new int[n][n];

        for (int[] oneD : arr) {
            Arrays.fill(oneD, (int) 1e9);
        }

        for (int[] edge : edges) {
            arr[edge[0]][edge[1]] = edge[2];
            arr[edge[1]][edge[0]] = edge[2];
        }

        for (int i = 0; i < n; i++) {
            arr[i][i] = 0;
        }

        for (int intermediate = 0; intermediate < n; intermediate++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    arr[i][j] = Math.min(arr[i][j], (arr[i][intermediate] + arr[intermediate][j]));
                }
            }
        }

        int minNoOfCity = (int) 1e9;
        int ans = -1;

        for (int i = 0; i < n; i++) {
            int cityCount = 0;
            for (int j = 0; j < n; j++) {
                if (arr[i][j] <= distanceThreshold) {
                    cityCount++;
                }
            }
            if (cityCount <= minNoOfCity) {
                minNoOfCity = cityCount;
                ans = i;
            }
        }
        return ans;
    }

    // GFG Floyd Warshall

    public void shortest_distance(int[][] matrix) {
        for (int k = 0; k < matrix.length; k++) {
            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix.length; j++) {
                    if (matrix[i][k] != -1 && matrix[k][j] != -1) {
                        if (matrix[i][k] + matrix[k][j] < matrix[i][j] || matrix[i][j] == -1) {
                            matrix[i][j] = matrix[i][k] + matrix[k][j];
                        }
                    }
                }
            }
        }
    }

    // LEETCODE 399. Evaluate Division

    private Map<String, Map<String, Double>> makeGraph(List<List<String>> e, double[] values) {

        Map<String, Map<String, Double>> graph = new HashMap<>();
        String u, v;

        for (int i = 0; i < e.size(); i++) {
            u = e.get(i).get(0);
            v = e.get(i).get(1);

            graph.putIfAbsent(u, new HashMap<>());
            graph.get(u).put(v, values[i]);

            graph.putIfAbsent(v, new HashMap<>());
            graph.get(v).put(u, 1 / values[i]);

        }
        return graph;
    }

    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        Map<String, Map<String, Double>> graph = makeGraph(equations, values);

        double[] ans = new double[queries.size()];

        for (int i = 0; i < queries.size(); i++) {
            ans[i] = dfs(queries.get(i).get(0), queries.get(i).get(1), new HashSet<>(), graph);
        }
        return ans;
    }

    public double dfs(String src, String dest, Set<String> visited, Map<String, Map<String, Double>> graph) {

        if (graph.containsKey(src) == false)
            return -1.0;

        if (graph.get(src).containsKey(dest)) {
            return graph.get(src).get(dest);
        }

        visited.add(src);

        for (Map.Entry<String, Double> nbr : graph.get(src).entrySet()) {
            if (visited.contains(nbr.getKey()) == false) {
                double weight = dfs(nbr.getKey(), dest, visited, graph);

                if (weight != -1.0) {
                    return nbr.getValue() * weight;
                }
            }
        }
        return -1.0;
    }

    // LEETCODE 909. Snakes and Ladders

    public int snakesAndLadders(int[][] board) {
        if (board.length == 1)
            return 0;
        int steps = 0;
        int n = board.length;
        Map<Integer, Node> nodes = createBoard(board);
        boolean[][] visited = new boolean[n][n];
        Queue<Integer> queue = new LinkedList<>();
        queue.add(1);
        visited[n - 1][0] = true;

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int curr = queue.poll();
                if (curr == n * n) {
                    return steps;
                }
                for (int k = 1; k <= 6; k++) {
                    int next = curr + k;
                    if (next > n * n)
                        break;
                    Node node = nodes.get(next);
                    if (visited[node.x][node.y])
                        continue;
                    visited[node.x][node.y] = true;
                    if (board[node.x][node.y] == -1)
                        queue.add(next);
                    else
                        queue.add(board[node.x][node.y]);
                }
            }
            steps++;
        }
        return -1;
    }

    public Map<Integer, Node> createBoard(int[][] board) {
        Map<Integer, Node> nodes = new HashMap<>();
        int x = board.length - 1;
        int y = 0;
        int num = 0;
        boolean leftToRight = true;

        while (x >= 0 && y >= 0) {
            num++;
            nodes.put(num, new Node(x, y, board[x][y]));
            if (leftToRight && y == board.length - 1) {
                x--;
                leftToRight = false;
            } else if (!leftToRight && y == 0) {
                x--;
                leftToRight = true;
            } else {
                if (leftToRight) {
                    y++;
                } else {
                    y--;
                }
            }
        }
        return nodes;
    }

    class Node {
        int x;
        int y;
        int val;

        Node(int x, int y, int val) {
            this.x = x;
            this.y = y;
            this.val = val;
        }
    }

    // GFG Topological sort

    static int[] topoSort(int V, ArrayList<ArrayList<Integer>> adj) {
        ArrayList<Integer> result = new ArrayList<>();
        int indegree[] = getIndegree(adj, V);
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < indegree.length; i++) {
            if (indegree[i] == 0) {
                queue.add(i);
            }
        }

        while (!queue.isEmpty()) {
            int curr = queue.poll();
            result.add(curr);
            for (int i : adj.get(curr)) {
                indegree[i]--;
                if (indegree[i] == 0) {
                    queue.add(i);
                }
            }

        }

        int intArray[] = new int[result.size()];

        for (int i = 0; i < intArray.length; i++) {
            intArray[i] = result.get(i);
        }
        return intArray;
    }

    private static int[] getIndegree(ArrayList<ArrayList<Integer>> adj, int V) {
        int indegree[] = new int[V];
        for (int i = 0; i < adj.size(); i++) {
            for (int j : adj.get(i)) {
                indegree[j]++;
            }
        }
        return indegree;
    }

    // LEETCODE 787. Cheapest Flights Within K Stops

    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {

        Map<Integer, List<int[]>> graph = new HashMap<>();
        buildGraph(flights, graph);

        PriorityQueue<int[]> minHeap = new PriorityQueue<>((a, b) -> Integer.compare(a[1], b[1]));
        minHeap.add(new int[] { src, 0, 0 });

        HashMap<Integer, Integer> visited = new HashMap<>();

        while (!minHeap.isEmpty()) {
            int[] curr = minHeap.poll();
            int city = curr[0];
            int cost = curr[1];
            int stops = curr[2];

            if (!visited.containsKey(city) || stops < visited.get(city))
                visited.put(city, stops);
            else
                continue;

            if (city == dst)
                return cost;

            if (stops > k || !graph.containsKey(city))
                continue;

            for (int[] adjCity : graph.get(city)) {
                minHeap.add(new int[] { adjCity[0], cost + adjCity[1], stops + 1 });
            }
        }
        return -1;
    }

    void buildGraph(int[][] flights, Map<Integer, List<int[]>> graph) {
        for (int[] flight : flights) {
            graph.putIfAbsent(flight[0], new ArrayList<>());
            graph.get(flight[0]).add(new int[] { flight[1], flight[2] });
        }
    }

}
