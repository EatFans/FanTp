package top.eatfan.fanTp.config;

import org.bukkit.configuration.file.FileConfiguration;

/**
 * 语言配置文件类
 *
 * @author Fan
 */
public class LangConfig extends BaseConfig{
    private String prefix;

    public LangConfig(FileConfiguration fileConfiguration){
        super(fileConfiguration);
    }

    @Override
    protected void init() {
        prefix = fileConfiguration.getString("prefix");
    }

    public String getPrefix(){
        return prefix;
    }
}
