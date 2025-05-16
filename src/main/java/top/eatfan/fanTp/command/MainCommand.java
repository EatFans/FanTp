package top.eatfan.fanTp.command;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import top.eatfan.fanTp.FanTp;

public class MainCommand implements CommandExecutor {

    private final FanTp plugin;

    public MainCommand(FanTp plugin){
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        // 检查指令是谁发出的
        if (!(commandSender instanceof Player)){
            commandSender.sendMessage(ChatColor.RED + "This command is only allowed for players to use! Not available in console!");
            return true;
        }

        // 如果是玩家
        if (commandSender instanceof Player) {
            Player player = (Player) commandSender;

            // 检查玩家是否有权限
            if (!player.hasPermission("fantp.tp")){
                player.sendMessage(ChatColor.RED + "没有权限！");
                return true;
            }
            // 打开传送菜单
            Inventory menu = plugin.getMenu();
            if (menu != null) {
                player.sendMessage(ChatColor.GREEN + "你打开了传送菜单！");
                player.openInventory(menu);
            }
        }

        return true;
    }
}
