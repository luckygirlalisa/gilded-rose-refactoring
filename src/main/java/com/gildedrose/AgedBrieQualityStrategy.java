package com.gildedrose;

public class AgedBrieQualityStrategy extends QualityStrategy {
    Item updateQuality(Item item) {
        if (item.getQuality() < 50 && item.getName().equals(GildedRose.AGED_BRIE) ) {
            item.setQuality(item.getQuality() + 1);
        }

        return item;
    }
}
