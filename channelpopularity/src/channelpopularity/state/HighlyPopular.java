// package channelpopularity.state;

// import channelpopularity.context.ContextI;

// public class HighlyPopular extends AbstractState {

//     public HighlyPopular(ContextI channel){
//         setContext(channel);
//         setName("HighlyPopular");
//         setLimit(10);
//     }

// }




































//     public String video;
//     public int[] stats;
//     private int adLimit;

//     private ContextI currentChannel;

//     public HighlyPopular(ContextI channel, int adLimit){
//         this.currentChannel = channel;
//         this.adLimit = adLimit;
//     }

//     @Override
//     public void addVideo(String[] details){
//         stats = new int[]{0, 0, 0, 30};
//         this.currentChannel.editAccount("put", details[0], stats);

//         this.currentChannel.setState(this.currentChannel.getMidPopularState());
//         return;
//     }

//     @Override
//     public void removeVideo(String[] details){
//         this.currentChannel.editAccount("remove", details[0], stats);
        
//         this.currentChannel.setState(this.currentChannel.getMidPopularState());
//         return;
//     }

//     @Override
//     public void addMetrics(String[] details){
//         int[] previous = this.currentChannel.editAccount("get", details[0], stats);
//         for(int i = 1; i < 4; i++){
//             previous[i-1] = previous[i-1] + Integer.parseInt(details[i]);
//             if (previous[i-1] < 0) {previous[i-1] = 0;}
//         }
//         previous[3] = this.adLimit;
//         this.currentChannel.editAccount("put", details[0], previous);

//         this.currentChannel.setState(this.currentChannel.getMidPopularState());
//         return;
//     }

//     @Override
//     public void adRequest(String[] details){
//         if (Integer.parseInt(details[1]) > this.adLimit){
//             System.out.println("\nRejected");
//         }
//             else{
//             System.out.println("\nAccepted");
//         }
//     this.currentChannel.setState(this.currentChannel.getMidPopularState());
//     return;
//     }

// }
