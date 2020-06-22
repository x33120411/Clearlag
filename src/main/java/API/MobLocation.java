package API;

import org.bukkit.Location;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MobLocation {

    private static HashMap<Location, Integer> LocationNumber = new HashMap<>();

    public static boolean isNeedAdd(Location location){
        for (Location testLocation : LocationNumber.keySet()){
            if (testLocation.distance(location) > RemoveEntity.SceneRadius && testLocation.distance(location) < RemoveEntity.SceneRadius * 2){
                return true;
            }
            if (testLocation.distance(location) > RemoveEntity.SceneRadius * 2){
                return true;
            }
        }
        return false;
    }

    public static boolean isLocationMaxNum(Location location){
        return LocationNumber.get(location) < PluginData.EntityLimit;
    }

    public static Location GetNearLocation(Location location){
        double CheckDistance = 90000;
        Location ReturnLocation = null;

        for (int i = 0; i < LocationNumber.size(); i++){
            if (((Location) LocationNumber.keySet().toArray()[i]).distance(location) < CheckDistance){
                CheckDistance = ((Location) LocationNumber.keySet().toArray()[i]).distance(location);
                ReturnLocation = (Location) LocationNumber.keySet().toArray()[i];
            }
        }
        return ReturnLocation;
    }

    public static void AddLocation(Location location){
        LocationNumber.put(location, 0);
    }

    public static void AddLocationValue(Location location, int Value){
        if (LocationNumber.containsKey(location))
            LocationNumber.put(location, LocationNumber.get(location) + Value);
        else
            AddLocation(location);
    }
}
