import java.util.Comparator;

public class ComparatorByTransfers implements Comparator<Distance> {
        public int compare(Distance d1, Distance d2) {
            long t1, t2;
            if ((t1 = d1.getTransfers()) == (t2 = d2.getTransfers())) {
                t1 = d1.getTime();
                t2 = d2.getTime();
            }
            return (t1 - t2 == 0 ? 0 :
                    (t1 - t2 < 0 ? -1 : 1));
        }
}
