package channelpopularity.context;


// import channelpopularity.state.UnPopular;
// import channelpopularity.state.MidPopular;
// import channelpopularity.state.HighlyPopular;
// import channelpopularity.state.UltraPopular;
import channelpopularity.state.StateName;
import channelpopularity.state.StateI;

public interface ContextI {

    public int[] editAccount(String action, String key, int[] value);
    public int updateScore();
    public void setState(StateName newStateName);

    public void addVideo(String[] details);
    public void removeVideo(String[] details);
    public void addMetrics(String[] details);
    public void adRequest(String[] details);

    public void parseInput(String inpt);


}
