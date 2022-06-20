public class HeightMap {
    private int contents;
    private HeightMap[] neighbors;
    private boolean corner;
    private boolean visited;

    public HeightMap(int x) {
        this.contents = x;
        this.visited = x == 9;
        this.corner = false;
        this.neighbors = new HeightMap[4];
    }

    public int getContents() {
        return this.contents;
    }

    public boolean getVisited() {
        return this.visited;
    }

    public boolean getCorner() {
        return this.corner;
    }

    public HeightMap[] getNeighbors() {
        return this.neighbors;
    }

    public void setNeighbors(HeightMap[] x) {
        for (int i = 0; i < x.length; ++i) {
            this.neighbors[i] = x[i];
        }
    }

    public void setCorner(boolean x) {
        this.corner = x;
    }

    public void setVisited(boolean x) {
        this.visited = x;
    }
}
