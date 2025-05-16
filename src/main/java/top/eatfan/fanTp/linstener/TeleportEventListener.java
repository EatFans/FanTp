package top.eatfan.fanTp.linstener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import top.eatfan.fanTp.FanTp;
import top.eatfan.fanTp.event.TeleportRequestSendEvent;

/**
 * 传送事件监听器
 *
 * @author Fan
 */
public class TeleportEventListener implements Listener {

    private final FanTp plugin;

    public TeleportEventListener(FanTp plugin){
        this.plugin = plugin;
    }

    /**
     * 当传送请求消息发送
     * @param event 传送请求消息发送事件
     */
    @EventHandler
    public void onTeleportRequestSend(TeleportRequestSendEvent event){
        Player sender = event.getSender();
        Player targetPlayer = event.getTargetPlayer();

        sender.sendMessage("你正在给"+targetPlayer.getName()+"发送传送请求");
        targetPlayer.sendMessage(sender.getName() + "请求传送到你这里");
    }
}
