package top.eatfan.fanTp.core;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    private final int LAST_PAGE_BUTTON_INDEX = 21; // 上一页按钮物品在容器的位置索引
    private final int CLOSE_BUTTON_INDEX = 22;  // 关闭按钮物品在容器的位置索引
    private final int NEXT_PAGE_BUTTON_INDEX = 23;  // 下一页按钮物品在容器的位置索引

    private boolean isEnableLastPageButton;  // 是否启用开启上一页按钮
    private boolean isEnableNextPageButton;  // 是否启用开启下一页按钮

    private int currentPage;
    private int totalPage;

    private final List<Player> onlinePlayers = new ArrayList<>();

    private final Map<ItemStack,Player> menuPlayerItems = new HashMap<>(); // 玩家物品
    private Inventory inventory;  // 菜单的容器

    public Menu(){
        inventory = Bukkit.createInventory(null,27, ChatColor.translateAlternateColorCodes('&',"&a&l传送列表"));
        decorativeBoard = new ItemStack(Material.BLACK_STAINED_GLASS_PANE,1);
        lastPageButton = new ItemStack(Material.GRAY_STAINED_GLASS_PANE,1);
        nextPageButton = new ItemStack(Material.GRAY_STAINED_GLASS_PANE,1);
        closeButton = new ItemStack(Material.RED_STAINED_GLASS_PANE,1);
        isEnableLastPageButton = false;
        isEnableNextPageButton = false;
        currentPage = 1;
        totalPage = 1;

        initMenuContent();
    }

    /**
     * 初始化容器菜单内容
     */
    private void initMenuContent(){
        decorativeBoard.setItemMeta(createNamedItem(decorativeBoard," "));
        lastPageButton.setItemMeta(createNamedItem(lastPageButton,"&c上一页"));
        nextPageButton.setItemMeta(createNamedItem(nextPageButton,"&c下一页"));
        closeButton.setItemMeta(createNamedItem(closeButton,"&c关闭"));

        inventory.setItem(LAST_PAGE_BUTTON_INDEX,lastPageButton);
        inventory.setItem(NEXT_PAGE_BUTTON_INDEX,nextPageButton);
        inventory.setItem(CLOSE_BUTTON_INDEX,closeButton);

        for (int i = 18; i <= 20; i++)
            inventory.setItem(i, decorativeBoard);
        for (int i = 24; i <= 26; i++)
            inventory.setItem(i,decorativeBoard);
    }

    private ItemMeta createNamedItem(ItemStack item, String displayName) {
        ItemMeta meta = item.getItemMeta();
        if (meta != null) {
            meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', displayName));
        }
        return meta;
    }

    /**
     * 玩家打开菜单,
     * @param player 打开菜单的玩家
     */
    public void open(Player player){
        // 先清除0-17号的所有物品
        for (int i = 0; i <= 17; i++)
            inventory.setItem(i, null);

        // 获取所有在线玩家（不包括自己）
        onlinePlayers.clear();
        for (Player p : Bukkit.getOnlinePlayers()) {
            if (!p.equals(player)) {
                onlinePlayers.add(p);
            }
        }

        // 计算总页数（每页最多18个玩家）
        totalPage = (int) Math.ceil(onlinePlayers.size() / 18.0);
        if (totalPage <= 0) totalPage = 1;

        // 当前页容错修正
        if (currentPage < 1) currentPage = 1;
        if (currentPage > totalPage) currentPage = totalPage;

        // 计算当前页显示的玩家索引范围
        int startIndex = (currentPage - 1) * 18;
        int endIndex = Math.min(startIndex + 18, onlinePlayers.size());

        // 创建在线玩家物品
        for (int i = startIndex; i < endIndex; i++){
            Player target = onlinePlayers.get(i);
            ItemStack playerHead = createPlayerHead(target);
            menuPlayerItems.put(playerHead,target);
            inventory.setItem(i - startIndex, playerHead);
        }

        // TODO 如果玩家数量大于18，就需要分页处理

        player.openInventory(inventory);
    }

    /**
     * 切换到上一页
     */
    public void toLastPage(){

    }

    /**
     * 切换到下一页
     */
    public void toNextPage(){

    }

    /**
     * 是否点击关闭按钮
     * @param itemStack 物品
     * @return
     */
    public boolean isClickCloseButton(ItemStack itemStack){
        return closeButton.equals(itemStack);
    }


    /**
     * 创建玩家头颅物品
     * @param player 玩家
     * @return 返回玩家头颅物品
     */
    private ItemStack createPlayerHead(Player player){
        ItemStack playerHead;
        try {
            playerHead = new ItemStack(Material.valueOf("PLAYER_HEAD"));
        } catch (IllegalArgumentException e) {
            // 兼容老版本（如 1.12 及以下）
            playerHead = new ItemStack(Material.valueOf("SKULL_ITEM"), 1, (short) 3);
        }
        ItemMeta meta = playerHead.getItemMeta();
        if (meta instanceof SkullMeta){
            SkullMeta skullMeta = (SkullMeta) meta;
            skullMeta.setOwningPlayer(player);
            skullMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&',"&f&l[ &a"+player.getDisplayName() + " &f&l]"));
            List<String> lore = new ArrayList<>();
            lore.add(ChatColor.GRAY + "点击传送到该玩家");
            skullMeta.setLore(lore);
            playerHead.setItemMeta(skullMeta);
        }
        return playerHead;
    }


    public Inventory getInventory(){
        return inventory;
    }

    public boolean isEnableLastPageButton() {
        return isEnableLastPageButton;
    }

    public boolean isEnableNextPageButton() {
        return isEnableNextPageButton;
    }

    public ItemStack getCloseButton(){
        return closeButton;
    }

    /**
     * 通过玩家头，来获取目标玩家
     * @param itemStack 玩家头物品
     * @return 目标玩家
     */
    public Player getTargetPlayer(ItemStack itemStack){
        return menuPlayerItems.get(itemStack);
    }
}
