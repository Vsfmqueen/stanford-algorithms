import java.util.PriorityQueue;

/**
 * Created by Vera_Sidarovich on 7/11/2014.
 */
public class GraphNode {
    private Integer key;
    private PriorityQueue<WeightNode> nodes;
    private Integer pathValue;

    public GraphNode() {
        nodes = new PriorityQueue<WeightNode>();
        pathValue = 0;
    }

    public GraphNode(PriorityQueue<WeightNode> nodes) {
        this.nodes = nodes;
    }

    public Integer getKey() {
        return key;
    }

    public void setKey(Integer key) {
        this.key = key;
    }

    public PriorityQueue<WeightNode> getNodes() {
        return nodes;
    }

    public void setNodes(PriorityQueue<WeightNode> nodes) {
        this.nodes = nodes;
    }

    public Integer getPathValue() {
        return pathValue;
    }

    public void setPathValue(Integer pathValue) {
        this.pathValue = pathValue;
    }

    @Override
    public String toString() {
        return "GraphNode{" +
                "key=" + key +
                ", nodes=" + nodes +
                ", pathValue=" + pathValue +
                '}';
    }
}
