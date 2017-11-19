package com.gildedrose;

public class Item {

    private String name;

    public int sellIn;

    private int quality;
    private QualityStrategy qualityStrategy;

    public Item(String name, int sellIn, int quality) {
        this.setName(name);
        this.sellIn = sellIn;
        this.setQuality(quality);
        setQualityStrategy(initialQualityStrategy(name));
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
        return this.getName() + ", " + this.sellIn + ", " + this.getQuality();
    }

    Item updateSellInForNotSulfuras() {
        if (!getName().equals(GildedRose.SULFURAS_HAND)) {
            sellIn = sellIn - 1;
        }
        return this;
    }

    Item updateQualityWhenNoSellin() {
        getQualityStrategy().updateQualityForNormalItemWhenNoSellIn(this);
        getQualityStrategy().updateQualityForBackstagePassesWithNoSellIn(this);
        getQualityStrategy().udpateQualityForAgedBrieWithNoSellIn(this);

        return this;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuality() {
        return quality;
    }

    public void setQuality(int quality) {
        this.quality = quality;
    }

    public QualityStrategy getQualityStrategy() {
        return qualityStrategy;
    }

    public void setQualityStrategy(QualityStrategy qualityStrategy) {
        this.qualityStrategy = qualityStrategy;
    }
}
