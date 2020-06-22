package listener;

import API.MobLocation;
import clearlagcustomize.main.Main;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;

import java.util.List;

public class Creature implements Listener {

    @EventHandler
    public void OnCreatureSpawn(CreatureSpawnEvent e){
        if (MobLocation.isNeedAdd(e.getLocation())){
            MobLocation.AddLocation(e.getLocation());
        }
        else if (MobLocation.isLocationMaxNum(MobLocation.GetNearLocation(e.getLocation()))){
            return;
        }
        List<Entity> NearEnt = (List<Entity>) e.getLocation().getWorld().getNearbyEntities(e.getLocation(), 32, 32, 32);
        for (Entity Ent : NearEnt){
            if (Ent.getType() == EntityType.PLAYER)
                return;
        }
        e.setCancelled(true);
    }
}
