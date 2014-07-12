import com.sun.javafx.css.FontUnits;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * Created by Vera_Sidarovich on 7/10/2014.
 */
public class TaskLauncher {

    private static Integer SOURCE_VERTEX = 1;

    private static HashMap<Integer, GraphNode> graphNodes;

    private static ArrayList<Integer> vertices;

    private static ArrayList<GraphNode> selectedNodes;

    private static Integer pathValue;

    public static void main(String... args) {
        graphNodes = new HashMap();
        vertices = new ArrayList<Integer>() {{
           /* add(7);
            add(37);
            add(59);
            add(82);
            add(99);
            add(115);
            add(133);
            add(165);
            add(188);
            add(197);*/
            add(2);
            add(3);
            add(4);
            add(5);
            add(6);
        }};

        selectedNodes = new ArrayList(vertices.size());

        scanFile();

        Set<Integer> keys = graphNodes.keySet();
        for (Integer key : keys) {
            System.out.println("Node = " + graphNodes.get(key));
        }

        GraphNode firstNode = graphNodes.get(SOURCE_VERTEX);

        for (WeightNode weightNode : firstNode.getNodes()) {
            pathValue = 0;
            System.out.println(weightNode.getNodeKey());
            findShortestPath(weightNode);
        }

        for (GraphNode node : selectedNodes) {
            System.out.println("Node = " + node.getKey() + " PathValue = " + node.getPathValue());
        }

        System.out.println(vertices);
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
                node.setKey(nodeValue);
            } else {
                String[] nextNodeData = rowDatum.split(",");
                WeightNode nextNode = new WeightNode();
                nextNode.setNodeKey(Integer.parseInt(nextNodeData[0]));
                nextNode.setDistance(Integer.parseInt(nextNodeData[1]));
                node.getNodes().add(nextNode);
            }
        }
        graphNodes.put(nodeValue, node);
    }

    private static void findShortestPath(WeightNode weightNode) {

        if (vertices.size() == 0) {
            return;
        }

        pathValue += weightNode.getDistance();

        Integer graphValue = weightNode.getNodeKey();
        GraphNode node = graphNodes.get(graphValue);

        if (vertices.contains(node.getKey())) {
            node.setPathValue(pathValue);
            selectedNodes.add(node);
            vertices.remove(node.getKey());
        }

        //return a node with the smallest weight
        WeightNode nextWeightNode = node.getNodes().peek();
        GraphNode nextNode = graphNodes.get(nextWeightNode);

        for(WeightNode nodes: nextNode.getNodes()){
            //обновить значения вершин
        }


        findShortestPath(nextWeightNode);
    }
}
