package com.stevertus.objd.basic;

import com.stevertus.objd.Widget;

public class Say extends Widget {
    private String value;

    public Say(String value) {
        this.value = value;
    }

    @Override
    public String generate() {
        return "say " + value;
    }
}
