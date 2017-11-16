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

            updateBackstateAndAgeBrie(items[i]);

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

    private void updateBackstateAndAgeBrie(Item item) {
        if (item.name.equals(BACKSTAGE_PASSES)) {
            updateQualityWheQualityLessThan50ForBackStage(item);
        }

        if (item.name.equals(AGED_BRIE) ) {
            updateQualityWheQualityLessThan50ForAgedBrie(item);
        }
    }

    private void updateQualityWheQualityLessThan50ForBackStage(Item item) {
        if (item.quality >= 50) {
            return;
        }

        item.quality += 1;

        if (item.quality < 50) {
            if (item.sellIn < 11) {
                item.quality = item.quality + 1;
            }
            if (item.sellIn < 6) {
                item.quality = item.quality + 1;
            }
        }
    }

    private void updateQualityWheQualityLessThan50ForAgedBrie(Item item) {
        if (item.quality < 50) {
            item.quality = item.quality + 1;
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