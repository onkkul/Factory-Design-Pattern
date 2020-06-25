package channelpopularity.driver;

import java.util.HashMap;
import java.util.Map;
import java.util.Iterator;
import java.util.Set;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.FileInputStream;
import channelpopularity.util.FileProcessor;
import channelpopularity.context.ContextI;
import channelpopularity.context.ChannelContext;


// import channelpopularity.context.ContextI;
// import channelpopularity.state.UnPopular;
// import channelpopularity.state.MidPopular;
// import channelpopularity.state.HighlyPopular;
// import channelpopularity.state.UltraPopular;
import channelpopularity.state.StateName;
import channelpopularity.state.factory.SimpleStateFactoryI;

/**
 * @author John Doe
 *
 */
public class Driver {
	private static final int REQUIRED_NUMBER_OF_CMDLINE_ARGS = 2;

    /**
    * Starts file processing
    * @exception InvalidPathException On invalid path string.
    * @exception SecurityException On not having necessary read permissions to the input file.
    * @exception FileNotFoundException On input file not found.
    * @exception IOException On any I/O errors while reading lines from input file.
    *
    * @return void
    */
	private static void executeProcess(String inputFile, String outputFile){
        
        try {
            FileProcessor fileProcessor = new FileProcessor(inputFile); 
            StateName states[] = StateName.values();
            List<StateName> stateNames = Arrays.asList(states);
            SimpleStateFactoryI stateFactoryIn = null;
            ContextI channel = new ChannelContext(stateFactoryIn, stateNames);
            String line = fileProcessor.poll();
            while(line != null){
                channel.parseInput(line);
                line = fileProcessor.poll();
            }
        }
        catch(Exception e){
          e.printStackTrace();
        }

	}
	public static void main(String[] args) throws Exception {

		/*
		 * As the build.xml specifies the arguments as input,output or metrics, in case the
		 * argument value is not given java takes the default value specified in
		 * build.xml. To avoid that, below condition is used
		 */
		if ((args.length != 2) || (args[0].equals("${input}")) || (args[1].equals("${output}"))) {
			System.err.printf("Error: Incorrect number of arguments. Program accepts %d arguments.", REQUIRED_NUMBER_OF_CMDLINE_ARGS);
			System.exit(0);
		}
		System.out.println("Hello World! Lets get started with the assignment");
		
		System.out.println(args[0]);
		System.out.println(args[1]);

		executeProcess(args[0], args[1]);
	}
}



// public class TestATMMachine {
// 	public static void main(String[] args){
// 		ATMMachine atmMachine = new ATMMachine();
// 		atmMachine.insertCard();
// 		atmMachine.ejectCard();		
// 		atmMachine.insertCard();		
// 		atmMachine.insertPin(1234);		
// 		atmMachine.requestCash(2000);		
// 		atmMachine.insertCard();		
// 		atmMachine.insertPin(1234);		
// 	}
// }