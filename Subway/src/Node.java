import java.util.ArrayList;

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


