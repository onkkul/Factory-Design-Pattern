package channelpopularity.context;

import channelpopularity.context.ContextI;
import channelpopularity.state.UnPopular;
import channelpopularity.state.MidPopular;
import channelpopularity.state.HighlyPopular;
import channelpopularity.state.UltraPopular;
import channelpopularity.state.StateI;

import java.util.HashMap;
import java.util.Map;
import java.util.Iterator;
import java.util.Set;
import java.util.Arrays;

public class ChannelContext implements ContextI {

    /*              ## Code given ##
    private StateI curState;
    private Map<StateName, StateI> availableStates;

    public ChannelContext(SimpleStateFactoryI stateFactoryIn, List<StateName> stateNames) {
    // initialize states using factory instance and the provided state names.
    // initialize current state.
    }

    // Called by the States based on their logic of what the machine state should change to.
    public void setCurrentState(StateName nextState) {
        if (availableStates.containsKey(nextState)) { // for safety.
            curState = availableStates.get(nextState);
        }
    } */

    private StateI unPopular;
    private StateI midPopular;
    private StateI highlyPopular;
    private StateI ultraPopular;
    private StateI currentState;

    public String command;
    public String[] details;
    int[] stats = new int[]{0, 0, 0, 0};

    public int popularityScore;

    public Map<String, int[]> account = new HashMap<String, int[]>();

    private void printAccount(String command, String[] details){
        System.out.print("command: "+ command);
        for(String a:details)
            System.out.print(" " + a);
        System.out.print("\n");
        System.out.println("Account status: ");
        for (String name: account.keySet()){
            String key = name.toString();
            int[] value = account.get(name);  
            System.out.print("Key: " + key + " value: ");
            for (int a:value)
                System.out.print(a +" ");
            System.out.print("\n");
        }
        System.out.println("\n");
    }

    public ChannelContext(){
        unPopular     = new UnPopular(this, 10);
        midPopular    = new MidPopular(this, 20);
        highlyPopular = new HighlyPopular(this, 30);
        ultraPopular  = new UltraPopular(this, 40);

        currentState = unPopular;
    }


    @Override
    public void setState(StateI newStateName){
        updateScore();
        // currentState = newStateName;
        System.out.println("############"+popularityScore+"############");
        if (0 <= popularityScore && popularityScore <= 1000){
            currentState = getUnPopularState();
        }
        if (1000 < popularityScore && popularityScore <= 10000){
            currentState = getMidPopularState();
        }
        if (10000 < popularityScore && popularityScore <= 100000){
            currentState = getHighlyPopularState();
        }
        if (100000 < popularityScore && popularityScore <= Integer.MAX_VALUE){
            currentState = getUltraPopularState();
        }
    }

    @Override
    public void addVideo(String[] details){
        currentState.addVideo(details);
    }

    @Override
    public void removeVideo(String[] details){
        currentState.removeVideo(details);
    }

    @Override
    public void addMetrics(String[] details){
        currentState.addMetrics(details);
    }

    @Override
    public void adRequest(String[] deails){
        currentState.adRequest(details);
    }

    @Override
    public StateI getUnPopularState()    {  return unPopular;}
    @Override
    public StateI getMidPopularState()   {  return midPopular;}
    @Override
    public StateI getHighlyPopularState(){  return highlyPopular;}
    @Override
    public StateI getUltraPopularState() {  return ultraPopular;}

    @Override
    public int[] editAccount(String action, String key, int[] value){
        if (action == "get"){ 
            try{    
                stats = account.get(key);
            }
            catch (NullPointerException keyNotPresent){
                return stats;
            }
            return stats;
        }
        if (action == "put")    {   account.put(key, value);    }
        if (action == "remove") {   account.remove(key);        }
        return value;
    }

    public void updateScore(){
        int[] totalCount = new int[4];
        int start = 0;
        for (String name: account.keySet()){
            String key = name.toString();
            int[] value = account.get(name);  
            // System.out.print("Key: " + key + " value: ");
            for (int a:value){
                totalCount[start] = totalCount[start] + a;
                start++;
            }
            start = 0;
        }

        popularityScore = totalCount[0] + 2 * (totalCount[1] - totalCount[2]);
    }

    @Override
    public void parseInput(String inpt){

        String[] arrOfKeywords = {"ADD_VIDEO", "METRICS__", "AD_REQUEST__", "REMOVE_VIDEO", "]",  "::", "[VIEWS=", ",LIKES=", ",DISLIKES=", "LEN="};
        for (int i = 0; i< 10; i++){
            if (inpt.contains(arrOfKeywords[i]) && i <4){
                command = arrOfKeywords[i];
            }
            if (i < 6)      {   inpt = inpt.replace(arrOfKeywords[i], "");     }
            else            {   inpt = inpt.replace(arrOfKeywords[i], " ");    }
        }

        details = inpt.split(" ", -1);
        

        if (command == "ADD_VIDEO")     {   addVideo(details);     }
        if (command == "REMOVE_VIDEO")  {   removeVideo(details);  }
        if (command == "METRICS__")     {   addMetrics(details);   }
        if (command == "AD_REQUEST__")  {   adRequest(details);    }

        printAccount(command, details);
    }
}