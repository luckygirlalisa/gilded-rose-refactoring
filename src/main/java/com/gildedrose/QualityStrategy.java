package com.gildedrose;

public class QualityStrategy {

    boolean isNormalItem(Item item) {
        return !item.name.equals(GildedRose.AGED_BRIE) && !item.name.equals(GildedRose.BACKSTAGE_PASSES) && !item.name.equals(GildedRose.SULFURAS_HAND);
    }

    Item updateQualityForNormalItem(Item item) {
        if (isNormalItem(item)) {
            if (item.quality > 0) {
                item.quality -= 1;
            }
        }

        return item;
    }

    Item updateQualityForBackStage(Item item) {
        if (item.name.equals(GildedRose.BACKSTAGE_PASSES)) {
            if (item.quality < 50) {
                item.quality += 1;

                if (item.quality < 49) {
                    int incrementWhenSellin11days = item.sellIn < 11 ? 1 : 0;
                    int incrementWhenSellin6days = item.sellIn < 6 ? 1 + incrementWhenSellin11days: incrementWhenSellin11days;
                    item.quality += incrementWhenSellin6days;
                }
            }
        }

        return item;
    }

    Item updateQualityForAgedBrie(Item item) {
        if (item.quality < 50 && item.name.equals(GildedRose.AGED_BRIE) ) {
            item.quality += 1;
        }

        return item;
    }

    void udpateQualityForAgedBrieWithNoSellIn(Item item) {
        if (item.name.equals(GildedRose.AGED_BRIE) && item.sellIn < 0){
            if (item.quality < 50) {
                item.quality = item.quality + 1;
            }
        }
    }

    void updateQualityForBackstagePassesWithNoSellIn(Item item) {
        if (item.name.equals(GildedRose.BACKSTAGE_PASSES) && item.sellIn < 0) {
            item.quality = 0;
        }
    }

    void updateQualityForNormalItemWhenNoSellIn(Item item) {
        if (isNormalItem(item) && item.sellIn < 0 && item.quality > 0) {
            item.quality = item.quality - 1;
        }
    }
}
