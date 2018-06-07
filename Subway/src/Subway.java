import java.io.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Subway {
    //there is one virtual node per one station name
    //virtualNode is connected to all nodes that has same name with it
    //Edge going out from vNode is (1, 0), into vNode is (0, 0)
    //Transfer is implemented using vNode (shortest path b/w transfer station is (1,0))
    //(name, node)
    private static HashMap<String, Node> virtualNodes = new HashMap<>();
    //(id, node)
    private static HashMap<String, Node> id_node = new HashMap<>();
    private static Set<Node> nodes = new HashSet<>();

    public static void main(String[] args) {
        virtualNodes = new HashMap<>();
        id_node = new HashMap<>();
        nodes = new HashSet<>();

        readFile(args[0]);

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            try {
                String input = br.readLine();
                if (input.compareTo("QUIT") == 0)
                    break;
                command(input);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void readFile(String filename) {
        String s;
        try {
            BufferedReader br = new BufferedReader(new FileReader(new File(filename)));
            while (!(s = br.readLine()).equals("")) {
                String[] token = s.split(" ");
                String id = token[0];
                String name = token[1];
                Node vNode, nNode;
                //create virtual node if not exists
                if (!virtualNodes.containsKey(name)) {
                    vNode = new Node(null, name);
                    virtualNodes.put(name, vNode);
                    nodes.add(vNode);
                } else {
                    vNode = virtualNodes.get(name);
                }
                //create edge between current station and virtual station
                nNode = new Node(id, name);
                nodes.add(nNode);
                id_node.put(id, nNode);
                nNode.addEdge(new Edge(vNode, new Distance(0, 0)));
                vNode.addEdge(new Edge(nNode, new Distance(1, 0)));
            }

            //add edges in input file
            while ((s = br.readLine()) != null) {
                String[] token = s.split(" ");
                Node n1 = id_node.get(token[0]);
                Node n2 = id_node.get(token[1]);
                long time = Long.parseLong(token[2]);
                n1.addEdge(new Edge(n2, new Distance(0, time)));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void command(String input) {
        String[] token = input.split(" ");
        Node start = virtualNodes.get(token[0]);
        Node end = virtualNodes.get(token[1]);
        Graph g= new Graph(nodes);
        if (token.length == 2) {
            g.dijkstraAlgorithm(start, end, "byTime");
        } else {
            g.dijkstraAlgorithm(start, end, "byTransfers");
        }
    }
}
