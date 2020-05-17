package me.Stellrow.HealthPacks;

import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

public class HealthPack implements Listener {
    private final HealthPacks pl;
    private Location location;
    private int timer;
    private Entity item;

    public HealthPack(HealthPacks pl, Location location, Integer timer){
        this.pl = pl;
        this.location=location.add(0.4,0,0.4);
        this.timer=timer;
        init();
    }
    private void init(){
        pl.getServer().getPluginManager().registerEvents(this,pl);
        spawnMedPack(1);
    }
    public void spawnMedPack(Integer delay){
        new BukkitRunnable(){

            @Override
            public void run() {
                item = location.getWorld().dropItem(location,pl.getMedpack());
                item.setGravity(false);
                forceGravity(item);
            }
        }.runTaskLater(pl,delay*20);
    }
    private void forceGravity(Entity toForce){
        new BukkitRunnable(){
            Vector v = new Vector(0,0,0);

            @Override
            public void run() {
                if(!toForce.isValid()){
                    this.cancel();
                    return;
                }
                toForce.setVelocity(v);
            }
        }.runTaskTimer(pl,0,10);
    }
    public void shutDown(){
        EntityPickupItemEvent.getHandlerList().unregister(this);
        item.remove();
    }
    @EventHandler
    public void onPickUp(EntityPickupItemEvent event){
    if(event.getItem().equals(item)){
        spawnMedPack(timer);
        event.setCancelled(true);
        item.remove();
        if(!(event.getEntity()instanceof Player)){
            return;
        }
        tryHeal((Player) event.getEntity());
    }
    }
    public void clearEntity(){
        item.remove();
    }
    public void tryHeal(Player p){
        int toHeal = pl.getConfig().getInt("General.healthToHeal");
        if(p.getHealth()+toHeal>20){
            playSound(p.getLocation());
            p.setHealth(20);
            return;
        }
        playSound(p.getLocation());
        p.setHealth(p.getHealth()+toHeal);
    }
    private void playSound(Location loc){
        Sound toPlay = Sound.valueOf(pl.getConfig().getString("General.soundToPlay.sound"));
        loc.getWorld().playSound(loc,toPlay,Float.parseFloat(pl.getConfig().getString("General.soundToPlay.volume")),Float.parseFloat(pl.getConfig().getString("General.soundToPlay.pitch")));
    }

}
