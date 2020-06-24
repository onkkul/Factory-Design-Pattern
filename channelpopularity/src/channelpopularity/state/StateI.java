package channelpopularity.state;



public interface StateI {
    // Different states expected
    // UNPOPULAR, MILDLY_POPULAR, HIGHLY_POPULAR, ULTRA_POPULAR;    
    public void addVideo(String[] details);
    public void removeVideo(String[] details);
    public void addMetrics(String[] details);
    public void adRequest(String[] details);

}


// public interface ATMState {
    
//     // Different states expected
//     // HasCard, NoCard, HasPin, NoCash
//     void insertCard();
//     void ejectCard();
//     void insertPin(int pinEntered);
//     void requestCash(int cashToWithdraw);
// }
