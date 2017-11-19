package com.gildedrose;

public abstract class QualityStrategy {

    boolean isNormalItem(Item item) {
        return !item.getName().equals(GildedRose.AGED_BRIE) && !item.getName().equals(GildedRose.BACKSTAGE_PASSES) && !item.getName().equals(GildedRose.SULFURAS_HAND);
    }

    abstract Item updateQuality(Item item);

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
