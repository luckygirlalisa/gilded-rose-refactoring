package com.gildedrose;

public class NormalQualityStrategy extends QualityStrategy {
    Item updateQuality(Item item) {
        if (isNormalItem(item)) {
            if (item.getQuality() > 0) {
                item.setQuality(item.getQuality() - 1);
            }
        }

        return item;
    }
}
