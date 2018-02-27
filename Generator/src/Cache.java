/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
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
