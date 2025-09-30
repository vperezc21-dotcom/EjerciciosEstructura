import java.util.*;

public class NetworkGraph {
    private final List<List<Integer>> adj;

    public NetworkGraph(int n) {
        adj = new ArrayList<>();
        for (int i = 0; i < n; i++) adj.add(new ArrayList<>());
    }

    public void addConnection(int a, int b) {
        adj.get(a).add(b);
        adj.get(b).add(a);
    }

    public boolean canCommunicate(int start, int goal) {
        boolean[] visited = new boolean[adj.size()];
        Queue<Integer> q = new LinkedList<>();
        q.add(start);
        visited[start] = true;

        while (!q.isEmpty()) {
            int curr = q.poll();
            if (curr == goal) return true;
            for (int nei : adj.get(curr)) {
                if (!visited[nei]) {
                    visited[nei] = true;
                    q.add(nei);
                }
            }
        }
        return false;
    }

    public List<Integer> shortestPath(int start, int goal) {
        int n = adj.size();
        boolean[] visited = new boolean[n];
        int[] parent = new int[n];
        Arrays.fill(parent, -1);
        Queue<Integer> q = new LinkedList<>();
        q.add(start);
        visited[start] = true;

        while (!q.isEmpty()) {
            int curr = q.poll();
            if (curr == goal) break;
            for (int nei : adj.get(curr)) {
                if (!visited[nei]) {
                    visited[nei] = true;
                    parent[nei] = curr;
                    q.add(nei);
                }
            }
        }

        List<Integer> path = new ArrayList<>();
        if (!visited[goal]) return path;
        for (int at = goal; at != -1; at = parent[at]) path.add(at);
        Collections.reverse(path);
        return path;
    }

    public static void main(String[] args) {
        NetworkGraph net = new NetworkGraph(6);
        net.addConnection(0, 1);
        net.addConnection(0, 2);
        net.addConnection(1, 3);
        net.addConnection(2, 3);
        net.addConnection(3, 4);
        net.addConnection(4, 5);

        System.out.println("Comunicación 0 a 5: " + net.canCommunicate(0,5));
        System.out.print("Camino más corto de 0 a 5: ");
        List<Integer> path = net.shortestPath(0, 5);
        path.forEach(n -> System.out.print(n + " "));
    }
}