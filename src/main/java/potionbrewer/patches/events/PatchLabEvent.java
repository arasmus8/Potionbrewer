package potionbrewer.patches.events;

import basemod.ReflectionHacks;
import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.events.shrines.Lab;
import com.megacrit.cardcrawl.localization.EventStrings;
import com.megacrit.cardcrawl.potions.PotionSlot;
import com.megacrit.cardcrawl.rewards.RewardItem;
import com.megacrit.cardcrawl.screens.CombatRewardScreen;
import javassist.CtBehavior;
import potionbrewer.characters.Potionbrewer;
import potionbrewer.relics.AlchemistFlask;

public class PatchLabEvent {
    @SpirePatch(
            clz = Lab.class,
            method = SpirePatch.STATICINITIALIZER
    )
    public static class PatchStaticInitializer {

        public static void Postfix() {
            if (AbstractDungeon.player.chosenClass == Potionbrewer.Enums.POTIONBREWER) {
                EventStrings eventStrings = CardCrawlGame.languagePack.getEventString("potionbrewer:Lab");
                ReflectionHacks.setPrivateStaticFinal(Lab.class, "DIALOG_1", eventStrings.DESCRIPTIONS[0]);
            }
        }
    }

    @SpirePatch(
            clz = Lab.class,
            method = SpirePatch.CONSTRUCTOR
    )
    public static class PatchConstructor {

        public static void Prefix(Lab __instance) {
            if (AbstractDungeon.player.chosenClass == Potionbrewer.Enums.POTIONBREWER) {
                EventStrings eventStrings = CardCrawlGame.languagePack.getEventString("potionbrewer:Lab");
                ReflectionHacks.setPrivateStatic(Lab.class, "DIALOG_1", eventStrings.DESCRIPTIONS[0]);
                __instance.imageEventText.setDialogOption(eventStrings.OPTIONS[0]);
            }
        }
    }

    @SpirePatch(
            clz = Lab.class,
            method = "buttonEffect"
    )
    public static class PatchButtonEffect {
        @SpireInsertPatch(
                locator = Locator.class
        )
        public static void Insert(Lab __instance, int buttonPressed) {
            if (AbstractDungeon.player.chosenClass == Potionbrewer.Enums.POTIONBREWER && buttonPressed == 0) {
                AbstractDungeon.getCurrRoom().rewards.clear();
                AbstractDungeon.getCurrRoom().rewards.add(new RewardItem(new AlchemistFlask()));
                for (int i = 0; i < AbstractDungeon.player.potionSlots; i++) {
                    AbstractDungeon.player.potions.set(i, new PotionSlot(i));
                }
            }
        }

        private static class Locator extends SpireInsertLocator {
            @Override
            public int[] Locate(CtBehavior ctMethodToPatch) throws Exception {
                Matcher finalMatcher = new Matcher.MethodCallMatcher(CombatRewardScreen.class, "open");
                return LineFinder.findInOrder(ctMethodToPatch, finalMatcher);
            }
        }
    }
}
