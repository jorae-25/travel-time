import java.util.*;
public class Graph {
    private final  Map<Long, Location> locations;
    private final Map<Long, List<Road>> roads;

    public Graph(){
        locations = new HashMap<>();
        roads = new HashMap<>();
    }

    public void addRoad(long locId1, long locId2, int speed, String name){
        Road road = new Road(locId1, locId2, speed, name);
        List<Road> loc1list = new ArrayList<>();
        List<Road> loc2list = new ArrayList<>();

        if(!hasRoad(locId1) && hasRoad(locId2)){// 1 doesn't exist but 2 does
            roads.put(locId1, loc1list);
        }
        else if(hasRoad(locId1) && !hasRoad(locId2)){ //1 exists but 2 doesn't
            roads.put(locId2, loc2list);
        }
        else if(!hasRoad(locId1) && !hasRoad(locId2)){ // both don't exist
            roads.put(locId1, loc1list);
            roads.put(locId2, loc2list);
        }
        //both existing is handled here implicitly
        roads.get(locId1).add(road);
        roads.get(locId2).add(road);
    }
    public void addLocation(long locId, double latitude, double longitude){
        Location loc = new Location(locId,latitude,longitude);
        locations.put(locId, loc);
    }
    public boolean hasLocation(long locId){
        return locations.containsKey(locId);
    }
    public boolean hasRoad(long locId){
        return roads.containsKey(locId);
    }

    public List<Road> getRoadsFromLoc(long locId){
        return roads.get(locId);
    }

    public double getlat(long locId){
        return locations.get(locId).getLatitude();
    }
    public double getlong(long locId){
        return locations.get(locId).getLongitude();
    }
}
