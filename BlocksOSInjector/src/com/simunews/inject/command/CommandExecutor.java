package com.simunews.inject.command;

import org.blocks.sender.CommandSender;
import org.jetbrains.annotations.NotNull;

public interface CommandExecutor {
    boolean onCommand(CommandSender sender, Command command, String label, String[] args);
    PluginCommand getPluginCommand(@NotNull String var1);
}
