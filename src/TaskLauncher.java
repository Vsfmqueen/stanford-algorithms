import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
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

        Iterator<GraphNode> iterator = firstNode.getNodes().iterator();

        while (iterator.hasNext()) {
            GraphNode nextChildNode = firstNode.getNodes().poll();
            System.out.println("Next source node = "+nextChildNode);
            pathValue = 0;
            setShortestPath(nextChildNode);
            findShortestPath(nextChildNode);
        }

        for (GraphNode node : graph) {
            System.out.println(node.getKey() + " :  " + node.getDistance());
        }

        ArrayList<Integer> vertices = getVerticesFromFile();

     /*   for (Integer vertice : vertices) {
            System.out.println("Vertice = " + vertice + " Distance = " + graph.get(vertice - 1).getDistance());
        }*/
    }

    private static void fillGraphFromFile() {
        Scanner scanner = null;
        File file = new File("c:\\test.txt");

        try {
            scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
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
            while (scanner.hasNextLine()) {
                String row = scanner.nextLine();
                vertices.add(Integer.parseInt(row));
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
                node.setDistance(1000000);
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

        System.out.println("Path value = " + pathValue + " Node value = " + childNode);

        GraphNode actualNode = graph.get(childNodeIndex);

        for (GraphNode node : actualNode.getNodes()) {
            setShortestPath(node);
        }

        actualNode.setDirty(true);
        Iterator<GraphNode> iterator =actualNode.getNodes().iterator();

        ArrayList<GraphNode> peeks = new ArrayList<GraphNode>();

        while (iterator.hasNext()) {
            //не удалять вершину
            GraphNode nextChildNode = actualNode.getNodes().poll();
            peeks.add(nextChildNode);
            if (!graph.get(nextChildNode.getKey() - 1).isDirty()) {//ошибка
                findShortestPath(nextChildNode);

                break;
            }
        }

         actualNode.getNodes().addAll(peeks);
    }

    private static void setShortestPath(GraphNode node) {
        GraphNode childActualNode = graph.get(node.getKey() - 1);

        //System.out.println("Current path = "+pathValue);
        //System.out.println("Node = " + childActualNode.getKey() + " Distance = " + node.getDistance());

        int newPath = node.getDistance() + pathValue;

        if(node.getKey().equals(5)){
            System.out.println(5);
        }

        Integer oldPath = childActualNode.getDistance();
        if (oldPath > newPath) {
            childActualNode.setDistance(newPath);
            System.out.println("Changed from = " + oldPath + " to " + newPath);
        }
    }
}
