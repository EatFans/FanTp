package top.eatfan.fanTp.event;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

/**
 * 玩家拒绝传送请求事件
 * 传送请求接受者，如果拒绝了传送请求，就触发这个事件
 *
 * @author Fan
 */
public class TeleportDenyEvent extends Event {
    private static final HandlerList handlers = new HandlerList();
    private final Player targetPlayer;
    public TeleportDenyEvent(Player targetPlayer){
        this.targetPlayer = targetPlayer;
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
