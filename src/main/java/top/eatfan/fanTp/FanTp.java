package top.eatfan.fanTp;

import org.bukkit.event.HandlerList;
import org.bukkit.plugin.java.JavaPlugin;
import top.eatfan.fanTp.command.FanTpCommand;
import top.eatfan.fanTp.command.MainCommand;
import top.eatfan.fanTp.core.ConfigManager;
import top.eatfan.fanTp.core.MenuManager;
import top.eatfan.fanTp.core.TeleportRequestManager;
import top.eatfan.fanTp.linstener.MenuEventListener;
import top.eatfan.fanTp.linstener.TeleportEventListener;

public final class FanTp extends JavaPlugin {
    private static FanTp instance;
    private MenuManager menuManager;
    private TeleportRequestManager teleportRequestManager;
    private ConfigManager configManager;

    @Override
    public void onEnable() {
        getLogger().info("插件正在启动中...");
        instance = this;

        configManager = new ConfigManager(this);

        // 注册事件监听器到服务器上
        getServer().getPluginManager().registerEvents(new MenuEventListener(this),this);
        getServer().getPluginManager().registerEvents(new TeleportEventListener(this),this);

        // 注册指令到服务器上
        MainCommand mainCommand = new MainCommand(this);
        FanTpCommand fanTpCommand = new FanTpCommand(this);
        getCommand("tpa").setExecutor(mainCommand);
        getCommand("t").setExecutor(mainCommand);
        getCommand("fantp").setExecutor(fanTpCommand);

        menuManager = new MenuManager();
        teleportRequestManager = new TeleportRequestManager(this);

        getLogger().info("插件启动完毕");

    }

    @Override
    public void onDisable() {
        getLogger().info("插件正在关闭中...");
        configManager.save();
        HandlerList.unregisterAll();
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

    public ConfigManager getConfigManager(){
        return configManager;
    }
}
