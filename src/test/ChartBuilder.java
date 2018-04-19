/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import com.sun.javafx.charts.Legend;
import java.awt.Color;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.image.WritableImage;
import javafx.stage.Stage;
import javax.imageio.ImageIO;


public class ChartBuilder extends Application{
           
    public static String destination = "Line Chart.jpg";    
    public static ArrayList<String> sources = new ArrayList<>();

    @Override
    public void start(Stage primaryStage) throws Exception {
        saveAsPNG(destination);
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
        final NumberAxis xAxis = new NumberAxis(0, 305, 5);
        final NumberAxis yAxis = new NumberAxis(0, 100, 5);
        //creating the chart
        final LineChart<Number,Number> lineChart = new LineChart<>(xAxis,yAxis);   
        
        try {
            br = new BufferedReader(new FileReader(sources.get(0)));
            String currentLine, title;
            //read title, legend and check that file has at least the two lines to start
            if(((title = br.readLine()) != null)){
                lineChart.setTitle(title);
                //defining a series
                XYChart.Series series = new XYChart.Series() ;                
                //start reading the numbers and loading them to the series
                int index = 0;                              
                while(index < sources.size()){
                    int j = 1;
                    currentLine = br.readLine();
                    series.setName(currentLine);
                    while((currentLine = br.readLine()) != null){
                        series.getData().add(new XYChart.Data(j++, Double.parseDouble(currentLine)));
                    }
                    
                    lineChart.getData().add(series);
                    br.close();
                    if(++index < sources.size()){
                        br = new BufferedReader(new FileReader(sources.get(index)));          
                    }
                    
                    Node line = series.getNode().lookup(".chart-series-line");
                    Color color;
                    switch(index-1){
                        case 0: color = Color.RED; 
                                break;
                        case 1: color = Color.BLUE; 
                                break;
                        case 2: color = Color.GREEN; 
                                break;
                        default:
                                color = Color.BLACK;                         
                    }

                    String rgb = String.format("%d, %d, %d",
                                (int) (color.getRed()),
                                (int) (color.getGreen()),
                                (int) (color.getBlue()));

                    line.setStyle("-fx-stroke: rgba(" + rgb + ", 1.0);");                        
                    series = new XYChart.Series();
                }
                
                int j = 0;
                for(Node n : lineChart.getChildrenUnmodifiable()){
                    if(n instanceof Legend){
                        for(Legend.LegendItem legendItem : ((Legend)n).getItems()){
                            String color;
                            switch(j){
                                case 0: color = "#ff0000"; 
                                        break;
                                case 1: color = "#0000ff"; 
                                        break;
                                case 2: color = "#00ff00"; 
                                        break;
                                default:
                                        color = "#000000";                        
                            }
                            j++;
                            legendItem.getSymbol().setStyle("-fx-background-color: "+color+", white;");
                        }
                        break;
                    }
                }
            
                //hide XAxis
                lineChart.getXAxis().setTickLabelsVisible(false);
                lineChart.getXAxis().setOpacity(0);
                lineChart.setAnimated(false);
                //hide dots
                lineChart.setCreateSymbols(false);
                //increase text size
                lineChart.setStyle("-fx-font-size: 100px;");
                //set image size
                scene = new Scene(lineChart,5760,1920);    
                  
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