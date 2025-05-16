package top.eatfan.fanTp;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

/**
 * 传送列表菜单
 *
 * @author Fan
 */
public class Menu {
    private ItemStack decorativeBoard;  // 装饰板物品堆
    private ItemStack lastPageButton; // 上一页按钮物品堆
    private ItemStack nextPageButton; // 下一页按钮物品堆
    private ItemStack closeButton; // 关闭菜单按钮

    private final int LAST_PAGE_BUTTON_INDEX = 21;
    private final int CLOSE_BUTTON_INDEX = 22;
    private final int NEXT_PAGE_BUTTON_INDEX = 23;

    private Inventory inventory;

    public Menu(){
        inventory = Bukkit.createInventory(null,27, ChatColor.translateAlternateColorCodes('&',"&a&l传送列表"));
        decorativeBoard = new ItemStack(Material.BLACK_STAINED_GLASS_PANE,1);
        lastPageButton = new ItemStack(Material.GRAY_STAINED_GLASS_PANE,1);
        nextPageButton = new ItemStack(Material.GRAY_STAINED_GLASS_PANE,1);
        closeButton = new ItemStack(Material.RED_STAINED_GLASS_PANE,1);
    }

    /**
     * 初始化容器菜单内容
     */
    public void init(){
        ItemMeta decorativeBoardItemMeta = decorativeBoard.getItemMeta();
        decorativeBoardItemMeta.setDisplayName(" ");
        decorativeBoard.setItemMeta(decorativeBoardItemMeta);

        ItemMeta lastPageButtonItemMeta = lastPageButton.getItemMeta();
        lastPageButtonItemMeta.setDisplayName(ChatColor.RED +"上一页");
        lastPageButton.setItemMeta(lastPageButtonItemMeta);

        ItemMeta nextPageButtonItemMeta = nextPageButton.getItemMeta();
        nextPageButtonItemMeta.setDisplayName(ChatColor.RED + "下一页");
        nextPageButton.setItemMeta(nextPageButtonItemMeta);

        ItemMeta closeButtonItemMeta = closeButton.getItemMeta();
        closeButtonItemMeta.setDisplayName(ChatColor.RED + "关闭");
        closeButton.setItemMeta(closeButtonItemMeta);

        inventory.setItem(LAST_PAGE_BUTTON_INDEX,lastPageButton);
        inventory.setItem(NEXT_PAGE_BUTTON_INDEX,nextPageButton);
        inventory.setItem(CLOSE_BUTTON_INDEX,closeButton);

        for (int i = 18; i <= 20; i++)
            inventory.setItem(i, decorativeBoard);
        for (int i = 24; i <= 26; i++)
            inventory.setItem(i,decorativeBoard);
    }

    private ItemMeta setItemMeta(ItemStack itemStack,String name){
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&',name));
        return itemMeta;
    }

    public Inventory getInventory(){
        return inventory;
    }
}
