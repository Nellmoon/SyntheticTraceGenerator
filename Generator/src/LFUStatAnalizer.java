import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LFUStatAnalizer {
    public static void main(String[] args) throws IOException, Exception {
        Scanner in = new Scanner(System.in);
        String file = in.nextLine();
        int N = 500;
        LFUCache lfu = new LFUCache(N);
        BufferedWriter writer;
        writer = new BufferedWriter(new FileWriter("..\\Stats\\"+file + "LFUSingle.txt", true));                
        writer.append(file + "LFUSingle" + "\n");              
        writer.append("Percent of elements with freequency 1\n");
        
        BufferedWriter writer2;
        writer2 = new BufferedWriter(new FileWriter("..\\Stats\\"+file + "LFUPollution.txt", true));             
        writer2.append(file + "LFUPollution" + "\n");              
        writer2.append("Percent of elements with freequency > 2\n");;
        
        List<Integer> requests = readLines(new File("..\\Output\\"+file + ".txt"));
        //fill cache
        int cur = 0;
        while(requests.get(cur) != -1){
            lfu.set(requests.get(cur));            
            cur++;
        }
        int i = N - 1;
        while (cur < requests.size()){
            if (requests.get(cur) == -1){      
                i++;
                cur++;
                continue;  
            }
            if (i >= N){
                 writer.append((countSingles(lfu.minHeap.toArray())/N)*100 + "\n");
                 writer2.append((countPollution(lfu.minHeap.toArray(), N, cur)/N)*100 + "\n");
                i = 0;
            }
            lfu.set(requests.get(cur));            
            i++;
            cur++;
        }        
        new Thread() {
            @Override
            public void run() {
                ChartBuilder.source = "..\\Stats\\"+file + "LFUSingle.txt"; 
                ChartBuilder.destination = "..\\Stats\\"+file + "LFUSingle.jpg";
                javafx.application.Application.launch(ChartBuilder.class);
            }
        }.start();
        writer.close();
        writer2.close();
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
    
  public static double countSingles(Object[] array){
      double result = 0;
      for (int i = 0; i < array.length; i++){
          if (((Pair)array[i]).times ==1)
              result += 1;
      }      
      return result;
  }
  
  public static double countPollution(Object[] array, int N, int cur){
      double result = 0;
      for (int i = 0; i < array.length; i++){
          if (((Pair)array[i]).times > 1 && ((Pair)array[i]).stamp < cur - N)
              result += 1;
      }      
      return result;
  }

}
