public class Road {
    private final long startingId;
    private final long endingId;
    private final String roadName;
    private final int speedLimit;

    public Road(long sId, long eId, int sLimit, String rName){
        this.startingId = sId;
        this.endingId = eId;
        this.speedLimit = sLimit;
        this.roadName = rName;
    }

    public long getStartingId(){
        return startingId;
    }

    public long getEndingId(){
        return endingId;
    }

    public String getRoadName(){
        return roadName;
    }
    public int getSpeedLimit(){
        return speedLimit;
    }
}

