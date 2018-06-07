import java.util.Comparator;

public class ComparatorByTime implements Comparator<Distance> {
    public int compare(Distance d1, Distance d2) {
        return Long.compare(d1.getTotTime(), d2.getTotTime());
    }
}