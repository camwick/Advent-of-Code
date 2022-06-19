public class HeatMap {
    private int contents;
    private int[] neighbors;
    private boolean visited;

    public HeatMap(int x) {
        this.contents = x;
        this.visited = x == 9;
        this.neighbors = new int[4];
    }

    private int getContents() {
        return this.contents;
    }

    private boolean getVisited() {
        return this.visited;
    }

    private int[] getNeighbors() {
        return this.neighbors;
    }

    private void setNeighbors(int[] x) {
        for (int i = 0; i < x.length; ++i) {
            this.neighbors[i] = x[i];
        }
    }
}
