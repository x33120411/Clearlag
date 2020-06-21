package API;

import org.bukkit.Location;

import java.util.ArrayList;
import java.util.List;

public class MobLocation {

    private static List<Location> hasLocation = new ArrayList<>();

    public static boolean isNeedRecord(Location location){
        for (Location testLocation : hasLocation){
            if (testLocation.distance(location) > RemoveEntity.SceneRadius && testLocation.distance(location) < RemoveEntity.SceneRadius * 2){
                return true;
            }
            if (testLocation.distance(location) > RemoveEntity.SceneRadius * 2){
                return true;
            }
        }
        return false;
    }

    public static void AddLocation(){

    }
}
