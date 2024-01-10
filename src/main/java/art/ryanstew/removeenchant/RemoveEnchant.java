package art.ryanstew.removeenchant;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public final class RemoveEnchant extends JavaPlugin
{
    private final List<String> helpMessageLines = new ArrayList<>();


    @Override
    public void onEnable()
    {
        saveDefaultConfig();

        helpMessageLines.add("\n");
        helpMessageLines.add("&7[&d&l&kx&dRemoveEnchant&d&l&kx&7]:");
        helpMessageLines.add("&7- &a/re <enchantment> &7- Removes the given enchantment.");
        helpMessageLines.add("&7- &a/re help &7- Shows this page.");

        Objects.requireNonNull(getCommand("removeenchant"))
                .setExecutor(new RECommand(this, helpMessageLines));
    }


    @Override
    public void onDisable() { }


    public void sendFormattedMessage(CommandSender sender, String message, boolean prefixed)
    {
        String msg = prefixed ? getConfig().getString("prefix") + message : message;
        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', msg));
    }
}
