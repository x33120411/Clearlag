package API;

import clearlagcustomize.main.Main;
import me.ryanhamshire.GriefPrevention.Claim;
import me.ryanhamshire.GriefPrevention.GriefPrevention;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class RemoveEntity {

    public static double SceneRadius = 16;
    private static HashMap<UUID, List<Entity>> NoticeNearEntity = new HashMap<>();

    private static boolean RemoveEntityNotice(Player p){
        final List<Entity> NearEnt = p.getNearbyEntities(SceneRadius, SceneRadius, SceneRadius);
        boolean isNeedRemove = false;

        if (NearEnt.size() > PluginData.EntityLimit) {
            int RemoveNum = 0;
            for (Entity Ent : NearEnt) {
                for (Object obj : GriefPrevention.instance.dataStore.getClaims().toArray()) {
                    Claim claim = (Claim) obj;
                    final Location MonsterLocation = Ent.getLocation();
                    final Location Corner1 = claim.getGreaterBoundaryCorner();
                    final Location Corner2 = claim.getLesserBoundaryCorner();

                    final double MinX1 = Math.min(Corner1.getX(), Corner2.getX());
                    final double MinZ1 = Math.min(Corner1.getZ(), Corner2.getZ());
                    final double MaxX1 = Math.max(Corner1.getX(), Corner2.getX());
                    final double MaxZ1 = Math.max(Corner1.getZ(), Corner2.getZ());

                    boolean Condition1 = MonsterLocation.getX() >= MinX1 && MonsterLocation.getZ() >= MinZ1;
                    boolean Condition2 = MonsterLocation.getX() <= MaxX1 && MonsterLocation.getZ() <= MaxZ1;

                    if (Condition1 && Condition2){
                        RemoveNum++;
                        break;
                    }
                }
            }
            NoticeNearEntity.put(p.getUniqueId(), NearEnt);
            isNeedRemove = true;
            p.sendMessage("你的領地內即將移除個生物");
        }
        return isNeedRemove;
    }

    public static void RemoveRadiusEntity(Player p){
        if (NoticeNearEntity.get(p.getUniqueId()).size() > PluginData.EntityLimit){
            final int ReduceNum = NoticeNearEntity.get(p.getUniqueId()).size() - PluginData.EntityLimit;
            for (int i = 0; i < ReduceNum; i++){
                NoticeNearEntity.get(p.getUniqueId()).get(i).remove();
            }
        }
    }
}
