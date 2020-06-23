package channelpopularity.state;

import channelpopularity.context.ContextI;
public class MidPopular implements StateI {

    ContextI currentChannel;

    public MidPopular(ContextI channel){
        currentChannel = channel;
    }

    @Override
    public void addVideo(String video){
        System.out.println(video + "--------> MidPopular");
        // TODO add video to the list of videos
        // set state using:
        currentChannel.setState(currentChannel.getHighlyPopularState());
    }

    @Override
    public void removeVideo(String video){
        // TODO remove videos from the list
        // set state using:
        // currentChannel.setState(currentChannel.getMidPopularState());
    }

    @Override
    public void addMetrics(String video, int views, int likes, int dislikes){
        // TODO check if video exists, then add the metrics
        // set state using:
        // currentChannel.setState(currentChannel.getMidPopularState());
    }

    @Override
    public void adRequest(String video, int len){
        // TODO if len satisfies the criteria, accept, else reject
        // set state using:
        // currentChannel.setState(currentChannel.getMidPopularState());
    }
}