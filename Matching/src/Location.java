public class Location implements Comparable<Location>{
    private int row;
    private int col;

    public Location(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    @Override
    public int compareTo(Location o) {

    }

    @Override
    public String toString() {
        return String.format("(%d, %d)", row, col);
    }
}
