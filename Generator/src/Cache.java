/*
 * @author Wendy
 */
public interface Cache {
    public void set(int key);
    public int get(int key);
    public boolean contains(int key);
    public int containsHistory(int key);
    public int containsArc(int key); 
    public void printCache();     
}
