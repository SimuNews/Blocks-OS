package com.simunews.inject.command;

import com.sun.istack.internal.NotNull;
import org.blocks.sender.CommandSender;

public interface CommandExecutor {
    boolean onCommand(CommandSender sender, Command command, String label, String[] args);
    PluginCommand getPluginCommand(@NotNull String var1);
}
