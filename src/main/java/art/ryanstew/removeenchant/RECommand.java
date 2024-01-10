package art.ryanstew.removeenchant;

import org.bukkit.NamespacedKey;
import org.bukkit.Registry;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;


public class RECommand implements CommandExecutor, TabExecutor
{
    private static final String RELOAD_PERMISSION = "removeenchant.reload";

    private final RemoveEnchant plugin;
    private final List<String> helpMessageLines;
    private final Registry<Enchantment> enchantmentRegistry = Registry.ENCHANTMENT;


    public RECommand(RemoveEnchant plugin, List<String> helpMessageLines)
    {
        this.plugin = plugin;
        this.helpMessageLines = helpMessageLines;
    }


    private List<String> cloneList(List<String> list)
    {
        List<String> clone = new ArrayList<>(list.size());
        clone.addAll(list);
        return clone;
    }


    private void sendHelpMessage(CommandSender sender)
    {
        List<String> lines = cloneList(helpMessageLines);
        if (sender.hasPermission(RELOAD_PERMISSION)) lines.add("&7- &a/re reload &7- Reloads RemoveEnchant's config.\n&r");
        else lines.add("\n&r");

        String message = String.join("\n", lines);
        plugin.sendFormattedMessage(sender, message, false);
    }


    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args)
    {
        if (args.length == 0 || args[0].equalsIgnoreCase("help"))
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

            plugin.reloadConfig();
            plugin.sendFormattedMessage(sender, "&aSuccessfully reloaded the config!", true);
            return true;
        }

        if (!(sender instanceof Player player))
        {
            plugin.sendFormattedMessage(sender, "&cOnly players can run this command!", true);
            return true;
        }

        ItemStack item = player.getInventory().getItemInMainHand();
        String argsString = String.join("_", args);

        Enchantment found = enchantmentRegistry.get(NamespacedKey.minecraft(argsString));
        if (found == null)
        {
            plugin.sendFormattedMessage(player, "&cError: Invalid enchantment name!", true);
            return true;
        }

        if (item.removeEnchantment(found) == 0)
        {
            plugin.sendFormattedMessage(player, "&cYour held item doesn't have that enchantment!", true);
            return true;
        }

        String enchantName = found.key().asMinimalString().replaceAll("_", " ");
        plugin.sendFormattedMessage(player, String.format("&aSuccessfully removed %s from your held item!", enchantName), true);
        return true;
    }


    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args)
    {
        if (!(sender instanceof Player player))
        {
            return List.of("reload");
        }

        if (args.length == 1)
        {
            return player.getInventory()
                    .getItemInMainHand()
                    .getEnchantments()
                    .keySet()
                    .stream()
                    .map(enchantment -> enchantment.key().asMinimalString())
                    .toList();
        }

        return new ArrayList<>();
    }
}
