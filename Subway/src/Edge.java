public class Edge {
    Node dest;
    Distance distance;

    public Edge(Node dest, Distance distance) {
        this.dest = dest;
        this.distance = distance;
    }

    public Node getDest() {
        return dest;
    }

    public Distance getDistance() {
        return distance;
    }
}
