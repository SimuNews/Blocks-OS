package com.simunews.types;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Item {
    private String type;
    private int count = 1;
    private int damage = 0;
    private Slot slot;
    private Map<String, String> tag;

    Map<String, String> t = new TreeMap<>();

    public Item(String _type) {
        type = _type;
    }
    public Item count(int _count) {
        count = _count;
        return this;
    }
    public Item damage(int _damage) {
        damage = _damage;
        return this;
    }
    public Item slot(Slot _slot) {
        slot = _slot;
        return this;
    }
    public Item nbt(Map<String, String> _nbt) {
        tag = _nbt;
        return this;
    }
    private void _checkTags(int _model, String _type, int hide_flags, String _name, List<String> _lore) {
        if(tag != null && !tag.isEmpty()) t.putAll(tag);
        if(damage != 0) t.put("Damage", String.valueOf(damage));
        if(_model != 0) t.put("CustomModelData", String.valueOf(_model));
        if(hide_flags != 0) t.put("HideFlags", String.valueOf(hide_flags));
        if(_name != null) {
            t.put("display", "");
            //t.put("display", _name.toJson());
        }
    }

    public String getGiveNotation(boolean with_damage) {
        String result = type;
        if(tag != null && !tag.isEmpty()) {
            result += tag;
        }
        result += " " + String.valueOf(count);
        if(with_damage) result += " " + String.valueOf(damage);
        return result;
    }

    public String getId() {
        return type.replaceFirst("", "minecraft:");
    }
}
