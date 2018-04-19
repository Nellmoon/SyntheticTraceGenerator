package controllers;

/**
 * Represents a page request element
 * @author Wendy Aleman Martinez
 */
public class Pair implements Comparable<Pair> {
    long stamp;
    int key;
    int times;

    /**
     * @param key
     * Represents the page
     * @param times
     * Represents the times (frequency) the page key has being accessed
     * @param stamp
     * Represents the order of the last time the page key was requested
     */
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