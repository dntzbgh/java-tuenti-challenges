package edition2011.challenge1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ChallengeOne {
    private static final int EDITION_YEAR=2011;
    private static final int CHALLENGE_NUMBER=1;
    private static final String SAMPLE_INPUT="src/edition"+EDITION_YEAR+"/challenge"+CHALLENGE_NUMBER+"/raw/inputs/sample_input.txt";
    private static final String SAMPLE_OUTPUT="src/edition"+EDITION_YEAR+"/challenge"+CHALLENGE_NUMBER+"/raw/outputs/sample_output.txt";
    private static final String TEST_INPUT="src/edition"+EDITION_YEAR+"/challenge"+CHALLENGE_NUMBER+"/raw/inputs/test_input.txt";
    private static final String TEST_OUTPUT="src/edition"+EDITION_YEAR+"/challenge"+CHALLENGE_NUMBER+"/raw/outputs/test_output.txt";

    private static final String INPUT=TEST_INPUT;
    private static final String OUTPUT="src/edition"+EDITION_YEAR+"/challenge"+CHALLENGE_NUMBER+"/raw/outputs/output.txt";

    public static void main(String[] args) {
        Future<BigInteger> auxFutureObj;
        int number_cores = Runtime.getRuntime().availableProcessors();
        ExecutorService executorService= Executors.newFixedThreadPool(number_cores);
        ArrayList<Future<BigInteger>> futureObjArray=new ArrayList<>();

        BufferedReader bufferedReader=null;
        FileReader fileReader=null;

        try{
            fileReader=new FileReader(INPUT);
            bufferedReader=new BufferedReader(fileReader);
            String currentLine;

            while ((currentLine = bufferedReader.readLine()) != null) {
                String[] separatedItems=currentLine.replaceAll("(^\\s+|\\s+$)", "").split("\\s+");
                BigInteger[] longNumbers=new BigInteger[separatedItems.length];
                for (int i=0;i<separatedItems.length;i++){
                    longNumbers[i]=new BigInteger(separatedItems[i]);

                }
                auxFutureObj=executorService.submit(new TaskChallengeOne(longNumbers));
                futureObjArray.add(auxFutureObj);
            }


        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {

                if (bufferedReader != null)
                    bufferedReader.close();

                if (fileReader != null)
                    fileReader.close();

            } catch (IOException ex) {

                ex.printStackTrace();

            }
        }
        executorService.shutdown();
        for(int i=0;i<futureObjArray.size();i++){
            try {
                auxFutureObj=futureObjArray.get(i);
                BigInteger result=auxFutureObj.get();
                System.out.println(result);

            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
    }
}