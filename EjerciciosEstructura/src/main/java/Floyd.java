
class FloydWarshall {
    int V;
    int[][] distancia;
    int INF = 99999;

    FloydWarshall(int V) {
        this.V = V;
        distancia = new int[V][V];
    }

    void inicializar(int[][] grafo) {
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                if (i == j) distancia[i][j] = 0;
                else if (grafo[i][j] != 0) distancia[i][j] = grafo[i][j];
                else distancia[i][j] = INF;
            }
        }
    }

    void floydWarshall() {
        for (int k = 0; k < V; k++) {
            for (int i = 0; i < V; i++) {
                for (int j = 0; j < V; j++) {
                    if (distancia[i][k] + distancia[k][j] < distancia[i][j]) {
                        distancia[i][j] = distancia[i][k] + distancia[k][j];
                    }
                }
            }
        }
    }

    void mostrarMatriz() {
        System.out.println("Matriz de distancias mínimas:");
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                if (distancia[i][j] == INF) System.out.print("INF ");
                else System.out.print(distancia[i][j] + " ");
            }
            System.out.println();
        }
    }

    void caminoMinimo(int inicio, int fin) {
        System.out.print("Distancia mínima de " + inicio + " a " + fin + ": ");
        if (distancia[inicio][fin] == INF) {
            System.out.println("No existe camino.");
        } else {
            System.out.println(distancia[inicio][fin]);
        }
    }
}


class Warshall {
    int V;
    int[][] matriz;

    Warshall(int V) {
        this.V = V;
        matriz = new int[V][V];
    }

    void inicializar(int[][] grafo) {
        for (int i = 0; i < V; i++)
            System.arraycopy(grafo[i], 0, matriz[i], 0, V);
    }

    void warshall() {
        for (int k = 0; k < V; k++) {
            for (int i = 0; i < V; i++) {
                for (int j = 0; j < V; j++) {
                    matriz[i][j] = (matriz[i][j] == 1 || (matriz[i][k] == 1 && matriz[k][j] == 1)) ? 1 : 0;
                }
            }
        }
    }

    void mostrarMatriz() {
        System.out.println("Matriz de alcanzabilidad:");
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                System.out.print(matriz[i][j] + " ");
            }
            System.out.println();
        }
    }

    boolean esFuertementeConexo() {
        for (int i = 0; i < V; i++)
            for (int j = 0; j < V; j++)
                if (matriz[i][j] == 0) return false;
        return true;
    }
}

public class Floyd {
    public static void main(String[] args) {

        int INF = 99999;
        int V = 6;
        int[][] grafoFloyd = {
            {0, 10, INF, INF, 3, INF},
            {INF, 0, 2, INF, 4, INF},
            {INF, INF, 0, 9, INF, INF},
            {INF, INF, 7, 0, INF, INF},
            {INF, 1, 8, 2, 0, INF},
            {INF, INF, INF, INF, INF, 0}
        };

        FloydWarshall fw = new FloydWarshall(V);
        fw.inicializar(grafoFloyd);
        fw.floydWarshall();
        fw.mostrarMatriz();
        fw.caminoMinimo(0, 3);
        System.out.println();

        int[][] grafoWarshall = {
            {0, 1, 0, 0},
            {0, 0, 1, 0},
            {1, 0, 0, 1},
            {0, 0, 0, 0}
        };
        Warshall w = new Warshall(4);
        w.inicializar(grafoWarshall);
        w.warshall();
        w.mostrarMatriz();
        System.out.println("¿El grafo es fuertemente conexo? " + (w.esFuertementeConexo() ? "Sí" : "No"));
    }
}