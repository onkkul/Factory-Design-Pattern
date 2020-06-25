package channelpopularity.state;

import channelpopularity.context.ContextI;
import channelpopularity.state.StateName;


public abstract class AbstractState implements StateI {

    private StateName stateName;
    private int adLimit;
    private ContextI currentChannel;

    public void setName(StateName name)    {   this.stateName = name;   }
    public StateName getName()             { return this.stateName;     }

    public void setLimit(int adLimit)   {   this.adLimit = adLimit;  }
    public int getLimit()               { return this.adLimit;       }

    public void setContext(ContextI channel)   {   this.currentChannel = channel;  }
    public ContextI getContext()               { return this.currentChannel;       }


    public StateI decideState(){

        StateI nexttState;
        int popularityScore = this.currentChannel.updateScore();

        System.out.println("############"+popularityScore+"############");
        if (0 <= popularityScore && popularityScore <= 1000){
            nexttState = currentChannel.getUnPopularState();
        }
        if (1000 < popularityScore && popularityScore <= 10000){
            nexttState = currentChannel.getMidPopularState();
        }
        if (10000 < popularityScore && popularityScore <= 100000){
            nexttState = currentChannel.getHighlyPopularState();
        }
        if (100000 < popularityScore && popularityScore <= Integer.MAX_VALUE){
            nexttState = currentChannel.getUltraPopularState();
        }


        return nexttState;

    }

    @Override
    public void addVideo(String[] details){
        stats = new int[]{0, 0, 0, 10};
        this.currentChannel.editAccount("put", details[0], stats);        
        this.currentChannel.setState(decideState());
    }

    @Override
    public void removeVideo(String[] details){
        this.currentChannel.editAccount("remove", details[0], stats);

        this.currentChannel.setState(decideState());
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

        this.currentChannel.setState(decideState());
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

        this.currentChannel.setState(decideState());
    return;
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