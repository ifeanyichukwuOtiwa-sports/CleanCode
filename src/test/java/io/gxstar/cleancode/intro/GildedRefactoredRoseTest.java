package io.gxstar.cleancode.intro;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import io.gxstar.cleancode.intro.GildedRose;
import io.gxstar.cleancode.intro.Item;

class GildedRefactoredRoseTest {

    public static final String DEFAULT_ITEM = "DEFAULT_ITEM";
    private static final String BACKSTAGE_NAME = "Backstage passes to a TAFKAL80ETC concert";
    private static final String AGED_BRIE_NAME = "Aged Brie";
    public static final int NOT_EXPIRED_SELLIN = 15;
    public static final int NOT_EXPIRED_SELLIN_LESS_THAN6 = 4;
    public static final int DEFAULT_QUALITY = 3;
    public static final int EXPIRED_SELLIN = -1;
    private static final int MAX_VALID_QUALITY = 50;
    private static final int NOT_EXPIRED_SELLIN_ABOVE6 = 7;


    /**
     * Method to test the variation in quality of the item for the non expired
     * Item.
     * <p>
     * The quality should decrease by 1 when the item is not expired
     * and sell in should decrease by 1.
     */
    @Test
    void testNonExpiredDefaultItem_shouldDecreaseQualityBy1() {
        //setup
        GildedRose app = createGildedRoseWithOneItem(DEFAULT_ITEM, NOT_EXPIRED_SELLIN, DEFAULT_QUALITY);

        //invoke
        app.updateQuality();

        //verify
        final Item actualItem = app.items[0];
        final Item expectedItem = new Item(DEFAULT_ITEM, NOT_EXPIRED_SELLIN - 1, DEFAULT_QUALITY - 1);


        assertItem(actualItem, expectedItem);
    }

    /**
     * Method to test the variation in quality of the item for the non expired
     * Item.
     * <p>
     * The quality should decrease by 2 when the item is expired(Sell in  < 0) and sell in should decrease by 1.
     */
    @Test
    void testExpiredDefaultItem_shouldDecreaseQualityBy2() {
        //setup
        GildedRose app = createGildedRoseWithOneItem(DEFAULT_ITEM, EXPIRED_SELLIN, DEFAULT_QUALITY);

        app.updateQuality();

        final Item actualItem = app.items[0];
        final Item expectedItem = new Item(DEFAULT_ITEM, EXPIRED_SELLIN - 1, DEFAULT_QUALITY - 2);

        assertItem(actualItem, expectedItem);
    }




    @DisplayName("1.")
    @Test
    void testNonExpiredAgedBrieItem_shouldIncreaseQualityByOne() {
        //setup
        GildedRose app = createGildedRoseWithOneItem(AGED_BRIE_NAME, NOT_EXPIRED_SELLIN, DEFAULT_QUALITY);
        //invoke
        app.updateQuality();

        final Item actualItem = app.items[0];
        final Item expectedItem = new Item(AGED_BRIE_NAME, NOT_EXPIRED_SELLIN - 1, DEFAULT_QUALITY + 1);

        //test
        assertItem(expectedItem, actualItem);

    }

    @DisplayName("2.")
    @Test
    void testExpiredAgedBrieItem_shouldDecreaseQualityByTwo() {
        GildedRose app = createGildedRoseWithOneItem(AGED_BRIE_NAME, EXPIRED_SELLIN, DEFAULT_QUALITY);


        app.updateQuality();

        final Item actualItem = app.items[0];
        final Item expectedItem = new Item(AGED_BRIE_NAME, EXPIRED_SELLIN - 1, DEFAULT_QUALITY + 2);

        assertItem(expectedItem, actualItem);
    }

    @DisplayName("3.")
    @Test
    void testItemWithValidMaxQuality_qualityShouldNotChange() {

        GildedRose app = createGildedRoseWithOneItem(AGED_BRIE_NAME, NOT_EXPIRED_SELLIN, MAX_VALID_QUALITY);

        app.updateQuality();

        final Item actualItem = app.items[0];
        final Item expectedItem = new Item(AGED_BRIE_NAME, NOT_EXPIRED_SELLIN - 1, MAX_VALID_QUALITY);

        assertItem(expectedItem, actualItem);
    }


    @Test
    void testUpdateQualityBackstagePasses1() {
        GildedRose app = createGildedRoseWithOneItem(BACKSTAGE_NAME, NOT_EXPIRED_SELLIN, DEFAULT_QUALITY);
        app.updateQuality();

        final Item actual = app.items[0];
        final Item expected = new Item(BACKSTAGE_NAME, NOT_EXPIRED_SELLIN - 1, DEFAULT_QUALITY + 1);

        assertItem(expected, actual);
    }

    @Test
    void testNonExpiredBackstagePasses_shouldIncreaseQualityBy2() {
        GildedRose app = createGildedRoseWithOneItem(BACKSTAGE_NAME, NOT_EXPIRED_SELLIN_ABOVE6, DEFAULT_QUALITY);

        final Item actual = app.items[0];
        final Item expected = new Item(BACKSTAGE_NAME, NOT_EXPIRED_SELLIN_ABOVE6 - 1, DEFAULT_QUALITY + 2);

        app.updateQuality();

        assertItem(expected, actual);

    }

    @Test
    public void testUpdateQualityBackstagePasses3() {

        GildedRose app = createGildedRoseWithOneItem(BACKSTAGE_NAME, NOT_EXPIRED_SELLIN_LESS_THAN6, DEFAULT_QUALITY);
        app.updateQuality();

        final Item actualItem = app.items[0];
        final Item expectedItem = new Item(BACKSTAGE_NAME, NOT_EXPIRED_SELLIN_LESS_THAN6 - 1, DEFAULT_QUALITY + 3);

        assertEquals("Backstage passes to a TAFKAL80ETC concert",
                app.items[0].name);
        assertEquals(3, app.items[0].sellIn);
        assertEquals(6, app.items[0].quality);
    }




    private static GildedRose createGildedRoseWithOneItem(final String itemType, final int sellin, final int quality) {
        Item item = new Item(itemType, sellin, quality);
        Item[] items = new Item[]{item};
        return new GildedRose(items);
    }

    private static void assertItem(final Item actualItem, final Item expectedItem) {
        assertEquals(expectedItem.name, actualItem.name);
        assertEquals(expectedItem.sellIn, actualItem.sellIn);
        assertEquals(expectedItem.quality, actualItem.quality);
    }

}