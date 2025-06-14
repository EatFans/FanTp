package top.eatfan.fanTp.linstener;

import org.bukkit.ChatColor;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import top.eatfan.fanTp.FanTp;
import top.eatfan.fanTp.menu.*;

/**
 * 主要菜单事件监听器
 *
 * @author Fan
 */
public class MainMenuListener implements Listener {
    private final FanTp plugin;

    @Deprecated
    private boolean isEnableClick;
    private MainMenu mainMenu;

    public MainMenuListener(FanTp plugin){
        this.plugin = plugin;
        this.isEnableClick = false;
    }

    /**
     * 当玩家打开菜单时候
     * @param event 容器被打开事件
     */
    @Deprecated
    @EventHandler
    public void onPlayerOpenMenu(InventoryOpenEvent event){
        HumanEntity humanEntity = event.getPlayer();
        Inventory inventory = event.getInventory();
        if (humanEntity instanceof Player){
            Player player = (Player) humanEntity;

            BaseMenu menu = plugin.getMenuManager().getMenu(player);
            if (menu instanceof MainMenu)
                mainMenu = (MainMenu) menu;

            if (mainMenu != null){
                if (mainMenu.getInventory().equals(inventory)){
                    isEnableClick = false;
                    player.sendMessage("main容器被打开");
                }
            }
        }
    }

    /**
     * 当玩家关闭菜单
     * @param event 容器关闭事件
     */
    @EventHandler
    public void onPlayerCloseEvent(InventoryCloseEvent event){
        HumanEntity humanEntity = event.getPlayer();
        Inventory inventory = event.getInventory();

        if (humanEntity instanceof Player){
            Player player = (Player) humanEntity;

            BaseMenu menu = plugin.getMenuManager().getMenu(player);
            if (menu instanceof MainMenu)
                mainMenu = (MainMenu) menu;

            if (mainMenu != null){
                if (mainMenu.getInventory().equals(inventory)){
                    isEnableClick = true;
                    player.sendMessage("main容器被关闭");
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
            if (menu instanceof MainMenu)
                mainMenu = (MainMenu) menu;

            // 检查实际容器是不是对应的菜单容器
            if (mainMenu != null){
                if(mainMenu.getInventory().equals(inventory)){
                    event.setCancelled(true); // 如果禁止点击了，就取消点击事件继续执行
                }
            }

        }
    }

    /**
     * 当玩家点击特定物品
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
            if (menu instanceof MainMenu)
                mainMenu = (MainMenu) menu;

            if (mainMenu == null)
                return;

            if (mainMenu.getInventory().equals(inventory) &&
                mainMenu.getInventory().equals(clickedInventory)){

                if (currentItem == null)
                    return;
                // 检查是否点击关闭按钮
                if (mainMenu.isClickCloseButton(currentItem)){
                    player.closeInventory();
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', "关闭"));
                    event.setCancelled(true);
                    return;
                }

                // 检查是否点击路径点传送按钮
                if (mainMenu.isClickWayPointButton(currentItem)){
                    player.sendMessage("点击路径点传送");
                    WaypointTeleportMenu waypointTeleportMenu = new WaypointTeleportMenu();
                    player.sendMessage("你成功打开了路径点传送菜单");
                    waypointTeleportMenu.open(player);
                    menuManager.setPlayerMenu(player,waypointTeleportMenu);
                    return;
                }

                // 检查是否点击玩家传送按钮
                if (mainMenu.isClickPlayerTeleportButton(currentItem)){
                    player.sendMessage("点击玩家传送");
                    TeleportPlayerMenu teleportPlayerMenu = new TeleportPlayerMenu();
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&',
                            "你成功打开了玩家传送菜单"));
                    teleportPlayerMenu.open(player);
                    menuManager.setPlayerMenu(player,teleportPlayerMenu);

                }
            }
        }
    }
}
