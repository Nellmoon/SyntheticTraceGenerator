import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.UserDefinedFileAttributeView;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class SyntecticGen {
    static int exp1Hits, exp1Miss, exp2Hits, exp2Miss, exp1, exp2, cacheeSize, reqRange, neededSeq, noise;
    static boolean startExp1;
    static int[] options;
    static Cache exp1Cache, exp2Cache;
    static BufferedWriter writer;
    public static void main(String[] args) throws IOException {        
        Scanner in = new Scanner(System.in);
        
        System.out.println("Name of the file to read the Configuration of the Experiment");
        String filenameProp  = in.nextLine();
        System.out.println("Name of the file to wrtie the traces");
        String filenameOut  = in.nextLine();        
        writer = new BufferedWriter(new FileWriter("..\\Output\\"+filenameOut, true));     
        new ImputGen(filenameProp);
        LoadPropertieFile();
        if (exp1 == exp2){
            System.out.println("Experts can't be the same algorithm");
            return;
        }
        if (exp1 > 3 || exp1 < 1 || exp2 > 3 || exp2 < 1 ){
            System.out.println("You must select a possible option");
            return;
        }      
        options = new int[reqRange];  
        for (int i = 0; i < reqRange; i++){
            options[i] = i+1;
        }
        shuffleArray(options);   
        
        if (exp1 + exp2 == 3)
            LRULFU();
        else{
            if (exp1 + exp2 == 4){
                LRUARC();
            }
            else{
                LFUARC();
            }
        }
        writer.close();        
        insertFileAttr(Paths.get("..\\Output\\"+filenameOut));
    }
    static void shuffleArray(int[] ar)
    {
        Random rnd = ThreadLocalRandom.current();
        for (int i = ar.length - 1; i > 0; i--)
        {
          int index = rnd.nextInt(i + 1);
          int a = ar[index];
          ar[index] = ar[i];
          ar[i] = a;
        }
    }
    
    static int seqInt (int prob, boolean startLRU){
        if (prob < 1)
            return 1;
        return 2;            
    }
    
    static boolean coinFlip(){
        int randomNum = ThreadLocalRandom.current().nextInt(0, 100);
        return randomNum < 21;
    }
    static boolean addNoise(){
        int randomNum = ThreadLocalRandom.current().nextInt(0, 100);
        return randomNum < noise;
    }
    static void LRULFU() throws IOException{
        {
            exp1Cache = new LFUCache(cacheeSize);    
            exp2Cache = new LRUCache(cacheeSize);

            int rep;
            for (int i = 0; i < cacheeSize; i++){            
                int randomNum = ThreadLocalRandom.current().nextInt(0, 100);
                rep = seqInt(randomNum, startExp1);
                for (int j = 0; j < rep; j++){
                    writer.append(options[i] + "\n");
                    exp1Cache.set(options[i]);
                    exp2Cache.set(options[i]);
                }           
            }

            int bothmiss, optLru, optLfu;
            boolean flip = false;
            for (int i = 0; i < neededSeq; i++){
                if (addNoise()){
                    int randomReq = ThreadLocalRandom.current().nextInt(0, reqRange);
                    writer.append(randomReq + "\n");
                    exp2Cache.set(randomReq);
                    exp1Cache.set(randomReq);
                    if (exp1Cache.contains(randomReq))
                        exp1Hits ++;
                    else
                        exp1Miss ++;
                    if (exp2Cache.contains(randomReq))
                        exp2Hits ++;
                    else
                        exp2Miss ++;
                }
                else{
                    bothmiss = -1;
                    optLfu = -1;
                    optLru = -1;
                    if (startExp1){                
                        for (int j = 0; j < options.length; j++){
                            if (exp2Cache.contains(options[j]) && !exp1Cache.contains(options[j]) && optLru == -1 && coinFlip()){
                                optLru = options[j];
                            }
                            if (!exp2Cache.contains(options[j]) && !exp1Cache.contains(options[j]) && (bothmiss == -1 || coinFlip()))
                                bothmiss = options[j];
                        }
                        if (optLru != -1){
                            writer.append(optLru + "\n");
                            exp2Cache.set(optLru);
                            exp1Cache.set(optLru);
                            exp1Hits ++;
                            exp2Miss ++;
                        }
                        else{
                            writer.append(bothmiss + "\n");
                            exp2Cache.set(bothmiss);
                            exp1Cache.set(bothmiss);
                            exp1Miss ++;
                            exp2Miss ++;
                        }
                    }
                    else{
                        for (int j = 0; j < options.length; j++){
                            if (!exp2Cache.contains(options[j]) && exp1Cache.contains(options[j]) && (optLfu == -1 || coinFlip())){
                                optLfu = options[j];
                            }
                            if (!exp2Cache.contains(options[j]) && !exp1Cache.contains(options[j]) && (bothmiss == -1 || coinFlip()))
                                bothmiss = options[j];                    
                        }
                        if (optLfu != -1){
                            writer.append(optLfu + "\n");     
                            exp2Cache.set(optLfu);
                            exp1Cache.set(optLfu);
                            exp2Hits ++;
                            exp1Miss ++;
                        }
                        else{
                            writer.append(bothmiss + "\n"); 
                            exp2Cache.set(bothmiss);
                            exp1Cache.set(bothmiss);
                            exp1Miss ++;
                            exp2Miss ++;
                        }
                    }
                }
                if (!flip && i > neededSeq/2 ){
                    startExp1 = !startExp1;
                    flip = true;
                }
            }
            System.out.println("");
            System.out.println("***************************************************");
            System.out.println("*   LRU hits: "+ exp1Hits +"   LRU Misses: "+ exp1Miss);
            System.out.println("*   LFU hits: "+ exp2Hits +"   LFU Misses: "+ exp2Miss);
            System.out.println("***************************************************");
        }        
    }        
    static void LRUARC(){
           
    }
        
    static void LFUARC(){
            
    }
    
    static void LoadPropertieFile() throws FileNotFoundException, IOException{
        Properties prop = new Properties();
	InputStream input = null;
        input = new FileInputStream("config.properties");

        // load a properties file
        prop.load(input);

        // get the property value and print it out
        exp1 = Integer.parseInt(prop.getProperty("firstExpert"));
        exp2 = Integer.parseInt(prop.getProperty("secondExpert"));
        cacheeSize = Integer.parseInt(prop.getProperty("cacheSize"));
        reqRange = Integer.parseInt(prop.getProperty("maxRequest"));
        startExp1 = prop.getProperty("startFirst").equals("Y");
        noise = Integer.parseInt(prop.getProperty("noiseLevel"));
        neededSeq = Integer.parseInt(prop.getProperty("sequencesNeeded"));
        input.close();
    }
    
    static void insertFileAttr(Path filePath) throws FileNotFoundException, IOException{
        UserDefinedFileAttributeView view = Files.getFileAttributeView(filePath, UserDefinedFileAttributeView.class);
        // The file attribute
        String name = "Configuration";
        String value = "firstExpert: "+ exp1 + "   secondExpert: "+ exp2 +"   cacheSize: "+ cacheeSize +"   maxRequest: "+ reqRange +"   startFirst: "+ startExp1 +"   noiseLevel: "+ noise +"   sequencesNeeded: "+ neededSeq;
        // Write the properties
        byte[] bytes = value.getBytes("UTF-8");
        ByteBuffer writeBuffer = ByteBuffer.allocate(bytes.length);
        writeBuffer.put(bytes);
        writeBuffer.flip();
        view.write(name, writeBuffer);
        ByteBuffer readBuffer = ByteBuffer.allocate(view.size(name));
        view.read(name, readBuffer);
        readBuffer.flip();
        String valueFromAttributes = new String(readBuffer.array(), "UTF-8");
        System.out.println("File Attribute: " + valueFromAttributes);
    }    
}



