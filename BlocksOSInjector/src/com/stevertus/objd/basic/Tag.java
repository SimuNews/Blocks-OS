package com.stevertus.objd.basic;

import com.stevertus.objd.basic.types.Entity;

public class Tag extends Widget {

    private String tag;
    private Entity entity = new Entity();
    private boolean value = true;


    public Tag(String _tag) {
        tag = _tag;
    }
    public Tag entity(Entity _entity) {
        entity = _entity;
        return this;
    }
    public Tag value(boolean _value) {
        value = _value;
        return this;
    }

    @Override
    public String generate() {
        if(value) {
            return "tag " + entity.toString() + " add " + tag;
        } else {
            return "tag " + entity.toString() + " remove " + tag;
        }
    }
}
