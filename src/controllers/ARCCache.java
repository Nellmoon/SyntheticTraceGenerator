package controllers;

import java.util.LinkedList;

/**
 * @author Wendy Aleman Martinez & Giuseppe Vietri
 * Implementation of the Adaptive Replacement Algorithm (ARC)
 */
public class ARCCache implements Cache{  

    /**
     * Size of the Cache (N)
     */
    public int C;

    /**
     * Cache portion for recency (LRU)
     */
    public LinkedList<Integer> t1,

    /**
     * Cache portion for frequency (LFU)
     */
    t2,

    /**
     * History portion for recency (LRU)
     */
    b1,

    /**
     * History portion for frequency (LFU)
     */
    b2;

    /**
     * Parameter P that controls the size of L1 and L2
     */
    public double P;
    
    /**
     * Constructor
     * @param cache_sz
     * Cache size = C = N
     */
    public ARCCache(int cache_sz){
        C = cache_sz;
        t1 = new LinkedList<>();
        t2 = new LinkedList<>();
        b1 = new LinkedList<>();
        b2 = new LinkedList<>();
        P = 0.0;
    }
    
    public void set(int x)
    {
        double delta = 0;
        if(IN(x,t1) ){
            DEL(t1,x);
            ADD_MRU(t2,x);
        }else if(IN(x,t2) ){
            DEL(t2,x);
            ADD_MRU(t2,x);
        }else if(IN(x,b1)){
            delta = SZ(b1) > SZ(b2) ? 1: SZ(b2) / (double)SZ(b1);
            P = Math.min(C, P +delta);
            replace(x);
            DEL(b1,x);
            ADD_MRU(t2,x);
        }else if( IN(x,b2) ){
            delta = SZ(b2) > SZ(b1) ? 1: SZ(b1) / (double)SZ(b2);
            P = Math.max(0, P-delta);
            replace(x);
            DEL(b2,x);
            ADD_MRU(t2,x);
        }else{
            if(SZ(t1) + SZ(b1) == C){
                if(SZ(t1) < C){
                    DEL_LRU(b1);
                    replace(x);
                }else 
                    DEL_LRU(t1);
            }else{
                if(SZ(t1) + SZ(b1) + SZ(t2) + SZ(b2) >= C){
                    if(SZ(t1) + SZ(b1) + SZ(t2) + SZ(b2) >= 2*C)
                        DEL_LRU(b2);
                    replace(x);
                }
            }
            ADD_MRU(t1,x);
        }
    }
    
    private void replace(int x){
        int y;
        if(SZ(t1) >0 && (SZ(t1) > P + 0.000001 || (IN(x,b2) && SZ(t1) == (int)Math.floor(P)))){
            y = DEL_LRU(t1);
            ADD_MRU(b1,y);
        }else{
            y = DEL_LRU(t2);
            ADD_MRU(b2,y);
        }	
    }
    
    public int get(int key){
        return -1;
    }
    
    public boolean contains(int key){
        return t1.contains(key) || t2.contains(key);
    }
        
    public int containsArc(int key) {
        if (t1.contains(key))
            return 1;
        if (t2.contains(key))
            return 2;
        return 0;
    }
    
    public int containsHistory(int key){
        if (b1.contains(key))
            return 1;
        if (b2.contains(key))
            return 2;
        return 0;
    }
    
    public void printCache(){
        System.out.print("ARC   T1: " + t1.toString());
        System.out.print( " T2: " + t2.toString());
        System.out.print(" B1: " + b1.toString());
        System.out.println(" B2: " + b2.toString());
    }
    
    /**
     * @param key 
     * @return
     * 1 if the page key is contained on T1, 0 otherwise
     */
    public boolean t1_contains(int key){
        return t1.contains(key);
    }
    
    /**
     * @param key
     * @return
     * 1 if the page key is contained on T1, 0 otherwise
     */
    public boolean t2_contains(int key){
        return t2.contains(key);
    }
    
    /**
     * @param key
     * @return
     * 1 if the page key is contained on B1, 0 otherwise
     */
    public boolean b1_contains(int key){
        return b1.contains(key);
    }
    
    /**
     * @param key
     * @return
     * 1 if the page key is contained on B2, 0 otherwise
     */
    public boolean b2_contains(int key){
        return b2.contains(key);
    }
    
    /**
     * @param l
     * @param x
     * Delete element x from linked list l
     */
    protected void DEL(LinkedList<Integer> l, int x){
        l.removeFirstOccurrence((Integer)x);
    }
    
    /**
     * @param l
     * @return 
     * Size of the linked list l
     */
    protected int SZ(LinkedList<Integer> l){
        return l.size();
    }

    /**
     * @param x
     * @param l
     * @return
     * 1 if l is contained on linked list x, 0 otherwise
     */
    protected boolean IN(int x, LinkedList<Integer> l){
        return l.contains(x);
    }

    /**
     * @param l
     * @param x
     * Add x as the most recently used page of l
     */
    protected void ADD_MRU(LinkedList<Integer> l, int x){
        l.addLast(x);
    }
    
    int DEL_LRU(LinkedList<Integer> l){
        int x = l.getFirst();
        l.removeFirst();
        return x;
    }

    /**
     * @return 
     * hashed string with the contents of the cache
     */
    public String str_hash(){
        String hash = "";

        hash += "|";
        for(int x : t1)
            hash += x + (x == t1.getLast() ? "" : ",");
        hash += "|";
        for(int x : t2)
            hash += x + (x == t2.getLast() ? "" : ",");
        hash += "|";
        for(int x : b1)
            hash += x + (x == b1.getLast() ? "" : ",");
        hash += "|";
        for(int x : b2)
            hash += x + (x == b2.getLast() ? "" : ",");
        hash += "|";

        return hash;
    }

    public int[] toArrayInt() {
        Integer[] arr1 = new Integer[t1.size()];
        arr1 = t1.toArray(arr1);
        Integer[] arr2 = new Integer[t2.size()];
        arr2 = t2.toArray(arr2);
        int[] arr = new int[t1.size()+ t2.size()];
        int i = 0;
        while(i < t1.size()){
            arr[i] = (int)arr1[i];
            i++;
        }
        int j = 0;
        while(j < t2.size()){
            arr[t1.size() + j] = (int)arr2[j];
            j++;
        }
        return arr;
    }
}

