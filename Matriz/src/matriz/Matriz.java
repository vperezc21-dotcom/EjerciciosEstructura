/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package matriz;

/**
 *
 * @author aleja
 */

public class Matriz {
    private final int[][] matrizAdy;
    private final String[] vertices;
    private int numVertices;
    private final int maxVertices;

    public Matriz(int maxVertices) {
        this.maxVertices = maxVertices;
        matrizAdy = new int[maxVertices][maxVertices];
        vertices = new String[maxVertices];
        numVertices = 0;
    }

    public void agregarVertice(String nombre) {
        if (numVertices < maxVertices) {
            vertices[numVertices] = nombre;
            numVertices++;
        }
    }

    public void agregarArista(String origen, String destino) {
        int i = obtenerIndice(origen);
        int j = obtenerIndice(destino);
        if (i != -1 && j != -1) {
            matrizAdy[i][j] = 1;
            matrizAdy[j][i] = 1;
        }
    }

    private int obtenerIndice(String nombre) {
        for (int i = 0; i < numVertices; i++) {
            if (vertices[i].equals(nombre)) {
                return i;
            }
        }
        return -1;
    }

    public void mostrarMatriz() {
        System.out.print("   ");
        for (int i = 0; i < numVertices; i++) {
            System.out.print(vertices[i] + " ");
        }
        System.out.println();
        for (int i = 0; i < numVertices; i++) {
            System.out.print(vertices[i] + " ");
            for (int j = 0; j < numVertices; j++) {
                System.out.print(" " + matrizAdy[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Matriz grafo = new Matriz(5);
        grafo.agregarVertice("A");
        grafo.agregarVertice("B");
        grafo.agregarVertice("C");
        grafo.agregarVertice("D");
        grafo.agregarArista("A", "B");
        grafo.agregarArista("A", "C");
        grafo.agregarArista("B", "D");
        grafo.agregarArista("C", "D");
        grafo.mostrarMatriz();
    }
}