/**
 * Created by Vera_Sidarovich on 7/10/2014.
 */
public class WeightNode implements Comparable<WeightNode> {
    private int nodeKey;
    private int nodeValue;

    public WeightNode() {

    }

    public WeightNode(int nodeKey, int nodeValue) {
        this.nodeKey = nodeKey;
        this.nodeValue = nodeValue;
    }

    public int getNodeKey() {
        return nodeKey;
    }

    public void setNodeKey(int nodeKey) {
        this.nodeKey = nodeKey;
    }

    public int getNodeValue() {
        return nodeValue;
    }

    public void setNodeValue(int nodeValue) {
        this.nodeValue = nodeValue;
    }

    @Override
    public int compareTo(WeightNode secondNode) {
        return Integer.compare(getNodeValue(), secondNode.getNodeValue());
    }

    @Override
    public String toString() {
        return "TreeNode{" +
                "nodeKey=" + nodeKey +
                ", nodeValue=" + nodeValue +
                '}';
    }
}
