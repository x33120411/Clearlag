package listener;

import API.PlayerNearEntity;
import API.PluginData;
import clearlagcustomize.main.Main;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Monster;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityDeathEvent;

import java.util.List;

public class Creature implements Listener {

    @EventHandler
    public void OnCreatureSpawn(CreatureSpawnEvent e){
        if (e.getEntity() instanceof Monster) {
            List<Entity> NearEnt = (List<Entity>) e.getLocation().getWorld().getNearbyEntities(e.getLocation(), PluginData.SceneRadius * 2, 5, PluginData.SceneRadius * 2);
            for (Entity Ent : NearEnt) {
                if (Ent.getType() == EntityType.PLAYER && !PlayerNearEntity.hasPlayerNearMaxNumber((org.bukkit.entity.Player) Ent)) {
                    PlayerNearEntity.IncreasePlayerNearEntityValue((org.bukkit.entity.Player) Ent, e.getEntity());
                    //Debug Message
                    Ent.sendMessage("X: " + e.getLocation().getX() + " Y: " + e.getLocation().getY() + " Z: " + e.getLocation().getZ());
                    return;
                }
            }
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void OnCreatureRemove(EntityDeathEvent e){
        if (PlayerNearEntity.isEntityRecord(e.getEntity().getUniqueId())) {
            Main.main.getServer().getPlayer(PlayerNearEntity.GetRecordMonsterPlayer(e.getEntity().getUniqueId())).sendMessage("Reduce");
            PlayerNearEntity.ReducePlayerNearEntityValue(Main.main.getServer().getPlayer(PlayerNearEntity.GetRecordMonsterPlayer(e.getEntity().getUniqueId())), e.getEntity());
        }
    }
}
