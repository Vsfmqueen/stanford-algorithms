public class Edge {
    private Integer firstVertice;
    private Integer secondVertice;

    public Edge(Integer firstVertice, Integer secondVertice) {
        this.firstVertice = firstVertice;
        this.secondVertice = secondVertice;
    }

    public Integer getFirstVertice() {
        return firstVertice;
    }

    public void setFirstVertice(Integer firstVertice) {
        this.firstVertice = firstVertice;
    }

    public Integer getSecondVertice() {
        return secondVertice;
    }

    public void setSecondVertice(Integer secondVertice) {
        this.secondVertice = secondVertice;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((firstVertice == null) ? 0 : firstVertice.hashCode());
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
        if (firstVertice == null) {
            if (other.firstVertice != null)
                return false;
        } else if (!firstVertice.equals(other.firstVertice))
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
        return "Edge [firstVertice=" + firstVertice + ", secondVertice=" + secondVertice + "]";
    }
}
