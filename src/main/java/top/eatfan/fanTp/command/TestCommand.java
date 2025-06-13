package top.eatfan.fanTp.command;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import top.eatfan.fanTp.FanTp;
import top.eatfan.fanTp.event.TeleportAgreeEvent;
import top.eatfan.fanTp.event.TeleportDenyEvent;
import top.eatfan.fanTp.menu.MainMenu;
import top.eatfan.fanTp.menu.TeleportPlayerMenu;

public class TestCommand implements CommandExecutor {
    private final FanTp plugin;

    public TestCommand(FanTp plugin){
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
                player.sendMessage(ChatColor.translateAlternateColorCodes('&',
                        plugin.getConfigManager().getLangConfig().getNoPermission()));
                return true;
            }
            // 新创一个自己的主菜单，在菜单关闭时候删除
            MainMenu mainMenu = new MainMenu();
            player.sendMessage(ChatColor.translateAlternateColorCodes('&',
                    "你成功打开了主菜单"));
            plugin.getMenuManager().setPlayerMenu(player, mainMenu);
            mainMenu.open(player);
        }

        return true;
    }
}
