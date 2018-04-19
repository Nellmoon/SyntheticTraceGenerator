package controllers;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.Thread.State;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;

/**
 * Counts the pollution of a cache
 * @author Wendy Aleman Martinez
 */
public class PollutionCounter implements Runnable{

    /**
     * Cache size 
     */
    public int N = 500;

    /**
     * File with Trace 
     */
    public String file = "";
    
    /**
     * Maps the pages that have being on cache 
     */
    public List<Pair> MapDecayed = new ArrayList<>();

    /**
     * Maps page with the index on cache
     */
    public HashMap<Integer, Integer> hashMapInt = new HashMap<>();

    /**
     * Checks pollution for LRU, LFU, ARC
     * @param args
     * @throws IOException
     * @throws Exception
     */
    
    public PollutionCounter(String file, int cacheSize){
        N = cacheSize;
        this.file = file;
    }

    @Override
    @SuppressWarnings("empty-statement")
    public void run() {   
        try {
	boolean erased = false;
        int i, cur, unique;
        //read the file on this adress and save it as a list
        List<Integer> requests;
        requests = readLines(new File(file));
        requests.removeAll(Arrays.asList(-1));
        
        String tmp[] = file.split(Pattern.quote("\\"));
        String filename = tmp[tmp.length-1].substring(0, tmp[tmp.length-1].length()-4);

        //*********************************
        //*****        LFU       **********
        //*********************************
        i = 0;
        cur = 0;
        unique = 0;
        LFUCache lfu = new LFUCache(N);
        BufferedWriter writer2;
        writer2 = new BufferedWriter(new FileWriter("Stats\\"+filename + "LFUPollution.txt"));             
        //writer2.append(filename + "LFUandLRUPollution" + "\n");
        writer2.append(filename + "\n");
        writer2.append("LFU  \n");
        while(lfu.minHeap.size() < N){
            lfu.set(requests.get(cur));            
            cur++;
            if (!hashMapInt.containsKey(requests.get(cur))){
                unique++;   
                MapDecayed.add(new Pair(requests.get(cur), 1, unique));
                hashMapInt.put(requests.get(cur), (Integer)(MapDecayed.size()-1));
            }
            else
            {
                MapDecayed.set(hashMapInt.get(requests.get(cur)), new Pair(requests.get(cur), MapDecayed.get(hashMapInt.get(requests.get(cur))).times + 1, unique));
            }			
        }
        
        //Check for pollution 
        //writer.append((countSingles(lfu.minHeap.toArray())/N)*100 + "\n");
        writer2.append((countPollution(lfu.toArrayInt(), unique)/N)*100 + "\n");
        //get next N requests and Check for pollution

        while (cur < requests.size()){
            if (i >= N){
                writer2.append((countPollution(lfu.toArrayInt(), unique)/N)*100 + "\n");
                i = 0;
            }
            erased = hashMapInt.containsKey(requests.get(cur)) && !lfu.contains(requests.get(cur));
            lfu.set(requests.get(cur)); 
            if (erased){
                unique++;
                MapDecayed.set(hashMapInt.get(requests.get(cur)), new Pair(requests.get(cur), 1,unique));
            }
            else{
                if (!hashMapInt.containsKey(requests.get(cur))){
                    unique++;
                    MapDecayed.add(new Pair(requests.get(cur), 1, unique));
                    hashMapInt.put(requests.get(cur), (Integer)(MapDecayed.size()-1));
                }
                else
                {
                    MapDecayed.set(hashMapInt.get(requests.get(cur)), new Pair(requests.get(cur), MapDecayed.get(hashMapInt.get(requests.get(cur))).times + 1,unique));
                }
            }
            i++;
            cur++;
        }  
	writer2.close();
        
        //*********************************
        //*****        LRU       **********
        //*********************************
        i = 0;
        cur = 0;
        unique = 0;
        MapDecayed = new ArrayList<>();
        hashMapInt = new HashMap<>();
        LRUCache lru = new LRUCache(N); 
        BufferedWriter writer3;
        writer3 = new BufferedWriter(new FileWriter("Stats\\" + filename + "LRUPollution.txt"));                  
        writer3.append("LRU  \n");
        while(lru.map.size() < N){
            lru.set(requests.get(cur));            
            cur++;
            if (!hashMapInt.containsKey(requests.get(cur))){
                unique++;
                MapDecayed.add(new Pair(requests.get(cur), 1, unique));
                hashMapInt.put(requests.get(cur), (Integer)(MapDecayed.size()-1));
            }
            else
            {
                MapDecayed.set(hashMapInt.get(requests.get(cur)), new Pair(requests.get(cur), MapDecayed.get(hashMapInt.get(requests.get(cur))).times + 1,unique));
            }			
        }
		
        //Check for pollution 
        //writer.append((countSingles(lfu.minHeap.toArray())/N)*100 + "\n");
        writer3.append((countPollution(lru.toArrayInt(), unique)/N)*100 + "\n");
        //get next N requests and Check for pollution

        while (cur < requests.size()){
            if (i >= N){
                writer3.append((countPollution(lru.toArrayInt(), unique)/N)*100 + "\n");
                i = 0;
            }
            erased = hashMapInt.containsKey(requests.get(cur)) && !lru.contains(requests.get(cur));
            lru.set(requests.get(cur)); 
            if (erased){
                unique++;
                MapDecayed.set(hashMapInt.get(requests.get(cur)), new Pair(requests.get(cur), 1,unique));
            }
            else{
                if (!hashMapInt.containsKey(requests.get(cur))){
                    unique++;
                    MapDecayed.add(new Pair(requests.get(cur), 1, unique));
                    hashMapInt.put(requests.get(cur), (Integer)(MapDecayed.size()-1));
                }
                else
                {                    
                    MapDecayed.set(hashMapInt.get(requests.get(cur)), new Pair(requests.get(cur), MapDecayed.get(hashMapInt.get(requests.get(cur))).times + 1,unique));
                }
            }          
            i++;
            cur++;
        }        
        writer3.close();
        //*********************************
        //*****        ARC       **********
        //*********************************
        i = 0;
        cur = 0;
        unique = 0;
        MapDecayed = new ArrayList<>();
        hashMapInt = new HashMap<>();
        ARCCache arc = new ARCCache(N); 
        BufferedWriter writer4;
        writer4 = new BufferedWriter(new FileWriter("Stats\\" + filename + "ARCPollution.txt"));                  
        writer4.append("ARC  \n");
        while(arc.t1.size() + arc.t2.size() < N){
            arc.set(requests.get(cur));            
            cur++;
            if (!hashMapInt.containsKey(requests.get(cur))){
                unique++;
                MapDecayed.add(new Pair(requests.get(cur), 1, unique));
                hashMapInt.put(requests.get(cur), (Integer)(MapDecayed.size()-1));
            }
            else
            {
                MapDecayed.set(hashMapInt.get(requests.get(cur)), new Pair(requests.get(cur), MapDecayed.get(hashMapInt.get(requests.get(cur))).times + 1,unique));
            }			
        }
		
        //Check for pollution 
        //writer.append((countSingles(lfu.minHeap.toArray())/N)*100 + "\n");
        writer4.append((countPollution(arc.toArrayInt(), unique)/N)*100 + "\n");
        //get next N requests and Check for pollution
        while (cur < requests.size()){
            if (i >= N){
                writer4.append((countPollution(arc.toArrayInt(), unique)/N)*100 + "\n");
                i = 0;
            }
            erased = hashMapInt.containsKey(requests.get(cur)) && !arc.contains(requests.get(cur));
            arc.set(requests.get(cur)); 
            if (erased){
                unique++;
                MapDecayed.set(hashMapInt.get(requests.get(cur)), new Pair(requests.get(cur), 1,unique));
            }
            else{
                if (!hashMapInt.containsKey(requests.get(cur))){
                    unique++;
                    MapDecayed.add(new Pair(requests.get(cur), 1, unique));
                    hashMapInt.put(requests.get(cur), (Integer)(MapDecayed.size()-1));
                }
                else
                {
                    MapDecayed.set(hashMapInt.get(requests.get(cur)), new Pair(requests.get(cur), MapDecayed.get(hashMapInt.get(requests.get(cur))).times + 1,unique));
                }
            }	           
            i++;
            cur++;
        }        
        writer4.close();
	
        Thread run = new Thread() {
            @Override
            public void run() {
                ChartBuilder.sources.add("Stats\\"+filename + "LFUPollution.txt"); 
                ChartBuilder.sources.add("Stats\\"+filename + "LRUPollution.txt"); 
                ChartBuilder.sources.add("Stats\\"+filename + "ARCPollution.txt"); 
                ChartBuilder.destination = "Stats\\"+filename + "LFUandLRUandARCPollution.jpg";
                javafx.application.Application.launch(ChartBuilder.class);
            }
        };
        
        run.start();
        
        }catch (Exception ex) {
            Logger.getLogger(PollutionCounter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }	
	 
    /**
     * Counts the pollution at a specific moment of the cache
     * @param array
     * @param unique
     * @return
     */
    public double countPollution(int[] array, int unique){
	double result = 0;
	for (int i = 0; i < array.length; i++){
	    if (hashMapInt.containsKey(array[i]) && MapDecayed.get(hashMapInt.get(array[i])).times > 1 && MapDecayed.get(hashMapInt.get(array[i])).stamp < unique - 2*N)
	        result += 1;
	}      
	return result;
    }
	
    /**
     * Reads from a file
     * @param file
     * @return
     * @throws Exception
     */
    public List<Integer> readLines(File file) throws Exception {
        if (!file.exists()) {
            return new ArrayList<Integer>();
        }
        BufferedReader reader = new BufferedReader(new FileReader(file));
        List<Integer> results = new ArrayList<>();
        String line = reader.readLine();
        while (line != null) {
            results.add(Integer.parseInt(line));
            line = reader.readLine();
        }
        return results;
    }
}
