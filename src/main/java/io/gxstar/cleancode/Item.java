package io.gxstar.cleancode;

/**
 * Created by @author Ifeanyichukwu Otiwa
 * 21/08/2022
 */

public class Item {

    public String name;

    public int sellIn;

    public int quality;

    public Item(String name, int sellIn, int quality) {
        this.name = name;
        this.sellIn = sellIn;
        this.quality = quality;
    }

    @Override
    public String toString() {
        return "\n name=" + name + ", " + sellIn + ", " + quality + "\n" ;
    }
}
