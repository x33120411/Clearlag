package API;

import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class PlayerNearEntity {

    private static List<UUID> RecordEntity = new ArrayList<>();
    private static HashMap<UUID, UUID> RecordMonsterPlayer = new HashMap<>();
    public static HashMap<UUID, List<UUID>> PlayerNearEntityUID = new HashMap<>();

    public synchronized static void IncreasePlayerNearEntityValue(Player p, LivingEntity RecordEnt){
        if (PlayerNearEntityUID.containsKey(p.getUniqueId())) {
            PlayerNearEntityUID.get(p.getUniqueId()).add(RecordEnt.getUniqueId());
            RecordEntity.add(RecordEnt.getUniqueId());
            RecordMonsterPlayer.put(RecordEnt.getUniqueId(), p.getUniqueId());
        }
        else {
            List<UUID> NewList = new ArrayList<>();
            NewList.add(RecordEnt.getUniqueId());
            PlayerNearEntityUID.put(p.getUniqueId(), NewList);
        }
    }

    public synchronized static void ReducePlayerNearEntityValue(Player p, Entity RemoveEnt){
        if (PlayerNearEntityUID.containsKey(p.getUniqueId()) && PlayerNearEntityUID.get(p.getUniqueId()).size() > 0) {
            PlayerNearEntityUID.get(p.getUniqueId()).remove(RemoveEnt.getUniqueId());
            RecordEntity.remove(RemoveEnt.getUniqueId());
            RecordMonsterPlayer.remove(RemoveEnt.getUniqueId());
        }
    }

    public synchronized static List<UUID> GetPlayerRecordMonster(UUID PlayerUID){
        return PlayerNearEntityUID.get(PlayerUID);
    }

    public synchronized static int GetAllowSpawnNum(UUID PlayerUID){
        return PluginData.EntityLimit - PlayerNearEntityUID.get(PlayerUID).size();
    }

    public synchronized static UUID GetRecordMonsterPlayer(UUID MobUID){
        return RecordMonsterPlayer.get(MobUID);
    }

    public synchronized static boolean hasListBefore(UUID PlayerUID){
        return PlayerNearEntityUID.containsKey(PlayerUID);
    }

    public synchronized static boolean isEntityRecord(UUID UID){
        return RecordEntity.contains(UID);
    }

    public synchronized static boolean hasPlayerNearMaxNumber(Player p){
        if (PlayerNearEntityUID.containsKey(p.getUniqueId()))
            return PlayerNearEntityUID.get(p.getUniqueId()).size() >= PluginData.EntityLimit;
        else
            return false;
    }
}
