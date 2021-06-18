// nqueens(combination)

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        int tnq=4;
        boolean vis[][] = new boolean[4][4];
        vis[0][0]=false;
        System.out.println (nqueen_comb(vis,tnq,0,""));
        

    }
   public static boolean isSafeToPlaceQueen(boolean[][]vis , int row, int col)
    {
        int[][] dir ={{0, -1}, {-1, -1}, {-1, 0}, {-1, 1}};
         int n= vis.length;
        int m= vis[0].length;
        for(int d=0;d<dir.length;d++)
        {
        for(int rad=1;rad<vis.length;rad++)    
            {
            int r = row+rad* dir[d][0];
            int c = col+rad* dir[d][1];
            if(r>=0 && c>=0 && r<n && c<m )
            {
                if(vis[r][c])
                return false;
            }
            else
            break;
            
            }
        }
        return true;
    }

    public static int nqueen_comb(boolean[][] vis ,int tnq, int idx , String asf) 
    {
        if(tnq==0)
        {
            System.out.println(asf);
            return 1 ;
        }
        
        int count =0;
        int n= vis.length;
        int m= vis[0].length;
        for (int i=idx; i<n*m;i++)
        {
            int r = i/m;
            int c = i%m;
            if(isSafeToPlaceQueen(vis,r,c))
            {
                vis[r][c]=true;
                count += nqueen_comb(vis, tnq-1,i+1,asf+"(" + Integer.toString(r)+","+Integer.toString(c)+")");
                vis[r][c]=false;
            }
        }
        
        return count;
        
    }

}


// nqueens(permutation)

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        int tnq=4;
        boolean vis[][] = new boolean[4][4];
        vis[0][0]=false;
        System.out.println (nqueen(vis,tnq,0,""));
        

    }
   public static boolean isSafeToPlaceQueen(boolean[][]vis , int row, int col)
    {
        int[][] dir ={{0, -1}, {-1, -1}, {-1, 0}, {-1, 1}, {0, 1}, {1, 1}, {1, 0}, {1, -1}};
         int n= vis.length;
        int m= vis[0].length;
        for(int d=0;d<dir.length;d++)
        {
        for(int rad=1;rad<vis.length;rad++)    
            {
            int r = row+rad* dir[d][0];
            int c = col+rad* dir[d][1];
            if(r>=0 && c>=0 && r<n && c<m )
            {
                if(vis[r][c])
                return false;
            }
            else
            break;
            
            }
        }
        return true;
    }

    public static int nqueen(boolean[][] vis ,int tnq, int idx , String asf) 
    {
        if(tnq==0)
        {
            System.out.println(asf);
            return 1 ;
        }
        
        int count =0;
        int n= vis.length;
        int m= vis[0].length;
        for (int i=idx; i<n*m;i++)
        {
            int r = i/m;
            int c = i%m;
            if(isSafeToPlaceQueen(vis,r,c) && !vis[r][c])
            {
                vis[r][c]=true;
                count += nqueen(vis, tnq-1,0,asf+"(" + Integer.toString(r)+","+Integer.toString(c)+")");
                vis[r][c]=false;
            }
        }
        
        return count;
        
    }

}