import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.*;

/*
    *TODO
* change HashMap to ArrayList
* change Node to index
* */

public class TaskLauncher {

    private static HashMap<Integer, Node> initialGraph = new HashMap();

    private static ArrayList<Node> backwardGraph = new ArrayList();

    private static ArrayList<Integer> componentsLengths = new ArrayList<Integer>() {{
        add(0);
        add(0);
        add(0);
        add(0);
        add(0);
    }};


    private static Node biggestNode;

    private static Integer componentLength = 0;

    public static void main(String... args) {
        System.out.println("Started reading file..." + new Date());
        scanFile();
        System.out.println("Ended reading file..." + new Date());
        System.out.println("Started exploring initial graph..." + new Date());
        int greatestElement = initialGraph.size() - 1;
        biggestNode = initialGraph.get(greatestElement);
        exploreNode(biggestNode);

        initialGraph = null;
        System.out.println("Ended exploring initial graph..." + new Date());

        biggestNode = backwardGraph.get(backwardGraph.size() - 1);
        System.out.println("Started computing SCC..." + new Date());
        exploreBackwardNode(biggestNode);
        Collections.sort(componentsLengths, Collections.reverseOrder());
        System.out.println(componentsLengths.subList(0, 5));
        System.out.println("Ended computing SCC..." + new Date());
    }

    private static void scanFile() {
        File file = new File("c:\\SCC.txt");

        Scanner scanner = null;
        try {
            scanner = new Scanner(file);
            while (scanner.hasNext()) {
                String row = scanner.nextLine();
                fillArray(row, initialGraph);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            scanner.close();
        }
    }

    private static void fillArray(String row, HashMap<Integer, Node> initialGraph) {
        String[] rowArray = row.split(" ", row.length() - 1);
        Integer headEdgeValue = Integer.parseInt(rowArray[0]);
        Integer tailEdgeValue = Integer.parseInt(rowArray[1]);

        Node headNode = getNodeByValue(headEdgeValue);
        Node tailNode = getNodeByValue(tailEdgeValue);

        headNode.getOngoingNodes().add(tailNode);
    }

    private static Node getNodeByValue(Integer value) {
        Node node = initialGraph.get(value);

        if (node == null) {
            node = new Node(value);
            initialGraph.put(value, node);
        }
        return node;
    }

    private static void exploreNode(Node node) {
        if (!node.isExploredNode()) {
            node.setExploredNode(true);
            List<Node> onGoingNodes = node.getOngoingNodes();
            for (Node onGoingNode : onGoingNodes) {
                onGoingNode.getBackwardNodes().add(node);

                if (!onGoingNode.isExploredNode()) {
                    exploreNode(onGoingNode);
                }
            }

            if (backwardGraph.indexOf(node) == -1) {
                backwardGraph.add(node);
            }

            if (node.equals(biggestNode)) {
                Node nextNode = findNextBiggestNode();

                if (nextNode.equals(biggestNode)) {
                    return;
                }

                biggestNode = nextNode;
                exploreNode(biggestNode);
            }
        }
    }

    private static void exploreBackwardNode(Node node) {
        if (node.isExploredNode()) {
            node.setExploredNode(false);
            List<Node> onGoingNodes = node.getBackwardNodes();

            for (Node onGoingNode : onGoingNodes) {

                if (onGoingNode.isExploredNode()) {
                    componentLength++;
                    exploreBackwardNode(onGoingNode);
                }
            }

            if (node.getValue().equals(biggestNode.getValue())) {
                Node nextNode = findNextBiggestBackwardNode(biggestNode);
                componentsLengths.add(componentLength + 1);
                componentLength = 0;

                if (nextNode.equals(biggestNode)) {
                    return;
                }

                biggestNode = nextNode;
                exploreBackwardNode(biggestNode);
            }
        }
    }

    private static Node findNextBiggestNode() {
        Integer nodeValue = biggestNode.getValue();

        Node newBiggest = null;

        while (nodeValue != 0) {
            newBiggest = initialGraph.get(nodeValue);
            if (!newBiggest.isExploredNode()) {
                backwardGraph.add(biggestNode);
                break;
            }
            nodeValue--;
        }
        return newBiggest;
    }

    private static Node findNextBiggestBackwardNode(Node node) {
        Integer nextNodeIndex = backwardGraph.indexOf(node);

        Node newBackwardNode = node;

        while (nextNodeIndex > 0) {
            nextNodeIndex--;
            newBackwardNode = backwardGraph.get(nextNodeIndex);
            if (newBackwardNode.isExploredNode()) {
                break;
            }
        }
        return newBackwardNode;
    }
}
