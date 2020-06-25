package channelpopularity.state;

import channelpopularity.context.ContextI;
import channelpopularity.state.StateName;
// import channelpopularity.state.AbstractState;

public class GetStateObject extends AbstractState {

    public GetStateObject(ContextI channel, StateName stateName, int adLimit){
        setContext(channel);
        setName(stateName);
        setLimit(adLimit);
    }

}