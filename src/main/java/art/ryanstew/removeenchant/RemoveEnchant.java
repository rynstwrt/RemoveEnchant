package art.ryanstew.removeenchant;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;


public final class RemoveEnchant extends JavaPlugin
{
    @Override
    public void onEnable()
    {
        saveDefaultConfig();

        Objects.requireNonNull(getCommand("removeenchant"))
                .setExecutor(new RECommand(this));
    }


    @Override
    public void onDisable() { }


    public void sendFormattedMessage(CommandSender sender, String message, boolean prefixed)
    {
        String msg = prefixed ? getConfig().getString("prefix") + message : message;
        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', msg));
    }
}
