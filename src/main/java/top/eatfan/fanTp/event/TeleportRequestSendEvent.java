package top.eatfan.fanTp.event;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

/**
 * 传送请求发送事件
 * 当玩家点击传送菜单里某个玩家后，就触发该事件
 *
 * @author Fan
 */
public class TeleportRequestSendEvent extends Event {
    private static final HandlerList handlers = new HandlerList();

    private final Player sender;
    private final Player targetPlayer;
    public TeleportRequestSendEvent(Player sender, Player targetPlayer){
        this.sender = sender;
        this.targetPlayer = targetPlayer;
    }

    public Player getSender(){
        return sender;
    }

    public Player getTargetPlayer(){
        return targetPlayer;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
