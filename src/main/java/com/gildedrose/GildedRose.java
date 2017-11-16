package com.gildedrose;

class GildedRose {
    public static final String AGED_BRIE = "Aged Brie";
    public static final String BACKSTAGE_PASSES = "Backstage passes to a TAFKAL80ETC concert";
    public static final String SULFURAS_HAND = "Sulfuras, Hand of Ragnaros";
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (int i = 0; i < items.length; i++) {
            updateNormalItem(items[i]);

            updateQualityForBackStage(items[i]);
            updateQualityForAgedBrie(items[i]);

            updateSulfurasSellIn(items[i]);

            updateWhenNoSellin(items[i]);
        }
    }

    private void updateWhenNoSellin(Item item) {
        if (item.sellIn < 0) {
            if (!item.name.equals(AGED_BRIE)) {
                if (!item.name.equals(BACKSTAGE_PASSES)) {
                    if (item.quality > 0) {
                        if (!item.name.equals("Sulfuras, Hand of Ragnaros")) {
                            item.quality = item.quality - 1;
                        }
                    }
                } else {
                    item.quality = item.quality - item.quality;
                }
            } else {
                if (item.quality < 50) {
                    item.quality = item.quality + 1;
                }
            }
        }
    }

    private void updateSulfurasSellIn(Item item) {
        if (!item.name.equals(SULFURAS_HAND)) {
            item.sellIn = item.sellIn - 1;
        }
    }

    private void updateQualityForAgedBrie(Item item) {
        if (item.quality < 50 && item.name.equals(AGED_BRIE) ) {
            item.quality += 1;
        }
    }

    private void updateQualityForBackStage(Item item) {
        if (item.quality < 50 && item.name.equals(BACKSTAGE_PASSES)) {
            item.quality += 1;

            if (item.quality < 50) {
                int incrementWhenSellin11days = item.sellIn < 11 ? 1 : 0;
                int incrementWhenSellin6days = item.sellIn < 6 ? 1 + incrementWhenSellin11days: incrementWhenSellin11days;
                item.quality += incrementWhenSellin6days;
            }
        }
    }

    private void updateNormalItem(Item item) {
        if (!item.name.equals(AGED_BRIE)
                && !item.name.equals(BACKSTAGE_PASSES)) {
            if (item.quality > 0) {
                if (!item.name.equals(SULFURAS_HAND)) {
                    item.quality -= 1;
                }
            }
        }
    }
}