package art.ryanstew.removeenchant;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;


public class RemoveEnchantCommand implements CommandExecutor
{
    private final RemoveEnchant plugin;


    public RemoveEnchantCommand(RemoveEnchant plugin)
    {
        this.plugin = plugin;
    }


    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args)
    {

        return false;
    }
}
