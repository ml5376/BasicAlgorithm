import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class DPQ {
    private int dist[];
    private Set<Integer> settled;
    private PriorityQueue<Node> pq;
    private int V;
    List<List<Node> > adj;

    public DPQ(int V)
    {
        this.V = V;
        dist = new int[V];

        settled = new HashSet<Integer>();
        pq = new PriorityQueue<Node>(V, new Node());
        for(int i=0; i<V;i++) {

            this.dist[i] = Integer.MAX_VALUE;
        }


    }


    public void dijkstra(List<List<Node> > adj, int src)
    {
        this.adj = adj;



        Node a=new Node(src,dist[src]);

        pq.add(a);



        dist[src] =0;
        while (!pq.isEmpty()) {
            int u=0;

            Node n = pq.poll();

            if(n!=null)
            {u=n.node;


                settled.add(u);


                e_Neighbours(n); }
        }
    }


    private void e_Neighbours(Node n)
    {
        int edgeDistance = -1;
        int newDistance = -1;
        int p1=0;
        int p2=0;


        for (int i = 0; i < adj.get(n.node).size(); i++) {
            Node v = adj.get(n.node).get(i);



            if (!settled.contains(v.node)) {
                edgeDistance = v.edge;

                newDistance = dist[n.node] + edgeDistance;

                if (newDistance < dist[v.node])
                {        dist[v.node] = newDistance; v.cost=newDistance;

                }

                pq.add(new Node(v.node, dist[v.node]));
            }
        }
    }

    // Driver code
    public static void main(String arg[])
    {

        Scanner in = new Scanner(System.in);
        int []mn=new int[2];
        for(int i=0;i<2;i++) {
            mn[i]=in.nextInt();

        }
        int V = mn[0];
        int source = 0;

        List<List<Node> > adj = new ArrayList<List<Node> >();


        for (int i = 0; i < 2*V; i++) {
            List<Node> item = new ArrayList<Node>();
            adj.add(item);
        }

        for(int i=1;i<=mn[1];i++){

            int a =in.nextInt()-1;int b=in.nextInt()-1;int c=in.nextInt();
            if(c==1){adj.get(a).add(new Node(b,0));}
            if(c==2){adj.get(a).add(new Node(b,1));}
            if(c==1){adj.get(V+a).add(new Node(V+b,0));}
            if(c==2){adj.get(V+a).add(new Node(V+b,1));}
            if(c==1) {adj.get(a).add(new Node(b+V,0));}

        }

        DPQ dpq = new DPQ(2*V);
        dpq.dijkstra(adj, source);

        // for (int i = 0; i < dpq.dist.length; i++)
        //  { System.out.println((source+1) + " to " + (i+1)+ " is "
        //   + dpq.dist[i]+"-"+dpq.path[i]);}

        if(dpq.dist[2*V-1]==Integer.MAX_VALUE) {System.out.println(-1);}
        else {System.out.println(dpq.dist[2*V-1]);}

    }
}


class Node implements Comparator<Node> {
    public int node;
    public int cost;
    public int edge;
    public int PathTwo;
    public int cost_with_res;


    public Node()
    {
    }

    public Node(int node, int cost)
    {     this.edge=cost;
        this.node = node;
        this.cost = cost;
        if(this.edge==2) {this.PathTwo=1;}

    }

    @Override
    public int compare(Node node1, Node node2)
    {
        if (node1.cost < node2.cost)
            return -1;
        if (node1.cost > node2.cost)
            return 1;
        return 0;
    }
}