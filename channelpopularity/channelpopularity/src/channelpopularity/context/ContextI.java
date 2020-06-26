package channelpopularity.context;

import channelpopularity.state.StateI;
import channelpopularity.state.StateName;

public interface ContextI {

    public int[] editAccount(String action, String key, int[] value) throws IllegalArgumentException;
    public int updateScore();
    public void setState(StateName newStateName);

    public void addVideo(String[] details);
    public void removeVideo(String[] details);
    public void addMetrics(String[] details);
    public void adRequest(String[] details);

    public void parseInput(String inpt);


    public void storeResult(String line);
    public void persistResult();


}
