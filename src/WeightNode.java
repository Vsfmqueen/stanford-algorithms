/**
 * Created by Vera_Sidarovich on 7/10/2014.
 */
public class WeightNode implements Comparable<WeightNode> {
    private int nodeKey;
    private int distance;

    public WeightNode() {

    }

    public WeightNode(int nextNodeKey, int distance) {
        this.nodeKey = nextNodeKey;
        this.distance = distance;
    }

    public int getNodeKey() {
        return nodeKey;
    }

    public void setNodeKey(int nextNodeKey) {
        this.nodeKey = nextNodeKey;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    @Override
    public int compareTo(WeightNode secondNode) {
        return Integer.compare(getDistance(), secondNode.getDistance());
    }

    @Override
    public String toString() {
        return "WeightNode{" +
                "nodeKey=" + nodeKey +
                ", distance=" + distance +
                '}';
    }
}
