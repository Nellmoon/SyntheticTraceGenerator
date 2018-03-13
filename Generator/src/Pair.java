public class Pair implements Comparable<Pair> {
    long stamp;
    int key;
    int times;
    public Pair(int key, int times, long stamp) {
        this.key = key;
        this.times = times;
        this.stamp = stamp;
    }

    @Override
    public int compareTo(Pair that) {
        if (this.times == that.times) {
            return (int)(this.stamp - that.stamp);
        } else {
            return this.times - that.times;    
        }
    }
}