import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Vera_Sidarovich on 7/10/2014.
 */
public class TaskLauncher {

    private static final Integer SOURCE_VERTEX_INDEX = 0;

    private static ArrayList<GraphNode> graph;

    private static Integer pathValue;

    public static void main(String... args) {
        graph = new ArrayList<GraphNode>();
        fillGraphFromFile();

        GraphNode firstNode = graph.get(SOURCE_VERTEX_INDEX);
        firstNode.setDistance(0);
        firstNode.setDirty(true);

        for (GraphNode node : firstNode.getNodes()) {
            pathValue = 0;
            setShortestPath(node);
            findShortestPath(node);
        }

        for (GraphNode node : graph) {
            System.out.println(node.getKey() + " :  " + node.getDistance());
        }

        // ArrayList<Integer> vertices = getVerticesFromFile();
    }

    private static void fillGraphFromFile() {
        Scanner scanner = null;
        File file = new File("c:\\test.txt");

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

    private static ArrayList<Integer> getVerticesFromFile() {
        Scanner scanner = null;
        File file = new File("c:\\vertices.txt");
        ArrayList<Integer> vertices = new ArrayList<Integer>();

        try {
            scanner = new Scanner(file);
            while (scanner.hasNext()) {
                String row = scanner.nextLine();
                vertices.add(Integer.parseInt(scanner.nextLine()));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            scanner.close();
        }
        return vertices;
    }

    private static void addNewGraphNode(String row) {
        String[] rowData = row.split("\t");
        Integer nodeValue = null;
        GraphNode node = new GraphNode();

        for (String rowDatum : rowData) {
            if (!rowDatum.contains(",")) {
                nodeValue = Integer.parseInt(rowDatum);
                node.setKey(nodeValue);
                graph.add(nodeValue - 1, node);
                node.setDistance(Integer.MAX_VALUE);
            } else {
                String[] nextNodeData = rowDatum.split(",");

                Integer childNodeValue = Integer.parseInt(nextNodeData[0]);
                Integer childNodeDistance = Integer.parseInt(nextNodeData[1]);

                GraphNode childNode = new GraphNode();
                childNode.setKey(childNodeValue);
                childNode.setDistance(childNodeDistance);

                node.getNodes().add(childNode);
            }
        }
    }

    private static void findShortestPath(GraphNode childNode) {
        Integer childNodeIndex = childNode.getKey() - 1;
        pathValue += childNode.getDistance();

        GraphNode actualNode = graph.get(childNodeIndex);

        for (GraphNode node : actualNode.getNodes()) {
            setShortestPath(node);
        }

        if (!actualNode.isDirty()) {
            actualNode.setDirty(true);
            GraphNode nextChildNode = null;

            for (GraphNode nextNode : actualNode.getNodes()) {
                if (!nextNode.isDirty()) {
                    nextChildNode = nextNode;
                    break;
                }
            }
            findShortestPath(nextChildNode);
        }
    }

    private static void setShortestPath(GraphNode node) {
        GraphNode childActualNode = graph.get(node.getKey() - 1);
        int newPath = node.getDistance() + pathValue;
        Integer oldPath = childActualNode.getDistance();
        if (oldPath > newPath) {
            childActualNode.setDistance(newPath);
        }
    }
}
