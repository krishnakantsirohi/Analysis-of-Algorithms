import java.io.*;
import java.time.Clock;
import java.util.*;


public class Kruskal {

    private static int V,E;

    public void DFS(Graph graph) throws IOException
    {
            File outFile = new File("OutputKruskalDFS/output" + graph.V + ".out");
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(outFile));
            DFSTest dfsTest = new DFSTest(graph.V);
            for (int i = 0, count = 0; i < graph.edge.length && count < graph.V - 1; i++) {
                dfsTest.addEdge(graph.edge[i].src.id, graph.edge[i].dest.id);
                if (!dfsTest.isCyclic()) {
                    bufferedWriter.write(graph.edge[i].toString() + "\n");
                    count++;
                } else
                    dfsTest.removeEdge(graph.edge[i].src.id, graph.edge[i].dest.id);
                bufferedWriter.flush();
            }

        }

    public void UFPC(Graph graph) throws IOException
    {
        File outFile = new File("OutputKruskalUFPC/output" + graph.V + ".out");
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(outFile));
        UFPC ufpc = new UFPC();
        ufpc.MST(graph,bufferedWriter);
    }

    public void UnionFind(Graph graph) throws IOException
    {
            File outFile = new File("OutputKruskalUnionFind/output" + graph.V + ".out");
            if (!outFile.exists())
                outFile.createNewFile();
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(outFile));
            UnionFind unionFind = new UnionFind();
            unionFind.MST(graph,bufferedWriter);
    }

   /* public static void main(String[] args) throws IOException {
        Kruskal kruskal = new Kruskal();
        System.out.println("MST and TSP for Metric Graphs with MST Heuristic");
        Scanner sc = new Scanner(System.in);
        int sw = 0,V;
        while (true) {
            System.out.println("Please select from the following options:-");
            System.out.println("1. Kruskal with DFS");
            System.out.println("2. Kruskal with Union-Find");
            System.out.println("3. Kruskal with Union-Find & Path compression");
            System.out.println("4. Exit");
            sw = sc.nextInt();
            System.out.println("Enter the no of vertices in the graph:");
            V = sc.nextInt();
            switch (sw) {
                case 1:
                    System.out.println("Working on Kruskal with DFS...");
                    kruskal.DFS(V);
                    break;
                case 2:
                    System.out.println("Working on Kruskal with Union-Find...");
                    kruskal.UnionFind(V);
                    break;
                case 3:
                    System.out.println("Working on Kruskal with Union-Find & Path compression....");
                    kruskal.UFPC(V);
                    break;
                case 4:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid selection!");
                    break;
            }
        }

    }*/

}
