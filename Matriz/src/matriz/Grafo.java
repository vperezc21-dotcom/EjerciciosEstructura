package matriz;

import java.util.*;

public class Grafo {
    private final Map<Integer, List<Integer>> adjList;

    public Grafo() {
        adjList = new HashMap<>();
    }

    public void agregarArista(int origen, int destino) {
        adjList.putIfAbsent(origen, new ArrayList<>());
        adjList.putIfAbsent(destino, new ArrayList<>());
        adjList.get(origen).add(destino);
        adjList.get(destino).add(origen);
    }


    public void BFS(int inicio) {
        Set<Integer> visitados = new HashSet<>();
        Queue<Integer> cola = new LinkedList<>();

        visitados.add(inicio);
        cola.add(inicio);

        System.out.print("BFS: ");
        while (!cola.isEmpty()) {
            int nodo = cola.poll();
            System.out.print(nodo + " ");

            for (int vecino : adjList.getOrDefault(nodo, new ArrayList<>())) {
                if (!visitados.contains(vecino)) {
                    visitados.add(vecino);
                    cola.add(vecino);
                }
            }
        }
        System.out.println();
    }


    public void DFS(int inicio) {
        Set<Integer> visitados = new HashSet<>();
        System.out.print("DFS: ");
        DFSRecursivo(inicio, visitados);
        System.out.println();
    }

    private void DFSRecursivo(int nodo, Set<Integer> visitados) {
        visitados.add(nodo);
        System.out.print(nodo + " ");

        for (int vecino : adjList.getOrDefault(nodo, new ArrayList<>())) {
            if (!visitados.contains(vecino)) {
                DFSRecursivo(vecino, visitados);
            }
        }
    }

  
    public static void main(String[] args) {
        Grafo g = new Grafo();
        g.agregarArista(0, 1);
        g.agregarArista(0, 2);
        g.agregarArista(1, 3);
        g.agregarArista(1, 4);
        g.agregarArista(2, 5);
        g.agregarArista(2, 6);

        g.BFS(0);
        g.DFS(0);
    }
}
