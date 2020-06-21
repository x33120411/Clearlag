package clearlagcustomize.main;

import API.RemoveEntity;
import listener.Chunk;
import listener.Creature;
import listener.Player;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    public static Main main;

    private static void SetMain(Main main){
        Main.main = main;
    }

    private void SetConfig(){
        this.getConfig().options().copyDefaults();
        this.saveConfig();
    }

    @Override
    public void onEnable() {
        // Plugin startup logic
        SetMain(this);
        SetConfig();
        this.getServer().getPluginManager().registerEvents(new Player(), this);
        this.getServer().getPluginManager().registerEvents(new Creature(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
