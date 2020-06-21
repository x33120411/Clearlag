package listener;

import clearlagcustomize.main.Main;
import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.ChunkLoadEvent;
import org.bukkit.event.world.ChunkUnloadEvent;

public class Chunk implements Listener {

    @EventHandler
    public void OnUnloadChunk(ChunkUnloadEvent e){
        for (Entity Ent : e.getChunk().getEntities()){
            Ent.remove();
        }
    }
}
