package top.eatfan.fanTp.linstener;

import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import top.eatfan.fanTp.FanTp;
import top.eatfan.fanTp.menu.*;

/**
 * 路径点传送菜单监听器
 */
public class WayPointTeleportMenuListener implements Listener {

    private final FanTp plugin;
    public WayPointTeleportMenuListener(FanTp plugin){
        this.plugin = plugin;
    }

    private WaypointTeleportMenu waypointTeleportMenu;

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

            BaseMenu menu = plugin.getMenuManager().getMenu(player);
            if (menu instanceof WaypointTeleportMenu)
                waypointTeleportMenu = (WaypointTeleportMenu) menu;

            if (waypointTeleportMenu != null){
                if (waypointTeleportMenu.getInventory().equals(inventory)){
//                    isEnableClick = true;
                    // 删除菜单
                    plugin.getMenuManager().removeMenu(player);
                }
            }

        }
    }

    /**
     * 玩家想移动或点击物品传送菜单的物品,就禁止
     * @param event 容器点击事件
     */
    @EventHandler
    public void onPlayerMoveOrClickItem(InventoryClickEvent event){
        Inventory inventory = event.getInventory(); // 实际的容器
        HumanEntity whoClicked = event.getWhoClicked();
        if (whoClicked instanceof Player){
            Player player = (Player) whoClicked;

            BaseMenu menu = plugin.getMenuManager().getMenu(player);
            if (menu instanceof WaypointTeleportMenu)
                waypointTeleportMenu = (WaypointTeleportMenu) menu;

            // 检查实际容器是不是对应的菜单容器
            if (waypointTeleportMenu != null){
                if(waypointTeleportMenu.getInventory().equals(inventory)){
                    event.setCancelled(true); // 如果禁止点击了，就取消点击事件继续执行
                }
            }
        }
    }

    /**
     * 当前玩家点击菜单物品
     * @param event 容器点击事件
     */
    @EventHandler
    public void onPlayerClickMenuItem(InventoryClickEvent event){
        HumanEntity humanEntity = event.getWhoClicked();
        Inventory clickedInventory = event.getClickedInventory();
        Inventory inventory = event.getInventory();
        ItemStack currentItem = event.getCurrentItem();
        MenuManager menuManager = plugin.getMenuManager();
        if (humanEntity instanceof Player){
            Player player = (Player) humanEntity;

            BaseMenu menu = plugin.getMenuManager().getMenu(player);
            if (menu instanceof WaypointTeleportMenu)
                waypointTeleportMenu = (WaypointTeleportMenu) menu;

            if (waypointTeleportMenu != null){
                if (waypointTeleportMenu.getInventory().equals(inventory) &&
                        waypointTeleportMenu.getInventory().equals(clickedInventory)){

                    if (currentItem == null)
                        return;

                    // 检查是否点击返回按钮
                    if (waypointTeleportMenu.isClickCloseButton(currentItem)){
                        MainMenu mainMenu = new MainMenu();
                        mainMenu.open(player);
                        plugin.getMenuManager().setPlayerMenu(player,mainMenu);
                        return;
                    }

                    // 其他物品点击操作
                }
            }
        }
    }
}
