package channelpopularity.context;

import channelpopularity.context.ContextI;
import channelpopularity.state.UnPopular;
import channelpopularity.state.MidPopular;
import channelpopularity.state.HighlyPopular;
import channelpopularity.state.UltraPopular;
import channelpopularity.state.StateI;

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

    public ChannelContext(){
        unPopular     = new UnPopular(this);
        midPopular    = new MidPopular(this);
        highlyPopular = new HighlyPopular(this);
        ultraPopular  = new UltraPopular(this);

        currentState = unPopular;
    }


    @Override
    public void setState(StateI newStateName){
        currentState = newStateName;
    }

    @Override
    public void addVideo(String video){
        currentState.addVideo(video);
    }

    @Override
    public void removeVideo(String video){
        currentState.removeVideo(video);
    }

    @Override
    public void addMetrics(String video, int views, int likes, int dislikes){
        currentState.addMetrics(video, views, likes, dislikes);
    }

    @Override
    public void adRequest(String video, int len){
        currentState.adRequest(video, len);
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
    public void parseInput(String inpt){
        System.out.println(inpt);
        if (inpt.contains("ADD_VIDEO")){
            addVideo(inpt.replace("ADD_VIDEO::", ""));
        }

        if (inpt.contains("REMOVE_VIDEO")){
            removeVideo(inpt.replace("REMOVE_VIDEO::", ""));
        }
    }





}