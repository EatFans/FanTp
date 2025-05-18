package top.eatfan.fanTp.core;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import top.eatfan.fanTp.FanTp;
import top.eatfan.fanTp.config.BaseConfig;
import top.eatfan.fanTp.config.Config;
import top.eatfan.fanTp.config.LangConfig;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 配置文件管理类
 *
 * @author Fan
 */
public class ConfigManager {
    private final FanTp plugin;
    private FileConfiguration configFileConfiguration;
    private File configFile;
    private Config config;


    private FileConfiguration langFileConfiguration;
    private File langConfigFile;
    private LangConfig langConfig;

    // 存储其他配置文件

    public ConfigManager(FanTp plugin){
        this.plugin = plugin;

        plugin.saveDefaultConfig();
        // 加载配置
        reload();
    }

    /**
     * 重新加载配置文件
     */
    public void reload(){
        // 如果配置文件不存在，保存默认配置
        if (configFile == null) {
            configFile = new File(plugin.getDataFolder(), "config.yml");
        }
        // 检查文件是否存在，如果不存在则保存默认配置
        if (!configFile.exists()) {
            plugin.saveResource("config.yml", false);
        }
        // 重新加载配置
        configFileConfiguration = YamlConfiguration.loadConfiguration(configFile);
        config = new Config(configFileConfiguration);

        if (langConfigFile == null){
            langConfigFile = new File(plugin.getDataFolder(),"lang.yml");
        }
        if (!langConfigFile.exists()) {
            plugin.saveResource("lang.yml",false);
        }
        langFileConfiguration = YamlConfiguration.loadConfiguration(langConfigFile);
        langConfig = new LangConfig(langFileConfiguration);


    }

    /**
     * 保存配置文件
     */
    public void save() {
        if (configFileConfiguration == null || configFile == null) {
            return;
        }
        if (langFileConfiguration == null || langConfigFile == null){
            return;
        }

        try {
            configFileConfiguration.save(configFile);
            langFileConfiguration.save(langConfigFile);
        } catch (IOException e) {
            plugin.getLogger().warning("无法保存配置文件！");
        }
    }

    /**
     * 获取配置文件
     * @return config.yml配置文件
     */
    public Config getConfig(){
        return  config;
    }

    /**
     * 获取语言配置文件
     * @return lang.yml配置文件
     */
    public LangConfig getLangConfig(){
        return langConfig;
    }
}
