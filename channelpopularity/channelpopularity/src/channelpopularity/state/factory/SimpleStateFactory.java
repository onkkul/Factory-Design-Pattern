package channelpopularity.state.factory;

import channelpopularity.context.ContextI;

import channelpopularity.state.StateI;
import channelpopularity.state.StateName;
import channelpopularity.state.GetStateObject;


public class SimpleStateFactory implements SimpleStateFactoryI {

    private ContextI channel;
    public SimpleStateFactory(ContextI channel){
        this.channel = channel;
    }

    @Override
    public StateI createStateObjects(StateName stateName, int adLimit){
        return new GetStateObject(this.channel, stateName, adLimit);
    }

}
