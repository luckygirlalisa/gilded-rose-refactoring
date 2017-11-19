package com.gildedrose;

public class Item {

    public String name;

    public int sellIn;

    public int quality;

    public Item(String name, int sellIn, int quality) {
        this.name = name;
        this.sellIn = sellIn;
        this.quality = quality;
    }

   @Override
   public String toString() {
        return this.name + ", " + this.sellIn + ", " + this.quality;
    }

    Item updateNormalItem() {
        if (!name.equals(GildedRose.AGED_BRIE) && !name.equals(GildedRose.BACKSTAGE_PASSES)) {
            if (quality > 0) {
                if (!name.equals(GildedRose.SULFURAS_HAND)) {
                    quality -= 1;
                }
            }
        }

        return this;
    }

    Item updateQualityForBackStage() {
        if (quality < 50 && name.equals(GildedRose.BACKSTAGE_PASSES)) {
            quality += 1;

            if (quality < 50) {
                int incrementWhenSellin11days = sellIn < 11 ? 1 : 0;
                int incrementWhenSellin6days = sellIn < 6 ? 1 + incrementWhenSellin11days: incrementWhenSellin11days;
                quality += incrementWhenSellin6days;
            }
        }

        return this;
    }

    Item updateQualityForAgedBrie() {
        if (quality < 50 && name.equals(GildedRose.AGED_BRIE) ) {
            quality += 1;
        }

        return this;
    }

    Item updateSellInForSulfuras() {
        if (!name.equals(GildedRose.SULFURAS_HAND)) {
            sellIn = sellIn - 1;
        }
        return this;
    }

    Item updateWhenNoSellin() {
        if (sellIn < 0) {
            if (!name.equals(GildedRose.AGED_BRIE)) {
                if (!name.equals(GildedRose.BACKSTAGE_PASSES)) {
                    if (quality > 0) {
                        if (!name.equals("Sulfuras, Hand of Ragnaros")) {
                            quality = quality - 1;
                        }
                    }
                } else {
                    quality = 0;
                }
            } else {
                if (quality < 50) {
                    quality = quality + 1;
                }
            }
        }

        return this;
    }
}
