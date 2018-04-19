/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.io.File;
import java.io.IOException;
import java.util.Random;
import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.image.WritableImage;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
 
 
public class LineChartBuilder extends Application {
 
    @Override 
    public void start(Stage stage) {
        stage.setTitle("Line Chart");
        //defining the axes
        final NumberAxis xAxis = new NumberAxis();
        final NumberAxis yAxis = new NumberAxis();
        //creating the chart
        final LineChart<Number,Number> lineChart = new LineChart<>(xAxis,yAxis);
        
        
        lineChart.setTitle("Stock Monitoring, 2010");
        //defining a series
        XYChart.Series series = new XYChart.Series();
        series.setName("My portfolio");
        
        for(int i = 0; i < 500; i++){
            Random r = new Random();
            series.getData().add(new XYChart.Data(i, r.nextInt(100)));
        }
        
        lineChart.getXAxis().setTickLabelsVisible(false);
        lineChart.getXAxis().setOpacity(0);
        lineChart.setAnimated(false);
        lineChart.getData().add(series);
        Scene scene  = new Scene(lineChart,3840,1920);
        stage.setScene(scene);
        saveAsPng(scene, "javaFX.png");
        stage.show();
    }
    
    public void saveAsPng(Scene scene, String path) {
            WritableImage image = scene.snapshot(null);
            File file = new File(path);
            try {
                ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
 
    public static void main(String[] args) {
        launch(args);
    }
}
