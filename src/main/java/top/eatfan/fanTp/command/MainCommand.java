package top.eatfan.fanTp.command;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import top.eatfan.fanTp.FanTp;
import top.eatfan.fanTp.core.Menu;
import top.eatfan.fanTp.event.TeleportAgreeEvent;
import top.eatfan.fanTp.event.TeleportDenyEvent;

/**
 * 主要的指令处理器类
 */
public class MainCommand implements CommandExecutor {

    private final FanTp plugin;

    public MainCommand(FanTp plugin){
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {

        // 检查指令是谁发出的
        if (!(commandSender instanceof Player)){
            commandSender.sendMessage(ChatColor.RED + "This command is only allowed for players to use! Not available in console!");
            return true;
        }

        Player player = (Player) commandSender;

        if (args.length == 0){
            // 检查玩家是否有权限
            if (!player.hasPermission("fantp.tp")){
                player.sendMessage(ChatColor.RED + "没有权限！");
                return true;
            }
            // 新创一个自己的传送菜单，在菜单关闭时候删除
            Menu menu = new Menu();
            player.sendMessage(ChatColor.GREEN + "你打开了传送菜单！");
            plugin.getMenuManager().setPlayerMenu(player,menu);
            menu.open(player);
        } else if (args.length == 1){
            if (args[0].equals("yes")){
                plugin.getLogger().info("Player "+player.getName() + " agree teleport request.");
                TeleportAgreeEvent teleportAgreeEvent = new TeleportAgreeEvent(player);
                Bukkit.getPluginManager().callEvent(teleportAgreeEvent);
//                player.sendMessage("同意");
            } else if (args[0].equals("no")){
                plugin.getLogger().info("Player "+player.getName() + " deny teleport request.");
                TeleportDenyEvent teleportDenyEvent = new TeleportDenyEvent(player);
                Bukkit.getPluginManager().callEvent(teleportDenyEvent);
//                player.sendMessage("拒绝");
            }

        }

        return true;
    }
}
