package Foundation;

import java.io.*;
import java.util.*;

public class Graphs {

    // hasPath
    public static boolean hasPathh(ArrayList<Edge>[] graph, int src, int dest, boolean[] visited, int vtces) {
        boolean[] visited = new boolean[vtces];
        boolean path = hasPathh(graph, src, dest, visited);

        System.out.println(path);
    }

    public static boolean hasPath(ArrayList<Edge>[] graph, int src, int dest, boolean[] visited) {

        if (src == dest) {
            return true;
        }

        visited[src] = true;
        for (Edge edge : graph[src]) {
            if (visited[edge.nbr] == false) {
                boolean hasNbrPath = hasPath(graph, edge.nbr, dest, visited);
                if (hasNbrPath == true) {
                    return true;
                }
            }
        }
        return false;
    }

    // Print all paths
    public static void printAllPathss(ArrayList<Edge>[] graph, int src, int dest, boolean[] visited, String psf) {
        boolean[] visited = new boolean[vtces];

        printAllPathss(graph, src, dest, visited, src + "");
    }

    public static void printAllPaths(ArrayList<Edge>[] graph, int src, int dest, boolean[] visited, String psf) {

        if (src == dest) {
            System.out.println(psf);
            return;
        }

        visited[src] = true;
        for (Edge edge : graph[src]) {
            if (visited[edge.nbr] == false) {
                printAllPaths(graph, edge.nbr, dest, visited, psf + edge.nbr);
            }
        }
        visited[src] = false;
    }

    // Multisolver - Smallest, Longest, Ceil, Floor, Kthlargest Path

    public static void multisolver(ArrayList<Edge>[] graph, int src, int dest, boolean[] visited, int criteria, int k,
            String psf, int wsf) {
        if (src == dest) {
            if (wsf < spathwt) {
                spathwt = wsf;
                spath = psf;
            }
            if (wsf > lpathwt) {
                lpathwt = wsf;
                lpath = psf;
            }
            if (wsf > criteria && wsf < cpathwt) {
                cpathwt = wsf;
                cpath = psf;
            }
            if (wsf < criteria && wsf > fpathwt) {
                fpathwt = wsf;
                fpath = psf;
            }
            if (pq.size() < k) {
                pq.add(new Pair(wsf, psf));
            } else {
                if (wsf > pq.peek().wsf) {
                    pq.remove();
                    pq.add(new Pair(wsf, psf));
                }
            }
            return;
        }
        visited[src] = true;
        for (Edge e : graph[src]) {
            if (visited[e.nbr] == false) {
                multisolver(graph, e.nbr, dest, visited, criteria, k, psf + e.nbr, wsf + e.wt);
            }
        }
        visited[src] = false;

    }

    // Get Connected Components Of A Graph
    public static void drawTreee(ArrayList<Edge>[] graph, int src, ArrayList<Integer> comp, boolean[] visited,
            int vtces) {
        boolean[] visited = new boolean[vtces];
        for (int v = 0; v < vtces; v++) {
            if (visited[v] == false) {
                ArrayList<Integer> comp = new ArrayList<>();
                drawTreee(graph, v, comp, visited);
                comps.add(comp);
            }
        }

        System.out.println(comps);
    }

    public static void drawTree2(ArrayList<Edge>[] graph, int src, ArrayList<Integer> comp, boolean[] visited) {
        visited[src] = true;
        comp.add(src);
        for (Edge e : graph[src]) {
            if (visited[e.nbr] == false) {
                drawTree2(graph, e.nbr, comp, visited);
            }
        }
    }

    // Is Graph Connected
    public static void drawTree3(ArrayList<Edge>[] graph, int src, ArrayList<Integer> comp, boolean[] visited) {
        boolean[] visited = new boolean[vtces];
        for (int v = 0; v < vtces; v++) {
            if (visited[v] == false) {
                ArrayList<Integer> comp = new ArrayList<>();
                drawTree3(graph, v, comp, visited);
                System.out.println(comps.size() == 1);
            }
        }

        System.out.println(comps);
    }

    public static void drawTree4(ArrayList<Edge>[] graph, int src, ArrayList<Integer> comp, boolean[] visited) {
        visited[src] = true;
        comp.add(src);
        for (Edge e : graph[src]) {
            if (visited[e.nbr] == false) {
                drawTree4(graph, e.nbr, comp, visited);
            }
        }
    }

    // Number Of Islands
    public static void drawTree5(int[][] arr, int i, int j, boolean[][] visited) {
        boolean[][] visitedd = new boolean[arr.length][arr[0].length];
        int count = 0;
        for (int i1 = 0; i1 < arr.length; i1++) {
            for (int j1 = 0; j1 < arr[i].length; j1++) {
                if (arr[i][j] == 0 && visited[i][j] == false) {
                    drawTree5(arr, i, j, visited);
                    count++;
                }
            }
        }
        System.out.println(count);
    }

    public static void drawTree(int[][] arr, int i, int j, boolean[][] visited) {
        if (i < 0 || j < 0 || i >= arr.length || j >= arr[0].length || arr[i][j] == 1 || visited[i][j] == true) {
            return;
        }

        visited[i][j] = true;
        drawTree(arr, i - 1, j, visited);
        drawTree(arr, i, j + 1, visited);
        drawTree(arr, i, j - 1, visited);
        drawTree(arr, i + 1, j, visited);
    }

    // Perfect Friends
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int k = Integer.parseInt(br.readLine());

        // write your code here
        ArrayList<Edge>[] graph = new ArrayList[n];
        for (int v = 0; v < n; v++) {
            graph[v] = new ArrayList<>();
        }
        for (int e = 0; e < k; e++) {
            String line = br.readLine();
            String[] parts = line.split(" ");

            int v1 = Integer.parseInt(parts[0]);
            int v2 = Integer.parseInt(parts[1]);

            graph[v1].add(new Edge(v1, v2));
            graph[v2].add(new Edge(v2, v1));
        }

        boolean[] visited = new boolean[n];
        ArrayList<ArrayList<Integer>> comps = new ArrayList<>();
        for (int v = 0; v < n; v++) {
            if (visited[v] == false) {
                ArrayList<Integer> comp = new ArrayList<>();
                drawTree(graph, v, comp, visited);
                comps.add(comp);
            }
        }

        int pairs = 0;

        for (int i = 0; i < comps.size(); i++) {
            for (int j = i + 1; j < comps.size(); j++) {
                int count = comps.get(i).size() * comps.get(j).size();
                pairs += count;
            }
        }
        System.out.println(pairs);
    }

    public static void drawTree(ArrayList<Edge>[] graph, int src, ArrayList<Integer> comp, boolean[] visited) {
        visited[src] = true;
        comp.add(src);

        for (Edge e : graph[src]) {
            if (visited[e.n] == false) {
                drawTree(graph, e.n, comp, visited);
            }
        }
    }

    // Hamiltonian Path And Cycle

    HashSet<Integer> visited = new HashSet<>();

    hamiltonian(graph, src, visited, src + "", src);

    public static void hamiltonian(ArrayList<Edge>[] graph, int src, HashSet<Integer> visited, String psf, int osrc) {
        if (visited.size() == graph.length - 1) {
            System.out.print(psf);

            boolean closingEdgeFound = false;
            for (Edge e : graph[src]) {
                if (e.nbr == osrc) {
                    closingEdgeFound = true;
                    break;
                }
            }
            if (closingEdgeFound == true) {
                System.out.println("*");
            } else {
                System.out.println(".");
            }
            return;
        }
        visited.add(src);
        for (Edge e : graph[src]) {
            if (visited.contains(e.nbr) == false) {
                hamiltonian(graph, e.nbr, visited, psf + e.nbr, osrc);
            }
        }
        visited.remove(src);
    }

    // Knight's Tour
    public static void main(String[] args) throws Exception {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        int r = scn.nextInt();
        int c = scn.nextInt();

        int[][] chess = new int[n][n];
        printKnightsTour(chess, r, c, 1);

    }

    public static void printKnightsTour(int[][] chess, int r, int c, int move) {
        if (r < 0 || c < 0 || r >= chess.length || c >= chess.length || chess[r][c] > 0) {
            return;
        } else if (move == chess.length * chess.length) {
            chess[r][c] = move;
            displayBoard(chess);
            chess[r][c] = 0;
            return;
        }

        chess[r][c] = move;
        printKnightsTour(chess, r - 2, c + 1, move + 1);
        printKnightsTour(chess, r - 1, c + 2, move + 1);
        printKnightsTour(chess, r + 1, c + 2, move + 1);
        printKnightsTour(chess, r + 2, c + 1, move + 1);
        printKnightsTour(chess, r + 2, c - 1, move + 1);
        printKnightsTour(chess, r + 1, c - 2, move + 1);
        printKnightsTour(chess, r - 1, c - 2, move + 1);
        printKnightsTour(chess, r - 2, c - 1, move + 1);
        chess[r][c] = 0;

    }

    public static void displayBoard(int[][] chess) {
        for (int i = 0; i < chess.length; i++) {
            for (int j = 0; j < chess[0].length; j++) {
                System.out.print(chess[i][j] + " ");
            }
            System.out.println();
        }

        System.out.println();
    }

    // Breadth First Traversal
    ArrayDeque<Pair> queue = new ArrayDeque<>();queue.add(new Pair(src,src+""));
    boolean[] visited = new boolean[vtces];

    while(queue.size()>0)
    {
        // r m* w a*(remove,visit,work,add unvisited neighbours)
        Pair rem = queue.removeFirst();
        if (visited[rem.v] == true) {
            continue;
        }
        visited[rem.v] = true;
        System.out.println(rem.v + "@" + rem.psf);

        for (Edge e : graph[rem.v]) {
            if (visited[e.nbr] == false) {
                queue.add(new Pair(e.nbr, rem.psf + e.nbr));
            }
        }
    }

    // Is Graph Cyclic
    boolean[] visited = new boolean[vtces];for(
    int v = 0;v<vtces;v++)
    {
        if (visited[v] == false) {
            // traverse
            boolean cycle = isCyclic(graph, v, visited);
            if (cycle) {
                System.out.println(true);
                return;
            }
        }
    }System.out.println(false);

    static class Pair {
        int v;
        String psf;

        Pair(int v, String psf) {
            this.v = v;
            this.psf = psf;
        }
    }

    public static boolean isCyclic(ArrayList<Edge>[] graph, int src, boolean[] visited){
        ArrayDeque<Pair> q = new ArrayDeque<>();
        q.add(new Pair(src, src + ""));

        while(q.size()>0){
            Pair rem = q.removeFirst();

            if(visited[rem.v]==true){
                return true;
            }
            visited[rem.v]= true;

            for(Edge e: graph[rem.v]){
                if(visited[e.nbr]==false){
                    q.add(new Pair(e.nbr, rem.psf + e.nbr));
                }
            }
        }
        return false;
    }

    // Spread Of Infection
    ArrayDeque<Pair> q = new ArrayDeque<>();q.add(new Pair(src,1));
    int[] visited = new int[vtces];
    int count = 0;

    while(q.size()>0)
    {
        Pair rem = q.removeFirst();

        if (visited[rem.v] > 0) {
            continue;
        }
        visited[rem.v] = rem.time;
        if (rem.time > t) {
            break;
        }
        count++;

        for (Edge e : graph[rem.v]) {
            if (visited[e.nbr] == 0) {
                q.add(new Pair(e.nbr, rem.time + 1));
            }
        }
        System.out.println(count);
    }

    static class Pair {
        int v;
        int time;

        Pair(int v, int time) {
            this.v = v;
            this.time = time;
        }
    }

    // Shortest Path In Weights
    PriorityQueue<Pair> pq = new PriorityQueue<>();pq.add(new Pair(src,src+"",0));
    boolean[] visited = new boolean[vtces];

    while(pq.size()>0)
    {
        Pair rem = pq.remove();

        if (visited[rem.v] == true) {
            continue;
        }
        visited[rem.v] = true;

        System.out.println(rem.v + " via " + rem.psf + " @ " + rem.wsf);

        for (Edge e : graph[rem.v]) {
            if (visited[e.nbr] == false) {
                pq.add(new Pair(e.nbr, rem.psf + e.nbr, rem.wsf + e.wt));
            }
        }
    }

}

public static class Pair implements Comparable<Pair> {
    int v;
    String psf;
    int wsf;

    Pair(int v, String psf, int wsf) {
        this.v = v;
        this.psf = psf;
        this.wsf = wsf;
    }

    public int compareTo(Pair o) {
        return this.wsf - o.wsf;
    }

    // Minimum Wire Required To Connect All Pcs
    PriorityQueue<Pair> pq = new PriorityQueue<>();pq.add(new Pair(0,-1,0));
    boolean[] visited = new boolean[vtces];

    while(pq.size()>0)
    {
        Pair rem = pq.remove();

        if (visited[rem.v] == true) {
            continue;
        }
        visited[rem.v] = true;

        if (rem.av != -1) {
            System.out.println("[" + rem.v + "-" + rem.av + "@" + rem.wt + "]");
        }

        for (Edge e : graph[rem.v]) {
            if (visited[e.nbr] == false) {
                pq.add(new Pair(e.nbr, rem.v, e.wt));
            }
        }
    }

    // Order Of Compilation

    boolean[] visited = new boolean[vtces];
    Stack<Integer> st = new Stack<>();for(
    int v = 0;v<vtces;v++)
    {
        if (visited[v] == false) {
            topologicalSort(graph, v, visited, st);
        }
    }while(st.size()>0)
    {
        System.out.println(st.pop());
    }
   }

   public static void topologicalSort(ArrayList<Edge>[] graph, int src, boolean[] visited, Stack<Integer> st ){
       visited[src] =true;
       for(Edge e: graph[src] ){
           if(visited[e.nbr]==false){
               topologicalSort(graph, e.nbr, visited, st);
           }
       }
       st.push(src);
   

    // Iterative Depth First Traversal

    public static class Pair {
        int v;
        String psf;

        Pair(int v, String psf) {
            this.v = v;
            this.psf = psf;
        }
    }

    boolean[] visited = new boolean[vtces];
    Stack<Pair> st = new Stack<>();st.push(new Pair(src,src+""));

    while(st.size()>0)
    {
        Pair rem = st.pop();

        if (visited[rem.v] == true) {
            continue;
        }
        visited[rem.v] = true;

        System.out.println(rem.v + "@" + rem.psf);

        for (Edge e : graph[rem.v]) {
            if (visited[e.nbr] == false) {
                st.push(new Pair(e.nbr, rem.psf + e.nbr));
            }
        }
    }

    // Is Graph Bipartite

    public static class Pair {
        int v;
        String psf;
        int level;

        Pair(int v, String psf, int level) {
            this.v = v;
            this.psf = psf;
            this.level = level;
        }
    }

    int[] visited = new int[vtces];Arrays.fill(visited,-1);

    for(
    int v = 0;v<vtces;v++)
    {
        if (visited[v] == -1) {
            boolean isCompBipartite = checkComponentForBipartiteness(graph, v, visited);
            if (isCompBipartite == false) {
                System.out.println(false);
                return;
            }
        }
    }System.out.println(true);

    public static boolean checkComponentForBipartiteness(ArrayList<Edge>[] graph, int src, int[] visited){
        ArrayDeque<Pair> q = new ArrayDeque<>();
        q.add(new Pair(src, src+ "", 0));

        while(q.size()>0){
            Pair rem = q.removeFirst();

            if(visited[rem.v] != -1){
                if(rem.level != visited[rem.v]){
                    return false;
                }
            } else {
                visited[rem.v] = rem.level;
            }
            for(Edge e: graph[rem.v]){
                if(visited[e.nbr] == -1){
                    q.add(new Pair(e.nbr, rem.psf + e.nbr, rem.level + 1));
                }
            }
        }
        return true;
    }
}
