package com.gildedrose;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

import org.junit.Test;

public class GildedRoseTest {
    public static final String SULFURAS = "Sulfuras, Hand of Ragnaros";
    public static final String AGED_BRIE = "Aged Brie";
    public static final String BACKSTAGE_PASSES = "Backstage passes to a TAFKAL80ETC concert";
    public static final String NORMAL = "Normal";
    
    @Test
    public void at_the_end_of_each_day_our_system_lowers_sellIn_and_quality_values_by_1_if_sellIn_is_greater_than_0_for_normal_items() {
        Item item = new Item(NORMAL, 1, 20);

        Item[] items = new Item[] { item };
        GildedRose app = new GildedRose(items);

        app.updateQuality();

        assertThat(items[0].sellIn, is(0));
        assertThat(items[0].getQuality(), is(19));
    }

    @Test
    public void once_the_sell_by_date_has_passed_quality_of_normal_items_degrades_twice_as_fast() {
        Item item = new Item(NORMAL, 0, 20);

        Item[] items = new Item[] { item };
        GildedRose app = new GildedRose(items);
        app.updateQuality();

        assertThat(items[0].sellIn, is(-1));
        assertThat(items[0].getQuality(), is(18));
    }

    @Test
    public void the_quality_of_normal_items_should_be_greater_than_or_equal_to_0() {
        Item item = new Item(NORMAL, 0, 0);

        Item[] items = new Item[] { item };
        GildedRose app = new GildedRose(items);
        app.updateQuality();

        assertThat(items[0].sellIn, is(-1));
        assertThat(items[0].getQuality(), is(0));
    }

    @Test
    public void the_Aged_Brie_actually_increases_in_Quality_by_1_the_older_it_gets() {
        Item item = new Item(AGED_BRIE, 0, 0);

        Item[] items = new Item[] { item };
        GildedRose app = new GildedRose(items);
        app.updateQuality(); 
        

        assertThat(items[0].sellIn, is(-1));
        assertThat(items[0].getQuality(), is(2));
    }

    @Test
    public void the_quality_of_Aged_Brie_should_not_exceed_50() {
        Item item = new Item(AGED_BRIE, -1, 50);

        Item[] items = new Item[] { item };
        GildedRose app = new GildedRose(items);
        app.updateQuality();

        assertThat(items[0].sellIn, is(-2));
        assertThat(items[0].getQuality(), is(50));
    }

    @Test
    public void the_Sulfuras_is_a_legendary_item_and_as_such_its_Quality_is_80_and_it_never_alters() {
        Item item = new Item(SULFURAS, -1, 80);

        Item[] items = new Item[] { item };
        GildedRose app = new GildedRose(items);
        app.updateQuality();

        assertThat(items[0].sellIn, is(-1));
        assertThat(items[0].getQuality(), is(80));
    }

    @Test
    public void the_quality_of_backstage_passes_increases_by_1_when_the_sellIn_are_more_than_10_days() {
        Item item = new Item(BACKSTAGE_PASSES, 11, 20);

        Item[] items = new Item[] { item };
        GildedRose app = new GildedRose(items);
        app.updateQuality();

        assertThat(items[0].sellIn, is(10));
        assertThat(items[0].getQuality(), is(21));
    }

    @Test
    public void the_quality_of_backstage_passes_increases_by_2_when_the_sellIn_are_between_5_and_10_days() {
        Item item = new Item(BACKSTAGE_PASSES, 10, 20);

        Item[] items = new Item[] { item };
        GildedRose app = new GildedRose(items);
        app.updateQuality();

        assertThat(items[0].sellIn, is(9));
        assertThat(items[0].getQuality(), is(22));
    }

    @Test
    public void the_quality_of_backstage_passes_increases_by_3_when_the_sellIn_are_between_0_and_5_days() {
        Item item = new Item(BACKSTAGE_PASSES, 5, 20);


        Item[] items = new Item[] { item };
        GildedRose app = new GildedRose(items);
        app.updateQuality();

        assertThat(items[0].sellIn, is(4));
        assertThat(items[0].getQuality(), is(23));
    }

    @Test
    public void the_quality_of_backstage_passes_drops_to_0_after_the_concert() {
        Item item = new Item(BACKSTAGE_PASSES, 0, 20);

        Item[] items = new Item[] { item };
        GildedRose app = new GildedRose(items);
        app.updateQuality();

        assertThat(items[0].sellIn, is(-1));
        assertThat(items[0].getQuality(), is(0));
    }

    @Test
    public void the_quality_of_backstage_passes_is_not_greater_than_50() {
        Item item = new Item(BACKSTAGE_PASSES, 1, 50);


        Item[] items = new Item[] { item };
        GildedRose app = new GildedRose(items);
        app.updateQuality();

        assertThat(items[0].sellIn, is(0));
        assertThat(items[0].getQuality(), is(50));
    }

}
