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

    Item updateQualityForNormalItem() {
        if (isNormalItem()) {
            if (quality > 0) {
                quality -= 1;
            }
        }

        return this;
    }

    private boolean isNormalItem() {
        return !name.equals(GildedRose.AGED_BRIE) && !name.equals(GildedRose.BACKSTAGE_PASSES) && !name.equals(GildedRose.SULFURAS_HAND);
    }

    Item updateQualityForBackStage() {
        if (name.equals(GildedRose.BACKSTAGE_PASSES)) {
            if (quality < 50) {
                quality += 1;

                if (quality < 49) {
                    int incrementWhenSellin11days = sellIn < 11 ? 1 : 0;
                    int incrementWhenSellin6days = sellIn < 6 ? 1 + incrementWhenSellin11days: incrementWhenSellin11days;
                    quality += incrementWhenSellin6days;
                }
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

    Item updateSellInForNotSulfuras() {
        if (!name.equals(GildedRose.SULFURAS_HAND)) {
            sellIn = sellIn - 1;
        }
        return this;
    }

    Item updateWhenNoSellin() {
        updateQualityForNormalItemWhenNoSellIn();
        updateQualityForBackstagePassesWithNoSellIn();
        udpateQualityForAgedBrieWithNoSellIn();

        return this;
    }

    private void udpateQualityForAgedBrieWithNoSellIn() {
        if (name.equals(GildedRose.AGED_BRIE) && sellIn < 0){
            if (quality < 50) {
                quality = quality + 1;
            }
        }
    }

    private void updateQualityForBackstagePassesWithNoSellIn() {
        if (name.equals(GildedRose.BACKSTAGE_PASSES) && sellIn < 0) {
            quality = 0;
        }
    }

    private void updateQualityForNormalItemWhenNoSellIn() {
        if (isNormalItem() && sellIn < 0 && quality > 0) {
            quality = quality - 1;
        }
    }
}
