package potionbrewer.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpireField;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.characters.AbstractPlayer;

@SpirePatch(
        clz= AbstractPlayer.class,
        method=SpirePatch.CLASS
)
public class PotionTracker {
    public static SpireField<Integer> potionsUsedThisTurn = new SpireField<>(() -> 0);

    public static SpireField<Integer> potionsUsedThisCombat = new SpireField<>(() -> 0);
}
