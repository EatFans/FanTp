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
import top.eatfan.fanTp.core.TeleportRequestManager;
import top.eatfan.fanTp.event.TeleportAgreeEvent;
import top.eatfan.fanTp.event.TeleportDenyEvent;
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

        // 创建传送请求
        plugin.getTeleportRequestManager().addRequest(sender,targetPlayer);

        sender.sendMessage(ChatColor.translateAlternateColorCodes('&',
                plugin.getConfigManager().getLangConfig().getSendTpRequest()));

        targetPlayer.sendMessage(" ");
        targetPlayer.sendMessage(ChatColor.translateAlternateColorCodes('&',
                plugin.getConfigManager().getLangConfig().getPrefix()
                +"&a" + sender.getName() +
                plugin.getConfigManager().getLangConfig().getReceiveTpRequest()));
        targetPlayer.sendMessage(" ");

        TextComponent space = new TextComponent("  "); // 两个空格
        TextComponent agree = new TextComponent(plugin.getConfigManager().getLangConfig().getChatAgreeButton());
        agree.setColor(net.md_5.bungee.api.ChatColor.GREEN);
        agree.setBold(true);
        agree.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND,"/tpa yes"));
        agree.setHoverEvent(new HoverEvent(
                HoverEvent.Action.SHOW_TEXT,
                new ComponentBuilder(
                        plugin.getConfigManager().getLangConfig().getChatAgreeButtonHover()
                ).color(net.md_5.bungee.api.ChatColor.YELLOW).create()
        ));
        TextComponent deny = new TextComponent(plugin.getConfigManager().getLangConfig().getChatDenyButton());
        deny.setColor(net.md_5.bungee.api.ChatColor.RED);
        deny.setBold(true);
        deny.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND,"/tpa no"));
        deny.setHoverEvent(new HoverEvent(
                HoverEvent.Action.SHOW_TEXT,
                new ComponentBuilder(
                        plugin.getConfigManager().getLangConfig().getChatDenyButtonHover()
                ).color(net.md_5.bungee.api.ChatColor.YELLOW).create()
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

    /**
     * 玩家同意传送请求事件处理
     * @param event 传送请求同意事件
     */
    @EventHandler
    public void onPlayerAgreeTeleportRequest(TeleportAgreeEvent event){
        Player targetPlayer = event.getTargetPlayer();

        // 从传送请求管理获取传送请求发送者
        TeleportRequestManager teleportRequestManager = plugin.getTeleportRequestManager();
        Player sender = teleportRequestManager.getSender(targetPlayer);

        // 检查sender是否存在，如果不存在，就表明没有这个传送请求
        if (sender == null){
            targetPlayer.sendMessage(ChatColor.translateAlternateColorCodes('&',
                    plugin.getConfigManager().getLangConfig().getNoTpRequest()));
            return;
        }

        // 将发送者传送到接受者
        sender.sendMessage(ChatColor.translateAlternateColorCodes('&',
                plugin.getConfigManager().getLangConfig().getOtherAgreeTpRequest()));
        sender.teleport(targetPlayer);

        // 删除传送请求记录
        targetPlayer.sendMessage(ChatColor.translateAlternateColorCodes('&',
                plugin.getConfigManager().getLangConfig().getAgreeTpRequest()));
        teleportRequestManager.removeRequest(targetPlayer);
    }

    /**
     * 玩家拒绝传送请求事件处理
     * @param event 传送拒绝请求
     */
    @EventHandler
    public void onPlayerDenyTeleportRequest(TeleportDenyEvent event){
        Player targetPlayer = event.getTargetPlayer();

        // 从传送请求管理器获取传送请求发送者
        TeleportRequestManager teleportRequestManager = plugin.getTeleportRequestManager();
        Player sender = teleportRequestManager.getSender(targetPlayer);

        // 检查sender是否存在，如果不存在，就表明没有这个传送请求
        if (sender == null){
            targetPlayer.sendMessage(ChatColor.translateAlternateColorCodes('&',
                    plugin.getConfigManager().getLangConfig().getNoTpRequest()));
            return;
        }

        // 发送消息
        sender.sendMessage(ChatColor.translateAlternateColorCodes('&',
                plugin.getConfigManager().getLangConfig().getOtherDenyTpRequest()));
        targetPlayer.sendMessage(ChatColor.translateAlternateColorCodes('&',
                plugin.getConfigManager().getLangConfig().getDenyTpRequest()));
        // 删除传送请求记录
        teleportRequestManager.removeRequest(targetPlayer);

    }

}
