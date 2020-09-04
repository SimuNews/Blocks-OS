package com.simunews.inject.plugin;

public class EnablePlugin {

    public EnablePlugin(Plugin _plugin) {
        for (int i = 0; i < _plugin.spl.length(); i++) {
            System.out.println(_plugin.spl.executeMethod("onEnable", i));
        }

    }

}
