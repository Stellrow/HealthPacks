package me.Stellrow.HealthPacks;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class HealthPackManager {
    private final HealthPacks pl;
    private HashMap<String,HealthPack> healthPacks = new HashMap<String, HealthPack>();

    public HealthPackManager(HealthPacks pl) {
        this.pl = pl;
        init();
    }
    private void init(){
        if(pl.getConfig().contains("MedPacks")){
            int toShow = 0;
            for(String s : pl.getConfig().getConfigurationSection("MedPacks").getKeys(false)){
                HealthPack hp = new HealthPack(pl,SUtils.stringAsLocation(pl.getConfig().getString("MedPacks."+s+".Location")),pl.getConfig().getInt("MedPacks."+s+".Timer"));
                healthPacks.put(s,hp);
                toShow++;
                }
            pl.getServer().getConsoleSender().sendMessage("[HealthPacks] Found a total of "+toShow+" med packs!");
        }
    }
    public void addHealthPack(Player sender, String name, Location location, Integer time){
        if(healthPacks.containsKey(name)){
            sender.sendMessage(ChatColor.RED+"A medpack already exists with this name!");
            return;
        }
        HealthPack hp = new HealthPack(pl,location,time);
        healthPacks.put(name,hp);
        pl.getConfig().set("MedPacks."+name+".Location",SUtils.locationAsString(location));
        pl.getConfig().set("MedPacks."+name+".Timer",time);
        pl.saveConfig();
        sender.sendMessage(ChatColor.GREEN+"Created the med pack with success!");
    }
    public void removeHealthPack(Player sender,String name){
        if(healthPacks.containsKey(name)){
            healthPacks.get(name).shutDown();
            healthPacks.remove(name);
            pl.getConfig().set("MedPacks."+name,null);
            pl.saveConfig();
            sender.sendMessage(ChatColor.GREEN+"Deleted the medpack with success!");
            return;
        }
        sender.sendMessage(ChatColor.RED+"No medpack found with this name!");
    }
    public void clearMedPacks(){
        for(String s : healthPacks.keySet()){
            healthPacks.get(s).clearEntity();
        }
    }
    public HashMap<String,HealthPack> getHealthPacks(){
        return healthPacks;
    }
}
