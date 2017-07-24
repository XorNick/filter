package net.xornick.chatfilter;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class ChatFilter extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
    }

    @EventHandler
    public void onAsyncPlayerChat(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        String message = event.getMessage().toLowerCase();

        for (String filtered : getConfig().getStringList("filtered")) {
            if (message.contains(filtered)) {
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', getConfig().getString("NO_PERMISSION")));
                event.setCancelled(true);
            }
        }
    }
}
