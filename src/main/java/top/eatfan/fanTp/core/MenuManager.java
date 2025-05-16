package top.eatfan.fanTp.core;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

/**
 * 菜单管理类
 *
 * @author Fan
 */
public class MenuManager {
    private final Map<Player, Menu> playerMenuMap = new HashMap<>();

    public void setPlayerMenu(Player player, Menu menu){
        playerMenuMap.put(player,menu);
    }

    public Menu getMenu(Player player){
        return playerMenuMap.get(player);
    }

    public void removeMenu(Player player){
        playerMenuMap.remove(player);
    }
}
