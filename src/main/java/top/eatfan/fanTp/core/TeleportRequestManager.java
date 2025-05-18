package top.eatfan.fanTp.core;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import top.eatfan.fanTp.FanTp;

import java.util.HashMap;
import java.util.Map;

/**
 * 传送请求管理器
 *
 * @author Fan
 */
public class TeleportRequestManager {

    private final Map<Player,Player> teleportRequests = new HashMap<>();  // 接受者为key，发送者为value

    private FanTp plugin;

    public TeleportRequestManager(FanTp plugin){
        this.plugin = plugin;
    }

    /**
     * 添加传送请求
     * @param sender 发送者
     * @param targetPlayer 目标接受者
     */
    public void addRequest(Player sender, Player targetPlayer){
        teleportRequests.put(targetPlayer,sender);

        // 设置请求失效时间 默认二分钟
        Bukkit.getScheduler().runTaskLater(plugin, () ->{
            if (hasRequest(targetPlayer)){
                Player requester = getSender(targetPlayer);
                // 判断发送者是否存在是否在线
                if (requester != null && requester.isOnline()){
                    requester.sendMessage(ChatColor.translateAlternateColorCodes('&',
                            plugin.getConfigManager().getLangConfig().getTpRequestTimeoutA()));
                }
                // 判断目标玩家是否在线
                if (targetPlayer.isOnline()){
                    targetPlayer.sendMessage(ChatColor.translateAlternateColorCodes('&',
                            plugin.getConfigManager().getLangConfig().getTpRequestTimeoutB()));
                }
                // 移除请求
                removeRequest(targetPlayer);
            }
        },plugin.getConfigManager().getConfig().getRequestTimeout() * 20);
    }

    /**
     * 删除者
     * @param targetPlayer
     */
    public void removeRequest(Player targetPlayer){
        teleportRequests.remove(targetPlayer);
    }

    /**
     * 获取请求发送者
     * @param targetPlayer 请求接受者
     * @return 返回请求发送者
     */
    public Player getSender(Player targetPlayer){
        return teleportRequests.get(targetPlayer);
    }

    /**
     * 检查目标玩家是否已经存在传送请求
     * @param targetPlayer 接受目标者
     * @return 如果有就返回true，否则就返回false
     */
    public boolean hasRequest(Player targetPlayer){
        return teleportRequests.containsKey(targetPlayer);
    }
}
