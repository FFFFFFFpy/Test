public class Edge {
    Station from;
    Station to;
    int distance;

    Edge(Station from, Station to, int distance) {
        this.distance = distance;
        this.from = from;
        this.to = to;
    }

}
