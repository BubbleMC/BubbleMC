/*
Bubble Copyright © 2017 Il'ya Semyonov
License: https://www.gnu.org/licenses/gpl-3.0.en.html
 */
package ru.Marshal.Bubble;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.scheduler.BukkitRunnable;


public class main extends JavaPlugin implements Listener{

    public FileConfiguration config = getConfig();
    public static main plugin;
    public static Connection connection;
    public static String prefix = "[Bubble] ";


    @Override
    public void onEnable(){
        plugin = this;

        config.addDefault("database.host", "localhost");
        config.addDefault("database.port", 3306);
        config.addDefault("database.user", "root");
        config.addDefault("database.password", "root");
        config.addDefault("database.name", "bubble");

        config.addDefault("struct.table", "bubble_task");
        config.addDefault("struct.columnCmd", "task_cmd");
        config.addDefault("struct.columnId", "id");

        config.addDefault("basic.timer", 15);

        config.options().copyDefaults(true);
        saveConfig();

        getServer().getPluginManager().registerEvents(this, this);
        openConnection();

        try {
            if(connection != null && !connection.isClosed()){
                Bukkit.getServer().getLogger().info(prefix + "Connected to DB");
            } else{
                Bukkit.getServer().getLogger().info(prefix + "No connected to DB");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        int timer = config.getInt("basic.timer") * 20;

        BukkitRunnable scheduler = new BukkitRunnable() {
            @Override
            public void run(){
                worker.doTask();
            }
        };

        scheduler.runTaskTimer(this, 0, timer);
    }


    public void onDisable(){
        try {
            if(connection != null || !connection.isClosed()){
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            Bukkit.getServer().getLogger().info(prefix + "Cant disconnect to database");
        }
    }


    public static void openConnection(){
        try {
            connection = DriverManager.getConnection("jdbc:mysql://" +
                            plugin.getConfig().getString("database.host") + ":" +
                            plugin.getConfig().getInt("database.port") + "/" +
                            plugin.getConfig().getString("database.name"),
                            plugin.getConfig().getString("database.user"),
                            plugin.getConfig().getString("database.password"));
        } catch (Exception e){
            e.printStackTrace();
            Bukkit.getServer().getLogger().info(prefix + "Cant connect to database");
        }
    }


    public static void checkConnection(){
        try {
            if (connection == null || connection.isClosed()){
                openConnection();
                Bukkit.getServer().getLogger().info(prefix + "Reconnected to DB");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            Bukkit.getServer().getLogger().info(prefix + "Cant reconnect to database");
        }
    }


}
