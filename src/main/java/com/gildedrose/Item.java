package com.gildedrose;

public class Item {

    public String name;

    public int sellIn;

    public int quality;
    QualityStrategy qualityStrategy;

    public Item(String name, int sellIn, int quality) {
        this.name = name;
        this.sellIn = sellIn;
        this.quality = quality;
        qualityStrategy = initialQualityStrategy(name);
    }

    private QualityStrategy initialQualityStrategy(String name) {
        switch (name) {
            case GildedRose.AGED_BRIE:
                return new AgedBrieQualityStrategy();
            case GildedRose.BACKSTAGE_PASSES:
                return new BackstagePassesStrategy();
            case GildedRose.SULFURAS_HAND:
                return new SulfurasHandQualityStrategy();
        }
        return new NormalQualityStrategy();
    }

    @Override
   public String toString() {
        return this.name + ", " + this.sellIn + ", " + this.quality;
    }

    Item updateSellInForNotSulfuras() {
        if (!name.equals(GildedRose.SULFURAS_HAND)) {
            sellIn = sellIn - 1;
        }
        return this;
    }

    Item updateQualityWhenNoSellin() {
        qualityStrategy.updateQualityForNormalItemWhenNoSellIn(this);
        qualityStrategy.updateQualityForBackstagePassesWithNoSellIn(this);
        qualityStrategy.udpateQualityForAgedBrieWithNoSellIn(this);

        return this;
    }

}
