package org.blocks.sender;

import java.util.List;

public class Player extends CommandSender {
    private String name;
    private List<Double> pos;

    public Player(String _name) {
        name = _name;
    }

    public String getName() { return name; }

}
