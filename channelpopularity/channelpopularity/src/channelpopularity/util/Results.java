package channelpopularity.util;

import java.io.File;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.InvalidPathException;

public class Results implements FileDisplayInterface, StdoutDisplayInterface {

    private File outputFile;
    private String appendLine;
    private BufferedWriter outputWriter;

    public Results(String outputFile){
        try {
            
            this.outputFile = new File(outputFile);

            if (this.outputFile.createNewFile()) {
                System.out.println("File created: " + this.outputFile.getName());
            }
            else {
                new FileWriter(outputFile, false).close();
            }
        } 
        catch (IOException fileCreationError) {
          fileCreationError.printStackTrace();
        } 
    }

    public void writeLine(String line){
        appendLine = appendLine + line;
    }

    public void persistResult(){
        appendLine = appendLine.replace("null", "");
        writeFile();
        displayOutput();
    }

    @Override
    public void writeFile(){
        try{
            this.outputWriter = new BufferedWriter(new FileWriter(this.outputFile, true));
            outputWriter.write(appendLine);
            outputWriter.close();
        }

        catch(IOException writeRotatedError){
            writeRotatedError.printStackTrace();
        }
        return;
    }

    @Override
    public void displayOutput(){
        System.out.println(appendLine);
        return;
    }
}








    // private File outputFile;
    // private File metricsFile;

    // private BufferedWriter outputWriter;
    // private FileWriter metricsWriter;

    // /**
    // * Constructor for Results class, initializes empty output files
    // * 
    // * @return void
    // *
    // * @exception IOException
    // */
    // public Results(String outputFile, String metricsFile) throws InvalidPathException, SecurityException, IOException {

    //     // this.outputFile = outputFile;
        

    //     // try{
    //     //     // new FileWriter(outputFile, false).close();
    //     //     new FileWriter(metricsFile, false).close();
    //     // }
    //     // catch(IOException resultsFileNotEmpty){
    //     //     resultsFileNotEmpty.printStackTrace();
    //     // } 

    //     try {
            
    //         this.outputFile = new File(outputFile);
    //         this.metricsFile = new File(metricsFile);

    //         if (this.outputFile.createNewFile()) {
    //             System.out.println("File created: " + this.outputFile.getName());
    //         }
    //         else {
    //             new FileWriter(outputFile, false).close();
    //         }

    //         if (this.metricsFile.createNewFile()) {
    //             System.out.println("File created: " + this.metricsFile.getName());
    //         }
    //         else {
    //             new FileWriter(metricsFile, false).close();
    //         }
    //     } 
    //     catch (IOException fileCreationError) {
    //       fileCreationError.printStackTrace();
    //     }


    // }


    // /**
    // * Writes rotated words in the output file
    // * 
    // * @return void
    // *
    // * @exception IOException
    // */
    // public void writeRotated(String rotatedWord) throws IOException{

    //     System.out.println(rotatedWord);

    //     String fullStop = " ";
    //     if (rotatedWord.endsWith(".")){ fullStop = "\n";}

    //     try{
    //         this.outputWriter = new BufferedWriter(new FileWriter(this.outputFile, true));
    //         outputWriter.write(rotatedWord+fullStop);
    //         outputWriter.close();
    //     }

    //     catch(IOException writeRotatedError){
    //         writeRotatedError.printStackTrace();
    //     }

    //     return;
    // }
    

    // /**
    // * Writes stats in the metrics file
    // * 
    // * @return void
    // *
    // * @exception IOException
    // */
    // public void writeMatrix(float wordsPerSentense, float wordLength){

    //     float value = wordsPerSentense + wordLength;

    //     try{
    //         this.metricsWriter = new FileWriter(this.metricsFile);

    //         metricsWriter.write("AVG_NUM_WORDS_PERSENTENCE = " + String.format("%.2f", wordsPerSentense));
    //         metricsWriter.write("\nAVG_WORD_LENGTH = " + String.format("%.2f", wordLength)+"\n");
    //         metricsWriter.close();
    //     }

    //     catch(IOException writeMetricsError){
    //         writeMetricsError.printStackTrace();
    //     }
        
    //     return;
    // }
