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

    private void init(){
        requestTimeout = fileConfiguration.getInt("request-timeout");
    }

    public int getRequestTimeout(){
        return requestTimeout;
    }
}
