import java.io.*;
import java.util.*;

public class Solution {

    public static void main(String args[])
    {

        Scanner in= new Scanner(System.in);
        int []mn=new int[2];


        for(int i=0;i<2;i++) {

            mn[i]=in.nextInt();

        }

        int v=mn[0];
        Graph g=new Graph(v);
        for(int i=1;i<=mn[1];i++){
            g.addEdge(in.nextInt(),in.nextInt());
        }


        g.topologicalSort();


    }
}
class Graph
{
    int V;


    List <Integer> adj[];
    public Graph(int V)
    {
        this.V = V;
        adj = new ArrayList[V];
        for(int i = 0; i < V; i++)
            adj[i]=new ArrayList<Integer>();
    }


    public void addEdge(int u,int v)
    {
        adj[u-1].add(v-1);

    }



    public void topologicalSort()
    {

        int indegree[] = new int[V];


        for(int i = 0; i < V; i++)
        {
            ArrayList<Integer> temp = (ArrayList<Integer>) adj[i];
            for(int node : temp)
            {
                indegree[node]++;
            }
        }


        PriorityQueue<Integer> q = new PriorityQueue<Integer>();
        for(int i = 0;i < V; i++)
        {
            if(indegree[i]==0)
                q.add(i);

        }

        int cnt = 0;


        Vector <Integer> topOrder=new Vector<Integer>();
        while(!q.isEmpty())
        {

            int u=q.poll();
            topOrder.add(u);


            for(int node : adj[u])
            {

                if(--indegree[node] == 0)
                    q.add(node);
            }
            cnt++;
        }


        if(cnt != V)
        {
            System.out.println(-1);
            return ;
        }


        for(int i : topOrder)
        {
            System.out.print((i+1)+" ");
        }



    }
}