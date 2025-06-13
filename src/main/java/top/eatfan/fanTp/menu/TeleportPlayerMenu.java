package top.eatfan.fanTp.menu;

import com.cryptomorin.xseries.XMaterial;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import top.eatfan.fanTp.FanTp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 传送列表菜单
 *
 * @author Fan
 */
public class TeleportPlayerMenu extends BaseMenu{
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

    private final Map<String,Player> menuPlayerItems = new HashMap<>(); // 玩家物品

    public TeleportPlayerMenu(){
        super(FanTp.getInstance().getConfigManager().getLangConfig().getTpMenuName(),27);


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

    /**
     * 初始化容器菜单内容
     */
    @Override
    protected void initMenuContent(){
        decorativeBoard.setItemMeta(createNamedItem(decorativeBoard," "));
        lastPageButton.setItemMeta(createNamedItem(lastPageButton,"&c" +
                FanTp.getInstance().getConfigManager().getLangConfig().getTpMenuLastButton()));
        nextPageButton.setItemMeta(createNamedItem(nextPageButton,"&c"+
                FanTp.getInstance().getConfigManager().getLangConfig().getTpMenuNextButton()));
        closeButton.setItemMeta(createNamedItem(closeButton,"&c" +
                FanTp.getInstance().getConfigManager().getLangConfig().getTpMenuCloseButton()));

        inventory.setItem(LAST_PAGE_BUTTON_INDEX,lastPageButton);
        inventory.setItem(NEXT_PAGE_BUTTON_INDEX,nextPageButton);
        inventory.setItem(CLOSE_BUTTON_INDEX,closeButton);

        for (int i = 18; i <= 20; i++)
            inventory.setItem(i, decorativeBoard);
        for (int i = 24; i <= 26; i++)
            inventory.setItem(i,decorativeBoard);

    }

    /**
     * 玩家打开菜单,
     * @param player 打开菜单的玩家
     */
    @Override
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
            addPlayerToMenu(target,playerHead);
            inventory.setItem(i - startIndex, playerHead);
        }

        // 如果当前页大于1，上一页按钮启用
        isEnableLastPageButton = currentPage > 1;

        //启用下一页按钮
        isEnableNextPageButton = totalPage > 1 && currentPage >= 1 && currentPage < totalPage;

        lastPageButton = createLastPageButton(isEnableLastPageButton);
        nextPageButton = createNextPageButton(isEnableNextPageButton);
        inventory.setItem(LAST_PAGE_BUTTON_INDEX,lastPageButton);
        inventory.setItem(NEXT_PAGE_BUTTON_INDEX,nextPageButton);

        player.openInventory(inventory);
    }

    /**
     * 测试方法
     * @param player 玩家
     */
    @Deprecated
    public void openTest(Player player){
        // 清除界面中的玩家物品和映射
        for (int i = 0; i <= 17; i++)
            inventory.setItem(i, null);

        menuPlayerItems.clear();
        // 使用模拟玩家数据测试分页功能
        onlinePlayers.clear();
        for (int i = 1; i <= 40; i++) {  // 模拟40个玩家
            // 创建模拟的离线玩家对象，仅用于显示，不用于传送
            Player fakePlayer = Bukkit.getPlayerExact("测试玩家" + i); // 可返回null
            onlinePlayers.add(fakePlayer); // 插入null没关系，稍后头颅用名称代替
        }

        // 分页参数
        int itemsPerPage = 18;
        totalPage = (int) Math.ceil(onlinePlayers.size() / (double) itemsPerPage);
        if (totalPage <= 0) totalPage = 1;

        if (currentPage < 1) currentPage = 1;
        if (currentPage > totalPage) currentPage = totalPage;

        int startIndex = (currentPage - 1) * itemsPerPage;
        int endIndex = Math.min(startIndex + itemsPerPage, onlinePlayers.size());

        // 创建模拟的玩家头像
        for (int i = startIndex; i < endIndex; i++){
            Player target = onlinePlayers.get(i);
            ItemStack playerHead = createFakePlayerHead("测试玩家" + (i + 1));
            addPlayerToMenu(target,playerHead);
            inventory.setItem(i - startIndex, playerHead);
        }

        // 如果当前页大于1，上一页按钮启用
        isEnableLastPageButton = currentPage > 1;

        //启用下一页按钮
        isEnableNextPageButton = totalPage > 1 && currentPage >= 1 && currentPage < totalPage;

        lastPageButton = createLastPageButton(isEnableLastPageButton);
        nextPageButton = createNextPageButton(isEnableNextPageButton);
        inventory.setItem(LAST_PAGE_BUTTON_INDEX,lastPageButton);
        inventory.setItem(NEXT_PAGE_BUTTON_INDEX,nextPageButton);

        player.openInventory(inventory);
    }

    /**
     * 创建上一页按钮
     * @param isEnabled 是否启用
     * @return 上一页按钮物品
     */
    private ItemStack createLastPageButton(boolean isEnabled) {
        XMaterial material = isEnabled ? XMaterial.GREEN_STAINED_GLASS_PANE : XMaterial.GRAY_STAINED_GLASS_PANE;
        ChatColor color = isEnabled ? ChatColor.GREEN : ChatColor.RED;
        String displayName = color + FanTp.getInstance().getConfigManager().getLangConfig().getTpMenuLastButton();


        ItemStack itemStack = material.parseItem();
        ItemMeta itemMeta = itemStack.getItemMeta();
        if (itemMeta != null) {
            itemMeta.setDisplayName(displayName);
            itemStack.setItemMeta(itemMeta);
        }
        return itemStack;
    }

    /**
     * 创建下一页按钮
     * @param isEnabled 是否启用
     * @return 下一页按钮物品
     */
    private ItemStack createNextPageButton(boolean isEnabled) {
        XMaterial material = isEnabled ? XMaterial.GREEN_STAINED_GLASS_PANE : XMaterial.GRAY_STAINED_GLASS_PANE;
        ChatColor color = isEnabled ? ChatColor.GREEN : ChatColor.RED;
        String displayName = color + FanTp.getInstance().getConfigManager().getLangConfig().getTpMenuNextButton();
    
        ItemStack itemStack = material.parseItem();
        ItemMeta itemMeta = itemStack.getItemMeta();
        if (itemMeta != null) {
            itemMeta.setDisplayName(displayName);
            itemStack.setItemMeta(itemMeta);
        }
        return itemStack;
    }

    /**
     * 测试使用，创建测试头颅
     * @param name name
     * @return 物品
     */
    @Deprecated
    private ItemStack createFakePlayerHead(String name){
        ItemStack playerHead;
        try {
            playerHead = new ItemStack(Material.valueOf("PLAYER_HEAD"));
        } catch (IllegalArgumentException e) {
            playerHead = new ItemStack(Material.valueOf("SKULL_ITEM"), 1, (short) 3);
        }

        ItemMeta meta = playerHead.getItemMeta();
        if (meta instanceof SkullMeta){
            SkullMeta skullMeta = (SkullMeta) meta;
            skullMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&',"&f&l[ &e" + name + " &f&l]"));
            List<String> lore = new ArrayList<>();
            lore.add(ChatColor.GRAY + "这是一个测试玩家");
            skullMeta.setLore(lore);
            playerHead.setItemMeta(skullMeta);
        }
        return playerHead;
    }


    /**
     * 刷新菜单界面
     * @param viewer 当前查看菜单的玩家
     */
    private void refreshMenu(Player viewer) {
        open(viewer); // 复用 open 方法重新渲染菜单
    }

    /**
     * 切换到上一页
     * @param viewer 打开菜单的玩家
     */
    public void toLastPage(Player viewer){
        if (currentPage > 1) {
            currentPage--;
            isEnableLastPageButton = true;

            refreshMenu(viewer);
        } else {
            isEnableLastPageButton = false;
            viewer.sendMessage(ChatColor.translateAlternateColorCodes('&',
                    FanTp.getInstance().getConfigManager().getLangConfig().getIsFirstPage()));
        }
    }

    /**
     * 切换到下一页
     * @param viewer 打开菜单的玩家
     */
    public void toNextPage(Player viewer){
        if (currentPage < totalPage) {
            currentPage++;
            refreshMenu(viewer);
        } else {
            viewer.sendMessage(ChatColor.translateAlternateColorCodes('&',
                    FanTp.getInstance().getConfigManager().getLangConfig().getIsEndPage()));
        }
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
     * 是否为上一页按钮
     * @param itemStack 物品
     * @return
     */
    public boolean isLastPageButton(ItemStack itemStack){
        return lastPageButton.equals(itemStack);
    }

    /**
     * 是否为下一页按钮
     * @param itemStack 物品
     * @return
     */
    public boolean isNextPageButton(ItemStack itemStack){
        return nextPageButton.equals(itemStack);
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
            List<String> rawLore = FanTp.getInstance().getConfigManager().getLangConfig().getTpMenuHeadLore();
            List<String> coloredLore = rawLore.stream()
                    .map(line -> ChatColor.translateAlternateColorCodes('&', line))
                    .collect(Collectors.toList());
            skullMeta.setLore(coloredLore);
            playerHead.setItemMeta(skullMeta);
        }
        return playerHead;
    }
    // 在创建头颅时
    public void addPlayerToMenu(Player player, ItemStack headItem) {
        // 设置头颅的显示名称为玩家名称
        ItemMeta meta = headItem.getItemMeta();
        if (meta != null) {
            meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&f&l[ &e" + player.getName() + " &f&l]"));
            headItem.setItemMeta(meta);
        }
        menuPlayerItems.put(player.getName(), player);
    }


    /**
     * 按钮是否启用
     * @return 是否启用
     */
    public boolean isEnableLastPageButton() {
        return isEnableLastPageButton;
    }

    /**
     * 按钮是否启用
     * @return boolean
     */
    public boolean isEnableNextPageButton() {
        return isEnableNextPageButton;
    }

    /**
     * 获取关闭按钮
     * @return 物品
     */
    public ItemStack getCloseButton(){
        return closeButton;
    }

    /**
     * 获取上一页按钮
     * @return 物品
     */
    public ItemStack getLastPageButton(){
        return lastPageButton;
    }

    /**
     * 获取下一页按钮
     * @return 物品
     */
    public ItemStack getNextPageButton(){
        return nextPageButton;
    }

    /**
     * 通过玩家头，来获取目标玩家
     * @param itemStack 玩家头物品
     * @return 目标玩家
     */
    public Player getTargetPlayer(ItemStack itemStack) {
        if (itemStack != null && itemStack.hasItemMeta() && itemStack.getItemMeta().hasDisplayName()) {
            String displayName = ChatColor.stripColor(itemStack.getItemMeta().getDisplayName());
            // 从显示名称中提取玩家名称
            // 假设格式是 "[ 玩家名 ]"
            if (displayName.startsWith("[ ") && displayName.endsWith(" ]")) {
                String playerName = displayName.substring(2, displayName.length() - 2).trim();
                // 通过名称查找在线玩家
                return Bukkit.getPlayerExact(playerName);
            }
        }
        return null;
    }

}
