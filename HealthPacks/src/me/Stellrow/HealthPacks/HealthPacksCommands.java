package me.Stellrow.HealthPacks;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class HealthPacksCommands implements CommandExecutor {
    private final HealthPacks pl;

    public HealthPacksCommands(HealthPacks pl) {
        this.pl = pl;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String la, String[] args) {
        if(sender instanceof Player){
            Player p = (Player) sender;
            if(!p.hasPermission("healthpacks.admin")){
                p.sendMessage(ChatColor.RED+"No permission to use this command!");
                return true;
            }
            if(args.length==3&&args[0].equalsIgnoreCase("add")){
                pl.getHealthPackManager().addHealthPack(p,args[1],p.getLocation().add(0,1,0),SUtils.asInt(args[2]));
                return true;
            }
            if(args.length==2&&args[0].equalsIgnoreCase("remove")){
                pl.getHealthPackManager().removeHealthPack(p,args[1]);
                return true;
            }
        }else{
            sender.sendMessage(ChatColor.RED+"Not a player!");
        }


        return true;
    }
}
