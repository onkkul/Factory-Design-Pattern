package channelpopularity.state;

import channelpopularity.state.StateName;
import channelpopularity.context.ContextI;

public interface StateI {

    public int getLimit();
    public StateName getName();
    public ContextI getContext();
    public void addVideo(String[] details)throws IllegalArgumentException;
    public void removeVideo(String[] details);
    public void addMetrics(String[] details);
    public void adRequest(String[] details);

}
