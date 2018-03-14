
import java.util.Scanner;


public class StatChartCreator {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String file = in.nextLine();
        
        new Thread() {
            @Override
            public void run() {
                ChartBuilder.sources.add("..\\Stats\\"+file + "LFUPollution.txt"); 
                ChartBuilder.sources.add("..\\Stats\\"+file + "LRUPollution.txt"); 
                ChartBuilder.sources.add("..\\Stats\\"+file + "ARCPollution.txt"); 
                ChartBuilder.destination = "..\\Stats\\"+file + "LFUandLRUandARCPollution.jpg";
                javafx.application.Application.launch(ChartBuilder.class);
            }
        }.start();
    }
}
