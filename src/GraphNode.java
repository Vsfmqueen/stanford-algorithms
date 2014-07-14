import java.util.PriorityQueue;

/**
 * Created by Vera_Sidarovich on 7/11/2014.
 */
public class GraphNode implements Comparable<GraphNode> {
    private Integer key;
    private int distance;
    private PriorityQueue<GraphNode> nodes;
    private boolean isDirty;

    public GraphNode() {
        nodes = new PriorityQueue<GraphNode>();
    }

    public GraphNode(PriorityQueue<GraphNode> nodes) {
        this.nodes = nodes;
    }

    public Integer getKey() {
        return key;
    }

    public void setKey(Integer key) {
        this.key = key;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public PriorityQueue<GraphNode> getNodes() {
        return nodes;
    }

    public void setNodes(PriorityQueue<GraphNode> nodes) {
        this.nodes = nodes;
    }

    public boolean isDirty() {
        return isDirty;
    }

    public void setDirty(boolean isDirty) {
        this.isDirty = isDirty;
    }

    @Override
    public int compareTo(GraphNode secondNode) {
        return Integer.compare(getDistance(), secondNode.getDistance());
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + distance;
        result = prime * result + (isDirty ? 1231 : 1237);
        result = prime * result + ((key == null) ? 0 : key.hashCode());
        result = prime * result + ((nodes == null) ? 0 : nodes.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        GraphNode other = (GraphNode) obj;
        if (distance != other.distance)
            return false;
        if (isDirty != other.isDirty)
            return false;
        if (key == null) {
            if (other.key != null)
                return false;
        } else if (!key.equals(other.key))
            return false;
        if (nodes == null) {
            if (other.nodes != null)
                return false;
        } else if (!nodes.equals(other.nodes))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "GraphNode [key=" + key + ", distance=" + distance
                + ", nodesSize=" + nodes.size() + ", isDirty=" + isDirty + "]";
    }
}
