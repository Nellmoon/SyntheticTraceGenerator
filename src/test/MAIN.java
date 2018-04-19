/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

/**
 *
 * @author Luis
 */
public class MAIN {
    public static void main(String[] args) {
        new Thread() {
            @Override
            public void run() {
                ChartBuilder.destination = "asdasdasd.jpg";
                ChartBuilder.sources.add("File1.txt");
                ChartBuilder.sources.add("File2.txt");
                ChartBuilder.sources.add("File3.txt");
                javafx.application.Application.launch(ChartBuilder.class);
            }
        }.start();
    }
}




