package com.gildedrose;

public class BackstagePassesStrategy extends QualityStrategy {
    Item updateQuality(Item item) {
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
}
