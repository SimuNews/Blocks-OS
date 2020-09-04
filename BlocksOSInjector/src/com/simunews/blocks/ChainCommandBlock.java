package com.simunews.blocks;

import com.simunews.BuildList;
import com.simunews.CompoundTagBuilder;
import com.stevertus.objd.basic.Widget;
import org.jnbt.*;

public class ChainCommandBlock extends CommandBlock {
    public ChainCommandBlock(String facing, Widget cmd) {
        super(facing, cmd);
    }
    public ChainCommandBlock(String facing, Widget cmd, boolean auto) {
        super(facing, cmd, auto);
    }

    public CommandBlock X(int _x) {
        x = _x;
        return this;
    }
    public CommandBlock Y(int _y) {
        y = _y;
        return this;
    }
    public CommandBlock Z(int _z) {
        z = _z;
        return this;
    }

    public CompoundTag build() {
        CompoundTagBuilder ctb = new CompoundTagBuilder();
        CompoundTagBuilder nbt = new CompoundTagBuilder();

        ctb.put("pos", new ListTag("pos", IntTag.class, BuildList.pos(x,y,z)));

        //NBT
        if(auto) nbt.putByte("auto", (byte) 1);
        else nbt.putByte("auto", (byte) 0);
        nbt.putString("Command", cmd);
        ctb.put("nbt", nbt.build("nbt"));
        ctb.putInt("state", 1);

        return ctb.build();
    }
}
