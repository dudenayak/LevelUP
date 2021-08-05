package Foundation;

import java.io.*;
import java.lang.Thread.State;
import java.util.*;

import Level2.Graphs.Graphs.pair;
import jdk.internal.util.xml.impl.Pair;

public class Graphs {

    // hasPath
    boolean[] visited = new boolean[vtces];
    boolean path = hasPath(graph, src, dest, visited);

    System.out.println(path);

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

    boolean[] visited = new boolean[vtces];

    printAllPaths(graph, src, dest, visited, src + "");

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
    boolean[] visited = new boolean[vtces];for(
    int v = 0;v<vtces;v++)
    {
        if (visited[v] == false) {
            ArrayList<Integer> comp = new ArrayList<>();
            drawTree(graph, v, comp, visited);
            comps.add(comp);
        }
    }

    System.out.println(comps);

 public static void drawTree (ArrayList<Edge>[] graph, int src,  ArrayList<Integer> comp, boolean[] visited ){
     visited[src] = true;
     comp.add(src);
     for(Edge e: graph[src] ){
         if(visited[e.nbr]== false ){
             drawTree(graph,e.nbr, comp, visited);
         }
     }
 }

    // Is Graph Connected
    boolean[] visited = new boolean[vtces];for(
    int v = 0;v<vtces;v++)
    {
        if (visited[v] == false) {
            ArrayList<Integer> comp = new ArrayList<>();
            drawTree(graph, v, comp, visited);
            System.out.println(comps.size() == 1);
        }
    }

    System.out.println(comps);

    public static void drawTree (ArrayList<Edge>[] graph, int src,  ArrayList<Integer> comp, boolean[] visited ){
         visited[src] = true;
         comp.add(src);
         for(Edge e: graph[src] ){
             if(visited[e.nbr]== false ){
                 drawTree(graph,e.nbr, comp, visited);
             }
         }
     }

    // Number Of Islands
    boolean[][] visited = new boolean[arr.length][arr[0].length];
    int count = 0;for(
    int i = 0;i<arr.length;i++)
    {
        for (int j = 0; j < arr[i].length; j++) {
            if (arr[i][j] == 0 && visited[i][j] == false) {
                drawTree(arr, i, j, visited);
                count++;
            }
        }
    }System.out.println(count);

 public static void drawTree(int[][] arr, int i, int j, boolean[][] visited ){
     if( i<0 || j<0 || i>=arr.length || j>=arr[0].length || arr[i][j] == 1 || visited[i][j] == true){
         return;
     }
     
     visited[i][j] = true;
     drawTree(arr, i-1, j, visited);
     drawTree(arr, i, j+1, visited);
     drawTree(arr, i, j-1, visited);
     drawTree(arr, i+1, j, visited);
 }

    // Perfect Friends
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  
        int n = Integer.parseInt(br.readLine());
        int k = Integer.parseInt(br.readLine());
        
        // write your code here
        ArrayList<Edge> [] graph = new ArrayList[n];
        for(int v =0; v<n;v++){
            graph[v] = new ArrayList<>();
        }
        for(int e=0; e<k;e++){
            String line = br.readLine();
            String[] parts = line.split(" ");
            
            int v1 = Integer.parseInt(parts[0]);
            int v2 = Integer.parseInt(parts[1]);
            
            graph[v1].add(new Edge(v1,v2));
            graph[v2].add(new Edge(v2,v1));
        }
        
        boolean[] visited = new boolean[n];
        ArrayList<ArrayList<Integer>> comps = new ArrayList<>();
        for(int v=0; v<n; v++){
            if(visited[v] == false ){
                ArrayList<Integer> comp = new ArrayList<>();
                drawTree(graph, v, comp, visited);
                comps.add(comp);
            }
        }
        
        int pairs = 0;
        
        for(int i =0; i<comps.size();i++ ){
            for(int j =i+1; j<comps.size();j++ ){
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
        // r m* w a*(remove,visit,work,add)
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

}
