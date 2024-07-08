package grafo.dijkstra;

import grafo.dijkstra.entities.Edge;
import grafo.dijkstra.entities.Vertex;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.security.Key;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Program {
    public static void main(String[] args) {

        HashMap<String ,Vertex<String>> vertices = new HashMap<>();
        String filePath = "/home/jader/IdeaProjects/algoritimos/src/grafo/dijkstra/in.txt";

        Scanner sc = new Scanner(System.in);
        System.out.print("Ponto inicial: ");
        String ponto1 = sc.next();
        System.out.print("Ponto final: ");
        String ponto2 = sc.next();

        try (BufferedReader bf = new BufferedReader(new FileReader(filePath))) {
            String line = bf.readLine();
            while (line != null) {
                String[] fields = line.split(" ");

                if (!vertices.containsKey(fields[0])) {
                    vertices.put(fields[0], new Vertex<>(fields[0]));
                }
                if (!vertices.containsKey(fields[1])) {
                    vertices.put(fields[1], new Vertex<>(fields[1]));
                }

                Vertex<String> a = vertices.get(fields[0]);
                Vertex<String> b = vertices.get(fields[1]);

                a.addEdge(b, Integer.parseInt(fields[2]));

                line = bf.readLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        // distancia do ponto 1 do ponto 2
        HashMap<String, Integer> dist = new HashMap<>();
        HashMap<String, Boolean> explored = new HashMap<>();
        HashMap<String, String> prev = new HashMap<>();

        for (String key : vertices.keySet()) {
            dist.put(key, Integer.MAX_VALUE);
            explored.put(key, false);
        }

        dist.put(ponto1, 0);
        explored.put(ponto1, true);
        prev.put(ponto1, null);

        Vertex<String> vertice = vertices.get(ponto1);

        while (!explored.get(ponto2)) {
            String shortest = null;
            for (Edge<String> edge : vertice.getEdges()) {
                String destination = edge.getDestination().getName();

                if (edge.getWeight() + dist.get(vertice.getName()) < dist.get(destination)) {
                    prev.put(destination, vertice.getName());
                    dist.put(destination, edge.getWeight() + dist.get(vertice.getName()));
                }

                if (!explored.get(edge.getDestination().getName())) {
                    if (shortest == null) shortest = destination;
                    if (dist.get(shortest) > dist.get(destination))
                        shortest = destination;
                }
            }

            vertice = vertices.get(shortest);
            explored.put(shortest, true);
        }

        List<String> path = new ArrayList<>();
        path.add(ponto2);
        String p = ponto2;
        while (p != null) {
            path.add(prev.get(p));
            p = prev.get(p);
        }
        System.out.println("A menor distancia do ponto inicial até o ponto final é " + dist.get(ponto2));
        System.out.println("Caminho do ponto inicial até o ponto final: ");
        for (int i = path.size() - 2; i >= 1; i--) {
            System.out.print(path.get(i) + " --> ");
        }
        System.out.println(path.get(0));
    }
}
