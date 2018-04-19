package controllers;
/**
 * @author Wendy Aleman Martinez
 * Interface that represents all the cache algorithms
 */
public interface Cache {
    
    /**
     * @param key:
     * Inserts the key into the cache... could be a hit or a miss, basically it
     * represents a request to page key
     */
    public void set(int key);
    
    /**
     * @param key
     * Similar to set but only used if we know the page is already on cache
     * @return
     * the index of the page key on the cache
     */
    public int get(int key);

    /**
     * @param key
     * Represents the page request to search for on the cache
     * @return
     * 1 if the page is contained on the cache, 0 otherwise
     */
    public boolean contains(int key);

    /**
     * @param key
     * Represents the page request to search for on the history
     * @return
     * 1 if the page is contained on the history, 0 otherwise
     */
    public int containsHistory(int key);

    /**
     * Only applicable to ARC type 
     * @param key
     * Represents the page request to search for on the history
     * @return
     * 1 if the page is contained on L1, 2 if it is contained on L2, 0 otherwise
     */
    public int containsArc(int key); 

    /**
     * Prints the contents of the cache for Debug purposes
     */
    public void printCache();   

    /**
     * @return
     * converts the cache in an Array of integers
     */
    public int [] toArrayInt();
}
