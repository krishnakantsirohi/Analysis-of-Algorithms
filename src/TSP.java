import java.io.*;
import java.util.*;

public class TSP {
    static int V,E;
    public static LinkedList<Integer> gTree[];

    public static HashMap<Integer,Node> edgeHashMap = new HashMap<>();

    public static HashMap<String,Integer> graphHashMap = new HashMap<>();

    public TSP(int V)
    {
        gTree = new LinkedList[V];
        for (int i=0;i<V;i++) {
            gTree[i] = new LinkedList();
        }
    }

    public static void main(String[] args) throws IOException
    {
        File f1 = new File("KruskalDFS_Time.txt");
        if (!f1.exists())
            f1.createNewFile();
        File f2 = new File("KruskalUnionFind_Time.txt");
        if (!f2.exists())
            f2.createNewFile();
        File f3 = new File("KruskalUFPC_Time.txt");
        if (!f3.exists())
            f3.createNewFile();
        File f4 = new File("primsPQ_Time.txt");
        if (!f4.exists())
            f4.createNewFile();
        File f5 = new File("primsSortedList_Time.txt");
        if (!f5.exists())
            f5.createNewFile();
        File f6 = new File("primsUnsortedList_Time.txt");
        if (!f6.exists())
            f6.createNewFile();
        BufferedWriter dfstimeWriter = new BufferedWriter(new FileWriter(f1));
        BufferedWriter UFtimeWriter = new BufferedWriter(new FileWriter(f2));
        BufferedWriter UFPCtimeWriter = new BufferedWriter(new FileWriter(f3));
        BufferedWriter primsPQtimeWriter = new BufferedWriter(new FileWriter(f4));
        BufferedWriter primsSLtimeWriter = new BufferedWriter(new FileWriter(f5));
        BufferedWriter primsULtimeWriter = new BufferedWriter(new FileWriter(f6));
        {
            Scanner scwi = new Scanner(System.in);
            System.out.println("MST and TSP for Metric Graphs with MST Heuristic");
            int sw = 0;
            while (true)
            {
                System.out.println("Please select from the following options:-");
                System.out.println("1. Kruskal with DFS");
                System.out.println("2. Kruskal with Union-Find");
                System.out.println("3. Kruskal with Union-Find & Path compression");
                System.out.println("4. Prims with Priority Queue");
                System.out.println("5. Prims with Sorted List");
                System.out.println("6. Prims with Unsorted List");
                System.out.println("7. Generate TSP for all.");
                System.out.println("8. Exit");
                sw = scwi.nextInt();
                if (sw == 8) {
                    System.out.println("Exiting...");
                    return;
                }
                System.out.print("Enter the no of vertices in the graph:");
                V = scwi.nextInt();
                File inFile = new File("inputFiles/input" + V + ".in");
                Scanner sc = new Scanner(new FileInputStream(inFile));
                Kruskal kruskal = new Kruskal();
                Prims prims = new Prims(V);
                int E = (V * (V - 1)) / 2;
                V = sc.nextInt();
                E = sc.nextInt();
                Graph graph = new Graph(V, E);
                graph.V = V;
                graph.E = E;
                int index = 0;
                while (sc.hasNextLine()) {
                    graph.edge[index].src = new Node(sc.nextInt(), sc.nextInt(), sc.nextInt());
                    graph.edge[index].dest = new Node(sc.nextInt(), sc.nextInt(), sc.nextInt());
                    graph.edge[index].weight = sc.nextInt();
                    index++;
                }
                Arrays.sort(graph.edge);
                long start, end;
                switch (sw) {
                    case 1:
                        start = System.currentTimeMillis();
                        System.out.println("\nWorking on Kruskal with DFS...");
                        kruskal.DFS(graph);
                        RunTSP(V, "KruskalDFS");
                        end = System.currentTimeMillis();
                        dfstimeWriter.append(V + " " + String.valueOf(end - start) + "\n");
                        dfstimeWriter.flush();
                        System.out.println("Check the output in directory: TSP_KruskalDFS_Out");
                        break;
                    case 2:
                        start = System.currentTimeMillis();
                        System.out.println("\nWorking on Kruskal with Union-Find...");
                        kruskal.UnionFind(graph);
                        RunTSP(V, "KruskalUnionFind");
                        end = System.currentTimeMillis();
                        UFtimeWriter.append(V + " " + String.valueOf(end - start) + "\n");
                        UFtimeWriter.flush();
                        System.out.println("Check the output in directory: TSP_KruskalUnionFind_Out");
                        break;
                    case 3:
                        start = System.currentTimeMillis();
                        System.out.println("\nWorking on Kruskal with Union-Find & Path compression....");
                        kruskal.UFPC(graph);
                        RunTSP(V, "KruskalUFPC");
                        end = System.currentTimeMillis();
                        UFPCtimeWriter.append(V + " " + String.valueOf(end - start) + "\n");
                        UFPCtimeWriter.flush();
                        System.out.println("Check the output in directory: TSP_KruskalUFPC_Out");
                        break;
                    case 4:
                        start = System.currentTimeMillis();
                        System.out.println("\nWorking on Prims with Priority Queue....");
                        prims.prims_heap(graph);
                        RunTSP(V, "PrimsPriorityQueue");
                        end = System.currentTimeMillis();
                        primsPQtimeWriter.append(V + " " + String.valueOf(end - start) + "\n");
                        primsPQtimeWriter.flush();
                        System.out.println("Check the output in directory: TSP_PrimsPriorityQueue_Out");
                        break;
                    case 5:
                        start = System.currentTimeMillis();
                        System.out.println("\nWorking on Prims with Sorted List....");
                        prims.prims_sorted(graph);
                        RunTSP(V, "PrimsSortedList");
                        end = System.currentTimeMillis();
                        primsSLtimeWriter.append(V + " " + String.valueOf(end - start) + "\n");
                        primsSLtimeWriter.flush();
                        System.out.println("Check the output in directory: TSP_PrimsSortedList_Out");
                        break;
                    case 6:
                        start = System.currentTimeMillis();
                        System.out.println("\nWorking on Prims with Unsorted List....");
                        prims.prims_unsorted(graph);
                        RunTSP(V, "PrimsUnsortedList");
                        end = System.currentTimeMillis();
                        primsULtimeWriter.append(V + " " + String.valueOf(end - start) + "\n");
                        primsULtimeWriter.flush();
                        System.out.println("Check the output in directory: TSP_PrimsUnsortedList_Out");
                        break;
                    case 7:
                        start = System.currentTimeMillis();
                        System.out.println("\nWorking on Kruskal with DFS...");
                        kruskal.DFS(graph);
                        RunTSP(V, "KruskalDFS");
                        end = System.currentTimeMillis();
                        dfstimeWriter.append(V + " " + String.valueOf(end - start) + "\n");
                        dfstimeWriter.flush();
                        System.out.println("Check the output in directory: TSP_KruskalDFS_Out");
                        start = System.currentTimeMillis();
                        System.out.println("Working on Kruskal with Union-Find...");
                        kruskal.UnionFind(graph);
                        RunTSP(V, "KruskalUnionFind");
                        end = System.currentTimeMillis();
                        UFtimeWriter.append(V + " " + String.valueOf(end - start) + "\n");
                        UFtimeWriter.flush();
                        System.out.println("Check the output in directory: TSP_KruskalUnionFind_Out");
                        start = System.currentTimeMillis();
                        System.out.println("Working on Kruskal with Union-Find & Path compression....");
                        kruskal.UFPC(graph);
                        RunTSP(V, "KruskalUFPC");
                        end = System.currentTimeMillis();
                        UFPCtimeWriter.append(V + " " + String.valueOf(end - start) + "\n");
                        UFPCtimeWriter.flush();
                        System.out.println("Check the output in directory: TSP_KruskalUFPC_Out");
                        start = System.currentTimeMillis();
                        System.out.println("Working on Prims with Priority Queue....");
                        prims.prims_heap(graph);
                        RunTSP(V, "PrimsPriorityQueue");
                        end = System.currentTimeMillis();
                        primsPQtimeWriter.append(V + " " + String.valueOf(end - start) + "\n");
                        primsPQtimeWriter.flush();
                        System.out.println("Check the output in directory: TSP_PrimsPriorityQueue_Out");
                        start = System.currentTimeMillis();
                        System.out.println("Working on Prims with Sorted List....");
                        prims.prims_sorted(graph);
                        RunTSP(V, "PrimsSortedList");
                        end = System.currentTimeMillis();
                        primsSLtimeWriter.append(V + " " + String.valueOf(end - start) + "\n");
                        primsSLtimeWriter.flush();
                        System.out.println("Check the output in directory: TSP_PrimsSortedList_Out");
                        start = System.currentTimeMillis();
                        System.out.println("Working on Prims with Unsorted List....");
                        prims.prims_unsorted(graph);
                        RunTSP(V, "PrimsUnsortedList");
                        end = System.currentTimeMillis();
                        primsULtimeWriter.append(V + " " + String.valueOf(end - start) + "\n");
                        primsULtimeWriter.flush();
                        System.out.println("Check the output in directory: TSP_PrimsUnsortedList_Out");
                        break;
                    default:
                        System.out.println("Invalid selection!");
                        break;
                }
            }
        }
    }

    public static void RunTSP(int len, String algo) throws IOException
    {
        Scanner sc = new Scanner(new FileInputStream(new File("Output"+algo+"/output" + len + ".out")));
        Scanner scin = new Scanner(new FileInputStream(new File("inputFiles/input" + len + ".in")));
        int V = len, E = len, i = 0;
        Graph graph = new Graph(V, E);
        TSP tsp = new TSP(V);
        Boolean[] visited = new Boolean[V];
        int j = 0;
        Graph g1 = new Graph(scin.nextInt(), scin.nextInt());
        g1.E = (g1.V * (g1.V - 1)) / 2;
        while (scin.hasNextLine() && j < g1.E) {
            g1.edge[j].src = new Node(scin.nextInt(), scin.nextInt(), scin.nextInt());
            g1.edge[j].dest = new Node(scin.nextInt(), scin.nextInt(), scin.nextInt());
            g1.edge[j].weight = scin.nextInt();
            pair p = new pair(g1.edge[j].src, g1.edge[j].dest);
            graphHashMap.put(p.toString(), g1.edge[j].weight);
            edgeHashMap.put(g1.edge[j].src.id, g1.edge[j].src);
            edgeHashMap.put(g1.edge[j].dest.id, g1.edge[j].dest);
            j++;
        }

        while (sc.hasNextLine() && i < V - 1) {
            graph.edge[i].src = new Node(sc.nextInt(), sc.nextInt(), sc.nextInt());
            graph.edge[i].dest = new Node(sc.nextInt(), sc.nextInt(), sc.nextInt());
            graph.edge[i].weight = sc.nextInt();
            gTree[graph.edge[i].src.id].add(graph.edge[i].dest.id);
            gTree[graph.edge[i].dest.id].add(graph.edge[i].src.id);
            visited[i] = false;
            i++;
        }
        visited[i] = false;
        BufferedWriter writer = new BufferedWriter(new FileWriter(new File("TSP_intermediate/"+algo+"/output" + len + ".out")));
        writer.write(edgeHashMap.get(graph.edge[0].src.id).toString());
        tsp.preOrder(visited, graph.edge[0].src.id, writer);
        writer.write(" " + edgeHashMap.get(graph.edge[0].src.id));
        writer.flush();
        Scanner scout = new Scanner(new FileInputStream(new File("TSP_intermediate/"+algo+"/output" + len + ".out")));
        Graph graph2 = new Graph(V, E);
        int z = 0;
        BufferedWriter writer1 = new BufferedWriter(new FileWriter(new File("TSP_"+algo+"_Out/output" + len + ".out")));
        while (scout.hasNextLine()) {
            graph2.edge[z].src = new Node(scout.nextInt(), scout.nextInt(), scout.nextInt());
            graph2.edge[z].dest = new Node(scout.nextInt(), scout.nextInt(), scout.nextInt());
            pair p = new pair(graph2.edge[z].src, graph2.edge[z].dest);
            if (graphHashMap.get(p.toString()) == null)
                p = new pair(graph2.edge[z].dest, graph2.edge[z].src);
            graph2.edge[z].weight = graphHashMap.get(p.toString());
            writer1.write(graph2.edge[z].src.toString() + " " + graph2.edge[z].dest.toString() + " " + graph2.edge[z].weight + "\n");
            writer1.flush();
        }
    }

    public static void preOrder(Boolean[] visited, int n, BufferedWriter writer) throws IOException
    {
        visited[n] = true;
        int i;
        Iterator<Integer> it = gTree[n].iterator();
        while (it.hasNext())
        {
            i = it.next();
            if (!visited[i]) {
                pair p = new pair(edgeHashMap.get(n),edgeHashMap.get(i));
                writer.append(" "+edgeHashMap.get(i)+" \n"+edgeHashMap.get(i));
                writer.flush();
                preOrder(visited, i,writer);
            }
        }
    }
}
