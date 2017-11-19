package com.gildedrose;

public class AgedBrieQualityStrategy extends QualityStrategy {
    @Override
    Item updateQuality(Item item) {
        if (item.getQuality() < 50 && item.getName().equals(GildedRose.AGED_BRIE) ) {
            item.setQuality(item.getQuality() + 1);
        }

        return item;
    }

    @Override
    Item updateQualityWhenNoSellin(Item item) {
        if (item.getName().equals(GildedRose.AGED_BRIE) && item.sellIn < 0){
            if (item.getQuality() < 50) {
                item.setQuality(item.getQuality() + 1);
            }
        }

        return item;
    }
}
