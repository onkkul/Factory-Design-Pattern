package channelpopularity.context;

public ChannelContext implements ContextI {

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

    StateI unPopular;
    StateI midPopular;
    StateI highlyPopular;
    StateI ultraPopular;
    StateI currentState;

    public ChannelContext(){
        unPopular     = new UnPopular(this);
        midPopular    = new MidPopular(this);
        highlyPopular = new HighPopular(this);
        ultraPopular  = new UltraPopular(this);

        currentState = unPopular;
    }


    @Override
    public void setState(StateI newStateName){
        currentState = newStateName;
    }

    @Override
    public void addVideo(String video){
        currentState.addVideo(String video);
    }

    @Override
    public void removeVideo(String video){
        currentState.removeVideo(String video);
    }

    @Override
    public void addMetrics(String video, int views, int likes, int dislikes){
        currentState.addMetrics(String video, int views, int likes, int dislikes);
    }

    @Override
    public void adRequest(int len){
        currentState.adRequest(int len);
    }

    @Override
    public StateI getUnpopularState()    {  return unPopular;}
    @Override
    public StateI getMidPopularState()   {  return midPopular;}
    @Override
    public StateI getHighlyPopularState(){  return highlyPopular;}
    @Override
    public StateI getUltraPopularState() {  return ultraPopular;}





}