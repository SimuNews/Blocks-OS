package com.simunews.blocks;

import com.simunews.BuildList;
import com.simunews.BuildNBT;
import com.simunews.CompoundTagBuilder;
import org.jnbt.*;

import java.util.Vector;

public class StructureBlock {

    public static class MODE {
        public static String LOAD = "LOAD";
        public static String SAVE = "SAVE";
        public static String CORNER = "CORNER";
    }

    private String cmd;
    private String mode = MODE.LOAD;
    private int posX = 0, posY = 1, posZ = 0;   //nbt posX, posY, posZ
    private int x = 0, y = -1, z = 0;           //offset x, y, z

    public StructureBlock(String _cmd) {
        cmd = _cmd;
    }
    public StructureBlock mode(String _mode) {
        this.mode = _mode;
        return this;
    }
    public StructureBlock x(int _x) {
        this.x = _x;
        return this;
    }
    public StructureBlock y(int _y) {
        this.y = _y;
        return this;
    }
    public StructureBlock z(int _z) {
        this.z = _z;
        return this;
    }
    public StructureBlock posX(int pos_x) {
        this.posX = pos_x;
        return this;
    }
    public StructureBlock posY(int pos_y) {
        this.posY = pos_y;
        return this;
    }
    public StructureBlock posZ(int pos_z) {
        this.posZ = pos_z;
        return this;
    }

    public CompoundTag build() {
        CompoundTagBuilder ctb = new CompoundTagBuilder();
        CompoundTagBuilder nbt = new CompoundTagBuilder();

        ctb.put("pos", new ListTag("pos", IntTag.class, BuildList.pos(x,y,z)));
        ctb.putInt("state", 3);
        nbt.putString("name", cmd);
        nbt.putString("mode", mode);
        nbt.putString("author", "SimuNews Softwares");
        nbt.putInt("posX", posX);
        nbt.putInt("posY", posY);
        nbt.putInt("posZ", posZ);
        ctb.put("nbt", nbt.build("nbt"));

        return ctb.build();
    }

}
