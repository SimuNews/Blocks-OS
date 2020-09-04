package com.simunews.inject.command;

import com.simunews.inject.plugin.Plugin;
import org.blocks.sender.CommandSender;

public class PluginCommand extends Command {
    private final Plugin owningPlugin;
    private CommandExecutor executor;

    protected PluginCommand(String _name, Plugin _owner) {
        super(_name);
        this.executor = _owner;
        this.owningPlugin = _owner;
        this.usageMessage = "";
    }

    @Override
    public boolean execute(CommandSender _sender, String _label, String[] _args) {

        return false;
    }

    public Plugin getPlugin() {
        return owningPlugin;
    }

}
