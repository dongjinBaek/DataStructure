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
        int rowCmp = row - o.getRow();
        if (rowCmp > 0)
            return 1;
        else if (rowCmp < 0)
            return -1;
        else {
            int colCmp = col - o.getCol();
            if (colCmp > 0)
                return 1;
            else if (colCmp < 0)
                return -1;
            else
                return 0;
        }
    }

    @Override
    public String toString() {
        return String.format("(%d, %d)", row, col);
    }

    public Location add(int v) {
        return new Location(row, col + v);
    }
}
