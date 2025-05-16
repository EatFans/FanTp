package top.eatfan.fanTp;

import org.bukkit.plugin.java.JavaPlugin;

public final class FanTp extends JavaPlugin {
    private static FanTp instance;

    @Override
    public void onEnable() {
        getLogger().info("插件正在启动中...");
        instance = this;


        getLogger().info("插件启动完毕");
    }

    @Override
    public void onDisable() {
        getLogger().info("插件正在关闭中...");

        getLogger().info("插件关闭成功！");

    }

    public static FanTp getInstance() {
        return instance;
    }
}
