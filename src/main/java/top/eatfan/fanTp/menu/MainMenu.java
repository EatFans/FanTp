package top.eatfan.fanTp.menu;

import com.cryptomorin.xseries.XMaterial;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import top.eatfan.fanTp.FanTp;

/**
 * 主要核心菜单类
 *
 * @author Fan
 */
public class MainMenu extends BaseMenu{
    private ItemStack decorativeBoard;  // 装饰板物品堆
    private ItemStack waypoint;  // 路径点物品
    private ItemStack playerTeleport; // 玩家传送
    private ItemStack closeButton; // 关闭菜单按钮
    private final int CLOSE_BUTTON_INDEX = 22;  // 关闭按钮物品在容器的位置索引

    public MainMenu(){
        super("菜单",27);

        decorativeBoard = XMaterial.BLACK_STAINED_GLASS_PANE.parseItem();
        closeButton = XMaterial.RED_STAINED_GLASS_PANE.parseItem();
        waypoint = XMaterial.GRASS_BLOCK.parseItem();
        playerTeleport = XMaterial.PLAYER_HEAD.parseItem();

        initMenuContent();
    }

    /**
     * 初始化菜单内容部分
     */
    @Override
    protected void initMenuContent() {
        decorativeBoard.setItemMeta(createNamedItem(decorativeBoard," "));
        waypoint.setItemMeta(createNamedItem(waypoint,"路径点传送"));
        playerTeleport.setItemMeta(createNamedItem(playerTeleport,"玩家传送"));
        closeButton.setItemMeta(createNamedItem(closeButton,"&c" +
                FanTp.getInstance().getConfigManager().getLangConfig().getTpMenuCloseButton()));

        inventory.setItem(CLOSE_BUTTON_INDEX,closeButton);
        for (int i = 0; i < 9; i++)
            inventory.setItem(i,decorativeBoard);
        inventory.setItem(9,decorativeBoard);
        inventory.setItem(10,waypoint);
        inventory.setItem(11,playerTeleport);
        inventory.setItem(17,decorativeBoard);
        for (int i = 18; i <= 21; i++)
            inventory.setItem(i, decorativeBoard);
        for (int i = 23; i <= 26; i++)
            inventory.setItem(i,decorativeBoard);
    }

    /**
     * 打开菜单
     * @param player 玩家
     */
    @Override
    public void open(Player player) {
        player.openInventory(inventory);
    }

    public ItemStack getCloseButton(){
        return closeButton;
    }

    /**
     * 是否点击关闭按钮
     * @param itemStack 物品
     * @return 如果是就返回true，否则就返回false
     */
    public boolean isClickCloseButton(ItemStack itemStack){
        return closeButton.equals(itemStack);
    }

    /**
     * 是否点击路径点传送按钮
     * @param itemStack 物品
     * @return 如果是就返回true，否则就返回false
     */
    public boolean isClickWayPointButton(ItemStack itemStack){
        return waypoint.equals(itemStack);
    }

    /**
     * 是否点击玩家传送按钮
     * @param itemStack 物品
     * @return 如果是就返回true，否则就返回false
     */
    public boolean isClickPlayerTeleportButton(ItemStack itemStack){
        return playerTeleport.equals(itemStack);
    }

    public ItemStack getDecorativeBoard() {
        return decorativeBoard;
    }

    public ItemStack getWaypoint(){
        return waypoint;
    }
}
