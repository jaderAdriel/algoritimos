package grafo.dijkstra.entities;

public class Edge<T>{
    private final Vertex<T> source;
    private final Vertex<T> destination;
    private final Integer weight;

    public Edge(Vertex<T> source, Vertex<T> destination, Integer weight) {
        this.source = source;
        this.destination = destination;
        this.weight = weight;
    }

    public Vertex<T> getSource() {
        return source;
    }

    public Vertex<T> getDestination() {
        return destination;
    }

    public Integer getWeight() {
        return weight;
    }
}
