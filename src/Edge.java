public class Edge {
    private Integer secondVertice;
    private String initialEdge;

    public Edge(Integer secondVertice, String initialEdge) {
        this.secondVertice = secondVertice;
        this.initialEdge = initialEdge;
    }

    public Integer getSecondVertice() {
        return secondVertice;
    }

    public void setSecondVertice(Integer secondVertice) {
        this.secondVertice = secondVertice;
    }

    public String getInitialEdge() {
        return initialEdge;
    }

    public void setInitialEdge(String initialEdge) {
        this.initialEdge = initialEdge;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((initialEdge == null) ? 0 : initialEdge.hashCode());
        result = prime * result + ((secondVertice == null) ? 0 : secondVertice.hashCode());
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
        Edge other = (Edge) obj;
        if (initialEdge == null) {
            if (other.initialEdge != null)
                return false;
        } else if (!initialEdge.equals(other.initialEdge))
            return false;
        if (secondVertice == null) {
            if (other.secondVertice != null)
                return false;
        } else if (!secondVertice.equals(other.secondVertice))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Edge [secondVertice=" + secondVertice + ", initialEdge=" + initialEdge + "]";
    }
}
