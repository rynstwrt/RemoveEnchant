package art.ryanstew.removeenchant;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class ConfigHandler
{
    private static final String CONFIG_FILE_NAME = "config.yml";

    private final RemoveEnchant plugin;
    private String prefix;


    public ConfigHandler(RemoveEnchant plugin)
    {
        this.plugin = plugin;
    }


    public boolean loadConfig()
    {
        File configFile = new File(plugin.getDataFolder(), CONFIG_FILE_NAME);
        if (!configFile.exists())
        {
            configFile.getParentFile().mkdirs();
            plugin.saveResource(CONFIG_FILE_NAME, false);
        }

        FileConfiguration config = new YamlConfiguration();
        try
        {
            config.load(configFile);

            prefix = config.getString("prefix");

            return true;
        }
        catch (IOException | InvalidConfigurationException e)
        {
            e.printStackTrace();
            return false;
        }
    }


    public String getPrefix()
    {
        return prefix;
    }
}
