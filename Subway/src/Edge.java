import java.util.Comparator;

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

    public static Comparator<Edge> getComparatorByTime() {
        return new Comparator<Edge>() {
            @Override
            public int compare(Edge e1, Edge e2) {
                return new ComparatorByTime().compare(e1.distance, e2.distance);
            }
        };
    }
    public static Comparator<Edge> getComparatorByTransfers() {
        return new Comparator<Edge>() {
            @Override
            public int compare(Edge e1, Edge e2) {
                return new ComparatorByTransfers().compare(e1.distance, e2.distance);
            }
        };
    }
}
