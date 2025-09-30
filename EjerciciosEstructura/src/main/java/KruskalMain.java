import java.util.*;

class Arista implements Comparable<Arista> {
    int origen, destino, peso;
    Arista(int o, int d, int p) {
        origen = o;
        destino = d;
        peso = p;
    }
    @Override
    public int compareTo(Arista arista) {
        return this.peso - arista.peso;
    }
}

class Subconjunto {
    int padre, rango;
    Subconjunto(int p, int r) {
        padre = p;
        rango = r;
    }
}

class GrafoKruskal {
    int V, E;
    Arista[] aristas;

    GrafoKruskal(int v, int e) {
        V = v;
        E = e;
        aristas = new Arista[E];
    }

    int find(Subconjunto[] subconjuntos, int i) {
        if (subconjuntos[i].padre != i)
            subconjuntos[i].padre = find(subconjuntos, subconjuntos[i].padre);
        return subconjuntos[i].padre;
    }

    void union(Subconjunto[] subconjuntos, int x, int y) {
        int xRoot = find(subconjuntos, x), yRoot = find(subconjuntos, y);
        if (subconjuntos[xRoot].rango < subconjuntos[yRoot].rango) {
            subconjuntos[xRoot].padre = yRoot;
        } else if (subconjuntos[xRoot].rango > subconjuntos[yRoot].rango) {
            subconjuntos[yRoot].padre = xRoot;
        } else {
            subconjuntos[yRoot].padre = xRoot;
            subconjuntos[xRoot].rango++;
        }
    }

    void KruskalMST() {
        Arista[] resultado = new Arista[V];
        int e = 0;
        int i = 0;
        for (i = 0; i < V; ++i)
            resultado[i] = new Arista(0, 0, 0);

        Arrays.sort(aristas);

        Subconjunto[] subconjuntos = new Subconjunto[V];
        for (i = 0; i < V; ++i)
            subconjuntos[i] = new Subconjunto(i, 0);

        i = 0;

        int descartadas = 0;

        while (e < V - 1 && i < E) {
            Arista siguienteArista = aristas[i++];

            int x = find(subconjuntos, siguienteArista.origen);
            int y = find(subconjuntos, siguienteArista.destino);

            if (x != y) {
                resultado[e++] = siguienteArista;
                union(subconjuntos, x, y);
            } else {
                descartadas++;
            }
        }

        int costoTotal = 0;
        System.out.println("Aristas seleccionadas por Kruskal:");
        for (i = 0; i < e; ++i) {
            System.out.println(resultado[i].origen + " - " + resultado[i].destino + " (" + resultado[i].peso + ")");
            costoTotal += resultado[i].peso;
        }
        System.out.println("Número de aristas descartadas por formar ciclos: " + descartadas);
        System.out.println("Costo total MST con Kruskal: " + costoTotal);
    }
}

public class KruskalMain {
    public static void main(String[] args) {
        int V = 6;  
        int E = 9;  
        GrafoKruskal g = new GrafoKruskal(V, E);

        g.aristas[0] = new Arista(0, 1, 6);
        g.aristas[1] = new Arista(0, 2, 1);
        g.aristas[2] = new Arista(0, 3, 5);
        g.aristas[3] = new Arista(1, 2, 2);
        g.aristas[4] = new Arista(1, 4, 5);
        g.aristas[5] = new Arista(2, 3, 2);
        g.aristas[6] = new Arista(2, 4, 6);
        g.aristas[7] = new Arista(3, 5, 4);
        g.aristas[8] = new Arista(4, 5, 3);

        g.KruskalMST();
    }
}