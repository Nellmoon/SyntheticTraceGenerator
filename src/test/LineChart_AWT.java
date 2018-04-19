/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

public class LineChart_AWT extends ApplicationFrame {

    private JFreeChart lineChart ;
    
    public LineChart_AWT( String applicationTitle , String chartTitle ) {
      super(applicationTitle);
      lineChart = ChartFactory.createLineChart(
         chartTitle,
         "Years","Number of Schools",
         createDataset(),
         PlotOrientation.VERTICAL,
         true,true,false);
         
      ChartPanel chartPanel = new ChartPanel( lineChart );
      chartPanel.setPreferredSize( new java.awt.Dimension( 560 , 367 ) );
      chartPanel.setBackground(Color.red);
      setContentPane( chartPanel );
   }

   private DefaultCategoryDataset createDataset( ) {
      DefaultCategoryDataset dataset = new DefaultCategoryDataset( );
      dataset.addValue( 15 , "schools" , "." );
      dataset.addValue( 30 , "schools" , "." );
      dataset.addValue( 60 , "schools" ,  "." );
      dataset.addValue( 120 , "schools" , "." );
      dataset.addValue( 240 , "schools" , "." );
      dataset.addValue( 300 , "schools" , "." );
      return dataset;
   }
   
   public void exportAsJPG(){
        //SAVE CHART TO JPG FILE      
        int width = 640;    /* Width of the image */
        int height = 480;   /* Height of the image */ 
        File lineChartFile = new File( "LineChart.jpeg" ); 
        try {
            ChartUtilities.saveChartAsJPEG(lineChartFile ,lineChart, width ,height);
        } catch (IOException ex) {
            Logger.getLogger(LineChart_AWT.class.getName()).log(Level.SEVERE, null, ex);
        }
   }
   
   public static void main( String[ ] args ) {
      LineChart_AWT chart = new LineChart_AWT(
         "School Vs Years" ,
         "Numer of Schools vs Years");

      chart.pack( );
      RefineryUtilities.centerFrameOnScreen( chart );
      chart.setVisible( true );
   }
   
   
}