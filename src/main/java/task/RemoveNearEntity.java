package task;

import API.PlayerNearEntity;
import API.RemoveEntity;
import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;

public class RemoveNearEntity implements Runnable{

    Entity RemoveEnt;

    public RemoveNearEntity(Entity Ent){
        RemoveEnt = Ent;
    }

    @Override
    public void run() {
        if (RemoveEnt != null && !RemoveEnt.isDead()) {
            Bukkit.getServer().getPlayer(PlayerNearEntity.GetRecordMonsterPlayer(RemoveEnt.getUniqueId())).sendMessage("Reduce");
            PlayerNearEntity.ReducePlayerNearEntityValue(Bukkit.getServer().getPlayer(PlayerNearEntity.GetRecordMonsterPlayer(RemoveEnt.getUniqueId())), RemoveEnt);

            RemoveEnt.remove();
        }
        else {
            PlayerNearEntity.PlayerNearEntityUID.get(Bukkit.getServer().getPlayer(PlayerNearEntity.GetRecordMonsterPlayer(RemoveEnt.getUniqueId()))).remove(RemoveEnt.getUniqueId());
        }
    }
}
