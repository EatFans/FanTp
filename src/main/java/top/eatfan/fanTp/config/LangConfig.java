package top.eatfan.fanTp.config;

import org.bukkit.configuration.file.FileConfiguration;

import java.util.List;

/**
 * 语言配置文件类
 *
 * @author Fan
 */
public class LangConfig extends BaseConfig{
    private String prefix;
    private String noPermission;
    private String reload;
    private String openTpMenu;
    private String closeTpMenu;
    private String tpMenuName;
    private List<String> tpMenuHeadLore;
    private String tpMenuLastButton;
    private String tpMenuNextButton;
    private String tpMenuCloseButton;
    private String tpMenuBackButton;
    private String isFirstPage;
    private String isEndPage;
    private String tpRequestTimeoutA;
    private String tpRequestTimeoutB;
    private String notProcessedTpRequest;
    private String noTpRequest;
    private String otherAgreeTpRequest;
    private String otherDenyTpRequest;
    private String agreeTpRequest;
    private String denyTpRequest;
    private String sendTpRequest;
    private String receiveTpRequest;
    private String chatAgreeButton;
    private String chatAgreeButtonHover;
    private String chatDenyButton;
    private String chatDenyButtonHover;
    public LangConfig(FileConfiguration fileConfiguration){
        super(fileConfiguration);
    }

    @Override
    protected void init() {
        prefix = fileConfiguration.getString("prefix");
        noPermission = fileConfiguration.getString("no-permission");
        reload = fileConfiguration.getString("reload");
        openTpMenu = fileConfiguration.getString("open-tp-menu");
        closeTpMenu = fileConfiguration.getString("close-tp-menu");
        tpMenuName = fileConfiguration.getString("tp-menu-name");
        tpMenuHeadLore = fileConfiguration.getStringList("tp-menu-head-lore");
        tpMenuLastButton = fileConfiguration.getString("tp-menu-last-button");
        tpMenuNextButton = fileConfiguration.getString("tp-menu-next-button");
        tpMenuCloseButton = fileConfiguration.getString("tp-menu-close-button");
        tpMenuBackButton = fileConfiguration.getString("tp-menu-back-button");
        isFirstPage = fileConfiguration.getString("is-first-page");
        isEndPage = fileConfiguration.getString("is-end-page");
        tpRequestTimeoutA = fileConfiguration.getString("tp-request-timeout-a");
        tpRequestTimeoutB = fileConfiguration.getString("tp-request-timeout-b");
        notProcessedTpRequest = fileConfiguration.getString("not-processed-tp-request");
        noTpRequest = fileConfiguration.getString("no-tp-request");
        otherAgreeTpRequest = fileConfiguration.getString("other-agree-tp-request");
        otherDenyTpRequest = fileConfiguration.getString("other-deny-tp-request");
        agreeTpRequest = fileConfiguration.getString("agree-tp-request");
        denyTpRequest = fileConfiguration.getString("deny-tp-request");
        sendTpRequest = fileConfiguration.getString("send-tp-request");
        receiveTpRequest = fileConfiguration.getString("receive-tp-request");
        chatAgreeButton = fileConfiguration.getString("chat-agree-button");
        chatAgreeButtonHover = fileConfiguration.getString("chat-agree-button-hover");
        chatDenyButton = fileConfiguration.getString("chat-deny-button");
        chatDenyButtonHover = fileConfiguration.getString("chat-deny-button-hover");
    }

    public String getPrefix(){
        return prefix;
    }

    public String getNoPermission(){
        return prefix+noPermission;
    }

    public String getReload(){
        return prefix+reload;
    }
    public String getOpenTpMenu(){
        return prefix + openTpMenu;
    }

    public String getCloseTpMenu(){
        return prefix + closeTpMenu;
    }
    public String getTpMenuName(){
        return tpMenuName;
    }

    public List<String> getTpMenuHeadLore(){
        return tpMenuHeadLore;
    }

    public String getTpMenuLastButton(){
        return tpMenuLastButton;
    }

    public String getTpMenuNextButton(){
        return tpMenuNextButton;
    }

    public String getTpMenuCloseButton(){
        return tpMenuCloseButton;
    }

    public String getIsFirstPage(){
        return prefix + isFirstPage;
    }

    public String getIsEndPage(){
        return prefix + isEndPage;
    }

    public String getTpRequestTimeoutA() {
        return prefix + tpRequestTimeoutA;
    }

    public String getTpRequestTimeoutB(){
        return prefix + tpRequestTimeoutB;
    }

    public String getNotProcessedTpRequest(){
        return prefix + notProcessedTpRequest;
    }

    public String getNoTpRequest(){
        return prefix + noTpRequest;
    }

    public String getOtherAgreeTpRequest(){
        return prefix + otherAgreeTpRequest;
    }

    public String getOtherDenyTpRequest(){
        return prefix + otherDenyTpRequest;
    }

    public String getAgreeTpRequest(){
        return prefix + agreeTpRequest;
    }

    public String getDenyTpRequest(){
        return prefix + denyTpRequest;
    }
    public String getSendTpRequest(){
        return prefix + sendTpRequest;
    }

    public String getReceiveTpRequest(){
        return receiveTpRequest;
    }

    public String getChatAgreeButton(){
        return chatAgreeButton;
    }

    public String getChatAgreeButtonHover(){
        return chatAgreeButtonHover;
    }

    public String getChatDenyButton(){
        return chatDenyButton;
    }

    public String getChatDenyButtonHover(){
        return chatDenyButtonHover;
    }

    public String getTpMenuBackButton() {
        return tpMenuBackButton;
    }
}
