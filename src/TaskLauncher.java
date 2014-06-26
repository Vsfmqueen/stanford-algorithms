import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/*
* change HashMap to ArrayList
* change Node to index
* */

public class TaskLauncher {

    private static HashMap<Integer, Node> initialGraph = new HashMap();

    private static ArrayList<Node> backwardGraph = new ArrayList();

    private static ArrayList<Integer> componentsLengths;

    private static Node biggestNode;

    private static Integer componentLength = 0;

    public static void main(String... args) {
        scanFile();
        int greatestElement = initialGraph.size();
        biggestNode = initialGraph.get(greatestElement);
        exploreNode(biggestNode);

        initialGraph = null;

        //костыль
        backwardGraph.remove(backwardGraph.size()-1);

        for (Node node : backwardGraph) {
            System.out.println("Backward Node: " + node);
        }

/*        biggestNode = backwardGraph.get(backwardGraph.size() - 1);
        componentsLengths = new ArrayList();

        exploreBackwardNode(biggestNode);
        System.out.print(componentsLengths);*/
    }

    private static void scanFile() {
        File file = new File("c:\\example.txt");

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
        //    System.out.println("Current Node: " + node);
        if (!node.isExploredNode()) {
            node.setExploredNode(true);
            List<Node> onGoingNodes = node.getOngoingNodes();
            for (Node onGoingNode : onGoingNodes) {
                onGoingNode.getBackwardNodes().add(node);

                if (!onGoingNode.isExploredNode()) {
                    exploreNode(onGoingNode);
                }
            }

            if (node.equals(biggestNode)) {
                //  System.out.println("Explored Node: " + node);
                Node nextNode = findNextBiggestNode();

                if (nextNode.equals(biggestNode)) {
                    System.out.print("The end");
                    return;
                }

                biggestNode = nextNode;
                exploreNode(biggestNode);
            }
            node.setOngoingNodes(node.getBackwardNodes());
            backwardGraph.add(node);
         //   System.out.println("Explored Node: " + node);
        }
    }

    private static void exploreBackwardNode(Node node) {
        //    System.out.println("Current Node: " + node);
        if (node.isExploredNode()) {
            node.setExploredNode(false);
            List<Node> onGoingNodes = node.getOngoingNodes();
            for (Node onGoingNode : onGoingNodes) {

                componentLength++;

                if (onGoingNode.isExploredNode()) {
                    exploreBackwardNode(onGoingNode);
                }
            }

            node.setOngoingNodes(null);

            if (node.equals(biggestNode)) {
                //  System.out.println("Explored Node: " + node);

                Node nextNode = findNextBiggestBackwardNode(biggestNode);

                if (nextNode.equals(biggestNode)) {
                    System.out.print("The end");
                    return;
                }

                componentsLengths.add(componentLength);
                componentLength = new Integer(0);

                biggestNode = nextNode;
                exploreBackwardNode(biggestNode);
            }
        }
    }

    private static Node findNextBiggestNode() {
        Integer nodeValue = biggestNode.getValue();

        Node newBiggest = null;

        while (nodeValue != 0) {
          //  System.out.println("nodeValue = " + nodeValue);
            newBiggest = initialGraph.get(nodeValue);
            if (!newBiggest.isExploredNode()) {
           //     biggestNode.setOngoingNodes(null);
                backwardGraph.add(biggestNode);
                //System.out.println("Explored Node: " + biggestNode);
                break;
            }
            nodeValue--;
        }

        return newBiggest;
    }

    private static Node findNextBiggestBackwardNode(Node node) {
        Integer nextNodeIndex = backwardGraph.indexOf(node) - 1;

        Node newBackwardNode = node;

        Node newBiggest = null;

        while (nextNodeIndex >= 0) {
            //  System.out.println("nodeValue = " + nodeValue);
            if (newBiggest.isExploredNode()) {
                newBiggest = backwardGraph.get(nextNodeIndex);
                //System.out.println("Explored Node: " + biggestNode);
                break;
            }
            nextNodeIndex--;
        }

        return newBiggest;
    }

}
