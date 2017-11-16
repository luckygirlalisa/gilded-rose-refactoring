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
            updatedItem = updateNormalItem(updatedItem);
            updatedItem = updateQualityForBackStage(updatedItem);
            updatedItem = updateQualityForAgedBrie(updatedItem);

            updatedItem = updateSellInForSulfuras(updatedItem);

            updatedItems.add(updateWhenNoSellin(updatedItem));
        }
    }

    private Item updateWhenNoSellin(Item item) {
        if (item.sellIn < 0) {
            if (!item.name.equals(AGED_BRIE)) {
                if (!item.name.equals(BACKSTAGE_PASSES)) {
                    if (item.quality > 0) {
                        if (!item.name.equals("Sulfuras, Hand of Ragnaros")) {
                            item.quality = item.quality - 1;
                        }
                    }
                } else {
                    item.quality = 0;
                }
            } else {
                if (item.quality < 50) {
                    item.quality = item.quality + 1;
                }
            }
        }

        return item;
    }

    private Item updateSellInForSulfuras(Item item) {
        if (!item.name.equals(SULFURAS_HAND)) {
            item.sellIn = item.sellIn - 1;
        }
        return item;
    }

    private Item updateQualityForAgedBrie(Item item) {
        if (item.quality < 50 && item.name.equals(AGED_BRIE) ) {
            item.quality += 1;
        }

        return item;
    }

    private Item updateQualityForBackStage(Item item) {
        if (item.quality < 50 && item.name.equals(BACKSTAGE_PASSES)) {
            item.quality += 1;

            if (item.quality < 50) {
                int incrementWhenSellin11days = item.sellIn < 11 ? 1 : 0;
                int incrementWhenSellin6days = item.sellIn < 6 ? 1 + incrementWhenSellin11days: incrementWhenSellin11days;
                item.quality += incrementWhenSellin6days;
            }
        }

        return item;
    }

    private Item updateNormalItem(Item item) {
        if (!item.name.equals(AGED_BRIE)
                && !item.name.equals(BACKSTAGE_PASSES)) {
            if (item.quality > 0) {
                if (!item.name.equals(SULFURAS_HAND)) {
                    item.quality -= 1;
                }
            }
        }

        return item;
    }
}