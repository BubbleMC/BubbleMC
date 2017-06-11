/*
Bubble Copyright © 2017 Il'ya Semyonov
License: https://www.gnu.org/licenses/gpl-3.0.en.html
 */
package ru.Marshal.Bubble;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.bukkit.Bukkit;


class Worker{
    static void doTask(){
        try{
            PreparedStatement sql = DataBase.connection.prepareStatement("SELECT " + Config.columnCmd + ","
                                                                                        + Config.columnId + " FROM "
                                                                                        + Config.table);
            ResultSet task = sql.executeQuery();

            while(task.next()){
                if(removeTask(task.getInt(Config.columnId))){
                    doCmd(task.getString(Config.columnCmd));
                }
            }

            sql.close();
            task.close();
        }catch(SQLException e){
            Logger.info( "Cant get tasks from database");
        }
    }


    private static boolean removeTask(int id){
        try{
            PreparedStatement sql = DataBase.connection.prepareStatement("DELETE FROM " + Config.table + " WHERE "
                                                                                            + Config.columnId + "=?");
            sql.setInt(1, id);
            sql.executeUpdate();
            sql.close();
        }catch(SQLException e){
            Logger.info( "Cant remove task from database ");

            return false;
        }
        return true;
    }


    private static void doCmd(String cmd){
        Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), cmd);
    }
}
