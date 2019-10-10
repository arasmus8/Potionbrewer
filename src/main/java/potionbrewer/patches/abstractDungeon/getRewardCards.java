package potionbrewer.patches.abstractDungeon;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import potionbrewer.PotionbrewerMod;
import potionbrewer.cards.Prototype;
import potionbrewer.orbs.Reagent;

import java.util.ArrayList;

@SpirePatch(
        clz = AbstractDungeon.class,
        method = "getRewardCards"
)
public class getRewardCards {
    public static ArrayList<AbstractCard> Postfix(ArrayList<AbstractCard> __return) {
        if (PotionbrewerMod.reagents != null && PotionbrewerMod.reagents.size() >= 3) {
            Reagent a = PotionbrewerMod.reagents.get(0);
            Reagent b = PotionbrewerMod.reagents.get(1);
            Reagent c = PotionbrewerMod.reagents.get(2);
            __return.add(new Prototype(a, b, c));
        }
        return __return;
    }
}
