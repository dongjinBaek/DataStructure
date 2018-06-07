import java.util.Comparator;

public class ComparatorByTransfers implements Comparator<Distance> {
        public int compare(Distance d1, Distance d2) {
            long t1, t2;
            if ((t1 = d1.getTransfers()) == (t2 = d2.getTransfers()))
                return Long.compare(d1.getTime(), d2.getTime());
            else
                return Long.compare(t1, t2);
        }
}
