package channelpopularity.context;

public interface ContextI {
    public void setState(StateI newStateName);
    
    public void addVideo(String video);
    public void removeVideo(String video);
    public void addMetrics(String video, int views, int likes, int dislikes);
    public void adRequest(int len);

    public StateI getUnpopularState();
    public StateI getMidPopularState();
    public StateI getHighlyPopularState();
    public StateI getUltraPopularState();
}
