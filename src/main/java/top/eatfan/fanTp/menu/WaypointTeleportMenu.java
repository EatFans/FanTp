package top.eatfan.fanTp.menu;

import com.cryptomorin.xseries.XMaterial;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import top.eatfan.fanTp.FanTp;

/**
 * 路径点传送菜单
 *
 * @author Fan
 */
public class WaypointTeleportMenu extends BaseMenu{

    private ItemStack decorativeBoard;
    private ItemStack lastPageButton; // 上一页按钮物品堆
    private ItemStack nextPageButton; // 下一页按钮物品堆
    private ItemStack closeButton; // 关闭菜单按钮
    private final int LAST_PAGE_BUTTON_INDEX = 21; // 上一页按钮物品在容器的位置索引
    private final int CLOSE_BUTTON_INDEX = 22;  // 关闭按钮物品在容器的位置索引
    private final int NEXT_PAGE_BUTTON_INDEX = 23;  // 下一页按钮物品在容器的位置索引

    private boolean isEnableLastPageButton;  // 是否启用开启上一页按钮
    private boolean isEnableNextPageButton;  // 是否启用开启下一页按钮

    private int currentPage;
    private int totalPage;

    public WaypointTeleportMenu(){
        super("路径点传送菜单",27);

        decorativeBoard = XMaterial.BLACK_STAINED_GLASS_PANE.parseItem();
        lastPageButton = XMaterial.GRAY_STAINED_GLASS_PANE.parseItem();
        nextPageButton = XMaterial.GRAY_STAINED_GLASS_PANE.parseItem();
        closeButton = XMaterial.RED_STAINED_GLASS_PANE.parseItem();
        isEnableLastPageButton = false;
        isEnableNextPageButton = false;
        currentPage = 1;
        totalPage = 1;

        initMenuContent();
    }

    @Override
    protected void initMenuContent() {
        decorativeBoard.setItemMeta(createNamedItem(decorativeBoard," "));
        lastPageButton.setItemMeta(createNamedItem(lastPageButton,"&c" +
                FanTp.getInstance().getConfigManager().getLangConfig().getTpMenuLastButton()));
        nextPageButton.setItemMeta(createNamedItem(nextPageButton,"&c"+
                FanTp.getInstance().getConfigManager().getLangConfig().getTpMenuNextButton()));
        closeButton.setItemMeta(createNamedItem(closeButton,"&c" +
                FanTp.getInstance().getConfigManager().getLangConfig().getTpMenuBackButton()));

        inventory.setItem(LAST_PAGE_BUTTON_INDEX,lastPageButton);
        inventory.setItem(NEXT_PAGE_BUTTON_INDEX,nextPageButton);
        inventory.setItem(CLOSE_BUTTON_INDEX,closeButton);

        for (int i = 18; i <= 20; i++)
            inventory.setItem(i, decorativeBoard);
        for (int i = 24; i <= 26; i++)
            inventory.setItem(i,decorativeBoard);
    }

    @Override
    public void open(Player player) {
        player.openInventory(inventory);
    }

}
