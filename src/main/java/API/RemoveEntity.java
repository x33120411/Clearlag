package API;

import clearlagcustomize.main.Main;
import me.ryanhamshire.GriefPrevention.Claim;
import me.ryanhamshire.GriefPrevention.GriefPrevention;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import task.RemoveNearEntity;

import java.util.List;

public class RemoveEntity {

    private static void RemoveEntityNotice(Player p, Entity Ent) {

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

            if (Condition1 && Condition2) {
                p.sendMessage("你的領地內即將移除生物");
            }
        }
    }

    public static void RemoveRadiusEntity() {
        Main.main.getServer().getScheduler().scheduleSyncRepeatingTask(Main.main, new Runnable() {
            @Override
            public void run() {
                for (Player p : Bukkit.getServer().getOnlinePlayers()) {
                    if (PlayerNearEntity.hasListBefore(p.getUniqueId())) {
                        for (int i = 0; i < PlayerNearEntity.PlayerNearEntityUID.get(p.getUniqueId()).size(); i++) {
                            Entity Ent = Main.main.getServer().getEntity(PlayerNearEntity.PlayerNearEntityUID.get(p.getUniqueId()).get(i));
                            if (Ent == null) {
                                PlayerNearEntity.PlayerNearEntityUID.get(p.getUniqueId()).remove(i);
                                continue;
                            }
                            if (Ent.getLocation().distance(p.getLocation()) > PluginData.SceneRadius && PlayerNearEntity.isEntityRecord(PlayerNearEntity.PlayerNearEntityUID.get(p.getUniqueId()).get(i))) {
                                RemoveEntity.RemoveEntityNotice(p, Ent);
                                Bukkit.getServer().getScheduler().runTaskLater(Main.main, new RemoveNearEntity(Ent), PluginData.NoticeAfterDelay);
                            }
                        }
                    }
                }
            }
        }, PluginData.SceneDelay, PluginData.SceneDelay);
    }
}
