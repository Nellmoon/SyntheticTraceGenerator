import java.util.LinkedList;

public class ARCCache implements Cache{  
    private int C;
    private LinkedList<Integer> t1, t2, b1, b2;
    private LinkedList<String> hist;
    private double P;
    
    public ARCCache(int cache_sz){
        C = cache_sz;
        t1 = new LinkedList<>();
        t2 = new LinkedList<>();
        b1 = new LinkedList<>();
        b2 = new LinkedList<>();
        hist = new LinkedList<>();
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
        hist.add(this.str_hash());    
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
    
    public boolean t1_contains(int key){
        return t1.contains(key);
    }
    
    public boolean t2_contains(int key){
        return t2.contains(key);
    }
    
    public boolean b1_contains(int key){
        return b1.contains(key);
    }
    
    public boolean b2_contains(int key){
        return b2.contains(key);
    }
    
    protected void DEL(LinkedList<Integer> l, int x){
        l.removeFirstOccurrence((Integer)x);
    }
    
    protected int SZ(LinkedList<Integer> l){
        return l.size();
    }
    protected boolean IN(int x, LinkedList<Integer> l){
        return l.contains(x);
    }

    protected void ADD_MRU(LinkedList<Integer> l, int x){
        l.addLast(x);
    }
    int DEL_LRU(LinkedList<Integer> l){
        int x = l.getFirst();
        l.removeFirst();
        return x;
    }
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
}

