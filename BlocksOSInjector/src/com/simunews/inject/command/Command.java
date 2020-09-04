package com.simunews.inject.command;

import org.blocks.sender.CommandSender;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public abstract class Command {
    private String name;
    private String nextLabel;
    private String label;
    private List<String> aliases;
    private List<String> activeAliases;
    protected String description;
    protected String usageMessage;
    private String permission;
    private String permissionMessage;

    protected Command(String name) {
        this(name, "", "/" + name, new ArrayList());
    }

    protected Command(String _name, String _description, String usage_message, List<String> _aliases) {
        this.name = _name;
        this.nextLabel = _name;
        this.label = _name;
        this.description = description == null ? "" : _description;
        this.usageMessage = usageMessage == null ? "/" + _name : usage_message;
        this.aliases = _aliases;
        this.activeAliases = new ArrayList(aliases);
    }

    public abstract boolean execute(@NotNull CommandSender var1, @NotNull String var2, @NotNull String[] var3);

    public void setPermission(@Nullable String _permission) {
        this.permission = _permission;
    }
    public void setPermissionMessage(@Nullable String permission_message) { this.permissionMessage = permission_message; }

    public boolean testPermission(CommandSender _target) {

        return false;
    }

    public boolean testPermissionSilent(CommandSender _target) {

        return false;
    }

}
