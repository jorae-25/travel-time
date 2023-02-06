/************
 * Alvin Jorkey
 * I have neither given nor received unauthorized aid on this program
 ************/

import java.io.InputStream;
import java.util.Scanner;
import java.util.*;

public class GraphTester{
    public static void main(String[] args){
        Graph g = new Graph();

        //ask for filename
        Scanner s = new Scanner(System.in);
        System.out.print("Enter a filename: ");
        String fileName = s.nextLine();

        //make sure file name is valid
        InputStream istream = GraphTester.class.getResourceAsStream(fileName);
        if (istream == null) {
            System.err.println("Bad filename: " + fileName);
            System.exit(1);
        }

        //process file
        Scanner fContents = new Scanner(istream);
        while(fContents.hasNextLine()){
            String line = fContents.nextLine();
            String[] fparts = line.split("\\|");
            for(int i = 0; i< fparts.length; i++){
                if(fparts[0].compareTo("location")==0){ //location
                    g.addLocation(Long.parseLong(fparts[1]), Double.parseDouble(fparts[2]), Double.parseDouble(fparts[3]));
                    break;
                }
                else if(fparts[0].compareTo("road")==0){ //road
                    g.addRoad(Long.parseLong(fparts[1]),Long.parseLong(fparts[2]),Integer.parseInt(fparts[3]), fparts[4]);
                    break;
                }
            }
        }
        System.out.println();

        System.out.print("Enter a location ID or 0 to quit: ");
        String location = s.nextLine();

        while(location.compareTo("0") !=0){
            long numLocation = Long.parseLong(location); //long version of location
            if(g.hasLocation(numLocation)){
                System.out.println("Location " + location + " has roads leading to:");
                List<Road> connectedRoads = g.getRoadsFromLoc(numLocation);
                for (Road connectedRoad : connectedRoads) {
                    long loc1Id = connectedRoad.getStartingId();
                    long loc2Id = connectedRoad.getEndingId();

                    double lat1 = g.getlat(loc1Id);
                    double lat2 = g.getlat(loc2Id);
                    double long1 = g.getlong(loc1Id);
                    double long2 = g.getlong(loc2Id);

                    double milesDistance = distanceOnUnitSphere(lat1,long1,lat2,long2);
                    double seconds = (milesDistance/connectedRoad.getSpeedLimit()) * 3600;
                    if(numLocation == connectedRoad.getStartingId()){ // when location is the starting id
                        System.out.println("  Location " + connectedRoad.getEndingId() +", " + connectedRoad.getSpeedLimit() + " mph, " + connectedRoad.getRoadName() +", " + seconds + " seconds");
                    }
                    else{ //when location is the ending Id
                        System.out.println("  Location " + connectedRoad.getStartingId() +", " + connectedRoad.getSpeedLimit() + " mph, " + connectedRoad.getRoadName() +", " + seconds + " seconds");
                    }
                }
            }
            else{
                System.out.println("graph does not have location " + location);
            }
            System.out.println();
            System.out.print("Enter a location ID or 0 to quit: ");
            location = s.nextLine();
        }
    }

    public static double distanceOnUnitSphere(double lat1, double long1, double lat2, double long2){
        double degreeToRadians = Math.PI/180.0;

        double phi1 = (90.0 - lat1) * degreeToRadians;
        double phi2 = (90.0 - lat2) * degreeToRadians;

        double theta1 = long1* degreeToRadians;
        double theta2 = long2* degreeToRadians;

        double cos = (Math.sin(phi1)*Math.sin(phi2)*Math.cos(theta1 - theta2) +
                Math.cos(phi1)*Math.cos(phi2));
        double arc = Math.acos( cos );

        return arc * 3960;
    }
}

