package channelpopularity.state;

public interface StateI {
    // Different states expected
    // UNPOPULAR, MILDLY_POPULAR, HIGHLY_POPULAR, ULTRA_POPULAR;    
    public void addVideo(String video);
    public void removeVideo(String video);
    public void addMetrics(String video, int views, int likes, int dislikes);
    public void adRequest(int len);

}


// public interface ATMState {
    
//     // Different states expected
//     // HasCard, NoCard, HasPin, NoCash
//     void insertCard();
//     void ejectCard();
//     void insertPin(int pinEntered);
//     void requestCash(int cashToWithdraw);
// }
