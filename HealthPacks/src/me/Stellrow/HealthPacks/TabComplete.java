package me.Stellrow.HealthPacks;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class TabComplete implements TabCompleter {
    private final HealthPacks pl;
    private final static List<String> def = Arrays.asList("add","remove");
    private final static List<String> temp = new ArrayList<String>();
    //Create
    private final static List<String> name = Arrays.asList("medPackName(Identifier)");
    private final static List<String> timer = Arrays.asList("time(Seconds to respawn)");

    public TabComplete(HealthPacks pl) {
        this.pl = pl;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String sa, String[] args) {

        if(args.length>=1){
            if(args[0].equalsIgnoreCase("add")){
                switch(args.length){
                    case 2:
                        return name;
                    case 3:
                        return timer;
                    default:
                        return Collections.emptyList();
                }
            }
            if(args[0].equalsIgnoreCase("remove")){
                switch (args.length){
                    case 2:
                        for(String s : pl.getHealthPackManager().getHealthPacks().keySet()){
                            temp.add(s);
                        }
                        return temp;
                    default:
                        return Collections.emptyList();
                }
            }
        }
        return def;
    }
}
