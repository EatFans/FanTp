package top.eatfan.fanTp;

import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.java.JavaPlugin;
import top.eatfan.fanTp.command.MainCommand;

public final class FanTp extends JavaPlugin {
    private static FanTp instance;

    private Menu menu;

    @Override
    public void onEnable() {
        getLogger().info("插件正在启动中...");
        instance = this;

        MainCommand mainCommand = new MainCommand(this);
        getCommand("tpa").setExecutor(mainCommand);
        getCommand("t").setExecutor(mainCommand);

        menu = new Menu();

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

    public Inventory getMenu(){
        return null;
    }
}
