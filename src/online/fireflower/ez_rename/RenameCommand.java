package online.fireflower.ez_rename;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class RenameCommand implements CommandExecutor {


    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        if (!(commandSender instanceof Player)){
            commandSender.sendMessage("You must be a player to use this command");
            return true;
        }

        if (strings.length == 0)
            return false;

        Player player = (Player)commandSender;
        ItemStack playerItem = player.getItemInHand();

        if (playerItem == null)
            return true;

        ItemMeta itemMeta = playerItem.getItemMeta();
        itemMeta.setDisplayName(EzRename.parseColoredText(String.join(" ", strings)));
        playerItem.setItemMeta(itemMeta);

        player.sendMessage(ChatColor.GREEN + "Item renamed");
        return true;
    }




}
