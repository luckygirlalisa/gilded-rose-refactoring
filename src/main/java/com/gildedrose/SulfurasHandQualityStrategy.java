package com.gildedrose;

public class SulfurasHandQualityStrategy extends QualityStrategy {
    @Override
    Item updateQuality(Item item) {
        return item;
    }

    @Override
    Item updateQualityWhenNoSellin(Item item) {
        return item;
    }
}
