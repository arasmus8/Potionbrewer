package potionbrewer.patches.relics;

import basemod.ReflectionHacks;
import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ObtainPotionAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.potions.AbstractPotion;
import com.megacrit.cardcrawl.potions.EntropicBrew;
import com.megacrit.cardcrawl.rewards.RewardItem;
import com.megacrit.cardcrawl.shop.StorePotion;
import javassist.CtBehavior;
import potionbrewer.TonicLibrary;
import potionbrewer.relics.SalesContract;

import java.lang.reflect.Method;

public class SalesContractPatches {

    @SpirePatch(
            clz = RewardItem.class,
            method = "claimReward"
    )
    public static class RewardItemPatch {
        public static SpireReturn<Boolean> Prefix(RewardItem __instance) {
            if (__instance.type == RewardItem.RewardType.POTION && AbstractDungeon.player.hasRelic(SalesContract.ID)) {
                AbstractDungeon.player.getRelic(SalesContract.ID).flash();
                AbstractDungeon.player.gainGold(SalesContract.GOLD_AMOUNT);
                CardCrawlGame.sound.play("GOLD_GAIN");
                return SpireReturn.Return(true);
            }
            return SpireReturn.Continue();
        }
    }

    @SpirePatch(
            clz = StorePotion.class,
            method = "purchasePotion"
    )
    public static class StorePotionPatch {
        public static SpireReturn Prefix(StorePotion __instance) {
            if (AbstractDungeon.player.hasRelic(SalesContract.ID)) {
                AbstractDungeon.player.getRelic(SalesContract.ID).flash();
                return SpireReturn.Return(null);
            }
            return SpireReturn.Continue();
        }
    }

    @SpirePatch(
            clz = EntropicBrew.class,
            method = "use"
    )
    public static class EntropicBrewPatch {
        public static SpireReturn Prefix(EntropicBrew __instance, AbstractCreature target) {
            if (AbstractDungeon.player.hasRelic(SalesContract.ID)) {
                AbstractDungeon.player.getRelic(SalesContract.ID).flash();
                return SpireReturn.Return(null);
            }
            return SpireReturn.Continue();
        }
    }

    @SpirePatch(
            clz = ObtainPotionAction.class,
            method = "update"
    )
    public static class ObtainPotionActionPatch {
        @SpireInsertPatch(
                locator = Locator.class
        )
        public static SpireReturn Insert(ObtainPotionAction __instance) {
            if (AbstractDungeon.player.hasRelic(SalesContract.ID)) {
                AbstractPotion potion = (AbstractPotion) ReflectionHacks.getPrivate(__instance, ObtainPotionAction.class, "potion");
                if (!TonicLibrary.isATonic(potion)) {
                    AbstractDungeon.player.getRelic(SalesContract.ID).flash();
                    AbstractDungeon.player.gainGold(SalesContract.GOLD_AMOUNT);
                    CardCrawlGame.sound.play("GOLD_GAIN");

                    Class<?> clz = AbstractGameAction.class;
                    try {
                        Method tickDuration = clz.getDeclaredMethod("tickDuration");
                        tickDuration.setAccessible(true);
                        tickDuration.invoke(__instance);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    return SpireReturn.Return(null);
                }
            }
            return SpireReturn.Continue();
        }

        private static class Locator extends SpireInsertLocator {
            @Override
            public int[] Locate(CtBehavior ctMethodToPatch) throws Exception {
                Matcher finalMatcher = new Matcher.MethodCallMatcher(AbstractPlayer.class, "hasRelic");
                return LineFinder.findInOrder(ctMethodToPatch, finalMatcher);
            }
        }
    }
}
