package top.eatfan.fanTp;

import org.bukkit.plugin.java.JavaPlugin;
import top.eatfan.fanTp.command.MainCommand;
import top.eatfan.fanTp.core.MenuManager;
import top.eatfan.fanTp.core.TeleportRequestManager;
import top.eatfan.fanTp.linstener.InventoryEventListener;
import top.eatfan.fanTp.linstener.TeleportEventListener;

public final class FanTp extends JavaPlugin {
    private static FanTp instance;
    private MenuManager menuManager;
    private TeleportRequestManager teleportRequestManager;

    @Override
    public void onEnable() {
        getLogger().info("插件正在启动中...");
        instance = this;

        // 注册事件监听器到服务器上
        getServer().getPluginManager().registerEvents(new InventoryEventListener(this),this);
        getServer().getPluginManager().registerEvents(new TeleportEventListener(this),this);

        // 注册指令到服务器上
        MainCommand mainCommand = new MainCommand(this);
        getCommand("tpa").setExecutor(mainCommand);
        getCommand("t").setExecutor(mainCommand);

        menuManager = new MenuManager();
        teleportRequestManager = new TeleportRequestManager();


        getLogger().info("插件启动完毕");
    }

    @Override
    public void onDisable() {
        getLogger().info("插件正在关闭中...");

        getLogger().info("插件关闭成功！");

    }

    /***
     * 获取本插件的实例
     * @return 插件主类实例对象
     */
    public static FanTp getInstance() {
        return instance;
    }

    public MenuManager getMenuManager(){
        return menuManager;
    }

    public TeleportRequestManager getTeleportRequestManager(){
        return teleportRequestManager;
    }
}
