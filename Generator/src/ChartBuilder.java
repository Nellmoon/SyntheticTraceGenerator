import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.image.WritableImage;
import javafx.stage.Stage;
import javax.imageio.ImageIO;

/**
 *
 * @author Luis
 */
public class ChartBuilder extends Application{
           
    public static String source = "File.txt";
    public static String destination = "Line Chart.jpg";
    
    public static String source2 = "File.txt";
    public static String destination2 = "Line Chart.jpg";    
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        saveAsPNG(destination);
        source = source2;
        saveAsPNG(destination2);
        System.exit(0);
    }
    
    private void saveAsPNG(String destinationAddress){
        Scene scene = getChart();        
        if(scene != null){
            WritableImage image = scene.snapshot(null);
            File file = new File(destinationAddress);
            try {
                ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", file);
            } catch (IOException ex) {
                Logger.getLogger(ChartBuilder.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private Scene getChart(){
        BufferedReader br = null;
        Scene scene = null;
        
        //defining the axes
        final NumberAxis xAxis = new NumberAxis(0, 255, 5);
        final NumberAxis yAxis = new NumberAxis(0, 100, 5);
        //creating the chart
        final LineChart<Number,Number> lineChart = new LineChart<>(xAxis,yAxis);   
        
        try {
            br = new BufferedReader(new FileReader(source));
            String currentLine, title;
            
            //read title, legend and check that file has at least the two lines to start
            if(((title = br.readLine()) != null) && ((currentLine = br.readLine()) != null)){
                lineChart.setTitle(title);
                //defining a series
                XYChart.Series series = new XYChart.Series();
                series.setName(currentLine);
                
                //start reading the numbers and loading them to the series
                int i = 1;
                while((currentLine = br.readLine()) != null){
                    series.getData().add(new XYChart.Data(i++, Double.parseDouble(currentLine)));
                }
                
                //hide XAxis
                lineChart.getXAxis().setTickLabelsVisible(false);
                lineChart.getXAxis().setOpacity(0);
                lineChart.setAnimated(false);
                //hide dots
                lineChart.setCreateSymbols(false);
                //increase text size
                lineChart.setStyle("-fx-font-size: 40px;");
                //set data to chart
                lineChart.getData().add(series);
                //set image size
                scene  = new Scene(lineChart,5760,1920);    
                
            }else{
                return null;
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ChartBuilder.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException  ex) {
            Logger.getLogger(ChartBuilder.class.getName()).log(Level.SEVERE, null, ex);
        } 
        
        try {
            br.close();
        } catch (IOException ex) {
            Logger.getLogger(ChartBuilder.class.getName()).log(Level.SEVERE, null, ex);
        }
        return scene;
        
    }
    
    public static void main(String args[]){
        launch(args);
        
    }
    
}
