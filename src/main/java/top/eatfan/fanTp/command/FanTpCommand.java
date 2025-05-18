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
            commandSender.sendMessage(ChatColor.translateAlternateColorCodes('&',
                    plugin.getConfigManager().getLangConfig().getNoPermission()));
            return true;
        }
        if (args.length == 1){
            switch (args[0]){
                case "reload":
                    plugin.getConfigManager().reload();
                    commandSender.sendMessage(ChatColor.translateAlternateColorCodes('&',
                            plugin.getConfigManager().getLangConfig().getReload()));

                    String prefix = plugin.getConfigManager().getLangConfig().getPrefix();
                    plugin.getLogger().info(prefix);
                    break;
                default:
                    break;
            }
        }
        return true;
    }
}
