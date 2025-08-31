package com.mycompany.grafomatriz;

public class GrafoMatriz {
    int[][] matriz;    // Aquí guardamos las conexiones
    String[] vertices; // Lista con los nombres de los vértices
    int numVertices;   // Cuántos vértices llevamos

    // Constructor: tamaño máximo de vértices
    public GrafoMatriz(int maxVertices) {
        matriz = new int[maxVertices][maxVertices];
        vertices = new String[maxVertices];
        numVertices = 0;
    }

    // Agregar vértice
    public void agregarVertice(String nombre) {
        vertices[numVertices] = nombre;
        numVertices++;
    }

    // Agregar arista (relación entre dos vértices)
    public void agregarArista(int i, int j) {
        matriz[i][j] = 1;
        matriz[j][i] = 1; // si el grafo es NO dirigido
    }

    // Mostrar matriz
    public void mostrarMatriz() {
        System.out.println("Matriz de adyacencia:");
        for (int i = 0; i < numVertices; i++) {
            for (int j = 0; j < numVertices; j++) {
                System.out.print(matriz[i][j] + " ");
            }
            System.out.println();
        }
    }

    // Probar
    public static void main(String[] args) {
        GrafoMatriz grafo = new GrafoMatriz(5);

        grafo.agregarVertice("A");
        grafo.agregarVertice("B");
        grafo.agregarVertice("C");

        grafo.agregarArista(0, 1); // A-B
        grafo.agregarArista(0, 2); // A-C

        grafo.mostrarMatriz();
    }
}