package grafo.dijkstra.entities;

import java.util.ArrayList;
import java.util.List;

public class Vertex<Type>{
    private final Type name;
    private final List<Edge<Type>> edges;

    public Vertex(Type name) {
        this.name = name;
        this.edges = new ArrayList<>();
    }

    public Type getName() {
        return name;
    }

    public List<Edge<Type>> getEdges() {
        return edges;
    }

    public void addEdge(Vertex<Type> destination, int weight) {
        Edge<Type> edge = new Edge<>(this, destination, weight);
        this.edges.add(edge);
    }
}
