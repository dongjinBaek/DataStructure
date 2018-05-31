
public class Distance {
    private long transfers;
    private long time;
    private long totTime;

    public Distance(long transfers, long time) {
        this.transfers = transfers;
        this.time = time;
        this.totTime = this.transfers * 5 + this.time;
    }

    public Distance add(Distance d) {
        return new Distance(transfers + d.transfers, time + d.time);
    }

    public long getTransfers() {
        return transfers;
    }

    public long getTotTime() {
        return totTime;
    }

    public long getTime() {
        return time;
    }
}
