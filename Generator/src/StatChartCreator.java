import java.util.Scanner;

/**
 * Creates  the files for future use containing the pollution counter of the pages and
 * the Image of the graph with LFU, LRU, ARC, and LeCaR results. 
 * @author Wendy Aleman Martinez
 */
public class StatChartCreator {

    /**
     * @param args
     */
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String file = in.nextLine();
        
        new Thread() {
            @Override
            public void run() {
                ChartBuilder.sources.add("..\\Stats\\"+file + "LFUPollution.txt"); 
                ChartBuilder.sources.add("..\\Stats\\"+file + "LRUPollution.txt"); 
                ChartBuilder.sources.add("..\\Stats\\"+file + "ARCPollution.txt"); 
                ChartBuilder.sources.add("..\\Stats\\"+file + "LaCaRPollution.txt"); 
                ChartBuilder.destination = "..\\Stats\\"+file + "LFUandLRUandARCandLaCaRPollution.jpg";
                javafx.application.Application.launch(ChartBuilder.class);
            }
        }.start();
    }
}
