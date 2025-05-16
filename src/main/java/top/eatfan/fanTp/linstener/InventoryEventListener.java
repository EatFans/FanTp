package top.eatfan.fanTp.linstener;

import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import top.eatfan.fanTp.FanTp;
import top.eatfan.fanTp.Menu;

/**
 * 容器事件监听器
 * 用于处理本插件中菜单的各种操作事件
 *
 * @author Fan
 */
public class InventoryEventListener implements Listener {

    private final FanTp plugin;
    private boolean isEnableClick;

    public InventoryEventListener(FanTp plugin){
        this.plugin = plugin;
        isEnableClick = false;
    }

    /**
     * 当玩家打开菜单时候
     * @param event 容器被打开事件
     */
    @EventHandler
    public void OnPlayerOpenMenu(InventoryOpenEvent event){
        HumanEntity humanEntity = event.getPlayer();
        Inventory inventory = event.getInventory();
        // 为了防止有傻逼玩家的一些傻逼操作，这里就不要强制转换，检查后再转换，防止傻逼数据类型导致报错
        if (humanEntity instanceof Player){
            Player player = (Player) humanEntity;
            Menu menu = plugin.getMenuManager().getMenu(player);
            // 检查被打开的容器是不是本插件的菜单
            if (menu.getInventory().equals(inventory))
                isEnableClick = false;
            //
        }
    }

    /**
     * 当玩家关闭菜单
     * @param event 容器关闭事件
     */
    @EventHandler
    public void onPlayerCloseMenu(InventoryCloseEvent event){
        HumanEntity humanEntity = event.getPlayer();
        Inventory inventory = event.getInventory();
        if (humanEntity instanceof Player){
            Player player = (Player) humanEntity;
            Menu menu = plugin.getMenuManager().getMenu(player);
            if (menu != null){
                if (menu.getInventory().equals(inventory)){
                    isEnableClick = true;
                    // 删除菜单
                    plugin.getMenuManager().removeMenu(player);
                }
            }

        }
    }

    /**
     * 玩家想移动或点击物品
     * @param event 容器点击事件
     */
    @EventHandler
    public void onPlayerMoveOrClickItem(InventoryClickEvent event){
        Inventory inventory = event.getInventory(); // 实际的容器
        HumanEntity whoClicked = event.getWhoClicked();
        if (whoClicked instanceof Player){
            Player player = (Player) whoClicked;
            Menu menu = plugin.getMenuManager().getMenu(player);
            // 检查实际容器是不是对应的菜单容器
            if(menu.getInventory().equals(inventory)){
                if (!isEnableClick)
                    event.setCancelled(true); // 如果禁止点击了，就取消点击事件继续执行
            }
        }
    }

    /**
     * d
     * @param event
     */
    @EventHandler
    public void onPlayerClickMenuItem(InventoryClickEvent event){
        HumanEntity humanEntity = event.getWhoClicked();
        Inventory clickedInventory = event.getClickedInventory();
        ItemStack currentItem = event.getCurrentItem();
        if (humanEntity instanceof Player){
            Player player = (Player) humanEntity;


        }
    }
}
