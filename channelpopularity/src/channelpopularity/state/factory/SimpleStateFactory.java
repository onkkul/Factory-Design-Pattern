package channelpopularity.state.factory;


import channelpopularity.context.ContextI;
// import channelpopularity.state.UnPopular;
// import channelpopularity.state.MidPopular;
// import channelpopularity.state.HighlyPopular;
// import channelpopularity.state.UltraPopular;
import channelpopularity.state.StateName;
import channelpopularity.state.GetStateObject;
import channelpopularity.state.StateI;


public class SimpleStateFactory implements SimpleStateFactoryI {

    private StateI unPopular;
    private StateI midPopular;
    private StateI highlyPopular;
    private StateI ultraPopular;
    private StateI currentState;
    private ContextI channel;

    public SimpleStateFactory(ContextI channel){
        this.channel = channel;
        // this.unPopular     = new UnPopular(channel);
        // this.midPopular    = new MidPopular(channel);
        // this.highlyPopular = new HighlyPopular(channel);
        // this.ultraPopular  = new UltraPopular(channel);
        // this.currentState = unPopular;
    }

    @Override
    public StateI createStateObjects(StateName stateName, int adLimit){
        StateI temp = new GetStateObject(this.channel, stateName, adLimit);
        return temp;
    }

}
