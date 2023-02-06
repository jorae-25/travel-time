public class Location {
    private final long id;
    private final double latitude;
    private final double longitude;

    public Location(long id, double ltde, double lgtd){
        this.id = id;
        this.latitude = ltde;
        this.longitude = lgtd;
    }

    public long getId(){
        return id;
    }

    public double getLatitude(){
        return latitude;
    }

    public double getLongitude(){
        return longitude;
    }
}
