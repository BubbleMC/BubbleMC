/*
Bubble Copyright © 2017 Il'ya Semyonov
License: https://www.gnu.org/licenses/gpl-3.0.en.html
 */
package ru.Marshal.Bubble;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


class DataBase{
    static Connection connection;


    static void openConnection(){
        try{
            connection = DriverManager.getConnection("jdbc:mysql://" + Config.host + ":" + Config.port + "/" +
                                                                        Config.name, Config.user, Config.password);
        }catch(Exception e){
            Logger.info("Cant connect to database");
        }
    }


    static void checkConnection(){
        Main.check = true;

        new Thread(() -> {
            try{
                if(connection == null || connection.isClosed()){
                    Logger.info("Try to reconnect to database");
                    int delay = Config.delay * 1000;

                    while(connection == null || connection.isClosed()){
                        openConnection();
                        try{
                            Thread.sleep(delay);
                        }catch(InterruptedException e){
                            e.printStackTrace();
                        }
                    }
                    Logger.info("Succeccfully reconnected to database");
                }
            }catch(SQLException e){
                e.printStackTrace();
                Logger.info("Cant check connection with database");
            }finally{
                Main.check = false;
            }
        }).start();
    }
}
