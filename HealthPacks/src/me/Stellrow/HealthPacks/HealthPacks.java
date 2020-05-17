package me.Stellrow.HealthPacks;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.lang.reflect.Field;
import java.util.UUID;

public class HealthPacks extends JavaPlugin {
    private HealthPackManager hpm;
    private ItemStack medpack;
    public void onEnable(){
        loadConfig();
        buildMedPack();
        getCommand("healthpacks").setExecutor(new HealthPacksCommands(this));
        getCommand("healthpacks").setTabCompleter(new TabComplete(this));
        hpm=new HealthPackManager(this);
    }
    public void onDisable(){
    hpm.clearMedPacks();
    }

    private void loadConfig(){
        getConfig().options().copyDefaults(true);
        saveConfig();
    }
    private void buildMedPack(){
        ItemStack head = new ItemStack(Material.PLAYER_HEAD, 1);
        GameProfile skin1803103601 = new GameProfile(UUID.fromString("f017c5f5-e70e-4b16-9cb7-a82fcb1af9c3"), "skin1803103601");
        skin1803103601.getProperties().put("textures", new Property("textures", "ewogICJ0aW1lc3RhbXAiIDogMTU4OTcyOTE0Nzk5MiwKICAicHJvZmlsZUlkIiA6ICJhNmE3MzI2NjZhZTI0YjIwYWQyNmIzYWZkZWZjNmM1MCIsCiAgInByb2ZpbGVOYW1lIiA6ICJNeXN0aWNHYW1lck1hbiIsCiAgInNpZ25hdHVyZVJlcXVpcmVkIiA6IHRydWUsCiAgInRleHR1cmVzIiA6IHsKICAgICJTS0lOIiA6IHsKICAgICAgInVybCIgOiAiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS9lNTgwYzBlYWYzZTFkMGFlNWE0MmQ5NjY0MWJlZGMyNTFhZGQ2ZjA5NzI1NDI3OWJhYTJmNjI5OWNjZTYzMjcwIgogICAgfQogIH0KfQ==", "rXy8bq+RjMJiawajWDm2+N7jV+kCGwqYSmOLth5A7is0uyR8Aqe9DDcxUdAAdHJd+JOx5/KY9EmO8ExobQ0kt2sarJLlnkVQa3o+gEeadFj9DA6VwsWWki2qqAvgZQsQOe3w41UF9M3C96Z/EIBR8hZp8kG73WKwA3xQ9b6juk5PxDSGEuJbnpYZW5fxraOSmElMFqWJelU22z15I1nqk6aWzwa9WHh/ywX0Hy4BvqWvwqNhBoxHREwQ8TOLyairKxlo9YX/Q+0OaRHa120vynLKH2jtBH3qD2XUugnopyN4eoCos2uOkFE2T4zeBKkUSE10xNuMpowKXZlmXEi4qGh+KGcY1b284vvXX4Muztx8kxp+xSe5vAxVGSVqkRR7gt0yK1DCxlkgBCWERoNoBR81oNqON6Ild/rdhQBEZsTW/SuCsPTQa+GR6uOeNadyXSM6tSG8CAxaon0urrbNoy9rud3dQCrcwbkpzhdsQwaROWGhsdUaHfrSZ6z7wxHQTiAC8QLiy4NukyHfp5w3vMbUbs+6LCT774MdSo4hioCKalwHXfaDXHrXxPKjsfYCmDTRehBBioQpBakIv4vb3upWdo7WFgjzau83PljVP4lYfqaO3bktZtcLWT0VtYAbsbXH6JROrhNH1LIeGomUcP5ZjJeTYCc9IqbbvEV1oyQ="));
        SkullMeta headMeta = (SkullMeta) head.getItemMeta();
        try {
            Field profileField = headMeta.getClass().getDeclaredField("profile");
            profileField.setAccessible(true);
            profileField.set(headMeta, skin1803103601);

        } catch (IllegalArgumentException | NoSuchFieldException | SecurityException | IllegalAccessException error) {
            error.printStackTrace();
        }
        headMeta.setDisplayName(ChatColor.GREEN+"MedPack");
        head.setItemMeta(headMeta);
        medpack=head;
    }
    public ItemStack getMedpack(){
        return medpack;
    }
    public HealthPackManager getHealthPackManager(){
        return hpm;
    }

}
