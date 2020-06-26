package channelpopularity.state;

import channelpopularity.state.StateName;
import channelpopularity.context.ContextI;
import channelpopularity.state.AbstractState;

public class GetStateObject extends AbstractState {

    public GetStateObject(ContextI channel, StateName stateName, int adLimit){
        setDetails(channel, stateName, adLimit);
    }
}