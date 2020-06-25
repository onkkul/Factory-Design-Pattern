package channelpopularity.state;

import channelpopularity.context.ContextI;
import channelpopularity.state.StateName;


public abstract class AbstractState implements StateI {

    public String video;
    public int[] stats;
    private int adLimit;
    private ContextI currentChannel;
    private static StateName stateName;

    public void setDetails(ContextI channel, StateName name, int adLimit){
        this.currentChannel = channel;  
        this.stateName = name;
        this.adLimit = adLimit;
    }


    @Override
    public StateName getName()      { return this.stateName;        }
    @Override
    public int getLimit()           { return this.adLimit;          }
    @Override
    public ContextI getContext()    { return this.currentChannel;   }


    @Override
    public void addVideo(String[] details){
        stats = new int[]{0, 0, 0, 10};
        this.currentChannel.editAccount("put", details[0], stats);        
        decideState();
    }


    @Override
    public void removeVideo(String[] details){
        this.currentChannel.editAccount("remove", details[0], stats);
        decideState();
        return;
    }


    @Override
    public void addMetrics(String[] details){
        int[] previous = this.currentChannel.editAccount("get", details[0], stats);
        for(int i = 1; i < 4; i++){
            previous[i-1] = previous[i-1] + Integer.parseInt(details[i]);
            if (previous[i-1] < 0) {previous[i-1] = 0;}
        }
        previous[3] = this.adLimit;
        this.currentChannel.editAccount("put", details[0], previous);

        decideState();
        return;
    }

    @Override
    public void adRequest(String[] details){
        if (Integer.parseInt(details[1]) > this.adLimit){
            System.out.println("\nRejected");
        }
        else{
            System.out.println("\nAccepted");
        }

        decideState();
    return;
    }


    public void decideState(){
        // UNPOPULAR, MILDLY_POPULAR, HIGHLY_POPULAR, ULTRA_POPULAR;
        StateName nexttState = null;
        int popularityScore = this.currentChannel.updateScore();
        if (0 <= popularityScore && popularityScore <= 1000){
            this.currentChannel.setState(StateName.UNPOPULAR);
        }
        if (1000 < popularityScore && popularityScore <= 10000){
            this.currentChannel.setState(StateName.MILDLY_POPULAR);
        }
        if (10000 < popularityScore && popularityScore <= 100000){
            this.currentChannel.setState(StateName.HIGHLY_POPULAR);            // nexttState = this.currentChannel.getHighlyPopularState();
        }
        if (100000 < popularityScore && popularityScore <= Integer.MAX_VALUE){
            this.currentChannel.setState(StateName.HIGHLY_POPULAR);            // nexttState = this.currentChannel.getUltraPopularState();
        }
    }
}










//     // public class HasCard implements ATMState {
//     //     ATMMachine atmMachine;
//     //     public HasCard(ATMMachine newATMMachine){    atmMachine = newATMMachine;   }
//     // }
//     // public class NoCard implements ATMState {
//     //     ATMMachine atmMachine;
//     //     public NoCard(ATMMachine newATMMachine){    atmMachine = newATMMachine;    }
//     // }
//     // public class HasPin implements ATMState {
//     //     ATMMachine atmMachine;
//     //     public HasPin(ATMMachine newATMMachine){    atmMachine = newATMMachine;    }
//     // }
//     // public class NoCash implements ATMState {
//     //     ATMMachine atmMachine;
//     //     public NoCash(ATMMachine newATMMachine){    atmMachine = newATMMachine;    }
//     // }