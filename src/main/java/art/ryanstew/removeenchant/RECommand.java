package art.ryanstew.removeenchant;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;


public class RECommand implements CommandExecutor
{
    private static final String RELOAD_PERMISSION = "removeenchant.reload";

    private final RemoveEnchant plugin;
    private final ConfigHandler configHandler;
    private static List<String> helpMessageLines = new ArrayList<>();


    public RECommand(RemoveEnchant plugin, List<String> helpMessageLines, ConfigHandler configHandler)
    {
        this.plugin = plugin;
        this.helpMessageLines = helpMessageLines;
        this.configHandler = configHandler;
    }


    private List<String> cloneList(List<String> list)
    {
        List<String> clone = new ArrayList<String>(list.size());
        for (String s : list) clone.add(s);
        return clone;
    }


    private void sendHelpMessage(CommandSender sender)
    {
        List<String> lines = cloneList(helpMessageLines);
        if (sender.hasPermission(RELOAD_PERMISSION))
            lines.add("&7- &a/re reload");

        String message = String.join("\n", lines);
        plugin.sendFormattedMessage(sender, message, false);
    }


    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args)
    {
        if (args.length != 1)
        {
            sendHelpMessage(sender);
            return true;
        }

        if (args[0].equalsIgnoreCase("reload"))
        {
            if (!sender.hasPermission(RELOAD_PERMISSION))
            {
                plugin.sendFormattedMessage(sender, "&cYou do not have permission to run that command!", false);
                return true;
            }

            boolean successful = configHandler.loadConfig();
            String message = successful ? "&aSuccessfully reloaded the config!" : "&cFailed to load config!";
            plugin.sendFormattedMessage(sender, message, true);
            return true;
        }

        

        return false;
    }
}
