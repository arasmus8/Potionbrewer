package potionbrewer.patches.useCardAction;

import basemod.ReflectionHacks;
import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import javassist.CtBehavior;
import potionbrewer.cards.BeatDown;

@SpirePatch(
        clz = UseCardAction.class,
        method = "update"
)
public class AutoRebound {
    @SpireInsertPatch(
            locator = AutoRebound.Locator.class
    )
    public static void doAutoRebound(UseCardAction __instance) {
        AbstractCard targetCard = (AbstractCard) ReflectionHacks.getPrivate(__instance, UseCardAction.class, "targetCard");
        if (targetCard instanceof BeatDown) {
            BeatDown b = (BeatDown) targetCard;
            __instance.reboundCard = b.shouldRebound;
        }
    }

    private static class Locator extends SpireInsertLocator {
        @Override
        public int[] Locate(CtBehavior ctMethodToPatch) throws Exception {
            Matcher finalMatcher = new Matcher.FieldAccessMatcher(UseCardAction.class, "reboundCard");
            return LineFinder.findInOrder(ctMethodToPatch, finalMatcher);
        }
    }
}
