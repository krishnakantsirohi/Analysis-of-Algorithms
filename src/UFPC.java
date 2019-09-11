import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

class UFPC {

    class subset {
        int parent;
        int rank;
    }

    int find(subset[] subsets, int i) {
        if (subsets[i].parent != i)
            subsets[i].parent = find(subsets, subsets[i].parent);
        return subsets[i].parent;
    }

    void Union(subset[] subsets,int x,int y)
    {
        int xroot = find(subsets,x);
        int yroot = find(subsets,y);

        if (subsets[xroot].rank<subsets[yroot].rank)
            subsets[xroot].parent = yroot;
        else if (subsets[yroot].rank<subsets[xroot].rank)
            subsets[yroot].parent = xroot;
        else
        {
            subsets[xroot].parent = yroot;
            subsets[yroot].rank++;
        }
    }

    void MST(Graph g, BufferedWriter bufferedWriter) throws IOException
    {
        Edge edge[] = g.edge;
        Edge result[] = new Edge[g.V];
        int e = 0;
        int i = 0;
        for (i=0; i<g.V; ++i)
            result[i] = new Edge();

        subset subsets[] = new subset[g.V];
        for(i=0; i<g.V; ++i)
            subsets[i]=new subset();

        for (int v = 0; v < g.V; ++v)
        {
            subsets[v].parent = v;
            subsets[v].rank = 0;
        }

        i = 0;

        while (e < g.V - 1)
        {
            Edge next_edge = new Edge();
            next_edge = edge[i++];

            int x = find(subsets, next_edge.src.id);
            int y = find(subsets, next_edge.dest.id);

            if (x != y)
            {
                result[e++] = next_edge;
                Union(subsets, x, y);
            }
        }
        for (int index=0;index<g.V-1;index++)
            bufferedWriter.write(result[index].toString()+"\n");
        bufferedWriter.flush();
    }
}
