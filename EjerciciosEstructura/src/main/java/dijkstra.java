import java.util.*;


class GrafoBFS {
    int V;
    LinkedList<Integer>[] adj;

    GrafoBFS(int V) {
        this.V = V;
        adj = new LinkedList[V];
        for(int i = 0; i < V; i++)
            adj[i] = new LinkedList<>();
    }

    void agregarArista(int v, int w) {
        adj[v].add(w);
    }

    void BFS(int inicio, int destino) {
        boolean[] visitado = new boolean[V];
        int[] padre = new int[V];
        Arrays.fill(padre, -1);
        LinkedList<Integer> queue = new LinkedList<>();
        visitado[inicio] = true;

        while(!queue.isEmpty()) {
            int u = queue.poll();
            if(u == destino) break;
            for(int v : adj[u]) {
                if(!visitado[v]) {
                    visitado[v] = true;
                    padre[v] = u;
                    queue.add(v);
                }
            }
        }

        if(!visitado[destino]) {
            System.out.println("No hay camino de " + inicio + " a " + destino);
            return;
        }

        System.out.print("Camino más corto en número de aristas de " + inicio + " a " + destino + ": ");
        LinkedList<Integer> camino = new LinkedList<>();
        for(int v = destino; v != -1; v = padre[v])
            camino.addFirst(v);

        for(int v : camino) System.out.print(v + " ");
        System.out.println();
    }
}


class GrafoDijkstra {
    int V;
    LinkedList<int[]>[] adj;

    GrafoDijkstra(int V) {
        this.V = V;
        adj = new LinkedList[V];
        for(int i = 0; i < V; i++)
            adj[i] = new LinkedList<>();
    }

    void agregarArista(int u, int v, int peso) {
        adj[u].add(new int[]{v, peso});
    }

    void dijkstra(int inicio) {
        int[] dist = new int[V];
        int[] padre = new int[V];
        Arrays.fill(dist, Integer.MAX_VALUE);
        Arrays.fill(padre, -1);
        dist[inicio] = 0;

        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        pq.add(new int[]{inicio, 0});

        while(!pq.isEmpty()) {
            int[] actual = pq.poll();
            int u = actual[0], d = actual[1];
            if(d > dist[u]) continue;

            for(int[] vecino : adj[u]) {
                int v = vecino[0], peso = vecino[1];
                if(dist[u] + peso < dist[v]) {
                    dist[v] = dist[u] + peso;
                    padre[v] = u;
                    pq.add(new int[]{v, dist[v]});
                }
            }
        }

        System.out.println("Distancias desde el vértice " + inicio + ":");
        for(int i = 0; i < V; i++) {
            System.out.print("Vértice " + i + ": ");
            if(dist[i] == Integer.MAX_VALUE) {
                System.out.println("No alcanzable");
            } else {
                System.out.print("Distancia = " + dist[i] + ", Camino: ");
                printCamino(padre, i);
                System.out.println();
            }
        }
    }

    void printCamino(int[] padre, int v) {
        if(v == -1) return;
        printCamino(padre, padre[v]);
        System.out.print(v + " ");
    }
}

public class dijkstra {
    public static void main(String[] args) {
       
        GrafoBFS gBFS = new GrafoBFS(6);
        gBFS.agregarArista(0,1);
        gBFS.agregarArista(0,2);
        gBFS.agregarArista(1,3);
        gBFS.agregarArista(2,3);
        gBFS.agregarArista(3,4);
        gBFS.agregarArista(4,5);
        gBFS.BFS(0,5);

        System.out.println();


        GrafoDijkstra gD = new GrafoDijkstra(6);
        gD.agregarArista(0,1,6);
        gD.agregarArista(0,2,1);
        gD.agregarArista(0,3,5);
        gD.agregarArista(1,2,2);
        gD.agregarArista(1,4,5);
        gD.agregarArista(2,3,2);
        gD.agregarArista(2,4,6);
        gD.agregarArista(3,5,4);
        gD.agregarArista(4,5,3);
        gD.dijkstra(0);
    }
}