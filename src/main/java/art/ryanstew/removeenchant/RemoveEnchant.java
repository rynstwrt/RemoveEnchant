package art.ryanstew.removeenchant;

import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class RemoveEnchant extends JavaPlugin
{

    @Override
    public void onEnable()
    {
        Objects.requireNonNull(getCommand("removeenchantment"))
                .setExecutor(new RemoveEnchantCommand(this));
    }

    @Override
    public void onDisable() { }
}
