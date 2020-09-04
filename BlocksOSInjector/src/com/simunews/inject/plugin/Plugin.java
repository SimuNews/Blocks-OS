package com.simunews.inject.plugin;

import com.simunews.inject.command.Command;
import com.simunews.inject.command.CommandExecutor;
import com.simunews.inject.command.PluginCommand;
import com.sun.istack.internal.NotNull;
import org.blocks.sender.CommandSender;
import org.blocks.sender.Player;
import org.jnbt.Tag;

import java.util.Locale;

public abstract class Plugin implements CommandExecutor {
    private PluginDescriptionFile description;

    public PluginCommand getCommand(@NotNull String name) {
        String alias = name.toLowerCase(Locale.ENGLISH);
        PluginCommand command = getPluginCommand(alias);
        if (command == null || command.getPlugin() != this) {
            command = getPluginCommand(description.getName().toLowerCase(Locale.ENGLISH) + ":" + alias);
        }

        return command != null && command.getPlugin() == this ? command : null;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        return false;
    }


    /*MOVE*/
    public PluginCommand getPluginCommand(String name) {
        Command command = null; /*this.commandMap.getCommand(name);*/
        return command instanceof PluginCommand ? (PluginCommand)command : null;
    }
}
