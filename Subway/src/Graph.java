import java.util.*;

public class Graph {

    private Set<Node> nodes;

    public Graph(Set<Node> nodes) {
        this.nodes = nodes;
    }

    public void dijkstraAlgorithm(Node start, Node end, String compBy) {
        Set<Node> visitedNodes = new HashSet<>();
        //pq contains set of imaginary edges in form of (start->node)
        PriorityQueue<Edge> pq;
        Comparator<Distance> comparator;
        if (compBy.equals("byTime")) {
            comparator = new ComparatorByTime();
            pq = new PriorityQueue<>(Edge.getComparatorByTime());
        } else {
            comparator = new ComparatorByTransfers();
            pq = new PriorityQueue<>(Edge.getComparatorByTransfers());
        }

        for (Node node : nodes) {
            node.setDistToMax();
        }

        start.setDist(new Distance(0, 0));
        start.setPrev(null);
        pq.add(new Edge(start, new Distance(0, 0)));

        while (!pq.isEmpty()) {
            Node here = pq.poll().getDest();
            if (visitedNodes.contains(here))
                continue;
            visitedNodes.add(here);

            for (Edge edge : here.getAdj()) {
                Node next = edge.getDest();
                Distance d = edge.getDistance();
                if (visitedNodes.contains(next))
                    continue;
                Distance cand = here.getDist().add(d);
                if (comparator.compare(next.getDist(), cand) > 0) {
                    next.setDist(cand);
                    next.setPrev(here);
                    pq.add(new Edge(next, cand));
                }
            }
        }

        printPath(start, end);
        //need to subtract 5mins b/c start and end nodes are virtual nodes
        System.out.println(end.getDist().getTotTime() - 5);
    }

    private void printPath(Node start, Node end) {
        LinkedList<String> path = new LinkedList<>();
        Node here = end;
        while (true) {
            here = here.getPrev();
            if (here == start)
                break;
            //if path includes virtual node, it has transferred there
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
