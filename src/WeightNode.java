/**
 * Created by Vera_Sidarovich on 7/10/2014.
 */
public class WeightNode implements Comparable<WeightNode> {
    private int nextNodeKey;
    private int distance;

    public WeightNode() {

    }

    public WeightNode(int nextNodeKey, int distance) {
        this.nextNodeKey = nextNodeKey;
        this.distance = distance;
    }

    public int getNextNodeKey() {
        return nextNodeKey;
    }

    public void setNextNodeKey(int nextNodeKey) {
        this.nextNodeKey = nextNodeKey;
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
                "nextNodeKey=" + nextNodeKey +
                ", distance=" + distance +
                '}';
    }
}
