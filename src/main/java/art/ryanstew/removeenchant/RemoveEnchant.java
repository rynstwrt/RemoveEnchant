package art.ryanstew.removeenchant;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;


public final class RemoveEnchant extends JavaPlugin
{
    private static final Logger LOGGER = Logger.getLogger("Minecraft");

    private final List<String> helpMessageLines = new ArrayList<>();
    private ConfigHandler configHandler;


    @Override
    public void onEnable()
    {
        helpMessageLines.add("\n");
        helpMessageLines.add("&7[&d&l&kx&dRemoveEnchant&d&l&kx&7]:");
        helpMessageLines.add("&7- &a/re <enchantment> &7- Removes the given enchantment.");
        helpMessageLines.add("&7- &a/re help &7- Shows this page.");

        configHandler = new ConfigHandler(this);

        boolean loadSuccess = configHandler.loadConfig();
        if (loadSuccess) LOGGER.info("Successfully loaded RemoveEnchant's config!");
        else LOGGER.warning("Error loading RemoveEnchant's config!");

        Objects.requireNonNull(getCommand("removeenchant"))
                .setExecutor(new RECommand(this, helpMessageLines, configHandler));
    }


    @Override
    public void onDisable() { }


    public void sendFormattedMessage(CommandSender sender, String message, boolean prefixed)
    {
        String msg = prefixed ? configHandler.getPrefix() + message : message;
        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', msg));
    }
}
