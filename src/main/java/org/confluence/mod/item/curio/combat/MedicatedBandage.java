package org.confluence.mod.item.curio.combat;

import org.confluence.mod.item.ModRarity;
import org.confluence.mod.item.curio.BaseCurioItem;

public class MedicatedBandage extends BaseCurioItem implements EffectInvul.Poison, EffectInvul.Bleeding {
    public MedicatedBandage() {
        super(ModRarity.PINK);
    }
}