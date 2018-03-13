import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class LFUPollutionCounter {
    public static int N = 500;
    public static List<Pair> MapDecayed = new ArrayList<>();
    public static HashMap<Integer, Integer> hashMapInt = new HashMap<>();
    public static void main(String[] args) throws IOException, Exception {
        Scanner in = new Scanner(System.in);
        String file = in.nextLine();
        LFUCache lfu = new LFUCache(N);

        //Elements here are being tracked for their frequency (if they have not been access in more than 4N they decay by one once access)
			
        //BufferedWriter writer;
        //writer = new BufferedWriter(new FileWriter("..\\Stats\\"+file + "LFUSingle.txt", true));                
        //writer.append(file + "LFUSingle" + "\n");              
        //writer.append("Percent of elements with frequency 1\n");
		
	BufferedWriter writer2;
        writer2 = new BufferedWriter(new FileWriter("..\\Stats\\"+file + "LFUPollution.txt", true));             
        writer2.append(file + "LFUPollution" + "\n");              
        writer2.append("Percent of elements with frequency > 2\n");
		
        int i = 0;
        int cur = 0;
        //read the file on this adress and save it as a list
        List<Integer> requests = readLines(new File("..\\Output\\"+file + ".txt"));
        requests.removeAll(Arrays.asList(-1));

        while(lfu.minHeap.size() < N){
            lfu.set(requests.get(cur));            
            cur++;
            if (!hashMapInt.containsKey(requests.get(cur))){
                MapDecayed.add(new Pair(requests.get(cur), 1, cur));
                hashMapInt.put((Integer)(MapDecayed.size()-1), (Integer)(MapDecayed.size()-1));
            }
            else
            {
                MapDecayed.set(hashMapInt.get(requests.get(cur)), new Pair(requests.get(cur), MapDecayed.get(hashMapInt.get(requests.get(cur))).times,cur));
            }			
        }
		
        //Check for pollution 
        //writer.append((countSingles(lfu.minHeap.toArray())/N)*100 + "\n");
        writer2.append((countPollution(lfu.minHeap.toArray(), cur)/N)*100 + "\n");
        //get next N requests and Check for pollution

        while (cur < requests.size()){
            if (i >= N){
                writer2.append((countPollution(lfu.minHeap.toArray(), cur)/N)*100 + "\n");
                i = 0;
            }
            lfu.set(requests.get(cur)); 
            if (!hashMapInt.containsKey(requests.get(cur))){
                MapDecayed.add(new Pair(requests.get(cur), 1, cur));
                hashMapInt.put((Integer)(MapDecayed.size()-1), (Integer)(MapDecayed.size()-1));
            }
            else
            {
                MapDecayed.set(hashMapInt.get(requests.get(cur)), new Pair(requests.get(cur), MapDecayed.get(hashMapInt.get(requests.get(cur))).times,cur));
            }	           
            i++;
            cur++;
        }  
		
	new Thread() {
            @Override
            public void run() {
               //ChartBuilder.source = "..\\Stats\\"+file + "LFUSingle.txt"; 
                //ChartBuilder.destination = "..\\Stats\\"+file + "LFUSingle.jpg";
                ChartBuilder.source = "..\\Stats\\"+file + "LFUPollution.txt"; 
                ChartBuilder.destination = "..\\Stats\\"+file + "LFUPollution.jpg";
                javafx.application.Application.launch(ChartBuilder.class);
            }
        }.start();
        //writer.close();
        writer2.close();
	 }	
	 
	 public static double countPollution(Object[] array, int cur){
	      double result = 0;
	      for (int i = 0; i < array.length; i++){
	          if (hashMapInt.containsKey(((Pair)array[i]).key) && MapDecayed.get(hashMapInt.get(((Pair)array[i]).key)).key > 1 && MapDecayed.get(hashMapInt.get(((Pair)array[i]).key)).stamp >= cur - 2*N)
	              result += 1;
	      }      
	      return result;
  	}
	
	public static List<Integer> readLines(File file) throws Exception {
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
