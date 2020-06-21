package listener;

import clearlagcustomize.main.Main;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import task.CheckSinglePlayerNearEntity;

public class Player implements Listener {

    @EventHandler
    public void OnPlayerJoin(PlayerJoinEvent e){
        Main.main.getServer().getScheduler().runTask(Main.main, new CheckSinglePlayerNearEntity(e.getPlayer()));
    }
}
