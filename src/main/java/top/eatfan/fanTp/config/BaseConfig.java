package top.eatfan.fanTp.config;

import org.bukkit.configuration.file.FileConfiguration;

/**
 * 配置文件抽象基类
 *
 * @author Fan
 */
public abstract class BaseConfig {
    protected FileConfiguration fileConfiguration;

    public BaseConfig(FileConfiguration fileConfiguration){
        this.fileConfiguration = fileConfiguration;
        init();
    }

    /**
     * 初始化配置文件数据
     */
    protected abstract void init();

}
