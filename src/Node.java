import java.util.ArrayList;

/**
 * Created by Vera_Sidarovich on 6/24/2014.
 */
public class Node {
    private Integer value;
    private ArrayList<Node> ongoingNodes;
    private ArrayList<Node> backwardNodes;

    private boolean exploredNode;

    public Node() {
    }

    public Node(Integer value) {
        this.value = value;
        this.ongoingNodes = new ArrayList<Node>();
        this.backwardNodes = new ArrayList<Node>();
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public ArrayList<Node> getOngoingNodes() {
        return ongoingNodes;
    }

    public void setOngoingNodes(ArrayList<Node> ongoingNode) {
        this.ongoingNodes = ongoingNode;
    }

    public boolean isExploredNode() {
        return exploredNode;
    }

    public void setExploredNode(boolean exploredNode) {
        this.exploredNode = exploredNode;
    }

    public ArrayList<Node> getBackwardNodes() {
        return backwardNodes;
    }

    public void setBackwardNodes(ArrayList<Node> backwardNodes) {
        this.backwardNodes = backwardNodes;
    }

    @Override
    public String toString() {

        String ongoingNodesString = "Ongoing nodes : ";
        String backwardNodesString = "Backward nodes : ";

        for (Node node : ongoingNodes) {
            ongoingNodesString += node.getValue() + " ";
        }

        for (Node node : backwardNodes) {
            backwardNodesString += node.getValue() + " ";
        }

        return "Node{" +
                "exploredNode=" + exploredNode +
                ", value=" + value + ", ongoing=" + ongoingNodesString + ", backward=" + backwardNodesString +
                '}';
    }
}
