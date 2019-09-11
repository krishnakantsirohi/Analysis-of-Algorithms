import java.io.*;
import java.util.*;

class node1 {

    int node_id;
    int node_x;
    int node_y;
    int w;

    node1(int id,int x,int y, int w)
    {
        node_id = id;
        node_x = x;
        node_y = y;
        this.w = w;
    }
}

public class Prims {

    int V,E;

    public static HashMap<String,Integer> graphHashMap = new HashMap<>();

    public static HashMap<Integer,Node> edgeHashMap = new HashMap<>();

    public Prims(int V){
        this.V = V;
    }

    @SuppressWarnings("rawtypes")
    class node implements Comparable  {
        int vertex;
        int key;
        @Override
        public int compareTo(Object o) {
            // TODO Auto-generated method stub
            node obj = (node)o;
            return this.key - obj.key;
        }
    }

    class comparator implements Comparator<node> {
        @Override
        public int compare(node node0, node node1)
        {
            return node0.key - node1.key;
        }
    }

    void addEdge(Graph graph, int sid,int sx,int sy,int did,int dx, int dy, int w)
    {

        node1 node0 = new node1(did,dx,dy, w);
        node1 node = new node1(sid,dx,dy, w);
        graph.adj[sid].addLast(node0);
        graph.adj[did].addLast(node);
    }

    void prims_sorted(Graph graph) throws IOException
    {
        Scanner sc = new Scanner(new FileInputStream(new File("inputFiles/input"+graph.V+".in")));
        String st;
        st = sc.nextLine();
        String[] v = st.split(" ");
        V = Integer.parseInt(v[0]);
        E = Integer.parseInt(v[1]);
        //sc.nextLine();
        Prims prims = new Prims(V);
        Graph graph1 = new Graph(V,E);
        int i=0;
        while (sc.hasNextLine())
        {
            graph1.edge[i].src = new Node(sc.nextInt(),sc.nextInt(),sc.nextInt());
            graph1.edge[i].dest = new Node(sc.nextInt(),sc.nextInt(),sc.nextInt());
            graph1.edge[i].weight = sc.nextInt();
            edgeHashMap.put(graph1.edge[i].src.id,graph1.edge[i].src);
            edgeHashMap.put(graph1.edge[i].dest.id,graph1.edge[i].dest);
            pair p = new pair(graph1.edge[i].src,graph1.edge[i].dest);
            graphHashMap.put(p.toString(),graph1.edge[i].weight);
            prims.addEdge(graph, graph1.edge[i].src.id,graph1.edge[i].src.x,graph1.edge[i].src.y,graph1.edge[i].dest.id,graph1.edge[i].dest.x,graph1.edge[i].dest.y
                    ,graph1.edge[i].weight);
            i++;
        }
        Boolean[] mstset = new Boolean[graph.V];
        node[] e = new node[graph.V];

        int[] parent = new int[graph.V];

        for (i = 0; i < graph.V; i++)
            e[i] = new node();

        for (i = 0; i < graph.V; i++) {

            mstset[i] = false;

            e[i].key = Integer.MAX_VALUE;
            e[i].vertex = i;
            parent[i] = -1;
        }

        mstset[0] = true;

        e[0].key = 0;


        List<node> list_node = new ArrayList<node>();

        for (i = 0; i < graph.V; i++)
            list_node.add(e[i]);

        for(i = 0; i < list_node.size(); i ++)
        {
            node node0 = (node)list_node.get(i);
            mstset[node0.vertex] = true;

            for (node1 n : graph.adj[node0.vertex]) {


                if (mstset[n.node_id] == false)
                {
                    if (e[n.node_id].key > n.w) {

                        list_node.remove(e[n.node_id]);
                        e[n.node_id].key = n.w;
                        list_node.add(e[n.node_id]);
                        parent[n.node_id] = node0.vertex;
                    }
                }
            }

        }
        System.out.println("=====================================");
        System.out.println("Storing edges as Sorted Array");
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(new File("OutputPrimsSortedList/output"+graph.V+".out")));
        for (int o = 1; o < graph.V; o++){
            System.out.println("MST Includes Ege from " + parent[o] + " to  " + o);
            pair p = new pair(edgeHashMap.get(parent[o]),edgeHashMap.get(o));
            if (graphHashMap.get(p.toString())==null)
                p = new pair(edgeHashMap.get(o),edgeHashMap.get(parent[o]));
            bufferedWriter.write(p.toString()+" "+graphHashMap.get(p.toString())+"\n");
            bufferedWriter.flush();
        }

        System.out.println("=====================================");
    }

    void prims_unsorted(Graph graph) throws IOException
    {
        Scanner sc = new Scanner(new FileInputStream(new File("inputFiles/input"+graph.V+".in")));
        String st;
        st = sc.nextLine();
        String[] v = st.split(" ");
        V = Integer.parseInt(v[0]);
        E = Integer.parseInt(v[1]);
        //sc.nextLine();
        Prims prims = new Prims(V);
        Graph graph1 = new Graph(V,E);
        int i=0;
        while (sc.hasNextLine())
        {
            graph1.edge[i].src = new Node(sc.nextInt(),sc.nextInt(),sc.nextInt());
            graph1.edge[i].dest = new Node(sc.nextInt(),sc.nextInt(),sc.nextInt());
            graph1.edge[i].weight = sc.nextInt();
            edgeHashMap.put(graph1.edge[i].src.id,graph1.edge[i].src);
            edgeHashMap.put(graph1.edge[i].dest.id,graph1.edge[i].dest);
            pair p = new pair(graph1.edge[i].src,graph1.edge[i].dest);
            graphHashMap.put(p.toString(),graph1.edge[i].weight);
            prims.addEdge(graph, graph1.edge[i].src.id,graph1.edge[i].src.x,graph1.edge[i].src.y,graph1.edge[i].dest.id,graph1.edge[i].dest.x,graph1.edge[i].dest.y
                    ,graph1.edge[i].weight);
            i++;
        }
        Boolean[] mstset = new Boolean[graph.V];
        node[] e = new node[graph.V];

        int[] parent = new int[graph.V];

        for (i = 0; i < graph.V; i++)
            e[i] = new node();

        for (i = 0; i < graph.V; i++) {

            mstset[i] = false;

            e[i].key = Integer.MAX_VALUE;
            e[i].vertex = i;
            parent[i] = -1;
        }

        mstset[0] = true;

        e[0].key = 0;


        List<node> list_node = new ArrayList<node>();

        for (i = 0; i < graph.V; i++)
            list_node.add(e[i]);

        for(i = 0; i < list_node.size(); i ++)
        {
            node node0 = (node)list_node.get(i);
            mstset[node0.vertex] = true;

            for (node1 n : graph.adj[node0.vertex]) {


                if (mstset[n.node_id] == false)
                {
                    if (e[n.node_id].key > n.w) {

                        list_node.remove(e[n.node_id]);
                        e[n.node_id].key = n.w;
                        list_node.add(e[n.node_id]);
                        parent[n.node_id] = node0.vertex;
                    }
                }
            }

        }
        System.out.println("=====================================");
        System.out.println("Storing edges as Unsorted Array");
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(new File("OutputPrimsUnsortedList/output"+graph.V+".out")));
        for (int o = 1; o < graph.V; o++){
            System.out.println("MST Includes Ege from " + parent[o] + " to  " + o);
            pair p = new pair(edgeHashMap.get(parent[o]),edgeHashMap.get(o));
            if (graphHashMap.get(p.toString())==null)
                p = new pair(edgeHashMap.get(o),edgeHashMap.get(parent[o]));
            bufferedWriter.write(p.toString()+" "+graphHashMap.get(p.toString())+"\n");
            bufferedWriter.flush();
        }

        System.out.println("=====================================");
    }

    void prims_heap(Graph graph) throws IOException
    {
        Scanner sc = new Scanner(new FileInputStream(new File("inputFiles/input"+graph.V+".in")));
        String st;
        st = sc.nextLine();
        String[] v = st.split(" ");
        V = Integer.parseInt(v[0]);
        E = Integer.parseInt(v[1]);
        //sc.nextLine();
        Prims prims = new Prims(V);
        Graph graph1 = new Graph(V,E);
        int i=0;
        while (sc.hasNextLine())
        {
            graph1.edge[i].src = new Node(sc.nextInt(),sc.nextInt(),sc.nextInt());
            graph1.edge[i].dest = new Node(sc.nextInt(),sc.nextInt(),sc.nextInt());
            graph1.edge[i].weight = sc.nextInt();
            edgeHashMap.put(graph1.edge[i].src.id,graph1.edge[i].src);
            edgeHashMap.put(graph1.edge[i].dest.id,graph1.edge[i].dest);
            pair p = new pair(graph1.edge[i].src,graph1.edge[i].dest);
            graphHashMap.put(p.toString(),graph1.edge[i].weight);
            prims.addEdge(graph, graph1.edge[i].src.id,graph1.edge[i].src.x,graph1.edge[i].src.y,graph1.edge[i].dest.id,graph1.edge[i].dest.x,graph1.edge[i].dest.y
                    ,graph1.edge[i].weight);
            i++;
        }
        Boolean[] mstset = new Boolean[graph.V];
        node[] e = new node[graph.V];
        int[] parent = new int[graph.V];
        for (i = 0; i < graph.V; i++)
            e[i] = new node();
        for (i = 0; i < graph.V; i++) {
            mstset[i] = false;
            e[i].key = Integer.MAX_VALUE;
            e[i].vertex = i;
            parent[i] = -1;
        }

        mstset[0] = true;

        e[0].key = 0;

        PriorityQueue<node> queue = new PriorityQueue<>(graph.V, new comparator());

        for (i = 0; i < graph.V; i++)
            queue.add(e[i]);

        while (!queue.isEmpty())
        {
            node node0 = queue.poll();
            mstset[node0.vertex] = true;

            for (node1 n : graph.adj[node0.vertex]) {


                if (mstset[n.node_id] == false)
                {
                    if (e[n.node_id].key > n.w) {

                        queue.remove(e[n.node_id]);
                        e[n.node_id].key = n.w;
                        queue.add(e[n.node_id]);
                        parent[n.node_id] = node0.vertex;
                    }
                }
            }

        }
        System.out.println("=====================================");
        System.out.println("Storing edges as Heap");
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(new File("OutputPrimsPriorityQueue/output"+graph.V+".out")));
        for (int o = 1; o < graph.V; o++){
            System.out.println("MST Includes Ege from " + parent[o] + " to  " + o);
            pair p = new pair(edgeHashMap.get(parent[o]),edgeHashMap.get(o));
            if (graphHashMap.get(p.toString())==null)
                p = new pair(edgeHashMap.get(o),edgeHashMap.get(parent[o]));
            bufferedWriter.write(p.toString()+" "+graphHashMap.get(p.toString())+"\n");
            bufferedWriter.flush();
        }

        System.out.println("=====================================");
    }


    /*public static void main(String[] args) throws IOException
    {

        BufferedWriter timeWriter = new BufferedWriter(new FileWriter(new File("HEAP.txt")));
        long start,end;
        String file_name = args[0];
        File file = new File(file_name);
        BufferedReader br = new BufferedReader(new FileReader(file));
        String st;
        st = br.readLine();
        String[] v = st.split(" ");
        int V =  Integer.parseInt(v[0]);
        Graph graph = new Graph(V,(V*(V-1))/2);
        Prims e = new Prims(V);
        while ((st = br.readLine()) != null)
        {
            String temp[] = st.split(" ");
            e.addEdge(graph, Integer.parseInt(temp[0])
                    ,Integer.parseInt(temp[1])
                    ,Integer.parseInt(temp[2])
                    ,Integer.parseInt(temp[3])
                    ,Integer.parseInt(temp[4])
                    ,Integer.parseInt(temp[5])
                    ,  Integer.parseInt(temp[6]));

        }
        System.out.println("MST using prims algorithm!");
        System.out.println("Please choose from the following options:");
        System.out.println("1) Prims using Heap Implementation");
        System.out.println("2) Prims using unsorted array");
        System.out.println("3) Prims using sorted array");
        Scanner sc = new Scanner(System.in);
        String option = sc.nextLine();
        switch(option)
        {
            case "1":
                start = System.currentTimeMillis();
                e.prims_heap(graph);
                end = System.currentTimeMillis();
                timeWriter.write(V+" "+String.valueOf(end-start)+"\n");
                timeWriter.flush();
                break;
            case "2":
                //start = System.currentTimeMillis();
                e.prims_unsorted(graph);
                //timeWriter.write(V+" "+String.valueOf(end-start)+"\n");
                //timeWriter.flush();
                break;
            case "3":
                //start = System.currentTimeMillis();
                e.prims_sorted(graph);
                //timeWriter.write(V+" "+String.valueOf(end-start)+"\n");
                //timeWriter.flush();
                break;
            default:
                System.out.println("Incorrect Option!");
        }

    }*/
} 