/*
Bubble Copyright © 2017 Il'ya Semyonov
License: https://www.gnu.org/licenses/gpl-3.0.en.html
 */
package ru.Marshal.Bubble;


import org.bukkit.Bukkit;


class Logger{
    static void info(String msg){
        Bukkit.getServer().getLogger().info(Config.prefix + msg);
    }
}
