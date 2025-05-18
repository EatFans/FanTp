package top.eatfan.fanTp.config;

import org.bukkit.configuration.file.FileConfiguration;

/**
 * 默认config.yml配置数据实体
 *
 * @author Fan
 */
public class Config extends BaseConfig{

    private int requestTimeout;

    public Config(FileConfiguration fileConfiguration){
        super(fileConfiguration);
    }

    /**
     * 初始化数据
     */
    @Override
    protected void init(){
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
