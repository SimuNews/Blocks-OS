package com.simunews;

import org.jnbt.IntTag;
import org.jnbt.Tag;

import java.util.LinkedList;
import java.util.List;

public class BuildList {
    public static List pos(int _x, int _y, int _z) {
        List<Tag> pos = new LinkedList<>();
        pos.add(new IntTag("x", _x));
        pos.add(new IntTag("y", _y));
        pos.add(new IntTag("z", _z));
        return pos;
    }
}
