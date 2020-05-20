package potionbrewer.patches.relics;

import com.evacipated.cardcrawl.modthespire.lib.SpireField;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.potions.AbstractPotion;

@SpirePatch(
        clz = AbstractPotion.class,
        method = SpirePatch.CLASS
)
public class PotionStackField {
    public static SpireField<Integer> stackCount = new SpireField<>(() -> 1);
}
