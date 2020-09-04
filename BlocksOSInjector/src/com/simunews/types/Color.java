package com.simunews.types;

public class Color {
    private final String color;

    static public Color fromInt(int _color) {
        return new Color("#" + Integer.toHexString(_color));
    }
    static public Color fromRgb(int _r, int _g, int _b) {
        _r = (_r < 0) ? -_r : _r;
        _g = (_g < 0) ? -_g : _g;
        _b = (_b < 0) ? -_b : _b;
        _r = Math.min(_r, 255);
        _g = Math.min(_g, 255);
        _b = Math.min(_b, 255);
        return Color.fromInt(
                ((_r & 0x0ff) << 16) | ((_g & 0x0ff) << 8) | (_b & 0x0ff)
        );
    }

    public Color(String _color) {
        this.color = _color;
    }

    @Override
    public String toString() {
        return color;
    }

    static Color White = new Color("white");
    static Color Black = new Color("black");
    static Color DarkBlue = new Color("dark_blue");
    static Color DarkGreen = new Color("dark_green");
    static Color DarkAqua = new Color("dark_aqua");
    static Color DarkRed = new Color("dark_red");
    static Color DarkPurple = new Color("dark_purple");
    static Color Gold = new Color("gold");
    static Color Gray = new Color("gray");
    static Color DarkGray = new Color("dark_gray");
    static Color Blue = new Color("blue");
    static Color Green = new Color("green");
    static Color Aqua = new Color("aqua");
    static Color Red = new Color("red");
    static Color LightPurple = new Color("light_purple");
    static Color Yellow = new Color("yellow");
}