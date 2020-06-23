package channelpopularity.context;


import channelpopularity.state.UnPopular;
import channelpopularity.state.MidPopular;
import channelpopularity.state.HighlyPopular;
import channelpopularity.state.UltraPopular;
import channelpopularity.state.StateI;

public interface ContextI {
    public void setState(StateI newStateName);

    public void addVideo(String video);
    public void removeVideo(String video);
    public void addMetrics(String video, int views, int likes, int dislikes);
    public void adRequest(String video, int len);

    public StateI getUnPopularState();
    public StateI getMidPopularState();
    public StateI getHighlyPopularState();
    public StateI getUltraPopularState();

    public void parseInput(String inpt);
}
