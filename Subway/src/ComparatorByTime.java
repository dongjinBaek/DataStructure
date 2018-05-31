import java.util.Comparator;

public class ComparatorByTime implements Comparator<Distance> {
    public int compare(Distance d1, Distance d2) {
        long t1 = d1.getTotTime();
        long t2 = d2.getTotTime();
        return (t1 - t2 == 0 ? 0 :
                (t1 - t2 < 0 ? -1 : 1));
    }
}
