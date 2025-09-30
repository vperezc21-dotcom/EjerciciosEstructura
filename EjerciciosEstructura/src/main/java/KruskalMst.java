import java.util.*;

public class KruskalMst {
    static class Edge implements Comparable<Edge> {
        int src, dest, weight;
        Edge(int s, int d, int w) {
            src = s; dest = d; weight = w;
        }
        @Override
        public int compareTo(Edge other) {
            return this.weight - other.weight;
        }
        @Override
        public String toString() {
            return "(" + src + "-" + dest + " " + weight + ")";
        }
    }

    static int V = 6;
    static List<Edge>[] graph;

    static void buildGraph() {
        graph = new ArrayList[V];
        for(int i = 0; i < V; i++) graph[i] = new ArrayList<>();
        addEdge(0,1,6); addEdge(0,2,1); addEdge(0,3,5);
        addEdge(1,2,2); addEdge(1,4,5);
        addEdge(2,3,2); addEdge(2,4,6);
        addEdge(3,5,4);
        addEdge(4,5,3);
    }

    static void addEdge(int s, int d, int w) {
        graph[s].add(new Edge(s,d,w));
        graph[d].add(new Edge(d,s,w));
    }

    static void prim(int start) {
        System.out.println("Prim - inicio en vértice " + start);
        boolean[] visited = new boolean[V];
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        int cost = 0;
        int edgesUsed = 0;
        visited[start] = true;
        for(Edge e : graph[start]) pq.add(e);

        while (!pq.isEmpty() && edgesUsed < V - 1) {
            Edge e = pq.poll();
            if (!visited[e.dest]) {
                System.out.println("Seleccionada arista: " + e);
                cost += e.weight;
                edgesUsed++;
                visited[e.dest] = true;
                for (Edge next : graph[e.dest]) {
                    if (!visited[next.dest]) pq.add(next);
                }
            }
        }
        System.out.println("Costo total del MST (Prim): " + cost);
    }

    static int find (int[] parent, int i) {
        if (parent[i] != i)
            parent[i] = find(parent, parent[i]);
        return parent[i];
    }

    static void union(int[] parent, int[] rank, int x, int y) {
        int rx = find(parent, x);
        int ry = find(parent, y);
        if (rank[rx] < rank[ry]) parent[rx] = ry;
        else if(rank[ry] < rank[rx]) parent[ry] = rx;
        else {
            parent[ry] = rx;
            rank[rx]++;
        }
    }

    static void kruskal() {
        System.out.println("Kruskal");
        List<Edge> edges = new ArrayList<>();
        for (int i = 0; i < V; i++) edges.addAll(graph[i]);
        Set<String> seen = new HashSet<>();
        List<Edge> uniqueEdges = new ArrayList<>();
        for (Edge e : edges) {
            String key1 = e.src + "-" + e.dest;
            String key2 = e.dest + "-" + e.src;
            if (!seen.contains(key1) && !seen.contains(key2)) {
                uniqueEdges.add(e);
                seen.add(key1);
                seen.add(key2);
            }
        }
        Collections.sort(uniqueEdges);

        int[] parent = new int[V];
        int[] rank = new int[V];
        for (int i=0; i < V; i++) parent[i] = i;

        int cost = 0;
        int edgesUsed = 0;
        for (Edge e : uniqueEdges) {
            int rx = find(parent, e.src);
            int ry = find(parent, e.dest);
            if (rx != ry) {
                union(parent, rank, rx, ry);
                System.out.println("Seleccionada arista: " + e);
                cost += e.weight;
                edgesUsed++;
                if (edgesUsed == V-1) break;
            }
        }
        System.out.println("Costo total del MST (Kruskal): " + cost);
    }

    public static void main(String[] args) {
        buildGraph();
        prim(0);
        prim(2);
        kruskal();
    }
}
