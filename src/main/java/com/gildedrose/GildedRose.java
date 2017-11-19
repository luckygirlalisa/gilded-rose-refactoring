package com.gildedrose;

import java.util.ArrayList;
import java.util.List;

class GildedRose {
    public static final String AGED_BRIE = "Aged Brie";
    public static final String BACKSTAGE_PASSES = "Backstage passes to a TAFKAL80ETC concert";
    public static final String SULFURAS_HAND = "Sulfuras, Hand of Ragnaros";
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        List<Item> updatedItems = new ArrayList<>();
        for (Item item : items) {
            Item updatedItem = item;
            updatedItem = updatedItem.getQualityStrategy().updateQualityForNormalItem(updatedItem);
            updatedItem = updatedItem.getQualityStrategy().updateQualityForBackStage(updatedItem);
            updatedItem = updatedItem.getQualityStrategy().updateQualityForAgedBrie(updatedItem);


            updatedItem = updatedItem.updateSellInForNotSulfuras();
            updatedItems.add(updatedItem.updateQualityWhenNoSellin());
        }
    }

}