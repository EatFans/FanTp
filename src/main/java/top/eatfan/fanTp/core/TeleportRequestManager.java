package top.eatfan.fanTp.core;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

/**
 * 传送请求管理器
 *
 * @author Fan
 */
public class TeleportRequestManager {

    private final Map<Player,Player> teleportRequests = new HashMap<>();  // 接受者为key，发送者为value

    public TeleportRequestManager(){

    }

    /**
     * 添加传送请求
     * @param sender 发送者
     * @param targetPlayer 目标接受者
     */
    public void addRequest(Player sender, Player targetPlayer){
        teleportRequests.put(targetPlayer,sender);
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
}
