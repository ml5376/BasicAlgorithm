import java.io.*;
import java.util.*;

public class Solution {

    static int WHITE = 0, GRAY = 1, BLACK = 2;

    static class Graph
    {
        int V;
        LinkedList<Integer>[] adjList;

        Graph(int ver)
        {
            V = ver;
            adjList = new LinkedList[V];
            for (int i = 0; i < V; i++)
                adjList[i] = new LinkedList<>();

        }
    }

    static void addEdge(Graph g, int u, int v)
    {
        g.adjList[u].add(v);
    }

    static boolean DFSUtil(Graph g, int u, int[] color,int[] predecessor,LinkedList<Integer> c)
    {

        color[u] = GRAY;

        for (Integer in : g.adjList[u])
        {

            if (color[in] == GRAY)
            {
                int start=in+1;
                int end=u+1;

                c.add(u+1);
                int curr=predecessor[end-1];
                while(curr!=start) {
                    c.addFirst(curr);
                    curr=predecessor[curr-1];
                }
                c.addFirst(start);

                return true; }


            predecessor[in] = u+1;
            if (color[in] == WHITE && DFSUtil(g, in, color,predecessor,c) == true)
                return true;
        }

        color[u] = BLACK;
        return false;
    }


    static boolean isCyclic(Graph g, int[]predecessor,LinkedList<Integer> c)
    {
        // Initialize color of all vertices as WHITE
        int[] color = new int[g.V];
        for (int i = 0; i < g.V; i++)
        {
            color[i] = WHITE;
        }

        // Do a DFS traversal beginning with all
        // vertices
        for (int i = 0; i < g.V; i++)
        {
            if (color[i] == WHITE)
            {
                if(DFSUtil(g, i, color,predecessor,c) == true)
                    return true;
            }
        }
        return false;

    }

    // Driver code to test above
    public static void main(String args[])
    {
        LinkedList<Integer> c=new LinkedList<>();

        Scanner in= new Scanner(System.in);
        int []mn=new int[2];


        for(int i=0;i<2;i++) {

            mn[i]=in.nextInt();

        }

        int v=mn[0];
        Graph g=new Graph(v);
        int[]predecessor=new int[v];
        for(int i=0;i<predecessor.length;i++) {predecessor[i]=-1;}
        for(int i=1;i<=mn[1];i++){
            addEdge(g,in.nextInt()-1,in.nextInt()-1);
        }

        if (isCyclic(g,predecessor,c))
        {System.out.println("1");
            for(int i:c) {System.out.print(i+" ");}}

        else
            System.out.println("0");
    }
}