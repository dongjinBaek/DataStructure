import java.util.*;

public class Graph {

    private Set<Node> nodes;

    public Graph(Set<Node> nodes) {
        this.nodes = nodes;
    }

    public void dijkstraAlgorithm(Node start, Node end, String compBy) {
        Set<Node> visitedNodes = new HashSet<>();
        PriorityQueue<Node> pq;
        Comparator<Distance> comparator;
        if (compBy.equals("byTime")) {
            comparator = new ComparatorByTime();
            pq = new PriorityQueue<>(Node.getComparatorByTime());
        } else {
            comparator = new ComparatorByTransfers();
            pq = new PriorityQueue<>(Node.getComparatorByTransfers());
        }

        for (Node node : nodes) {
            node.setDistToMax();
        }

        start.setDist(new Distance(0, 0));
        start.setPrev(null);
        pq.add(start);

        while (!pq.isEmpty()) {
            Node here = pq.poll();
            if (visitedNodes.contains(here))
                continue;
            visitedNodes.add(here);
            for (Edge edge : here.getAdj()) {
                Node next = edge.getDest();
                Distance d = edge.getDistance();
                if (visitedNodes.contains(next))
                    continue;
                if (comparator.compare(next.getDist(), here.getDist().add(d)) > 0) {
                    next.setDist(here.getDist().add(d));
                    next.setPrev(here);
                    pq.add(next);
                }
            }
        }

        printPath(start, end);
        System.out.println(end.getDist().getTotTime() - 5);
    }

    private void printPath(Node start, Node end) {
        LinkedList<String> path = new LinkedList<>();
        Node here = end;
        while (true) {
            here = here.getPrev();
            if (here == start)
                break;
            if (here.getId() == null) {
                String st = path.pollLast();
                path.add("["+st+"]");
                here = here.getPrev();
            } else {
                path.add(here.getName());
            }
        }

        Collections.reverse(path);
        StringBuilder sb = new StringBuilder();
        for (String s : path) {
            sb.append(s).append(" ");
        }
        System.out.println(sb.toString().trim());

    }
}
