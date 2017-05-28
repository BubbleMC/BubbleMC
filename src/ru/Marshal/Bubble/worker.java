/*
Bubble Copyright © 2017 Il'ya Semyonov
License: https://www.gnu.org/licenses/gpl-3.0.en.html
 */
package ru.Marshal.Bubble;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;


public class worker {
    public static FileConfiguration config = main.plugin.getConfig();
    public static String table = config.getString("struct.table");
    public static String columnCmd = config.getString("struct.columnCmd");
    public static String columnId = config.getString("struct.columnId");


    public static void doTask(){
        main.checkConnection();
        try {
            PreparedStatement sql = main.connection.prepareStatement("SELECT " + columnCmd + "," + columnId + " FROM " + table);
            ResultSet task = sql.executeQuery();

            while(task.next()){
                if(removeTask(task.getInt(columnId))) {
                    Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), task.getString(columnCmd));
                }
            }

            sql.close();
            task.close();
        } catch (SQLException e) {
            e.printStackTrace();
            Bukkit.getServer().getLogger().info(main.prefix + "Cant get tasks from database");
        }
    }


    public static boolean removeTask(int id){
        main.checkConnection();
        try {
            PreparedStatement sql = main.connection.prepareStatement("DELETE FROM " + table + " WHERE " + columnId + "=?");
            sql.setInt(1, id);
            sql.executeUpdate();
            sql.close();
        } catch (SQLException e) {
            e.printStackTrace();
            Bukkit.getServer().getLogger().info(main.prefix + "Cant remove task from database ");

            return false;
        }

        return true;
    }


}
