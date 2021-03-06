package com.gildedrose;

public class BackstagePassesStrategy extends QualityStrategy {
    @Override
    Item updateQuality(Item item) {
        if (item.getQuality() < 50) {
            item.setQuality(item.getQuality() + 1);

            if (item.getQuality() < 49) {
                int incrementWhenSellin11days = item.sellIn < 11 ? 1 : 0;
                int incrementWhenSellin6days = item.sellIn < 6 ? 1 + incrementWhenSellin11days: incrementWhenSellin11days;
                item.setQuality(item.getQuality() + incrementWhenSellin6days);
            }
        }

        return item;
    }

    @Override
    Item updateQualityWhenNoSellin(Item item) {
        if (item.sellIn < 0) {
            item.setQuality(0);
        }

        return item;
    }
}
