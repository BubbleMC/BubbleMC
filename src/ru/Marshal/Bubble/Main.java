/*
Bubble Copyright © 2017 Il'ya Semyonov
License: https://www.gnu.org/licenses/gpl-3.0.en.html
 */
package ru.Marshal.Bubble;


import java.sql.SQLException;

import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;


public class Main extends JavaPlugin implements Listener{
    static Main plugin;
    static boolean check = false;

    @Override
    public void onEnable(){
        plugin = this;

        getServer().getPluginManager().registerEvents(this, this);

        Config.createConfig();
        Config.loadConfig();

        DataBase.openConnection();

        int timer = Config.timer * 20;

        BukkitRunnable scheduler = new BukkitRunnable(){
            @Override
            public void run(){
                if(!check){
                    DataBase.checkConnection();
                    Worker.doTask();
                }
            }
        };

        scheduler.runTaskTimer(this, 0, timer);
    }


    public void onDisable(){
        try{
            if(DataBase.connection != null || !DataBase.connection.isClosed()){
                DataBase.connection.close();
            }
        }catch(SQLException e){
            e.printStackTrace();
            Logger.info( "Cant disconnect from database");
        }
    }
}
