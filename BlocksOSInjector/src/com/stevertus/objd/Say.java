package com.stevertus.objd;

import com.stevertus.objd.basic.Widget;

public class Say extends Widget {
    private String value;

    public Say(String value) {
        this.value = value;
    }

    @Override
    public String generate() {
        return "..asd  /  say " + value;
    }
}
