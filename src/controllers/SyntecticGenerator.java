package controllers;

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
import java.nio.file.attribute.UserDefinedFileAttributeView;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Wendy Aleman Martinez
 * Class to create Synthetic traces 
 */
public class SyntecticGenerator implements Runnable {
    int exp1Hits, exp1Miss, exp2Hits, exp2Miss, exp1, exp2, cacheSize, reqRange, neededSeq, noise, numChanges;
    String Description;
    boolean start_Exp1, phaseState;
    int[] options;
    Cache exp2_Cache, exp1_Cache;
    BufferedWriter writer;
    String filenameProp = "";
    public String result = "";
    public int hits1 = 0;
    public int hits2 =  0;
    public int miss1 = 0;
    public int miss2 = 0;
    
    public SyntecticGenerator(String filename){    
        filenameProp  = filename;
    }
                
    @Override
    public void run() {
        try {     
            writer = new BufferedWriter(new FileWriter("Output\\"+filenameProp + "Result.txt", true));

            InputGen generator = new InputGen(filenameProp);
            LoadPropertieFile();
            
            options = new int[reqRange];  
            for (int i = 0; i < reqRange; i++){
                options[i] = i+1;
            }
            shuffleArray(options);   

            if (exp1 == 1 && exp2 == 2){            
                result = LRULFU();
            }
            if (exp1 == 1 && exp2 == 3){            
                result = LRUARC();
            }
            if (exp1 == 2 && exp2 == 1){        
                start_Exp1 = !start_Exp1;
                result = LRULFU();
            }        
            if (exp1 == 2 && exp2 == 3){   
                result = LFUARC();
            }
            if (exp1 == 3 && exp2 == 1){        
                start_Exp1 = !start_Exp1;
                result = LRUARC();
            }  
            if (exp1 == 3 && exp2 == 2){        
                start_Exp1 = !start_Exp1;
                result = LFUARC();
            } 

            writer.close();        
            insertFileAttr(Paths.get("Output\\"+filenameProp+ "Result.txt"));
        } catch (IOException ex) {
            Logger.getLogger(SyntecticGenerator.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Shuffle the contents of the array ar
     * @param ar
     */
    void shuffleArray(int[] ar){
        Random rnd = ThreadLocalRandom.current();
        for (int i = ar.length - 1; i > 0; i--)
        {
          int index = rnd.nextInt(i + 1);
          int a = ar[index];
          ar[index] = ar[i];
          ar[i] = a;
        }
    }
    
    /**
     * Returns the number of times an integer will be repeated based on a 
     * probability prob from 0 to 99 
     * @param prob
     */
    int seqInt (int prob){
        if (prob < 21)
            return 1;
        if (prob < 50)
            return 2;
        return 3;            
    }
    
    /**
     * @returns
     * 1 if random number is less than needed, 0 otherwise.... set to be 1 
     */
    boolean coinFlip(){
        int randomNum = ThreadLocalRandom.current().nextInt(0, 100);
        return randomNum < 100;
    }
    
    /**
     * @returns
     * 1 if random number is less than noise, 0 otherwise.... set to be 1 
     */
    boolean addNoise(){
        int randomNum = ThreadLocalRandom.current().nextInt(0, 100);
        return randomNum < noise;
    }
    
    /**
     * Pages printed will behave either LRU or LFU favorable
     * Leaves ARC out of the scope
     */
    String LRULFU() throws IOException{
            String ans = "";
            exp1_Cache = new LRUCache(cacheSize);
            exp2_Cache = new LFUCache(cacheSize);
            phaseState = start_Exp1;   

            int rep;
            for (int i = 0; i < cacheSize; i++){            
                int randomNum = ThreadLocalRandom.current().nextInt(0, 100);
                rep = seqInt(randomNum);
                for (int j = 0; j < rep; j++){
                    writer.append(options[i] + "\n");
                    exp2_Cache.set(options[i]);
                    exp1_Cache.set(options[i]);
                }           
            }
            
            writer.append(-1 + "\n"); 
            int bothmiss, optLru, optLfu;
            for (int i = 0; i < neededSeq; i++){
                if (addNoise()){
                    int randomReq = ThreadLocalRandom.current().nextInt(0, reqRange);
                    writer.append(randomReq + "\n");                    
                    if (exp2_Cache.contains(randomReq))
                        exp2Hits ++;
                    else
                        exp2Miss ++;
                    if (exp1_Cache.contains(randomReq))
                        exp1Hits ++;
                    else
                        exp1Miss ++;
                    exp1_Cache.set(randomReq);
                    exp2_Cache.set(randomReq);
                }
                else{
                    if (phaseState){  
                        bothmiss = -1;
                        optLru = -1;              
                        for (int j = 0; j < options.length; j++){
                            if (exp1_Cache.contains(options[j]) && !exp2_Cache.contains(options[j]) && optLru == -1 && coinFlip()){
                                optLru = options[j];
                            }
                            if (!exp1_Cache.contains(options[j]) && !exp2_Cache.contains(options[j]) && (bothmiss == -1 || coinFlip()))
                                bothmiss = options[j];
                        }
                        if (optLru != -1){
                            writer.append(optLru + "\n");
                            exp1_Cache.set(optLru);
                            exp2_Cache.set(optLru);
                            exp1Hits ++;
                            exp2Miss ++;
                        }
                        else{
                            writer.append(bothmiss + "\n");
                            exp1_Cache.set(bothmiss);
                            exp2_Cache.set(bothmiss);
                            exp1Miss ++;
                            exp2Miss ++;
                        }
                    }
                    else{  
                        bothmiss = -1;
                        optLfu = -1;
                        for (int j = 0; j < options.length; j++){
                            if (!exp1_Cache.contains(options[j]) && exp2_Cache.contains(options[j]) && (optLfu == -1 || coinFlip())){
                                optLfu = options[j];
                            }
                            if (!exp1_Cache.contains(options[j]) && !exp2_Cache.contains(options[j]) && (bothmiss == -1 || coinFlip()))
                                bothmiss = options[j];                    
                        }
                        if (optLfu != -1){
                            writer.append(optLfu + "\n");     
                            exp1_Cache.set(optLfu);
                            exp2_Cache.set(optLfu);
                            exp2Hits ++;
                            exp1Miss ++;
                        }
                        else{
                            writer.append(bothmiss + "\n"); 
                            exp1_Cache.set(bothmiss);
                            exp2_Cache.set(bothmiss);
                            exp1Miss ++;
                            exp2Miss ++;
                        }
                    }
                }
                
                if ((i/(neededSeq/numChanges)%2 == 0) != (phaseState == start_Exp1)){
                    phaseState = (i/(neededSeq/numChanges)%2 == 0) ? start_Exp1 : !start_Exp1;
                    writer.append(-1 + "\n");                     
                }
            }
        writer.append(-1 + "\n"); 
        ans = "The Experiment Concluded Successfully";
        hits1 = exp1Hits;
        miss1 = exp1Miss;
        hits2 = exp2Hits;
        miss2 = exp2Miss;   
        return ans;
    }    
    
    /**
     * Pages printed will behave either LRU or ARC favorable
     * Leaves LFU out of the scope
     */
    String LRUARC()throws IOException { 
        String ans = "";
        exp1_Cache = new LRUCache(cacheSize);
        exp2_Cache = new ARCCache(cacheSize);   
        phaseState = start_Exp1;   

        int rep;
        for (int i = 0; i < cacheSize; i++){            
            int randomNum = ThreadLocalRandom.current().nextInt(0, 100);
            rep = seqInt(randomNum);
            for (int j = 0; j < rep; j++){
                writer.append(options[i] + "\n");
                exp1_Cache.set(options[i]);
                exp2_Cache.set(options[i]);
            }           
        }

        writer.append(-1 + "\n");         
        int bothmiss, bothmissPref_b1, bothmissPref_b2, optArc_t1, optArc_t2, optLru, hitHistory_b1, hitHistory_b2, bothhit;
        for (int i = 0; i < neededSeq; i++){
            if (addNoise()){
                int randomReq = ThreadLocalRandom.current().nextInt(0, reqRange);
                writer.append(randomReq + "\n");
                if (exp2_Cache.contains(randomReq))
                    exp2Hits ++;
                else
                    exp2Miss ++;
                if (exp1_Cache.contains(randomReq))
                    exp1Hits ++;
                else
                    exp1Miss ++;
                exp1_Cache.set(randomReq);
                exp2_Cache.set(randomReq);
            }
            else{       
                hitHistory_b1 = -1;
                bothmissPref_b1 = -1;
                bothmissPref_b2 = -1;
                hitHistory_b2 = -1;
                optArc_t1 = -1;
                optLru = -1;
                bothhit = -1;
                if (phaseState){             
                    for (int j = 0; j < options.length; j++){
                        if (exp1_Cache.contains(options[j])){
                            if (!exp2_Cache.contains(options[j])){                                
                                if (exp2_Cache.containsHistory(options[j]) == 2 && (hitHistory_b2 == -1)){
                                    hitHistory_b2 = options[j];
                                    break;                                    
                                }
                                else{
                                    if (hitHistory_b1 == -1 && exp2_Cache.containsHistory(options[j]) == 1)
                                        hitHistory_b1 = options[j];
                                    else {
                                        if (hitHistory_b1 == -1 && optLru == -1 && exp2_Cache.containsHistory(options[j]) == 0)
                                            optLru = options[j];
                                    }
                                }
                            }
                        }
                        else{
                            if (hitHistory_b1 == -1 && optLru == -1 && bothhit == -1 && exp2_Cache.containsArc(options[j]) == 0){
                                if (bothmissPref_b1 == -1 && exp2_Cache.containsHistory(options[j]) == 1)
                                    bothmissPref_b1 = options[j];  
                                else{
                                    if (bothmissPref_b1 == -1 && bothmissPref_b2 == -1 && exp2_Cache.containsHistory(options[j]) == 2)
                                        bothmissPref_b2 = options[j];            
                                }
                            }
                            else{
                                if (hitHistory_b1 == -1 && optLru == -1 && bothhit == -1 && bothmissPref_b1 == -1 && bothmissPref_b2 == -1 && optArc_t1 == -1 && exp2_Cache.containsArc(options[j]) == 1)
                                    optArc_t1 = options[j];
                            }
                        }                    
                    }
                    if (hitHistory_b2 != -1){
                            writer.append(hitHistory_b2 + "\n");
                            exp1_Cache.set(hitHistory_b2);
                            exp2_Cache.set(hitHistory_b2);
                            exp1Hits ++;
                            exp2Miss ++;            
                    }
                    else{
                        if (hitHistory_b1 != -1){
                            writer.append(hitHistory_b1 + "\n");
                            exp1_Cache.set(hitHistory_b1);
                            exp2_Cache.set(hitHistory_b1);
                            exp1Hits ++;
                            exp2Miss ++;            
                        }
                        else{
                            if (optLru != -1){
                                writer.append(optLru + "\n");
                                exp1_Cache.set(optLru);
                                exp2_Cache.set(optLru);
                                exp1Hits ++;
                                exp2Miss ++; 
                            }else {
                                if (bothmissPref_b1 != -1){
                                    writer.append(bothmissPref_b1 + "\n");
                                    exp1_Cache.set(bothmissPref_b1);
                                    exp2_Cache.set(bothmissPref_b1);
                                    exp1Miss ++;
                                    exp2Miss ++; 
                                } else{
                                    if (bothmissPref_b2 != -1){
                                        writer.append(bothmissPref_b2 + "\n");
                                        exp1_Cache.set(bothmissPref_b2);
                                        exp2_Cache.set(bothmissPref_b2);
                                        exp1Miss ++;
                                        exp2Miss ++;  
                                    }
                                    else{
                                         writer.append(optArc_t1 + "\n");
                                        exp1_Cache.set(optArc_t1);
                                        exp2_Cache.set(optArc_t1);
                                        exp1Miss ++;
                                        exp2Hits ++; 
                                    }                                        
                                }
                            }
                        }                    
                    }
                }
                else{
                    hitHistory_b1 = -1;
                    hitHistory_b2 = -1;
                    bothmiss = -1;
                    optArc_t1 = -1;
                    optArc_t2 = -1;
                    for (int j = 0; j < options.length; j++){
                        if (!exp1_Cache.contains(options[j])){
                            if (exp2_Cache.containsArc(options[j]) == 2){                                
                                optArc_t2 = options[j];
                                break;
                            }
                            else{
                                if (optArc_t1 == -1  &&  exp2_Cache.containsArc(options[j]) == 1)
                                    optArc_t1 = options[j];
                                else{
                                    if (optArc_t1 == -1  && hitHistory_b2 == -1 && exp2_Cache.containsHistory(options[j]) == 2)
                                        hitHistory_b2 = options[j];
                                    else{
                                        if (optArc_t1 == -1  && hitHistory_b2 == -1 && hitHistory_b1 == -1 && exp2_Cache.containsHistory(options[j]) == 1 )
                                            hitHistory_b1 = options[j];
                                        else{
                                            if (optArc_t1 == -1  && hitHistory_b2 == -1 && hitHistory_b1 == -1 && bothmiss == -1 && !exp2_Cache.contains(options[j]))
                                                bothmiss = options[j]; 
                                        }
                                    }
                                }
                            }
                        }
                    }
                    if (optArc_t2 != -1){
                        writer.append(optArc_t2 + "\n");     
                        exp1_Cache.set(optArc_t2);
                        exp2_Cache.set(optArc_t2);
                        exp2Hits ++;
                        exp1Miss ++;            
                    }                    
                    else{
                        if (optArc_t1 != -1){
                            writer.append(optArc_t1 + "\n");     
                            exp1_Cache.set(optArc_t1);
                            exp2_Cache.set(optArc_t1);
                            exp2Hits ++;
                            exp1Miss ++;            
                        }
                        else{
                            if (hitHistory_b2 != -1){
                                writer.append(hitHistory_b2 + "\n");
                                exp1_Cache.set(hitHistory_b2);
                                exp2_Cache.set(hitHistory_b2);
                                exp1Miss ++;
                                exp2Miss ++;            
                            }
                            else{
                                if(hitHistory_b1 != -1){
                                    writer.append(hitHistory_b1 + "\n");
                                    exp1_Cache.set(hitHistory_b1);
                                    exp2_Cache.set(hitHistory_b1);
                                    exp1Miss ++;
                                    exp2Miss ++;            
                                }
                                else{
                                    writer.append(bothmiss + "\n"); 
                                    exp1_Cache.set(bothmiss);
                                    exp2_Cache.set(bothmiss);
                                    exp1Miss ++;
                                    exp2Miss ++;            
                                }
                            }
                        }
                    }
                }
                if ((i/(neededSeq/numChanges)%2 == 0) != (phaseState == start_Exp1)){
                    phaseState = (i/(neededSeq/numChanges)%2 == 0) ? start_Exp1 : !start_Exp1;
                    writer.append(-1 + "\n");
                }
            }
        }        
        writer.append(-1 + "\n");   
        ans = "The Experiment Concluded Successfully";
        hits1 = exp1Hits;
        miss1 = exp1Miss;
        hits2 = exp2Hits;
        miss2 = exp2Miss;   
        return ans;
    }   
        
    /**
     * Pages printed will behave either LFU or ARC favorable
     * Leaves LRU out of the scope
     */
    String LFUARC() throws IOException { 
        String ans;
        exp1_Cache = new LFUCache(cacheSize);
        exp2_Cache = new ARCCache(cacheSize);   
        phaseState = start_Exp1;   

        int rep;
        for (int i = 0; i < cacheSize; i++){            
            int randomNum = ThreadLocalRandom.current().nextInt(0, 100);
            rep = seqInt(randomNum);
            for (int j = 0; j < rep; j++){
                writer.append(options[i] + "\n");
                exp1_Cache.set(options[i]);
                exp2_Cache.set(options[i]);
            }           
        }

        writer.append(-1 + "\n"); 
        int bothmiss, optArc_t1, optArc_t2, optLfu, hitHistory_b1, hitHistory_b2;
        for (int i = 0; i < neededSeq; i++){
            if (addNoise()){
                int randomReq = ThreadLocalRandom.current().nextInt(0, reqRange);
                writer.append(randomReq + "\n");
                if (exp2_Cache.contains(randomReq))
                    exp2Hits ++;
                else
                    exp2Miss ++;
                if (exp1_Cache.contains(randomReq))
                    exp1Hits ++;
                else
                    exp1Miss ++;
                exp1_Cache.set(randomReq);
                exp2_Cache.set(randomReq);
            }
            else{
                if (phaseState){                      
                    hitHistory_b1 = -1;
                    hitHistory_b2 = -1;
                    bothmiss = -1;
                    optLfu = -1;
                    for (int j = 0; j < options.length; j++){
                        if (exp1_Cache.contains(options[j])){
                            if (!exp2_Cache.contains(options[j])){                                
                                if (optLfu == -1 && exp2_Cache.containsHistory(options[j]) == 0){
                                    optLfu = options[j];
                                    break;                                    
                                }
                                else{
                                    if (hitHistory_b1 == -1 && exp2_Cache.containsHistory(options[j]) == 1)
                                        hitHistory_b1 = options[j];
                                    else {
                                        if (hitHistory_b2 == -1 && hitHistory_b1 == -1 && exp2_Cache.containsHistory(options[j]) == 2)
                                            hitHistory_b2 = options[j];
                                    }
                                }
                            }
                        }
                        else{
                            if (bothmiss == -1 && exp2_Cache.containsHistory(options[j]) == 0 && !exp2_Cache.contains(options[j])){
                                bothmiss = options[j];
                            }
                        }                    
                    }
                    if (optLfu != -1){
                            writer.append(optLfu + "\n");
                            exp1_Cache.set(optLfu);
                            exp2_Cache.set(optLfu);
                            exp1Hits ++;
                            exp2Miss ++;            
                    }
                    else{
                        if (hitHistory_b1 != -1){
                            writer.append(hitHistory_b1 + "\n");
                            exp1_Cache.set(hitHistory_b1);
                            exp2_Cache.set(hitHistory_b1);
                            exp1Hits ++;
                            exp2Miss ++;            
                        }
                        else{
                            if (hitHistory_b2 != -1){
                                writer.append(hitHistory_b2 + "\n");
                                exp1_Cache.set(hitHistory_b2);
                                exp2_Cache.set(hitHistory_b2);
                                exp1Hits ++;
                                exp2Miss ++;            
                            }
                            else{
                                    writer.append(bothmiss + "\n");
                                    exp1_Cache.set(bothmiss);
                                    exp2_Cache.set(bothmiss);
                                    exp1Miss ++;
                                    exp2Miss ++;
                            }
                        }                    
                    }
                }
                else{                  
                    bothmiss = -1;
                    optArc_t1 = -1;
                    optArc_t2 = -1;
                    for (int j = 0; j < options.length; j++){
                        if (!exp1_Cache.contains(options[j])){
                            if (exp2_Cache.containsArc(options[j]) == 1){                                
                                optArc_t1 = options[j];
                                break;
                            }
                            else{
                                if (optArc_t2 == -1  &&  exp2_Cache.containsArc(options[j]) == 2)
                                    optArc_t2 = options[j];
                                else{
                                    if (bothmiss == -1 && optArc_t2 == -1 && exp2_Cache.containsHistory(options[j]) == 0 && exp2_Cache.containsArc(options[j]) == 0)
                                        bothmiss = options[j]; 
                                }
                            }
                        }
                    }
                    if (optArc_t1 != -1){
                        writer.append(optArc_t1 + "\n");     
                        exp1_Cache.set(optArc_t1);
                        exp2_Cache.set(optArc_t1);
                        exp2Hits ++;
                        exp1Miss ++;            
                    }                    
                    else{
                        if (optArc_t2 != -1){
                            writer.append(optArc_t2 + "\n");     
                            exp1_Cache.set(optArc_t2);
                            exp2_Cache.set(optArc_t2);
                            exp2Hits ++;
                            exp1Miss ++;            
                        }
                        else{
                            writer.append(bothmiss + "\n"); 
                            exp1_Cache.set(bothmiss);
                            exp2_Cache.set(bothmiss);
                            exp1Miss ++;
                            exp2Miss ++;        
                        }
                    }
                }
            }
            
            if ((i/(neededSeq/numChanges)%2 == 0) != (phaseState == start_Exp1)){
                phaseState = (i/(neededSeq/numChanges)%2 == 0) ? start_Exp1 : !start_Exp1;
                writer.append(-1 + "\n"); 
            }
        }
        
        writer.append(-1 + "\n"); 
        ans = "The Experiment Concluded Successfully";
        hits1 = exp1Hits;
        miss1 = exp1Miss;
        hits2 = exp2Hits;
        miss2 = exp2Miss;   
        return ans;
    } 
    
    /**
     * Reads the experiment config file 
     */
    void LoadPropertieFile() throws FileNotFoundException, IOException{
        Properties prop = new Properties();
	InputStream input = null;
        input = new FileInputStream("config.properties");
        
        // load a properties file
        prop.load(input);

        // get the property value and print it out
        Description = prop.getProperty("Description");
        exp1 = Integer.parseInt(prop.getProperty("firstExpert"));
        exp2 = Integer.parseInt(prop.getProperty("secondExpert"));
        cacheSize = Integer.parseInt(prop.getProperty("cacheSize"));
        numChanges = Integer.parseInt(prop.getProperty("numChanges"));
        reqRange = Integer.parseInt(prop.getProperty("maxRequest"));
        start_Exp1 = prop.getProperty("startFirst").equals("Y");
        noise = Integer.parseInt(prop.getProperty("noiseLevel"));
        neededSeq = Integer.parseInt(prop.getProperty("sequencesNeeded"));
        input.close();
    }
    
    /**
     * Inserts important variable and comments into the file metadata
     * so we can identify the setup without having extra files. 
     */
    void insertFileAttr(Path filePath) throws FileNotFoundException, IOException{
        UserDefinedFileAttributeView view = Files.getFileAttributeView(filePath, UserDefinedFileAttributeView.class);
        // The file attribute
        String name = "Configuration";
        String value = "Description: "+Description+" (firstExpert: "+ exp1 + "   secondExpert: "+ exp2 +"   cacheSize: "+ cacheSize +"   numChanges: "+ numChanges+"   maxRequest: "+ reqRange +"   startFirst: "+ start_Exp1 +"   noiseLevel: "+ noise +"   sequencesNeeded: "+ neededSeq + ")";
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



