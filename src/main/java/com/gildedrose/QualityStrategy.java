package com.gildedrose;

public class QualityStrategy {

    boolean isNormalItem(Item item) {
        return !item.getName().equals(GildedRose.AGED_BRIE) && !item.getName().equals(GildedRose.BACKSTAGE_PASSES) && !item.getName().equals(GildedRose.SULFURAS_HAND);
    }

    Item updateQuality(Item item) {
        if (isNormalItem(item)) {
            updateQualityForNormalItem(item);
        } else if (item.getName().equals(GildedRose.BACKSTAGE_PASSES)) {
            updateQualityForBackStage(item);
        } else if (item.getName().equals(GildedRose.AGED_BRIE)) {
            updateQualityForAgedBrie(item);
        }

        return item;
    }
    Item updateQualityForNormalItem(Item item) {
        if (isNormalItem(item)) {
            if (item.getQuality() > 0) {
                item.setQuality(item.getQuality() - 1);
            }
        }

        return item;
    }

    Item updateQualityForBackStage(Item item) {
        if (item.getName().equals(GildedRose.BACKSTAGE_PASSES)) {
            if (item.getQuality() < 50) {
                item.setQuality(item.getQuality() + 1);

                if (item.getQuality() < 49) {
                    int incrementWhenSellin11days = item.sellIn < 11 ? 1 : 0;
                    int incrementWhenSellin6days = item.sellIn < 6 ? 1 + incrementWhenSellin11days: incrementWhenSellin11days;
                    item.setQuality(item.getQuality() + incrementWhenSellin6days);
                }
            }
        }

        return item;
    }

    Item updateQualityForAgedBrie(Item item) {
        if (item.getQuality() < 50 && item.getName().equals(GildedRose.AGED_BRIE) ) {
            item.setQuality(item.getQuality() + 1);
        }

        return item;
    }

    void udpateQualityForAgedBrieWithNoSellIn(Item item) {
        if (item.getName().equals(GildedRose.AGED_BRIE) && item.sellIn < 0){
            if (item.getQuality() < 50) {
                item.setQuality(item.getQuality() + 1);
            }
        }
    }

    void updateQualityForBackstagePassesWithNoSellIn(Item item) {
        if (item.getName().equals(GildedRose.BACKSTAGE_PASSES) && item.sellIn < 0) {
            item.setQuality(0);
        }
    }

    void updateQualityForNormalItemWhenNoSellIn(Item item) {
        if (isNormalItem(item) && item.sellIn < 0 && item.getQuality() > 0) {
            item.setQuality(item.getQuality() - 1);
        }
    }
}
