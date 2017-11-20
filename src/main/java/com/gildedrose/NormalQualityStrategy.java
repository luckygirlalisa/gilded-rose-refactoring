package com.gildedrose;

public class NormalQualityStrategy extends QualityStrategy {
    @Override
    Item updateQuality(Item item) {
        if (item.getQuality() > 0) {
            item.setQuality(item.getQuality() - 1);
        }

        return item;
    }

    @Override
    Item updateQualityWhenNoSellin(Item item) {
        if (item.sellIn < 0 && item.getQuality() > 0) {
            item.setQuality(item.getQuality() - 1);
        }

        return item;
    }
}
