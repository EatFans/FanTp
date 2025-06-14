package top.eatfan.fanTp.linstener;

import com.cryptomorin.xseries.XMaterial;
import org.bukkit.Bukkit;
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
import top.eatfan.fanTp.menu.BaseMenu;
import top.eatfan.fanTp.menu.MainMenu;
import top.eatfan.fanTp.menu.TeleportPlayerMenu;
import top.eatfan.fanTp.menu.MenuManager;
import top.eatfan.fanTp.event.TeleportRequestSendEvent;

/**
 * 容器事件监听器
 * 用于处理本插件中菜单的各种操作事件
 *
 * @author Fan
 */
public class TeleportPlayerMenuListener implements Listener {

    private final FanTp plugin;

    @Deprecated
    private boolean isEnableClick;
    private TeleportPlayerMenu teleportPlayerMenu;

    public TeleportPlayerMenuListener(FanTp plugin){
        this.plugin = plugin;
        isEnableClick = false;
    }

    /**
     * 当玩家打开菜单时候
     * @param event 容器被打开事件
     */
    @Deprecated
    @EventHandler
    public void OnPlayerOpenMenu(InventoryOpenEvent event){
        HumanEntity humanEntity = event.getPlayer();
        Inventory inventory = event.getInventory();
        if (humanEntity instanceof Player){
            Player player = (Player) humanEntity;

            BaseMenu menu = plugin.getMenuManager().getMenu(player);
            if (menu instanceof TeleportPlayerMenu)
                teleportPlayerMenu = (TeleportPlayerMenu) menu;

            // 检查被打开的容器是不是本插件的菜单
            if (teleportPlayerMenu != null){
                if (teleportPlayerMenu.getInventory().equals(inventory))
                    isEnableClick = false;
                //
            }

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

            BaseMenu menu = plugin.getMenuManager().getMenu(player);
            if (menu instanceof TeleportPlayerMenu)
                teleportPlayerMenu = (TeleportPlayerMenu) menu;

            if (teleportPlayerMenu != null){
                if (teleportPlayerMenu.getInventory().equals(inventory)){
                    isEnableClick = true;
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
            if (menu instanceof TeleportPlayerMenu)
                teleportPlayerMenu = (TeleportPlayerMenu) menu;

            // 检查实际容器是不是对应的菜单容器
            if (teleportPlayerMenu != null){
                if(teleportPlayerMenu.getInventory().equals(inventory)){
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
            if (menu instanceof TeleportPlayerMenu)
                teleportPlayerMenu = (TeleportPlayerMenu) menu;


            if (teleportPlayerMenu != null){
                if (teleportPlayerMenu.getInventory().equals(inventory) &&
                        teleportPlayerMenu.getInventory().equals(clickedInventory)){

                    if (currentItem == null)
                        return;

                    // 检查是否点击返回按钮
                    if (teleportPlayerMenu.isClickCloseButton(currentItem)){
                        MainMenu mainMenu = new MainMenu();
                        mainMenu.open(player);
                        plugin.getMenuManager().setPlayerMenu(player,mainMenu);
                        return;
                    }

                    // 检查是否点击上一页
                    if (teleportPlayerMenu.isLastPageButton(currentItem)){
                        teleportPlayerMenu.toLastPage(player);
                        menuManager.setPlayerMenu(player, teleportPlayerMenu);
                        event.setCancelled(true);
                        return;
                    }

                    // 检查是否点击下一页
                    if (teleportPlayerMenu.isNextPageButton(currentItem)) {
                        teleportPlayerMenu.toNextPage(player);
                        menuManager.setPlayerMenu(player, teleportPlayerMenu);
                        event.setCancelled(true);
                        return;
                    }

                    // 检查是否点击头颅物品
                    if (XMaterial.PLAYER_HEAD.isSimilar(currentItem)) {
                        Player targetPlayer = teleportPlayerMenu.getTargetPlayer(currentItem);
                        if (targetPlayer != null) {
                            // 触发传送请求发送事件
                            player.closeInventory();
                            // 检查目标是否存在传送请求
                            if (plugin.getTeleportRequestManager().hasRequest(targetPlayer)){
                                player.sendMessage(ChatColor.translateAlternateColorCodes('&',
                                        plugin.getConfigManager().getLangConfig().getNotProcessedTpRequest()));
                                return;
                            }
                            TeleportRequestSendEvent teleportRequestSendEvent = new TeleportRequestSendEvent(player, targetPlayer);
                            Bukkit.getPluginManager().callEvent(teleportRequestSendEvent);
                            plugin.getLogger().info("Player " + player.getName() + " sent a teleport request to " + targetPlayer.getName());
                        } else {
                            event.setCancelled(true);

                        }

                    }


                }

            }

        }
    }

}
