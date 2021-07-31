package Foundation;

import java.io.*;
import java.util.*;

public class Graphs {

    //hasPath
    boolean[] visited = new boolean[vtces];
    boolean path = hasPath(graph, src, dest, visited);

    System.out.println(path);

    public static boolean hasPath(ArrayList<Edge>[] graph, int src, int dest, boolean[] visited ){

        if(src==dest){
            return true;
        }

        visited[src] = true;
        for(Edge edge : graph[src] ){
            if(visited[edge.nbr] == false){
                boolean hasNbrPath = hasPath(graph, edge.nbr, dest, visited);
                if(hasNbrPath == true){
                    return true;
                }
            }
        }
        return false;
    }

    //Print all paths

}
