import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class SyntecticGen {
    static int exp1Hits, exp1Miss, exp2Hits, exp2Miss, exp1, exp2, cacheeSize, reqRange, neededSeq, noise;
    static boolean startExp1;
    static int[] options;
    static Cache exp1Cache, exp2Cache;
    public static void main(String[] args) {        
        Scanner in = new Scanner(System.in);
        
        System.out.println("Select your First Expert: ");
        System.out.println("1 : LRU");
        System.out.println("2 : LFU");
        System.out.println("3 : ARC");
        exp1 = in.nextInt();
        
        System.out.println("Select your Second Expert: ");
        System.out.println("1 : LRU");
        System.out.println("2 : LFU");
        System.out.println("3 : ARC");
        exp2 = in.nextInt();
        
        if (exp1 == exp2){
            System.out.println("Experts can't be the same algorithm");
            return;
        }
        if (exp1 > 3 || exp1 < 1 || exp2 > 3 || exp2 < 1 ){
            System.out.println("You must select a possible option");
            return;
        }   
        System.out.println("Define the capacity (N) of your cache");
        cacheeSize = in.nextInt();
        
        System.out.println("Define the key of your max request (size of the data)");
        reqRange = in.nextInt();
        
        System.out.println("Define the ammount of the request sequences");
        neededSeq = in.nextInt();
        
        System.out.println("Will Expert 1 start first? Y/N");
        startExp1 = in.next().equals("Y");
        
        System.out.println("Define the level of noise");
        noise = in.nextInt();
        
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
    static void LRULFU(){
        {
            exp1Cache = new LFUCache(cacheeSize);    
            exp2Cache = new LRUCache(cacheeSize);

            int rep;
            for (int i = 0; i < cacheeSize; i++){            
                int randomNum = ThreadLocalRandom.current().nextInt(0, 100);
                rep = seqInt(randomNum, startExp1);
                for (int j = 0; j < rep; j++){
                    System.out.println(options[i]);
                    exp1Cache.set(options[i]);
                    exp2Cache.set(options[i]);
                }           
            }

            int bothmiss, optLru, optLfu;
            boolean flip = false;
            for (int i = 0; i < neededSeq; i++){
                if (addNoise()){
                    int randomReq = ThreadLocalRandom.current().nextInt(0, reqRange);
                    System.out.println(randomReq);
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
                            System.out.println(optLru);
                            exp2Cache.set(optLru);
                            exp1Cache.set(optLru);
                            exp1Hits ++;
                            exp2Miss ++;
                        }
                        else{
                            System.out.println(bothmiss);
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
                            System.out.println(optLfu);
                            exp2Cache.set(optLfu);
                            exp1Cache.set(optLfu);
                            exp2Hits ++;
                            exp1Miss ++;
                        }
                        else{
                            System.out.println(bothmiss);
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
}



