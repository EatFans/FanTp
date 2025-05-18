package top.eatfan.fanTp.command;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import top.eatfan.fanTp.FanTp;

public class FanTpCommand implements CommandExecutor {
    private final FanTp plugin;

    public FanTpCommand(FanTp plugin){
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        // 检查输入该指令的用户有没有权限
        if (!commandSender.hasPermission("fantp.admin")){
            commandSender.sendMessage(ChatColor.RED + "没有权限！");
            return true;
        }
        if (args.length == 1){
            switch (args[0]){
                case "reload":
                    plugin.getConfigManager().reload();
                    commandSender.sendMessage(ChatColor.GREEN + "配置文件已经重新加载！");

                    int requestTimeout = plugin.getConfigManager().getConfig().getRequestTimeout();
                    plugin.getLogger().info("请求超时时间："+ requestTimeout);

                    break;
                default:
                    break;
            }
        }
        return true;
    }
}
