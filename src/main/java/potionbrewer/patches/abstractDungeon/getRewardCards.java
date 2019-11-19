package potionbrewer.patches.abstractDungeon;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.MoltenEgg2;
import com.megacrit.cardcrawl.relics.ToxicEgg2;
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
            AbstractCard card = new Prototype(a, b, c);
            if (card.type == AbstractCard.CardType.SKILL && AbstractDungeon.player.hasRelic(ToxicEgg2.ID)) {
                card.upgrade();
            } else if (card.type == AbstractCard.CardType.ATTACK && AbstractDungeon.player.hasRelic(MoltenEgg2.ID)) {
                card.upgrade();
            }
            __return.add(card);
        }
        return __return;
    }
}
