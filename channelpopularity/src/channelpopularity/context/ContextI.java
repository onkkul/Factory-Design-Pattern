package channelpopularity.context;


import channelpopularity.state.UnPopular;
import channelpopularity.state.MidPopular;
import channelpopularity.state.HighlyPopular;
import channelpopularity.state.UltraPopular;
import channelpopularity.state.StateI;

public interface ContextI {
    public void setState(StateI newStateName);

    public void addVideo(String[] details);
    public void removeVideo(String[] details);
    public void addMetrics(String[] details);
    public void adRequest(String[] details);

    public StateI getUnPopularState();
    public StateI getMidPopularState();
    public StateI getHighlyPopularState();
    public StateI getUltraPopularState();

    public int[] editAccount(String action, String key, int[] value);
    public void parseInput(String inpt);

    public void updateScore();
}
