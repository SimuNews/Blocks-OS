package com.stevertus.objd.basic;

import com.stevertus.objd.Widget;

public class Command extends Widget {
    protected String command;

    public Command(String _cmd) {
        command = _cmd;
        if (command != null && command.substring(0, 1).equals("/")) {
            command = command.substring(1);
        }
    }

    public String generate() {
        return command;
    }
}
