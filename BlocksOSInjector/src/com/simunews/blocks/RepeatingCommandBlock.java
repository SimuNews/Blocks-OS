package com.simunews.blocks;

import com.stevertus.objd.basic.Command;

public class RepeatingCommandBlock extends CommandBlock {
    public RepeatingCommandBlock(Command _cmd) {
        super(FACING.UP, _cmd);
    }
}
