package io.gxstar.cleancode;

/**
 * Created by @author Ifeanyichukwu Otiwa
 * 21/08/2022
 */

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class GildedRoseADefaultItemTest {
    /**
     * Method to test the variation in quality of the item for the non expired
     * Item.
     * <p>
     * The quality should decrease by 1 when the item is not expired
     * and sell in should decrease by 1.
     */
    @Test
    public void testUpdateQualityDefault1() {
        Item item = new Item("DEFAULT_ITEM", 15, 3);
        Item[] items = new Item[]{item};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals("DEFAULT_ITEM", app.items[0].name);
        assertEquals(14, app.items[0].sellIn);
        assertEquals(2, app.items[0].quality);
    }

    /**
     * Method to test the variation in quality of the item for the non expired
     * Item.
     * <p>
     * The quality should decrease by 2 when the item is expired(Sell in  < 0) and sell in should decrease by 1.
     */
    @Test
    void testUpdateQualityForExpiredItem() {
        Item item = new Item("DEFAULT_ITEM", -1, 3);
        Item[] items = new Item[]{item};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals("DEFAULT_ITEM", app.items[0].name);
        assertEquals(-2, app.items[0].sellIn);
        assertEquals(1, app.items[0].quality);
    }
}