import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;

public class ImputGen {
    public ImputGen(String filename) throws FileNotFoundException, IOException{
        Properties prop = new Properties();
        OutputStream output = null;
        BufferedReader reader = new BufferedReader(new FileReader("..\\Imput\\"+filename));
        String line = "";
        output = new FileOutputStream("config.properties");
        try
        {
            //FIRST_EXPERT:
            line = reader.readLine();
             prop.setProperty("firstExpert", line.substring(13));
            //SECOND_EXPERT:
            line = reader.readLine();
             prop.setProperty("secondExpert", line.substring(14));            
            //CACHE_SIZE:
            line = reader.readLine();
             prop.setProperty("cacheSize", line.substring(11));            
            //MAX_REQUEST_SIZE:
            line = reader.readLine();
             prop.setProperty("maxRequest", line.substring(17));            
            //FIRST_EXPERT_STARTS:
            line = reader.readLine();
             prop.setProperty("startFirst", line.substring(20));            
            //LEVEL_NOISE:
            line = reader.readLine();
             prop.setProperty("noiseLevel", line.substring(12));
            //NEEDED_SEQ:
            line = reader.readLine();
             prop.setProperty("sequencesNeeded", line.substring(11));
            prop.store(output, null);
            output.close();
        }
        catch(Exception e)
        {
          System.err.format("Exception occurred trying to read '%s'.", filename);
          e.printStackTrace();
        }
    }
}
