import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * Created by Vera_Sidarovich on 7/10/2014.
 */
public class TaskLauncher {

    private static HashMap<Integer, GraphNode> graphNodes;

    private static ArrayList<Integer> vertices;

    private static ArrayList<GraphNode> selectedNodes;

    private static Integer pathValue = 0;

    public static void main(String... args) {
        graphNodes = new HashMap();
        vertices = new ArrayList<Integer>() {{
            add(7);
            add(37);
            add(59);
            add(82);
            add(99);
            add(115);
            add(133);
            add(165);
            add(188);
            add(197);
        }};

        selectedNodes = new ArrayList(vertices.size());

        scanFile();
        Set<Integer> keys = graphNodes.keySet();
        for (Integer key : keys) {
            System.out.println("Next node = " + key + "Distance = " + graphNodes.get(key));
        }
    }

    private static void scanFile() {
        File file = new File("c:\\test.txt");

        Scanner scanner = null;
        try {
            scanner = new Scanner(file);
            while (scanner.hasNext()) {
                String row = scanner.nextLine();
                addNewGraphNode(row);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            scanner.close();
        }
    }

    private static void addNewGraphNode(String row) {
        String[] rowData = row.split("\t");
        Integer nodeValue = null;
        GraphNode node = new GraphNode();
        for (String rowDatum : rowData) {
            if (!rowDatum.contains(",")) {
                nodeValue = Integer.parseInt(rowDatum);
            } else {
                String[] nextNodeData = rowDatum.split(",");
                WeightNode nextNode = new WeightNode();
                nextNode.setNodeKey(Integer.parseInt(nextNodeData[0]));
                nextNode.setNodeValue(Integer.parseInt(nextNodeData[1]));
                node.getNodes().add(nextNode);
            }
        }
        graphNodes.put(nodeValue, node);
    }

    private static void findShortestPath(GraphNode node) {
        if (node.getKey() > vertices.get(vertices.size() - 1)) {
            return;
        }

        if (vertices.contains(node.getKey())) {
            node.setPathValue(pathValue);
            selectedNodes.add(node);
        }
        //return a node with the smallest weight
        WeightNode weightNode = node.getNodes().peek();
        pathValue += weightNode.getNodeValue();
        GraphNode nextNode = graphNodes.get(weightNode);

        findShortestPath(nextNode);
    }
}
