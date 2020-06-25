package channelpopularity.context;

import channelpopularity.context.ContextI;

// import channelpopularity.state.MidPopular;
// import channelpopularity.state.HighlyPopular;
// import channelpopularity.state.UltraPopular;
import channelpopularity.state.StateI;
import channelpopularity.state.StateName;
import channelpopularity.state.factory.SimpleStateFactoryI;
import channelpopularity.state.factory.SimpleStateFactory;


import java.util.HashMap;
import java.util.Map;
import java.util.Iterator;
import java.util.Set;
import java.util.Arrays;
import java.util.List;

public class ChannelContext implements ContextI {

    private StateI curState;
    public String command;
    public String[] details;
    public int popularityScore;
    public int[] stats = new int[]{0, 0, 0, 0};
    private Map<String, int[]> account = new HashMap<String, int[]>();
    private Map<StateName, StateI> availableStates = new HashMap<StateName, StateI>();


    public ChannelContext(SimpleStateFactoryI stateFactoryIn, List<StateName> stateNames) {
        stateFactoryIn = new SimpleStateFactory(this);
        int adLimit = 10;
        for (StateName each_state: stateNames){
            curState = stateFactoryIn.createStateObjects(each_state, adLimit);
            availableStates.put(each_state, curState);
            adLimit = adLimit + 10;
        }
        curState = availableStates.get(StateName.UNPOPULAR);
    }


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
    @Override
    public int updateScore(){
        int[] totalCount = new int[4];
        int start = 0;
        for (String name: account.keySet()){
            String key = name.toString();
            int[] value = account.get(name);  
            for (int a:value){
                totalCount[start] = totalCount[start] + a;
                start++;
            }
            start = 0;
        }
        popularityScore = totalCount[0] + 2 * (totalCount[1] - totalCount[2]);
        return popularityScore;
    }
    @Override
    public void setState(StateName newStateName){curState = availableStates.get(newStateName);   }
    

    @Override
    public void addVideo(String[] details){     curState.addVideo(details);     }
    @Override
    public void removeVideo(String[] details){  curState.removeVideo(details);  }
    @Override
    public void addMetrics(String[] details){   curState.addMetrics(details);   }
    @Override
    public void adRequest(String[] deails){     curState.adRequest(details);    }


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
        System.out.println("CurrentScore: " + popularityScore + " State: " + curState.getName()+" Ad Limit: "+curState.getLimit());
        System.out.println("\n");
    }

    @Override
    public void parseInput(String inpt){
        String[] arrOfKeywords = {"ADD_VIDEO", "METRICS__", "AD_REQUEST__", "REMOVE_VIDEO", "]",  "::", "[VIEWS=", ",LIKES=", ",DISLIKES=", "LEN="};

        for (int i = 0; i< 10; i++){
            if (inpt.contains(arrOfKeywords[i]) && i <4){   command = arrOfKeywords[i]; }
            if (i < 6)      {   inpt = inpt.replace(arrOfKeywords[i], "");              }
            else            {   inpt = inpt.replace(arrOfKeywords[i], " ");             }
        }

        details = inpt.split(" ", -1);
        if (command == "ADD_VIDEO")     {   addVideo(details);     }
        if (command == "REMOVE_VIDEO")  {   removeVideo(details);  }
        if (command == "METRICS__")     {   addMetrics(details);   }
        if (command == "AD_REQUEST__")  {   adRequest(details);    }

        printAccount(command, details);
    }
}

// @Override
// // Called by the States based on their logic of what the machine state should change to.
// public void setCurrentState(StateName nextState) {
//     if (availableStates.containsKey(nextState)) { // for safety.
//         curState = availableStates.get(nextState);
//     }
// }























//     // private StateI unPopular;
//     // private StateI midPopular;
//     // private StateI highlyPopular;
//     // private StateI ultraPopular;
//     // private StateI curState;

//     public String command;
//     public String[] details;
//     int[] stats = new int[]{0, 0, 0, 0};

//     public int popularityScore;

//     public Map<String, int[]> account = new HashMap<String, int[]>();

//     private void printAccount(String command, String[] details){
//         System.out.print("command: "+ command);
//         for(String a:details)
//             System.out.print(" " + a);
//         System.out.print("\n");
//         System.out.println("Account status: ");
//         for (String name: account.keySet()){
//             String key = name.toString();
//             int[] value = account.get(name);  
//             System.out.print("Key: " + key + " value: ");
//             for (int a:value)
//                 System.out.print(a +" ");
//             System.out.print("\n");
//         }
//         System.out.println("\n");
//     }

//     public ChannelContext(){
//         // unPopular     = new UnPopular(this, 10);
//         // midPopular    = new MidPopular(this, 20);
//         // highlyPopular = new HighlyPopular(this, 30);
//         // ultraPopular  = new UltraPopular(this, 40);

//         // curState = unPopular;
//     }


//     @Override
//     public void setState(StateI newStateName){
//         // updateScore();
//         // // curState = newStateName;
//         // System.out.println("############"+popularityScore+"############");
//         // if (0 <= popularityScore && popularityScore <= 1000){
//         //     curState = getUnPopularState();
//         // }
//         // if (1000 < popularityScore && popularityScore <= 10000){
//         //     curState = getMidPopularState();
//         // }
//         // if (10000 < popularityScore && popularityScore <= 100000){
//         //     curState = getHighlyPopularState();
//         // }
//         // if (100000 < popularityScore && popularityScore <= Integer.MAX_VALUE){
//         //     curState = getUltraPopularState();
//         // }
//     }

//     @Override
//     public void addVideo(String[] details){
//         curState.addVideo(details);
//     }

//     @Override
//     public void removeVideo(String[] details){
//         curState.removeVideo(details);
//     }

//     @Override
//     public void addMetrics(String[] details){
//         curState.addMetrics(details);
//     }

//     @Override
//     public void adRequest(String[] deails){
//         curState.adRequest(details);
//     }

//     @Override
//     public StateI getUnPopularState()    {  return unPopular;}
//     @Override
//     public StateI getMidPopularState()   {  return midPopular;}
//     @Override
//     public StateI getHighlyPopularState(){  return highlyPopular;}
//     @Override
//     public StateI getUltraPopularState() {  return ultraPopular;}

//     @Override
//     public int[] editAccount(String action, String key, int[] value){
//         if (action == "get"){ 
//             try{    
//                 stats = account.get(key);
//             }
//             catch (NullPointerException keyNotPresent){
//                 return stats;
//             }
//             return stats;
//         }
//         if (action == "put")    {   account.put(key, value);    }
//         if (action == "remove") {   account.remove(key);        }
//         return value;
//     }

//     public int updateScore(){
//         int[] totalCount = new int[4];
//         int start = 0;
//         for (String name: account.keySet()){
//             String key = name.toString();
//             int[] value = account.get(name);  
//             // System.out.print("Key: " + key + " value: ");
//             for (int a:value){
//                 totalCount[start] = totalCount[start] + a;
//                 start++;
//             }
//             start = 0;
//         }

//         popularityScore = totalCount[0] + 2 * (totalCount[1] - totalCount[2]);

//         return popularityScore;
//     }

//     @Override
//     public void parseInput(String inpt){

//         String[] arrOfKeywords = {"ADD_VIDEO", "METRICS__", "AD_REQUEST__", "REMOVE_VIDEO", "]",  "::", "[VIEWS=", ",LIKES=", ",DISLIKES=", "LEN="};
//         for (int i = 0; i< 10; i++){
//             if (inpt.contains(arrOfKeywords[i]) && i <4){
//                 command = arrOfKeywords[i];
//             }
//             if (i < 6)      {   inpt = inpt.replace(arrOfKeywords[i], "");     }
//             else            {   inpt = inpt.replace(arrOfKeywords[i], " ");    }
//         }

//         details = inpt.split(" ", -1);
        

//         if (command == "ADD_VIDEO")     {   addVideo(details);     }
//         if (command == "REMOVE_VIDEO")  {   removeVideo(details);  }
//         if (command == "METRICS__")     {   addMetrics(details);   }
//         if (command == "AD_REQUEST__")  {   adRequest(details);    }

//         printAccount(command, details);
//     }
// }

