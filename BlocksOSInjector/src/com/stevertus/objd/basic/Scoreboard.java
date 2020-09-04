package com.stevertus.objd.basic;

public class Scoreboard extends Widget {

    public static String prefix;

    private String subCmd = "add";
    private String name;
    private String type = "dummy";
    private String setDisplay = "sidebar";
    private TextComponent display = null;

    public static Scoreboard trigger(String _name, TextComponent _display) {
        return new Scoreboard(_name).type("trigger").display(_display);
    }
    public static Scoreboard click(String _name, TextComponent _display) {
        return new Scoreboard(_name).type("minecraft.used:minecraft.carrot_on_a_stick").display(_display);
    }
    public static Scoreboard remove(String _name, TextComponent _display) {
        return new Scoreboard(_name).subCmd("remove");
    }
    public static Scoreboard setDisplay(String _name) {
        return new Scoreboard(_name).subCmd("setDisplay");
    }
    public static Scoreboard setDisplay(String _name, String set_display) {
        return new Scoreboard(_name).subCmd("setDisplay").set_display(set_display);
    }

    public Scoreboard(String _name) {
        name = _name;
        subCmd = "add";
        prefixName();
    }

    public Scoreboard type(String _type) {
        type = _type;
        return this;
    }
    public Scoreboard display(TextComponent _display) {
        display = _display;
        if (display != null) type += ' ' + display.toJson();
        return this;
    }
    private Scoreboard subCmd(String sub_cmd) {
        subCmd = sub_cmd;
        return this;
    }
    private Scoreboard set_display(String set_display) {
        setDisplay = set_display;
        return this;
    }

    private void prefixName() {
        if(prefix != null && !name.contains(prefix)) name = prefix + name;
    }

    @Override
    public String generate() {
        switch (subCmd) {
            case "add":
                return "scoreboard objectives add " + name + " " + type;
            case "remove":
                return "scoreboard objectives remove " + name;
            case "setDisplay":
                return "scoreboard objectives setdisplay " + setDisplay + " " + name;
        }
        return "say #Error @ Scoreboard in Line " + "1";
    }
}
