import java.io.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;

class Node{
    int id,x,y;
    public Node()
    {
        this.id = 0;
        this.x = 0;
        this.y = 0;
    }
    public Node(int id,int x,int y)
    {
        this.id = id;
        this.x = x;
        this.y = y;
    }
    @Override
    public String toString()
    {
        return this.id+" "+this.x+" "+this.y;
    }
}
class pair {
    Node s,d;
    public pair(Node s,Node d)
    {
        this.s = s;
        this.d = d;
    }
    @Override
    public String toString()
    {
        return this.s+" "+this.d;
    }
}

class Edge implements Comparable<Edge>{
    Node src = new Node();
    Node dest = new Node();

    int weight = 0;
    @Override
    public int compareTo(Edge edge) {
        return this.weight-edge.weight;
    }
    @Override
    public String toString(){
        return this.src.toString()+" "+this.dest.toString()+" "+this.weight;
    }
}

public class Graph {

    static int V,E;

    Edge edge[];
    LinkedList<node1>[] adj;

    public Graph(int v, int e){
        V = v;
        E = e;
        edge = new Edge[E];
        adj = new LinkedList[E];
        for (int i=0;i<e;i++) {
            edge[i] = new Edge();
            adj[i] = new LinkedList<>();
        }
    }

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);

        //for (int j = 164; j <= 200; j++) {
            System.out.print("Enter the number of vertices:");
            int vertices = sc.nextInt();

            int edges = (vertices * (vertices - 1)) / 2;

            Graph graph = new Graph(vertices, edges);

            Random random = new Random();

            HashMap<Integer, Node> point = new HashMap<>();

            int[][] edgeMap = new int[edges][edges];

            int count = 0;


            File file = new File("inputFiles/input" + vertices + ".in");
            if (!file.exists())
                file.createNewFile();

            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));

            for (int i = 0; i < edges; i++, count++) {
                int srcid = random.nextInt(vertices);
                int destid = random.nextInt(vertices);

                Node src = new Node(srcid, random.nextInt(vertices), random.nextInt(vertices));
                Node dest = new Node(destid, random.nextInt(vertices), random.nextInt(vertices));

                while (edgeMap[srcid][destid] == 1 || edgeMap[destid][srcid] == 1 || srcid == destid) {
                    srcid = random.nextInt(vertices);
                    destid = random.nextInt(vertices);
                }

                if (point.get(srcid) == null)
                    src = new Node(srcid, random.nextInt(vertices), random.nextInt(vertices));
                else
                    src = point.get(srcid);
                if (point.get(destid) == null)
                    dest = new Node(destid, random.nextInt(vertices), random.nextInt(vertices));
                else
                    dest = point.get(destid);

                graph.edge[i].src = src;
                graph.edge[i].dest = dest;
                int x1 = graph.edge[i].src.x;
                int y1 = graph.edge[i].src.y;
                int x2 = graph.edge[i].dest.x;
                int y2 = graph.edge[i].dest.y;
                graph.edge[i].weight = (int)Math.sqrt(Math.abs(Math.pow((x2-x1),2)+Math.pow((y2-y1),2)));

                point.put(srcid, graph.edge[i].src);
                point.put(destid, graph.edge[i].dest);

                edgeMap[srcid][destid] = 1;
                edgeMap[destid][srcid] = 1;

                //System.out.println("Node " + i + "=" + graph.edge[i]);
            }
            bufferedWriter.write(vertices + " " + edges);
            bufferedWriter.flush();
            for (Edge e : graph.edge) {
                bufferedWriter.write("\n" + e.toString());
                bufferedWriter.flush();
            }
            System.out.println("\nGraph generated Succefully!");
        //}
    }
}
