package javaapplication5.newpackage;

import java.util.HashMap;
import java.util.ArrayList;

public class GrafoLista {
    private final HashMap<String, ArrayList<String>> grafo;

    // Constructor
    public GrafoLista() {
        grafo = new HashMap<>();
    }

    // Agregar un vértice
    public void agregarVertice(String vertice) {
        if (!grafo.containsKey(vertice)) {
            grafo.put(vertice, new ArrayList<>());
        }
    }

    // Agregar una arista (no dirigido)
    public void agregarArista(String origen, String destino) {
        if (grafo.containsKey(origen) && grafo.containsKey(destino)) {
            grafo.get(origen).add(destino);
            grafo.get(destino).add(origen); // Si quieres dirigido, quita esta línea
        }
    }

    // Mostrar el grafo
    public void mostrarGrafo() {
        for (String vertice : grafo.keySet()) {
            System.out.print(vertice + " -> ");
            System.out.println(grafo.get(vertice));
        }
    }

    // Main para probar
    public static void main(String[] args) {
        GrafoLista grafo = new GrafoLista();

        // Agregamos vértices
        grafo.agregarVertice("A");
        grafo.agregarVertice("B");
        grafo.agregarVertice("C");
        grafo.agregarVertice("D");

        // Agregamos aristas
        grafo.agregarArista("A", "B");
        grafo.agregarArista("A", "C");
        grafo.agregarArista("B", "D");

        // Mostramos el grafo
        grafo.mostrarGrafo();
    }
}