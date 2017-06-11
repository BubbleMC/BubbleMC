/*
Bubble Copyright © 2017 Il'ya Semyonov
License: https://www.gnu.org/licenses/gpl-3.0.en.html
 */
package ru.Marshal.Bubble;


import org.bukkit.configuration.file.FileConfiguration;


class Config{
    private static FileConfiguration config = Main.plugin.getConfig();

    static String host;
    static int port;
    static String user;
    static String password;
    static String name;

    static String table;
    static String columnCmd;
    static String columnId;

    static String prefix;
    static int timer;
    static int delay;

    static void createConfig(){
        config.addDefault("database.host", "localhost");
        config.addDefault("database.port", 3306);
        config.addDefault("database.user", "root");
        config.addDefault("database.password", "root");
        config.addDefault("database.name", "bubble");

        config.addDefault("struct.table", "bubble_task");
        config.addDefault("struct.columnCmd", "task_cmd");
        config.addDefault("struct.columnId", "id");

        config.addDefault("basic.prefix", "[Bubble] ");
        config.addDefault("basic.timer", 15);
        config.addDefault("basic.delay", 5);

        config.options().copyDefaults(true);
        Main.plugin.saveConfig();
    }


    static void loadConfig(){
        host = config.getString("database.host");
        port = config.getInt("database.port");
        user = config.getString("database.user");
        password = config.getString("database.password");
        name = config.getString("database.name");

        table = config.getString("struct.table");
        columnCmd = config.getString("struct.columnCmd");
        columnId = config.getString("struct.columnId");

        prefix = config.getString("basic.prefix");
        timer = config.getInt("basic.timer");
        delay = config.getInt("basic.delay");
    }
}
