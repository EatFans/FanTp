package top.eatfan.fanTp.linstener;

import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import top.eatfan.fanTp.FanTp;
import top.eatfan.fanTp.event.TeleportRequestSendEvent;

import java.util.ArrayList;
import java.util.List;

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

        // 创建传送请求
        plugin.getTeleportRequestManager().addRequest(sender,targetPlayer);

        sender.sendMessage(ChatColor.translateAlternateColorCodes('&',"&a已经向 &f&l" + targetPlayer.getName() + " &a发送了传送请求！"));

        targetPlayer.sendMessage(" ");
        targetPlayer.sendMessage(ChatColor.translateAlternateColorCodes('&',"&a玩家 &f&l" + sender.getName() + " &a请求传送到你的位置，是否接受？"));
        targetPlayer.sendMessage(" ");

        TextComponent space = new TextComponent("  "); // 两个空格
        TextComponent agree = new TextComponent("[ 同意 ]");
        agree.setColor(net.md_5.bungee.api.ChatColor.GREEN);
        agree.setBold(true);
        agree.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND,"/tpa yes"));
        agree.setHoverEvent(new HoverEvent(
                HoverEvent.Action.SHOW_TEXT,
                new ComponentBuilder("点击以接受传送请求").color(net.md_5.bungee.api.ChatColor.YELLOW).create()
        ));
        TextComponent deny = new TextComponent("[ 拒绝 ]");
        deny.setColor(net.md_5.bungee.api.ChatColor.RED);
        deny.setBold(true);
        deny.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND,"/tpa no"));
        deny.setHoverEvent(new HoverEvent(
                HoverEvent.Action.SHOW_TEXT,
                new ComponentBuilder("点击以拒绝传送请求").color(net.md_5.bungee.api.ChatColor.YELLOW).create()
                ));

        TextComponent fullMessage = new TextComponent();
        fullMessage.addExtra(space);
        fullMessage.addExtra(space);
        fullMessage.addExtra(agree);
        fullMessage.addExtra(space);
        fullMessage.addExtra(space);
        fullMessage.addExtra(deny);

        targetPlayer.spigot().sendMessage(fullMessage);
        targetPlayer.sendMessage(" ");
    }

}
