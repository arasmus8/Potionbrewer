package potionbrewer.patches.soul;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.Soul;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.rewards.RewardItem;
import potionbrewer.PotionbrewerMod;
import potionbrewer.cards.Prototype;

import java.util.List;
import java.util.stream.Collectors;

@SpirePatch(
        clz = Soul.class,
        method = "obtain"
)
public class Obtain {
    public static void Postfix(Soul __instance, AbstractCard card) {
        if (card instanceof Prototype) {
            PotionbrewerMod.reagents.clear();
            List<RewardItem> cardRewards = AbstractDungeon.combatRewardScreen.rewards.stream()
                    .filter(rewardItem -> rewardItem.type == RewardItem.RewardType.CARD)
                    .collect(Collectors.toList());
            cardRewards.forEach(rewardItem -> {
                AbstractCard prototypeCardInReward = rewardItem.cards.stream()
                        .filter(abstractCard -> abstractCard instanceof Prototype)
                        .findFirst()
                        .orElse(null);
                if (prototypeCardInReward != card) {
                    // The prototype card is another instance - prayer wheel? Remove it from the rewards
                    rewardItem.cards.remove(prototypeCardInReward);
                }
            });
        }
    }
}
