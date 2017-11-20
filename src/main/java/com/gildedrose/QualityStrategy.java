package com.gildedrose;

public abstract class QualityStrategy {

    abstract Item updateQuality(Item item);

    abstract Item updateQualityWhenNoSellin(Item item);
}
