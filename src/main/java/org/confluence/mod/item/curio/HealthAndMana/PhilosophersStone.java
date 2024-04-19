package org.confluence.mod.item.curio.HealthAndMana;

import net.minecraft.world.item.Rarity;
import org.confluence.mod.datagen.limit.CustomName;
import org.confluence.mod.item.ModRarity;
import org.confluence.mod.item.curio.BaseCurioItem;

public class PhilosophersStone extends BaseCurioItem implements CustomName {
    public PhilosophersStone() {
        super(ModRarity.LIGHT_RED);
    }

    public PhilosophersStone(Rarity rarity) {
        super(rarity);
    }

    @Override
    public String getName() {
        return "Philosopher's Stone";
    }
}
