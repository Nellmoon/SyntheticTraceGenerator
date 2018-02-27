import java.util.*;

class LRUCache implements Cache{
    int capacity;
    HashMap<Integer, Node> map = new HashMap<>();
    Node head=null;
    Node end=null;
 
    public LRUCache(int capacity) {
        this.capacity = capacity;
    }
 
    public int get(int key) {
        if (this.capacity == 0){
            return -1;
        }
        if(map.containsKey(key)){
            Node n = map.get(key);
            remove(n);
            setHead(n);
            return key;
        }
 
        return -1;
    }
    
    public boolean contains(int key) {
        if (this.capacity == 0){
            return false;
        }
        return map.containsKey(key);
    }

    public int containsHistory(int key) {
       return -1;
    }    
    
    public int containsArc(int key) {
       return -1;
    }
 
    public void remove(Node n){
        if(n.pre!=null){
            n.pre.next = n.next;
        }else{
            head = n.next;
        }
 
        if(n.next!=null){
            n.next.pre = n.pre;
        }else{
            end = n.pre;
        }
 
    }
 
    public void setHead(Node n){
        n.next = head;
        n.pre = null;
 
        if(head!=null)
            head.pre = n;
 
        head = n;
 
        if(end ==null)
            end = head;
    }
 
    public void set(int key) {
        if(map.containsKey(key)){
            Node old = map.get(key);
            remove(old);
            setHead(old);
        }else{
            Node created = new Node(key);
            if(map.size()>=capacity){
                map.remove(end.key);
                remove(end);
                setHead(created);
 
            }else{
                setHead(created);
            }    
 
            map.put(key, created);
        }
    }
    
    public void printCache(){
        Node holder = head;        
        System.out.print("LRU ");
        while (!holder.equals(end)){
            System.out.print(" " + holder.key);
            holder = holder.next;
        }
        System.out.println(" " +end.key);
    }
}

class Node{
    int key;
    Node pre;
    Node next;
 
    public Node(int key){
        this.key = key;
    }
}