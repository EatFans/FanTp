package top.eatfan.fanTp.config;

import org.bukkit.configuration.file.FileConfiguration;

/**
 * 默认config.yml配置数据实体
 *
 * @author Fan
 */
public class Config {
    private final FileConfiguration fileConfiguration;

    private int requestTimeout;

    public Config(FileConfiguration fileConfiguration){
        this.fileConfiguration = fileConfiguration;
        init();
    }

    /**
     * 初始化数据
     */
    private void init(){
        requestTimeout = fileConfiguration.getInt("request-timeout");
    }

    /**
     * 获取超时时间
     * @return 超时时间
     */
    public int getRequestTimeout(){
        return requestTimeout;
    }
}
