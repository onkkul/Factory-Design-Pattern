package channelpopularity.state.factory;

import channelpopularity.state.StateI;
import channelpopularity.state.StateName;

public interface SimpleStateFactoryI {
    public StateI createStateObjects(StateName stateName, int adLimit);
}
