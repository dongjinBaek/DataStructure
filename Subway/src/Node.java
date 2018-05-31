import java.util.ArrayList;
import java.util.Comparator;

public class Node {
    private String id;
    private String name;
    private ArrayList<Edge> adj;
    private Node prev;
    private Distance dist;

    public Node(String id, String name) {
        this.id = id;
        this.name = name;
        adj = new ArrayList<>();
        prev = null;
    }

    public static Comparator<Node> getComparatorByTime() {
        return new Comparator<Node>() {
            @Override
            public int compare(Node n1, Node n2) {
                return new ComparatorByTime().compare(n1.dist, n2.dist);
            }
        };
    }
    public static Comparator<Node> getComparatorByTransfers() {
        return new Comparator<Node>() {
            @Override
            public int compare(Node n1, Node n2) {
                return new ComparatorByTransfers().compare(n1.dist, n2.dist);
            }
        };
    }

    public void addEdge(Edge e) {
        adj.add(e);
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public Node getPrev() {
        return prev;
    }

    public void setPrev(Node prev) {
        this.prev = prev;
    }

    public ArrayList<Edge> getAdj() {
        return adj;
    }

    public Distance getDist() {
        return dist;
    }

    public void setDist(Distance dist) {
        this.dist = dist;
    }

    public void setDistToMax() {
        dist = new Distance(Long.MAX_VALUE / 5, Long.MAX_VALUE % 5);
    }
}


