package com.gildedrose;

public abstract class QualityStrategy {

    boolean isNormalItem(Item item) {
        return !item.getName().equals(GildedRose.AGED_BRIE) && !item.getName().equals(GildedRose.BACKSTAGE_PASSES) && !item.getName().equals(GildedRose.SULFURAS_HAND);
    }

    abstract Item updateQuality(Item item);

    abstract Item updateQualityWhenNoSellin(Item item);
}
