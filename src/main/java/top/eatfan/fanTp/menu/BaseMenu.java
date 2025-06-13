package top.eatfan.fanTp.menu;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

/**
 * 基础菜单类
 * 作为抽象基类
 *
 * @author Fan
 */
public abstract class BaseMenu {
    protected Inventory inventory;  // 菜单容器

    public BaseMenu(String menuName, int menuSize){
        inventory = Bukkit.createInventory(null,menuSize, ChatColor.translateAlternateColorCodes('&',menuName));

    }

    /**
     * 初始化菜单内容
     */
    protected abstract void initMenuContent();

    /**
     * 打开菜单
     * @param player 玩家
     */
    public abstract void open(Player player);


    /**
     * 创建带有名字的物品
     * @param item 物品
     * @param displayName 名字
     * @return 物品
     */
    protected ItemMeta createNamedItem(ItemStack item, String displayName) {
        ItemMeta meta = item.getItemMeta();
        if (meta != null) {
            meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', displayName));
        }
        return meta;
    }

    /**
     * 获取这个容器
     * @return 容器
     */
    public Inventory getInventory(){
        return inventory;
    }

    /**
     * 设置容器物品
     * @param inventory 容器
     */
    public void setInventory(Inventory inventory){
        this.inventory = inventory;
    }

}
