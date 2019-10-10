package potionbrewer.patches.soul;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.Soul;
import potionbrewer.PotionbrewerMod;
import potionbrewer.cards.Prototype;

@SpirePatch(
        clz = Soul.class,
        method = "obtain"
)
public class Obtain {
    public static void Postfix(Soul __instance, AbstractCard card) {
        if (card instanceof Prototype) {
            PotionbrewerMod.reagents.clear();
        }
    }
}
