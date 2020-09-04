package com.simunews;

import com.stevertus.objd.basic.Widget;
import com.simunews.blocks.*;
import org.jnbt.*;

import java.util.*;

public class BuildNBT {

    //Lists for the nbt File
    private List<Tag> mobList = new LinkedList<>();
    private List<Tag> blockList = new LinkedList<>();
    private List<Tag> paletteList = new LinkedList<>();
    private List<Tag> sizeList = new LinkedList<>();

    //CompoundTag
    private CompoundTagBuilder ctb = new CompoundTagBuilder();

    //Widget list
    private List<Widget> widgets;

    public BuildNBT(List<Widget> _widgets) {
        widgets = _widgets;
        sizeList.add(new IntTag("x", 1));
        sizeList.add(new IntTag("y", widgets.size()));
        sizeList.add(new IntTag("z", 1));

        fillPalette();
    }

    private void fillPalette() {
        paletteList.add(new CompoundTagBuilder().putString("Name", "minecraft:command_block").put("Properties", new CompoundTagBuilder().putString("facing", "up").build("Properties")).build());
        paletteList.add(new CompoundTagBuilder().putString("Name", "minecraft:chain_command_block").put("Properties", new CompoundTagBuilder().putString("facing", "up").build("Properties")).build());
        paletteList.add(new CompoundTagBuilder().putString("Name", "minecraft:repeating_command_block").put("Properties", new CompoundTagBuilder().putString("facing", "up").build("Properties")).build());
        paletteList.add(new CompoundTagBuilder().putString("Name", "minecraft:structure_block").put("Properties", new CompoundTagBuilder().putString("mode", "load").build("Properties")).build());
        paletteList.add(new CompoundTagBuilder().putString("Name", "minecraft:redstone_block").build());
    }

    //build
    public CompoundTag build() {
        List<List<Tag>> posList = new LinkedList<>();
        int state = 0;

        //Structure block with load++
        blockList.add(new StructureBlock("nbt_test0").build());
        blockList.add(new CommandBlock(FACING.UP, widgets.get(0), true).Y(0).build());

        for (int i = 1; i < widgets.size(); i++) {
            blockList.add(new ChainCommandBlock(FACING.UP, widgets.get(i), true).Y(i).build());
        }
        ctb.put("blocks", new ListTag("blocks", CompoundTag.class, blockList));
        ctb.put("palette", new ListTag("palette", CompoundTag.class, paletteList));
        ctb.put("entities", new ListTag("entities", CompoundTag.class, mobList));
        ctb.put("size", new ListTag("size", IntTag.class, sizeList));
        ctb.putInt("DataVersion", 984);
        ctb.putString("author", "SimuNews Softwares");
        return ctb.build();
    }

    public CompoundTag build(String _name) {
        return ctb.build(_name);
    }

}
