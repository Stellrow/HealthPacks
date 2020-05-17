package me.Stellrow.HealthPacks;

import org.bukkit.Bukkit;
import org.bukkit.Location;

public class SUtils {
    public static String locationAsString(Location location){
        return ""+location.getWorld().getName()+" "+location.getBlockX()+" "+location.getBlockY()+" "+location.getBlockZ();
    }
    public static Location stringAsLocation(String location){
        String[] raw = location.split(" ");
        Location toRet = new Location(Bukkit.getWorld(raw[0]),asDouble(raw[1]),asDouble(raw[2]),asDouble(raw[3]));
        return toRet;
    }
    public static Double asDouble(String s){
        try{
            return Double.parseDouble(s);
        }catch (IllegalArgumentException ex){
            Bukkit.getServer().getConsoleSender().sendMessage("Found illegal argument while parsing a double,a location is wrongly saved!");
            return 0.0;
        }
    }
    public static Integer asInt(String s){
        try{
            return Integer.parseInt(s);
        }catch (IllegalArgumentException ex){
            Bukkit.getServer().getConsoleSender().sendMessage("Found illegal argument while parsing a timer! Not a number!");
            return 0;
        }
    }
}
